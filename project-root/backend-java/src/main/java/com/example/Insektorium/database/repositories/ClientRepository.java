package com.example.Insektorium.database.repositories;

import com.example.Insektorium.database.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT floginUser(:name, :password)", nativeQuery = true)
    boolean loginUser(@Param("name") String name, @Param("password") String password);
}
