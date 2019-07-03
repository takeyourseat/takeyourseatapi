package com.stefanini.internship.authorizationserver.services;

import com.stefanini.internship.authorizationserver.dao.Grant;
import com.stefanini.internship.authorizationserver.dao.Role;
import com.stefanini.internship.authorizationserver.dao.repositories.GrantRepository;
import com.stefanini.internship.authorizationserver.dao.repositories.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RolesService {

    private final RoleRepository roleRepository;
    private final GrantRepository grantRepository;

    public RolesService(RoleRepository roleRepository, GrantRepository grantRepository) {
        this.roleRepository = roleRepository;
        this.grantRepository = grantRepository;
    }

    public List<Role> getAllUsers(){
        List<Role> roles = roleRepository.findAll();
        log.info("Returning list of "+roles.size()+" roles");
        return roles;
    }

    public List<Grant> getRoleGrants(String role){
        List<Grant> grants = grantRepository.getByRoleNameIgnoreCase(role);
        log.info("Returning list of "+grants.size()+" grants");
        return grants;
    }
}
