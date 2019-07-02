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
    @RequestMapping(value = "/users/{searchArgument}/info", method = RequestMethod.POST)
    public List<User> getOneUser(@RequestBody UserSearchModel userSearchModel, @PathVariable String searchArgument) {
        List<User> user = userRepository.findByFNameContainingOrLNameContaining(
                userSearchModel.getSearchArgument(),
                userSearchModel.getSearchArgument()
        );
        return user;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/users/getUsers", method = RequestMethod.GET)
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping("/users/getRoles")
    public List<Role> getRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/users/createUser", method = RequestMethod.POST)
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
    @RequestMapping(value = "/users/{managerUsername}/list", method = RequestMethod.GET)
    public List<User> getUserByManagerUsername(@PathVariable("managerUsername")String managerUsername) {
        List<User> users = userRepository.findUsersByManagerUsername(managerUsername);
        return users;
    }

}
