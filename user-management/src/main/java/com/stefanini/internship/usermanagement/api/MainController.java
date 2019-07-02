package com.stefanini.internship.usermanagement.api;


import com.stefanini.internship.usermanagement.dao.Role;
import com.stefanini.internship.usermanagement.dao.User;
import com.stefanini.internship.usermanagement.dao.repository.RoleRepository;
import com.stefanini.internship.usermanagement.dao.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);


    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/users", params = "searchArgument", method = RequestMethod.GET)
    public List<User> getOneUser(@RequestParam("searchArgument")String searchArgument) {
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
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        logger.info("Accessed the GET method to find all users");
        List<User> users = userRepository.findAll();
        logger.debug("GET method for taking all users performs itself");
        return users;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public List<Role> getRoles(){
        logger.info("Accessed the GET method to returns all roles");
        List<Role> roles = roleRepository.findAll();
        logger.debug("GET method for taking all roles performs itself");
        return roles;
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
        logger.debug(String.format("GET method performs searching user's ('%s') manager" , username));
        return user.getManager();
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/users", params = "manager", method = RequestMethod.GET)
    public List<User> getUsersByManagerUsername(@RequestParam("manager")String managerUsername) {
        logger.info("Accessed GET method to return all users of the manager :" + managerUsername);
        List<User> users = userRepository.findUsersByManagerUsername(managerUsername);
        logger.debug("GET method performs searching all users of the manager" + managerUsername);
        return users;
    }

}
