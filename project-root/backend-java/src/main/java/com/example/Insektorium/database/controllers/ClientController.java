package com.example.Insektorium.database.controllers;

import com.example.Insektorium.database.services.ClientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class ClientController {

    ClientService clientService;

    ClientController(ClientService clientService){
        this.clientService = clientService;
    }


/*    @RequestBody("/login")
    public String login(@RequestBody String username, @RequestBody String password){}

    @RequestBody("/register")
    public String register(@RequestBody String username, @RequestBody String password, @RequestBody String role){}*/
}
