package com.example.Insektorium.database.repositories;

import com.example.Insektorium.database.entities.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BugRepository extends JpaRepository<Bug, Long> {

}
