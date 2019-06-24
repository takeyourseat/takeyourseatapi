package com.stefanini.internship.authorizationserver.services;

import com.stefanini.internship.authorizationserver.dao.*;
import com.stefanini.internship.authorizationserver.dao.repositories.RoleRepository;
import com.stefanini.internship.authorizationserver.dao.repositories.UserRepository;
import com.stefanini.internship.authorizationserver.dto.PostUserRequest;
import com.stefanini.internship.authorizationserver.exceptions.DuplicateUserException;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorizationUsersService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private final static Logger logger = Logger.getLogger(AuthorizationUsersService.class);


    public AuthorizationUsersService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void createUser(PostUserRequest user) {

        String authenticatedUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        if (userRepository.existsByUsername(user.getUsername())) {
            RuntimeException exception = new DuplicateUserException("User " + user.getUsername() + " already exists");
            logger.error(String.format("Could not create user '%s' because username already exists",user.getUsername()),exception);
            throw exception;
        }

        if (user.getRole() != null) {
            Role userRole = roleRepository.findByName(user.getRole().getName());
            user.setRole(userRole);
        }

        User userSid = new User(user.getId(), user.getUsername(), user.getRole());
        logger.debug(String.format("User %s attempts to save user %s with role = '%s')",authenticatedUserName, user.getUsername(), user.getRole().getName()));
        userRepository.save(userSid);
        logger.info(String.format("User %s creates save %s with role = '%s')",authenticatedUserName, user.getUsername(), user.getRole().getName()));
    }
}
