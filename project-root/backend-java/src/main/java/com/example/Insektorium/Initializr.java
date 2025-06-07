package com.example.Insektorium;

import com.example.Insektorium.database.services.BugFoundService;
import com.example.Insektorium.database.services.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Initializr {
    
    @Bean
    CommandLineRunner testing(ClientService clientService, BugFoundService bugFoundService){
        return args -> {
            System.out.println(clientService.loginUser("JohnDoe", "password"));
            System.out.println(clientService.loginUser("JohnDoe", "securepass"));
            System.out.println(clientService.loginUser("jan_kowalski", "zahaszowane_haslo"));
            System.out.println(clientService.floginUser("jan@example.com", "zahaszowane_haslo"));
            System.out.println(clientService.floginUser("anna@example.com", "bezpieczne_haslo123"));
            System.out.println(clientService.floginUser("johndoe@gg.com", "securepass"));

            System.out.println(clientService.getAllUsers());
            System.out.println(bugFoundService.getAllByClient_Id(1L));
        };
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
