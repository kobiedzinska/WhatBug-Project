package com.example.Insektorium.database.controllers;

import com.example.Insektorium.database.services.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }


/*    @RequestBody("/login")
    public String login(@RequestBody String username, @RequestBody String password){}

    @RequestBody("/register")
    public String register(@RequestBody String username, @RequestBody String password, @RequestBody String role){}*/
}
