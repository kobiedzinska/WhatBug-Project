package com.example.Insektorium.database.controllers;

import com.example.Insektorium.database.entities.entities.BugFound;
import com.example.Insektorium.database.services.BugFoundService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bugs_found")
public class BugFoundController {
    BugFoundService bugFoundService;

    public BugFoundController(BugFoundService bugFoundService) {
        this.bugFoundService = bugFoundService;
    }


    @PostMapping("/add/{client_id}")
    ResponseEntity<?> addBug(@RequestBody BugFound bug, @PathVariable Long client_id) {
        return new ResponseEntity<>(bug, HttpStatus.OK);
    }

    @GetMapping("/all/{client_id}")
    ResponseEntity<?> getAllBugs(@PathVariable Long client_id) {
        return new ResponseEntity<>(bugFoundService.getAllByClient_Id(client_id), HttpStatus.OK);
    }
}
