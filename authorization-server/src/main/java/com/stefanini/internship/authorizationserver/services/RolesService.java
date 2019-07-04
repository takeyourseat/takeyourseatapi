package com.stefanini.internship.authorizationserver.services;

import com.stefanini.internship.authorizationserver.dao.Grant;
import com.stefanini.internship.authorizationserver.dao.Role;
import com.stefanini.internship.authorizationserver.dao.repositories.GrantRepository;
import com.stefanini.internship.authorizationserver.dao.repositories.RoleRepository;
import com.stefanini.internship.authorizationserver.dto.responses.RoleGrantsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    public RoleGrantsResponse getRoleGrants(String role){
        List<Grant> grants = grantRepository.getByRoleNameIgnoreCase(role);
        log.debug("Processing a list of "+grants.size()+" grants");
        RoleGrantsResponse response = buildRoleGrantsResponse(role, grants);
        return response;
    }

    RoleGrantsResponse buildRoleGrantsResponse(String role, List<Grant> grants){
        RoleGrantsResponse response = new RoleGrantsResponse();
        response.setRole(role);
        response.setGrants(new HashMap<>());
        for(Grant grant : grants){
            String grantRole = grant.getRole().getName();
            if(!role.equalsIgnoreCase(grantRole))
                throw new IllegalArgumentException(String.format("Cannot build RoleGrantResponse for role '%s' with grants of role '%s'",role,grantRole));
            response.getGrants().put(grant.getDataType().getName().toLowerCase(),grant.getPermission());
        }
        return response;
    }

}
