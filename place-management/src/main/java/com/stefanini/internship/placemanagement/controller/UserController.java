package com.stefanini.internship.placemanagement.controller;

import com.stefanini.internship.placemanagement.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/users/{username}")
    public ResponseEntity getUserByUsername(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByUsername(username));
    }

    @RequestMapping(value = "/users", params = "managerId", method = RequestMethod.GET)
    public ResponseEntity getUsersByManagerUsername(@RequestParam String managerUsername) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersByManagerUsername(managerUsername));
    }
}
