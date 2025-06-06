package com.example.Insektorium.database.controllers;

import com.example.Insektorium.database.entities.dtos.BugFoundDTO;
import com.example.Insektorium.database.entities.tables.BugFound;
import com.example.Insektorium.database.entities.tables.Client;
import com.example.Insektorium.database.services.BugFoundService;
import com.example.Insektorium.database.services.BugService;
import com.example.Insektorium.database.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/bugs_found")
public class BugFoundController {
    BugFoundService bugFoundService;
    ClientService clientService;
    BugService bugService;

    public BugFoundController(BugFoundService bugFoundService) {
        this.bugFoundService = bugFoundService;
    }


    @PostMapping("/add/{clientId}")
    ResponseEntity<?> addBug(@RequestBody BugFoundDTO bugDTO, @PathVariable Long clientId) {
        Client client = clientService.findClientById(clientId);
        if (client == null) return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);

        BugFound bug = new BugFound();

        bug.setBug(bugService.findBugByName(bugDTO.getName()));
        bug.setLatitude(bugDTO.getLatitude());
        bug.setLongitude(bugDTO.getLongitude());
        bug.setLocation(bugDTO.getLocation());
        bug.setCreatedAt(new Date());
        client.addBug(bug);
        return new ResponseEntity<>(bug, HttpStatus.OK);
    }

    @GetMapping("/all/{clientId}")
    ResponseEntity<?> getAllBugs(@PathVariable Long clientId) {
        return new ResponseEntity<>(bugFoundService.getAllByClient_Id(clientId), HttpStatus.OK);
    }
}
