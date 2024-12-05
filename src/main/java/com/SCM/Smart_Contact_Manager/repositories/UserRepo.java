package com.SCM.Smart_Contact_Manager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SCM.Smart_Contact_Manager.entities.user;

@Repository
public interface UserRepo extends JpaRepository<user, String> {

    Optional<user> findByEmail(String email);

    Optional<user> findByEmailAndPassword(String email, String password);


}
