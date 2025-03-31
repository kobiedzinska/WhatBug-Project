package com.example.Insektorium.database.repositories;

import com.example.Insektorium.database.entities.BugFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BugFoundRepository extends JpaRepository<BugFound, Long> {
    List<BugFound> findAllByClient_Id(Long id);
}
