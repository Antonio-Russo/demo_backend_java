package com.example.demo_be.repositories;

import com.example.demo_be.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findOneByUsername(String Username);

    User findOneByUsernameAndPassword(String Username, String PasswordEncoded);

    //nelle query update torna sempre un int 1 se ok 0 se in errore, e va annotata come Modifying e Transactional
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "ALTER TABLE users AUTO_INCREMENT = 1")
    int resetIdIncrement();

}

