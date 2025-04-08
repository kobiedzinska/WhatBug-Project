package com.example.Insektorium.database.controllers;

import com.example.Insektorium.database.entities.entities.Bug;
import com.example.Insektorium.database.services.BugService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bugs")
public class BugController {
    BugService bugService;
    BugController(BugService bugService){
        this.bugService = bugService;
    }

    @PostMapping("/add")
    ResponseEntity<?> addBug(@RequestBody Bug bug){
        return new ResponseEntity<>(bugService.addBug(bug), HttpStatus.OK);
    }

    @GetMapping("/all")
    ResponseEntity<?> getAllBugs(){
        return new ResponseEntity<>(bugService.getAllBugs(), HttpStatus.OK);
    }




}
