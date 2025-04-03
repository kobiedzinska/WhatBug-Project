package com.example.Insektorium.database.services;

import com.example.Insektorium.database.entities.entities.Client;
import com.example.Insektorium.database.repositories.ClientRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    ClientRepository clientRepository;
    ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }
    public List<Client> getAllUsers(){
        return clientRepository.findAll();
    }

    public String loginUser(String username, String password){
        return clientRepository.loginUser(username, password);
    }
}
