package com.example.Insektorium.database.repositories;

import com.example.Insektorium.database.entities.tables.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository extends JpaRepository<Bug, Long> {

}
