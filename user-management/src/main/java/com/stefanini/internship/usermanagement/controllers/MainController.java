package com.stefanini.internship.usermanagement.controllers;

import com.stefanini.internship.usermanagement.dao.Role;
import com.stefanini.internship.usermanagement.dao.User;
import com.stefanini.internship.usermanagement.dao.repository.UserRepository;
import com.stefanini.internship.usermanagement.services.RoleService;
import com.stefanini.internship.usermanagement.services.UserManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserManagementService userManagementService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        logger.info("Accessed the GET method to find all users");
        List<Role> allUsersRoles = roleService.getAllRoles();
        List<User> allUsers = userManagementService.getAllUsers();
        List<User> allUsers1 = userRepository.findAll();
        logger.debug("GET method for taking all users performs itself");
        return allUsers;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/allroles", method = RequestMethod.GET)
    public ResponseEntity getAllRoles() {
        logger.info("Accessed the GET method to find all roles");
        List<Role> allroles = roleService.getAllRoles();
        logger.debug("GET method for taking all users performs itself");
        return ResponseEntity.ok().body(allroles);
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/users", params = "searchArgument", method = RequestMethod.GET)
    public List<User> getOneUser(@RequestParam("searchArgument") String searchArgument) {
        logger.info("Accessed GET method for searching users with parameters : " + searchArgument);
        List<User> user = userRepository.findByFNameContainingOrLNameContaining(
                searchArgument,
                searchArgument
        );
        logger.debug(String.format("Method performs searching the users according to the input argument = '%s' and returns result", searchArgument));
        return user;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @PreAuthorize("@AuthorizationService.hasPermission('User','write')")
    public void createUser(@RequestBody User user) {
        logger.info("Accessed POST method to create a user" + user.getUsername());
        userRepository.save(user);
        logger.debug("POST method saved the user" + user.getUsername());
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/users/edit", method = RequestMethod.POST)
    @PreAuthorize("@AuthorizationService.hasPermission('User','write')")
    public void editUser(@RequestBody User user) {
        logger.info("Accessed POST method to edit an user" + user.getUsername());
        User editableUser = userRepository.findUserByUsername(user.getUsername());

        if (!user.getfName().isEmpty())
            editableUser.setfName(user.getfName());
        if (!user.getlName().isEmpty())
            editableUser.setlName(user.getlName());
        if (!user.getEmail().isEmpty())
            editableUser.setEmail(user.getEmail());
        if (!user.getPassword().isEmpty())
            editableUser.setPassword(user.getPassword());
        if (!user.getManager().getUsername().isEmpty())
            editableUser.setManager(user.getManager());
        userRepository.save(editableUser);
        logger.debug("POST method saved the user:" + user.getUsername());
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public User getUserByUsername(@PathVariable("username") String username) {
        logger.info("Accessed GET method for searching user by username = " + username);
        User user = userRepository.findUserByUsername(username);
        logger.debug("GET method performs searching users by username" + username);
        return user;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/users/{username}/manager", method = RequestMethod.GET)
    public User getUserManager(@PathVariable("username") String username) {
        logger.info(String.format("Accessed GET method to find user's ('%s') manager", username));
        User user = userRepository.findUserByUsername(username);
        logger.debug(String.format("GET method performs searching user's ('%s') manager", username));
        return user.getManager();
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/users", params = "manager", method = RequestMethod.GET)
    public List<User> getUsersByManagerUsername(@RequestParam("manager") String managerUsername) {
        logger.info("Accessed GET method to return all users of the manager :" + managerUsername);
        List<User> users = userRepository.findUsersByManagerUsername(managerUsername);
        logger.debug("GET method performs searching all users of the manager" + managerUsername);
        return users;
    }

}
