package com.stefanini.internship.placemanagement.controller;

import com.stefanini.internship.placemanagement.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }


    @GetMapping("/users/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.getUserById(id));
    }

    @RequestMapping(value = "/users", params = "managerId", method = RequestMethod.GET)
    public ResponseEntity getUsersByManagerId(@RequestParam Long managerId) {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.getUsersByManagerId(managerId));
    }
}
