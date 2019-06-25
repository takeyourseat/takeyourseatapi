package com.stefanini.internship.oauthserver.controllers;

import com.stefanini.internship.oauthserver.dao.User;
import com.stefanini.internship.oauthserver.dao.repositories.UserRepository;
import com.stefanini.internship.oauthserver.exceptions.UserNotFoundException;
import com.stefanini.internship.oauthserver.service.UserValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import static com.stefanini.internship.oauthserver.utils.AppConstants.API_ROOT_URL;

@RestController
@RequestMapping(API_ROOT_URL+"users")
public class UsersController {

    final private UserRepository userRepository;
    final private UserValidationService userValidationService;

    public UsersController(UserRepository userRepository, UserValidationService userValidationService) {
        this.userRepository = userRepository;
        this.userValidationService = userValidationService;
    }

    @PreAuthorize("@AuthorizationService.hasPermission('User','write')")
    @PostMapping
    public ResponseEntity createUser(@RequestBody User user){

        userValidationService.assertUnique(user);

        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        String password = hashPassword(user.getPassword());
        user.setPassword(password);

        userRepository.save(user);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("@AuthorizationService.hasPermission('User','write')")
    public ResponseEntity deactivateUser(@PathVariable String username) {
        User user = userRepository.findByUsername(username);

        if(user == null)
            throw new UserNotFoundException("User with username = "+username+" could not be found");

        user.setEnabled(false);
        userRepository.save(user);

        return ResponseEntity.noContent().build();
    }


    static String hashPassword(String password){
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
        return "{bcrypt}"+hashed;
    }
}
