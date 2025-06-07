package com.example.Insektorium.database.services;

import com.example.Insektorium.database.entities.tables.Bug;
import com.example.Insektorium.database.repositories.BugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BugService {

    @Autowired
    BugRepository bugRepository;
    BugService(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    public Bug saveBug(Bug bug){
        return bugRepository.save(bug);
    }

    public List<Bug> getAllBugs(){
        return bugRepository.findAll();
    }

    public void deleteBugById(Long id){
        bugRepository.deleteById(id);
    }

    public void deleteBugByObjects(Bug bug){
        bugRepository.delete(bug);
    }

    public Bug findBugById(Long id){
        return bugRepository.findById(id).orElse(null);
    }

    public Bug findBugByName(String name){
        return bugRepository.findBugByName(name);
    }

}
