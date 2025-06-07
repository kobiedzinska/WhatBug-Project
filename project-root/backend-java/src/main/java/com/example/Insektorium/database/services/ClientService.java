package com.example.Insektorium.database.services;

import com.example.Insektorium.database.entities.tables.Client;
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
    public Long fRegisterUser(String username, String email, String password){
        return clientRepository.fregisterUser(username, email, password, "client");
    }
    public Long floginUser(String username, String password){
        return clientRepository.floginUser(username, password);
    }
    public Client addClient(Client client){
        return clientRepository.save(client);
    }
    public Client findClientById(Long id){
        return clientRepository.findById(id).orElse(null);
    }
    public Client findClientByName(String name){
        return clientRepository.findClientByUsername(name);
    }
    public Client findClientByEmail(String email){
        return clientRepository.findClientByEmail(email);
    }
}
