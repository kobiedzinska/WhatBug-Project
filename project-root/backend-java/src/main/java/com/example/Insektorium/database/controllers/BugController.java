package com.example.Insektorium.database.controllers;

import com.example.Insektorium.database.entities.entities.MultipartInStream;
import com.example.Insektorium.database.entities.geometryUtils.GeometryUtils;
import com.example.Insektorium.database.entities.tables.Bug;
import com.example.Insektorium.database.entities.tables.BugFound;
import com.example.Insektorium.database.entities.tables.Client;
import com.example.Insektorium.database.services.BugFoundService;
import com.example.Insektorium.database.services.BugService;

import com.example.Insektorium.database.services.ClientService;
import com.example.Insektorium.security.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/bugs")
public class BugController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JwtUtil jwtUtil;
    BugService bugService;
    BugFoundService bugFoundService;
    ClientService clientService;

    @Value("${API_KEY}")
    private String apiKey;
    @Value("${AI_URL}")
    private String aiUrl;

    BugController(BugService bugService, BugFoundService bugFoundService, ClientService clientService){
        this.bugService = bugService;
        this.bugFoundService = bugFoundService;
        this.clientService = clientService;
    }

    @PostMapping("/add")
    ResponseEntity<?> addBug(@RequestBody Bug bug){
        return new ResponseEntity<>(bugService.saveBug(bug), HttpStatus.OK);
    }

    @PostMapping("/analyze")
    ResponseEntity<?> analyzeBug(@RequestParam("image") MultipartFile image, @RequestParam("lat") double lat,
                                 @RequestParam("lon") double lon, HttpServletRequest request) throws IOException {
        String authHeader = request.getHeader("Authorization");

        String token = (authHeader != null && authHeader.startsWith("Bearer "))
                ? authHeader.substring(7)
                : null;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("x-api-key", apiKey);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new MultipartInStream(image.getInputStream(), image.getOriginalFilename()));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> aiResponse = restTemplate.postForEntity(aiUrl, requestEntity, Map.class);

        if (!aiResponse.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("AI nie odpowiada");
        }

        String label = (String) aiResponse.getBody().get("label");
        double confidence = (Double) aiResponse.getBody().get("confidence");
        Long clientId = jwtUtil.getIdFromToken(token);

        Bug bug = bugService.findBugByName(label);

        if (bug == null) {
            bug = new Bug();
            bug.setName(label);
            bug = bugService.saveBug(bug);
        }
        System.out.println(label+", "+confidence);
        Client client = clientService.findClientById(clientId);

        if (client == null) return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        BugFound bugFound = new BugFound();
        bugFound.setBug(bug);
        bugFound.setName(label);
        bugFound.setImage(image.getBytes());
        bugFound.setLatitude(lat);
        bugFound.setLongitude(lon);
        bugFound.setLocation(GeometryUtils.createPoint(lat, lon));

        client.addBug(bugFound);
        bugFoundService.saveBug(bugFound);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    ResponseEntity<?> getAllBugs(){
        return new ResponseEntity<>(bugService.getAllBugs(), HttpStatus.OK);
    }

}
