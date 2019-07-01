package com.stefanini.internship.authorizationserver.services;

import com.stefanini.internship.authorizationserver.dao.*;
import com.stefanini.internship.authorizationserver.dao.repositories.RoleRepository;
import com.stefanini.internship.authorizationserver.dao.repositories.UserRepository;
import com.stefanini.internship.authorizationserver.dto.PostUserRequest;
import com.stefanini.internship.authorizationserver.exceptions.DuplicateUserException;
import com.stefanini.internship.authorizationserver.exceptions.ResourceNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationUsersService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private EntityValidationService entityValidationService;

    private final static Logger logger = Logger.getLogger(AuthorizationUsersService.class);


    public AuthorizationUsersService(UserRepository userRepository, RoleRepository roleRepository, EntityValidationService entityValidationService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.entityValidationService = entityValidationService;
    }

    @PreAuthorize("@authorizationService.checkAuthorization('user','write').isAuthorized()")
    public void createUser(PostUserRequest user) {

        String authenticatedUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        if (userRepository.existsByUsername(user.getUsername())) {
            RuntimeException exception = new DuplicateUserException("User " + user.getUsername() + " already exists");
            logger.error(String.format("Could not create user '%s' because username already exists",user.getUsername()),exception);
            throw exception;
        }

        String role = "null";
        if (user.getRole() != null) {
            Role userRole = roleRepository.findByName(user.getRole().getName());
            if(userRole == null)
                throw new ResourceNotFoundException("User's role could not be found");

            user.setRole(userRole);
            role = userRole.getName();

        }

        User userSid = new User(user.getId(), user.getUsername(), user.getRole(), true);
        logger.debug(String.format("User %s attempts to save user %s with role = '%s')",authenticatedUserName, user.getUsername(), role));
        userRepository.save(userSid);
        logger.info(String.format("User %s creates save %s with role = '%s')",authenticatedUserName, user.getUsername(), role));
    }


    @PreAuthorize("@authorizationService.checkAuthorization('user','write').isAuthorized()")
    public void deleteUser(String username){
        String authenticatedUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info(String.format("User '%s' tries to disable user '%s'",authenticatedUserName,username));
        User user = userRepository.findByUsername(username);
        entityValidationService.AssertValidResult(user, username);
        user.setEnabled(false);
        userRepository.save(user);

        logger.info(String.format("User '%s' disabled user '%s'",authenticatedUserName,username));
    }
}