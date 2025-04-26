package com.example.Insektorium.database.controllers;

import com.example.Insektorium.database.entities.dtos.ClientDTO;
import com.example.Insektorium.database.entities.entities.Client;
import com.example.Insektorium.database.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class ClientController {

    ClientService clientService;

    ClientController(ClientService clientService){
        this.clientService = clientService;
    }



    /*@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ClientDTO clientDTO){
        System.out.println(clientDTO);
        if(clientDTO.getUsername() == null || clientDTO.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Client client = new Client();
        if(clientService.loginUser(clientDTO.getUsername(), clientDTO.getPassword())!= null) {
            List<String> args = Arrays.asList(clientService.loginUser(clientDTO.getUsername(), clientDTO.getPassword()).split(","));
            client.setId(Long.parseLong(args.get(0)));
            client.setUsername(args.get(1));
            client.setRole(args.get(2));

            System.out.println(client);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }*/

    @RequestMapping("/register")
    public ResponseEntity<?> register(@RequestBody ClientDTO clientDTO){
        if(clientService.findClientByName(clientDTO.getUsername())!=null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }
}
