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


}
