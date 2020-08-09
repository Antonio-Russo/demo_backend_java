package com.example.demo_be.services;

import com.example.demo_be.entities.User;
import com.example.demo_be.repositories.UserRepository;
import com.example.demo_be.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    protected static final Logger logger = LoggerFactory.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository repository;

    public User findExistUser(String username, String password) {
        try {
            String passwordEncoded = StringUtils.encodeSha256(password);
            return repository.findOneByUsernameAndPassword(username, passwordEncoded);
        } catch (Exception ex) {
            logger.error("Errore ricerca user: " + ex.getMessage());
            return null;
        }
    }

}
