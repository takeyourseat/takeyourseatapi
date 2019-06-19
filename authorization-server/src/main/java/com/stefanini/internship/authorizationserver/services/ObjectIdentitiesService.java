package com.stefanini.internship.authorizationserver.services;

import com.stefanini.internship.authorizationserver.dao.*;
import com.stefanini.internship.authorizationserver.dao.repositories.RoleRepository;
import com.stefanini.internship.authorizationserver.dao.repositories.UserRepository;
import com.stefanini.internship.authorizationserver.dto.PostUserRequest;
import com.stefanini.internship.authorizationserver.exceptions.DuplicateUserException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ObjectIdentitiesService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public ObjectIdentitiesService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void createUser(PostUserRequest user) {
        if (userRepository.existsByUsername(user.getUsername()))
            throw new DuplicateUserException("User "+user.getUsername()+" already exists");

        if (user.getRole() != null) {
            Role userRole = roleRepository.findByName(user.getRole().getName());
            user.setRole(userRole);
        }
        User userSid = new User(user.getId(), user.getUsername(), user.getRole());
        userRepository.save(userSid);
    }
}
