package com.stefanini.internship.oauthserver.service;

import com.stefanini.internship.oauthserver.dao.User;
import com.stefanini.internship.oauthserver.dao.repositories.UserRepository;
import com.stefanini.internship.oauthserver.exceptions.DuplicateUserException;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService {


    private UserRepository userRepository;

    public UserValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void assertUnique(User user){
        if(userRepository.existsById(user.getId()))
            throw new DuplicateUserException("User ID = "+user.getId()+" is already taken");

        if(userRepository.existsByUsername(user.getUsername()))
            throw new DuplicateUserException("Username = "+user.getUsername()+" is already taken");
    }

    public void assertWasFound(User user){

    }

}
