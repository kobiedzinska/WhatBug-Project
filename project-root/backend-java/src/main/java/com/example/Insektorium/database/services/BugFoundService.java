package com.example.Insektorium.database.services;

import com.example.Insektorium.database.entities.entities.BugFound;
import com.example.Insektorium.database.repositories.BugFoundRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BugFoundService {
    BugFoundRepository bugFoundRepository;
    BugFoundService(BugFoundRepository bugFoundRepository) {
        this.bugFoundRepository = bugFoundRepository;
    }

    public List<BugFound> getAllBugFound(){
        return bugFoundRepository.findAll();
    }

    public List<BugFound> getAllByClient_Id(Long id){
        return bugFoundRepository.findAllByClient_IdOrderByName(id);
    }
}
