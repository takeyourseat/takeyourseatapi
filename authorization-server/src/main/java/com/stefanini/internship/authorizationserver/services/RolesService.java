package com.stefanini.internship.authorizationserver.services;

import com.stefanini.internship.authorizationserver.dao.DataType;
import com.stefanini.internship.authorizationserver.dao.Grant;
import com.stefanini.internship.authorizationserver.dao.Role;
import com.stefanini.internship.authorizationserver.dao.repositories.DataTypeRepository;
import com.stefanini.internship.authorizationserver.dao.repositories.GrantRepository;
import com.stefanini.internship.authorizationserver.dao.repositories.RoleRepository;
import com.stefanini.internship.authorizationserver.dto.responses.RoleGrantsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class RolesService {

    private final RoleRepository roleRepository;
    private final GrantRepository grantRepository;
    private final DataTypeRepository dataTypeRepository;
    private final EntityValidationService validationService;

    public RolesService(RoleRepository roleRepository, GrantRepository grantRepository, DataTypeRepository dataTypeRepository, EntityValidationService validationService) {
        this.roleRepository = roleRepository;
        this.grantRepository = grantRepository;
        this.dataTypeRepository = dataTypeRepository;
        this.validationService = validationService;
    }

    public List<Role> getAllRoles(){
        List<Role> roles = roleRepository.findAll();
        log.info("Returning list of "+roles.size()+" roles");
        return roles;
    }

    public void createRole(Role role){
        String authenticatedUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        log.debug("User {} tries to create role {}",authenticatedUserName, role.getName());
        validationService.assertRoleNotPresentInDb(role.getName());
        final Role savedRole = roleRepository.save(role);

        log.debug("Setting all the permissions for role {} to 0",savedRole.getName());
        List<DataType> dataTypes = dataTypeRepository.findAll();
        dataTypes.forEach(dataType -> {
            Grant grant = new Grant(dataType, 0, savedRole);
            grantRepository.save(grant);
        });

        log.info("User {} creates role {}",authenticatedUserName, role.getName());
    }

    public List<RoleGrantsResponse> getAllRolesGrants() {
        List<RoleGrantsResponse>response = new ArrayList<>();
        List<Role> roles = getAllRoles();
        for(Role role : roles)
            response.add(getRoleGrants(role.getName()));
        return response;
    }

    public RoleGrantsResponse getRoleGrants(String role){
        List<Grant> grants = grantRepository.getByRoleNameIgnoreCase(role);
        log.debug("Processing a list of "+grants.size()+" grants");
        RoleGrantsResponse response = buildRoleGrantsResponse(role, grants);
        return response;
    }

    public void grantPermissionToRole (String role, String dataType, int permission){
        Role roleEntity = roleRepository.findByNameIgnoreCase(role);
        validationService.AssertValidResult(roleEntity,role);

        DataType dataTypeEntity = dataTypeRepository.findByName(dataType);
        validationService.AssertValidResult(dataTypeEntity, dataType);

        Grant grant = grantRepository.findByDataTypeAndRole(dataTypeEntity, roleEntity);
        if(grant == null)
            grant = new Grant()
                    .setDataType(dataTypeEntity)
                    .setRole(roleEntity);

        grant.setPermission(permission);
        grantRepository.save(grant);
    }

    RoleGrantsResponse buildRoleGrantsResponse(String role, List<Grant> grants){
        RoleGrantsResponse response = new RoleGrantsResponse();
        response.setRole(role);
        response.setGrants(new HashMap<>());
        for(Grant grant : grants){
            String grantRole = grant.getRole().getName();
            if(!role.equalsIgnoreCase(grantRole))
                throw new IllegalArgumentException(String.format("Cannot build RoleGrantResponse for role '%s' with grants of role '%s'",role,grantRole));
            response.getGrants().put(grant.getDataType().getName().toLowerCase(),new RoleGrantsResponse.PermissionWrapper(grant.getPermission()));
        }
        return response;
    }



}
