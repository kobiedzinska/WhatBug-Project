package com.example.Insektorium.database.controllers;

import com.example.Insektorium.database.entities.dtos.ClientDTO;
import com.example.Insektorium.database.entities.entities.Client;
import com.example.Insektorium.database.entities.entities.RefreshToken;
import com.example.Insektorium.database.services.ClientService;
import com.example.Insektorium.database.services.TokenService;
import com.example.Insektorium.security.jwt.JwtUtil;
import com.example.Insektorium.security.jwt.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    ClientService clientService;
    @Autowired
    TokenService tokenService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtil jwtUtils;
/*    @PostMapping("/signin")
    public String authenticateUser(@RequestBody ClientDTO client) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        client.getUsername(),
                        client.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return jwtUtils.generateToken(userDetails.getUsername());
    }*/


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ClientDTO clientDTO){
        System.out.println(clientDTO);
        if(clientDTO.getEmail() == null || clientDTO.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Long id = clientService.floginUser(clientDTO.getEmail(), clientDTO.getPassword());
        if(id !=null) {

            Pair pair = jwtUtils.generateToken(clientDTO.getEmail());
            String token = pair.getToken();
            Date date = pair.getNow();
            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setClient(clientService.findClientById(id));
            refreshToken.setCreatedAt(date);
            refreshToken.setExpiresAt(new Date((date).getTime()+604800000));//7 days
            tokenService.saveToken(refreshToken);

            System.out.println(token);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody ClientDTO client) {
        if (clientService.findClientByName(client.getUsername()) == null) {
            return new ResponseEntity<>("Error: Username is already taken!", HttpStatus.CONFLICT);
        }
        // Create new user's account
        Client newClient = new Client();
        newClient.setUsername(client.getUsername());
        newClient.setEmail(client.getEmail());
        newClient.setPassword(encoder.encode(client.getPassword()));
        clientService.addClient(newClient);

        return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody ClientDTO client) {
        RefreshToken token = tokenService.findTokenByMail(client.getEmail());
        if(token == null) return new ResponseEntity<>("No token to send", HttpStatus.FORBIDDEN);//też powinno przekierować na logowanie
        if(token.getExpiresAt().before(new Date()))return new ResponseEntity<>(jwtUtils.generateToken(client.getEmail()).getToken(), HttpStatus.OK);
        return new ResponseEntity<>("Token expired", HttpStatus.UNAUTHORIZED);//to powinno przekierować na ponowne logowanie wtedy

    }

    @PostMapping("/test")
    public String test(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String token = jwtUtils.generateToken(username).getToken();
        System.out.println(token);
        return token;
    }

}
