package com.stefanini.internship.oauthserver.controllers;

import com.stefanini.internship.oauthserver.dao.User;
import com.stefanini.internship.oauthserver.dao.repositories.UserRepository;
import com.stefanini.internship.oauthserver.service.UserValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import static com.stefanini.internship.oauthserver.utils.AppConstants.API_ROOT_URL;

@RestController
@Slf4j
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

        String authenticatedUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info(String.format("User '%s' tries to create user with username '%s'",authenticatedUserName,user.getUsername()));

        userValidationService.assertUnique(user);

        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        String password = hashPassword(user.getPassword());
        user.setPassword(password);

        userRepository.save(user);
        log.info(String.format("User '%s' has successfully created user with username '%s'. Building HTTP response",authenticatedUserName,user.getUsername()));
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("{username}")
    @PreAuthorize("@AuthorizationService.hasPermission('User','write')")
    public ResponseEntity deactivateUser(@PathVariable String username) {
        String authenticatedUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info(String.format("User '%s' tries to disable user with username '%s'",authenticatedUserName,username));
        User user = userRepository.getByUsername(username);

        userValidationService.assertWasFound(user,username);

        user.setEnabled(false);
        userRepository.save(user);

        log.info(String.format("User '%s' successfully disables user with username '%s'",authenticatedUserName,username));
        return ResponseEntity.noContent().build();
    }


    static String hashPassword(String password){
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
        return "{bcrypt}"+hashed;
    }
}
