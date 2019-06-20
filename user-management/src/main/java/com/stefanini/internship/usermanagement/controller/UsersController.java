package com.stefanini.internship.usermanagement.controller;

import com.stefanini.internship.usermanagement.dao.Role;
import com.stefanini.internship.usermanagement.dao.User;
import com.stefanini.internship.usermanagement.dao.repository.RoleRepository;
import com.stefanini.internship.usermanagement.dao.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v01/")
public class UsersController {

    private UserRepository userRepo;
    private RoleRepository roleRepo;

    public UsersController(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }
    @PreAuthorize("@AuthorizationService.hasPermission('user', 'write')")
    @PostMapping("users")
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

    @GetMapping("users")
    @PreAuthorize("@AuthorizationService.hasPermission('user', 'read')")
    public ResponseEntity getReadableUsers(){
        List<User> toReturn = userRepo.findAll();
        toReturn.forEach(user -> user.setRole((Role)Hibernate.unproxy(user.getRole())));
        return ResponseEntity.ok(toReturn);
    }

    @GetMapping("users/{id}")
    @PreAuthorize("@AuthorizationService.hasPermission('user', 'read')")
    public ResponseEntity<User> GetUserById(@PathVariable Long id){
        User user = userRepo.findById(id).get();
        user.setRole((Role)Hibernate.unproxy(user.getRole()));
        user.setManager(null);
        return ResponseEntity.ok(user);
    }
}
