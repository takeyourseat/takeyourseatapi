package com.stefanini.internship.oauthserver.service;

import com.stefanini.internship.oauthserver.dao.User;
import com.stefanini.internship.oauthserver.dao.repositories.UserRepository;
import com.stefanini.internship.oauthserver.exceptions.DuplicateUserException;
import com.stefanini.internship.oauthserver.exceptions.UserNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService {

    private final static Logger logger = Logger.getLogger(UserValidationService.class);


    private UserRepository userRepository;

    public UserValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void assertUnique(User user){
        if(userRepository.existsById(user.getId())) {
            RuntimeException exception = new DuplicateUserException("User ID = " + user.getId() + " is already taken");
            logger.error("User ID = " + user.getId() + " is already taken",exception);
            throw exception;
        }

        if(userRepository.existsByUsername(user.getUsername())) {
            DuplicateUserException exception = new DuplicateUserException("Username = " + user.getUsername() + " is already taken");
            logger.error("Username = " + user.getUsername() + " is already taken",exception);
            throw exception;
        }
    }

    public void assertWasFound(User user, String expected){
        if(user == null){
            UserNotFoundException exception = new UserNotFoundException("User with username = " + expected + " could not be found");
            logger.error("User with username = "+expected+" could not be found",exception);
            throw exception;
        }
    }

}
