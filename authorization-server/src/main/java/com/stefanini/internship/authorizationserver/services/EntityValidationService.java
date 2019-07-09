package com.stefanini.internship.authorizationserver.services;

import com.stefanini.internship.authorizationserver.dao.DataType;
import com.stefanini.internship.authorizationserver.dao.Role;
import com.stefanini.internship.authorizationserver.dao.User;
import com.stefanini.internship.authorizationserver.dao.repositories.RoleRepository;
import com.stefanini.internship.authorizationserver.exceptions.DuplicateResourceException;
import com.stefanini.internship.authorizationserver.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EntityValidationService {

    private final RoleRepository roleRepository;

    public EntityValidationService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void AssertValidResult(User sid, String expected) throws ResourceNotFoundException {
        if(sid == null) {
            RuntimeException exception = new ResourceNotFoundException("User " + expected + " could not be found");
            log.debug("User not found", exception);
            throw exception;
        }
    }

    public void AssertValidResult(DataType dataType, String classname){
        if(dataType == null) {
            RuntimeException exception = new ResourceNotFoundException("Data type " + classname + " could not be found in the Authorization database");
            log.debug("Data type not found", exception);
            throw exception;
        }
    }

    public void AssertValidResult(Role role, String roleName){
        if(role == null) {
            RuntimeException exception = new ResourceNotFoundException("Role " + roleName + " could not be found in the Authorization database");
            log.debug("Role not found", exception);
            throw exception;
        }

    }

    public void assertRoleNotPresentInDb(String role){
        if(roleRepository.existsByNameIgnoreCase(role))
            throw new DuplicateResourceException("Role with name "+role+" already exists");
    }
}
