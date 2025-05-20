package com.example.Insektorium.database.controllers;

import com.example.Insektorium.database.entities.dtos.ClientDTO;
import com.example.Insektorium.database.entities.dtos.LoginRequest;
import com.example.Insektorium.database.entities.dtos.RefreshRequest;
import com.example.Insektorium.database.entities.dtos.RegisterRequest;
import com.example.Insektorium.database.entities.entities.JwtResponse;
import com.example.Insektorium.database.entities.tables.Client;
import com.example.Insektorium.database.entities.tables.RefreshToken;
import com.example.Insektorium.database.services.ClientService;
import com.example.Insektorium.database.services.TokenService;
import com.example.Insektorium.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
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



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){

        System.out.println(request);
        if(request.getEmail() == null || request.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
/*        Long id = clientService.floginUser(request.getEmail(), encoder.encode(request.getPassword()));
        if(id !=null) {
            Client client = clientService.findClientById(id);
            String accessToken = jwtUtils.generateToken(client);

            RefreshToken refreshToken = tokenService.createToken(client);

            System.out.println(accessToken);
            return new ResponseEntity<>(new JwtResponse(accessToken, refreshToken.getToken(), client.getId(),
                    client.getUsername(), client.getEmail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);*/


        Client client = clientService.findClientByEmail(request.getEmail());
        if(client==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(!encoder.matches(request.getPassword(), client.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String accessToken = jwtUtils.generateToken(client);
        RefreshToken refreshToken = tokenService.createToken(client);

        System.out.println(accessToken);
        return new ResponseEntity<>(new JwtResponse(accessToken, refreshToken.getToken(), client.getId(),
                client.getUsername(), client.getEmail()), HttpStatus.OK);
    }


/*    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        if (clientService.findClientByName(request.getUsername()) != null) {
            return new ResponseEntity<>("Error: Username is already taken!", HttpStatus.CONFLICT);
        }

        Client newClient = new Client();
        newClient.setUsername(request.getUsername());
        newClient.setEmail(request.getEmail());
        newClient.setPassword(encoder.encode(request.getPassword()));
        newClient = clientService.addClient(newClient);
        System.out.println(newClient);

        String accessToken = jwtUtils.generateToken(newClient);

        RefreshToken refreshToken = tokenService.createToken(newClient);
        return new ResponseEntity<>(new JwtResponse(accessToken, refreshToken.getToken(), newClient.getId(),
                newClient.getUsername(), newClient.getEmail()), HttpStatus.CREATED);
    }*/

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        Client client  = clientService.findClientByName(request.getUsername());

        /*Long id = clientService.fRegisterUser(request.getUsername(), request.getEmail(), encoder.encode(request.getPassword()));
        if(id==0){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }*/

        if(client!=null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Client newClient = new Client();
        newClient.setUsername(request.getUsername());
        newClient.setEmail(request.getEmail());
        newClient.setPassword(encoder.encode(request.getPassword()));
        clientService.addClient(newClient);


        return new ResponseEntity<>(HttpStatus.CREATED);
/*        Client client = clientService.findClientById(id);
        RefreshToken refreshToken = tokenService.createToken(client);
        String accessToken = jwtUtils.generateToken(client);
        return new ResponseEntity<>(new JwtResponse(accessToken, refreshToken.getToken(), client.getId(),
                client.getUsername(), client.getEmail()), HttpStatus.CREATED);*/


    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshRequest request) {
        String refreshTokenValue = request.getRefreshToken();
        RefreshToken token = tokenService.findTokenByTokenValue(refreshTokenValue);
        if(token == null) return new ResponseEntity<>("No token to send", HttpStatus.FORBIDDEN);//też powinno przekierować na logowanie
        if (token.getExpiresAt().isBefore(Instant.now())) {
            return new ResponseEntity<>("Refresh token expired", HttpStatus.UNAUTHORIZED);
        }
        Client client = token.getClient();
        String newAccessToken = jwtUtils.generateToken(client);

        return new ResponseEntity<>(new JwtResponse(newAccessToken, token.getToken(), client.getId(), client.getUsername(), client.getEmail()), HttpStatus.OK);

    }

/*    @PostMapping("/test")
    public String test(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String token = jwtUtils.generateToken(clientService.findClientByName(username)).getToken();
        System.out.println(token);
        return token;
    }*/


}
