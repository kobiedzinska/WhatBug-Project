package com.example.Insektorium.security.jwt;

import com.example.Insektorium.database.entities.tables.Client;

import com.example.Insektorium.database.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;
@Service
public class CustomUserDetailsService  implements UserDetailsService {
    @Autowired
    private ClientService clientService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientService.findClientByName(username);
        if (client == null) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                client.getUsername(),
                client.getPassword(),
                Collections.emptyList()
        );
    }
}
