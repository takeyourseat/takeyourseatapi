package com.stefanini.internship.oauthserver.controllers;

import com.stefanini.internship.oauthserver.dao.User;
import com.stefanini.internship.oauthserver.dao.repositories.UserRepository;
import com.stefanini.internship.oauthserver.exceptions.DuplicateUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import static com.stefanini.internship.oauthserver.utils.AppConstants.API_ROOT_URL;

@RestController
@RequestMapping(API_ROOT_URL+"users")
public class UsersController {

    @Autowired
    UserRepository userRepository;

    @PreAuthorize("@AuthorizationService.hasPermission('User','write')")
    @PostMapping
    public ResponseEntity createUser(@RequestBody User user){

        if(userRepository.existsById(user.getId()))
            throw new DuplicateUserException("This ID is already taken");

        if(userRepository.existsByUsername(user.getUsername()))
            throw new DuplicateUserException("This username is already taken");

        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        String password = hashPassword(user.getPassword());
        user.setPassword(password);

        userRepository.save(user);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@AuthorizationService.hasPermission('User','write')")
    public ResponseEntity deactivateUser(@PathVariable Long id){
        User user = userRepository.getOne(id);
        if (user == null)
            return ResponseEntity.notFound().build();

        user.setEnabled(false);
        userRepository.save(user);

        return ResponseEntity.noContent().build();
    }


    static String hashPassword(String password){
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
        return "{bcrypt}"+hashed;
    }
}
