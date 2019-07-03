package com.stefanini.internship.oauthserver.service;

import com.stefanini.internship.oauthserver.dao.User;
import com.stefanini.internship.oauthserver.dao.repositories.UserRepository;
import com.stefanini.internship.oauthserver.exceptions.DuplicateUserException;
import com.stefanini.internship.oauthserver.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserValidationService {

    private UserRepository userRepository;

    public UserValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void assertUnique(User user){
        if(userRepository.existsById(user.getId())) {
            RuntimeException exception = new DuplicateUserException("User ID = " + user.getId() + " is already taken");
            log.error("User ID = " + user.getId() + " is already taken",exception);
            throw exception;
        }

        if(userRepository.existsByUsername(user.getUsername())) {
            DuplicateUserException exception = new DuplicateUserException("Username = " + user.getUsername() + " is already taken");
            log.error("Username = " + user.getUsername() + " is already taken",exception);
            throw exception;
        }
    }

    public void assertWasFound(User user, String expected){
        if(user == null){
            UserNotFoundException exception = new UserNotFoundException("User with username = " + expected + " could not be found");
            log.error("User with username = "+expected+" could not be found",exception);
            throw exception;
        }
    }

}
