package com.stefanini.internship.usermanagement.api;


import com.stefanini.internship.usermanagement.dao.Role;
import com.stefanini.internship.usermanagement.dao.User;
import com.stefanini.internship.usermanagement.dao.repository.RoleRepository;
import com.stefanini.internship.usermanagement.dao.repository.UserRepository;
import com.stefanini.internship.usermanagement.api.searchmodel.UserSearchModel;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createUser(@RequestBody User user) {
        userRepository.save(user);
    }

}
