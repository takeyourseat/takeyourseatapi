package com.stefanini.internship.usermanagement.api;


import com.stefanini.internship.usermanagement.dao.Role;
import com.stefanini.internship.usermanagement.dao.User;
import com.stefanini.internship.usermanagement.dao.repository.RoleRepository;
import com.stefanini.internship.usermanagement.dao.repository.UserRepository;
import com.stefanini.internship.usermanagement.api.searchmodel.UserSearchModel;
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


    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/users", params = "searchArgument", method = RequestMethod.GET)
    public List<User> getOneUser(@RequestParam("searchArgument")String searchArgument) {
        List<User> user = userRepository.findByFNameContainingOrLNameContaining(
                searchArgument,
                searchArgument
        );
        return user;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public List<Role> getRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @PreAuthorize("@AuthorizationService.hasPermission('User','write')")
    public void createUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public User getUserByUsername(@PathVariable("username") String username) {
        User user = userRepository.findUserByUsername(username);
        return user;
    }


    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/users/{username}/manager", method = RequestMethod.GET)
    public User getUserManager(@PathVariable("username") String username) {
        User user = userRepository.findUserByUsername(username);
        return user.getManager();
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/users", params = "manager", method = RequestMethod.GET)
    public List<User> getUsersByManagerUsername(@RequestParam("manager")String managerUsername) {
        List<User> users = userRepository.findUsersByManagerUsername(managerUsername);
        return users;
    }

}