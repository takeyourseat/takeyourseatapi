package com.stefanini.internship.usermanagement.controller;

import com.stefanini.internship.usermanagement.dao.Role;
import com.stefanini.internship.usermanagement.dao.User;
import com.stefanini.internship.usermanagement.dao.repository.RoleRepository;
import com.stefanini.internship.usermanagement.dao.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {

    private UserRepository userRepo;
    private RoleRepository roleRepo;

    public UsersController(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    //Using GetMapping for testing convenience, to be changed to PostMapping
    @GetMapping("/api/v01/users")
    public ResponseEntity createUser(){
        Role role = roleRepo.findById(1L).get();
        User manager = userRepo.findById(1L).get();
        User newUser = new User()
                .setEmail("test@mail."+System.currentTimeMillis())
                .setUsername("some User "+System.currentTimeMillis())
                .setFirstName("John")
                .setLastName("Doe")
                .setRole(role)
                .setManager(manager)
                .setPassword("");
        userRepo.save(newUser);
        return ResponseEntity.ok().build();
    }
}
