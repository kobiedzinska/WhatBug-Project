package com.example.Insektorium.database.services;

import com.example.Insektorium.database.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
}
