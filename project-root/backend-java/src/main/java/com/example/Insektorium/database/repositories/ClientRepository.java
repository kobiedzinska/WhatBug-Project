package com.example.Insektorium.database.repositories;

import com.example.Insektorium.database.entities.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "CALL loginUser(:username, :password)", nativeQuery = true)
    String loginUser(@Param("username") String username, @Param("password") String password);

    @Query(value = "SELECT floginUser(:username, :password)", nativeQuery = true)
    Long floginUser(@Param("username") String username, @Param("password") String password);

    Client findClientByUsername(String name);
}
