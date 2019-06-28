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
    @RequestMapping("/getOneUser")
    public List<User> getOneUser(@RequestBody UserSearchModel userSearchModel) {
        List<User> user = userRepository.findByFNameContainingOrLNameContaining(
                userSearchModel.getSearchArgument(),
                userSearchModel.getSearchArgument()
        );
        return user;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping("/getUsers")
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping("/getRoles")
    public List<Role> getRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping("/createUser")
    @PreAuthorize("@AuthorizationService.hasPermission('User','write')")
    public void createUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/getUserByUsername", params = "username")
    public User getUserByUsername(@RequestParam String username) {
        User user = userRepository.findUserByUsername(username);
        return user;
    }


    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/getUserManager", params = "username")
    public User getUserManager(@RequestParam String username) {
        User user = userRepository.findUserByUsername(username);
        return user.getManager();
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/getUsersByManagerUsername", params = "username")
    public List<User> getUserByManagerUsername(@RequestParam String username) {
        List<User> users = userRepository.findUsersByManagerUsername(username);
        return users;
    }


}
