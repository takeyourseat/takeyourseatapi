package com.stefanini.internship.authorizationserver.services;

import com.stefanini.internship.authorizationserver.dao.*;
import com.stefanini.internship.authorizationserver.dao.classes.PlaceRequest;
import com.stefanini.internship.authorizationserver.dao.classes.User;
import com.stefanini.internship.authorizationserver.dao.repositories.*;
import com.stefanini.internship.authorizationserver.exceptions.ResourceNotFoundException;
import com.stefanini.internship.authorizationserver.utils.OwaPermission;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjectIdentitiesService {
    private OwaGrantRepository grantRepository;
    private OwaClassRepository classRepository;
    private OwaSidRepository sidRepository;
    private OwaObjectRepository objectRepository;
    private OwaRoleRepository roleRepository;

    private EntityValidationService validationService;
    private AuthorizationService authorizationService;

    public ObjectIdentitiesService(OwaGrantRepository grantRepository, OwaClassRepository classRepository, OwaSidRepository sidRepository, OwaObjectRepository objectRepository, OwaRoleRepository roleRepository, EntityValidationService validationService, AuthorizationService authorizationService) {
        this.grantRepository = grantRepository;
        this.classRepository = classRepository;
        this.sidRepository = sidRepository;
        this.objectRepository = objectRepository;
        this.roleRepository = roleRepository;
        this.validationService = validationService;
        this.authorizationService = authorizationService;
    }

    @Transactional
    public Long createUserAndSid(User user) {
        Long identifier = user.getId();
        OwaClass owaClass = classRepository.findByClassname("USER");

        if (user.getRole() != null) {
            OwaRole userRole = roleRepository.findByName(user.getRole().getName());
            user.setRole(userRole);
        }
        OwaSid userSid = new OwaSid(user.getId(), user.getUsername(), user.getRole());
        sidRepository.save(userSid);

        OwaObject userObject = new OwaObject(owaClass, identifier);
        objectRepository.save(userObject);

        List<OwaGrant> grants = new ArrayList<>();
        if (user.getManager() != null) {
            String manager = user.getManager().getUsername();
            OwaSid managerSid = sidRepository.findBySid(manager);
            validationService.AssertValidResult(managerSid, manager);
            grants.add(new OwaGrant(userObject, managerSid, OwaPermission.READ_MASK));
            grants.add(new OwaGrant(userObject, managerSid, OwaPermission.ADMINISTER_MASK));
        }
        grants.add(new OwaGrant(userObject, userSid, OwaPermission.READ_MASK));
        grantRepository.saveAll(grants);
        return userObject.getId();
    }

    @Transactional
    public Long createPlaceRequest(PlaceRequest placeRequest) {
        OwaClass owaClass = classRepository.findByClassname("PLACEREQUEST");

        OwaSid user = sidRepository
                .findById(placeRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + placeRequest.getUserId()));

        OwaObject toAdd = new OwaObject(owaClass, placeRequest.getId());
        toAdd = objectRepository.save(toAdd);

        /*Add here call to user management for retrieving user manager*/
        //Get user's manager
        //Get manager's SID
        //assume the first SID is the manager for now
        OwaSid managerSid = sidRepository.findAll().get(0);

        List<OwaGrant> grants = new ArrayList<>(3);

        if (managerSid != null) {
            grants.add(new OwaGrant(toAdd, managerSid, OwaPermission.READ_MASK));
            grants.add(new OwaGrant(toAdd, managerSid, OwaPermission.ADMINISTER_MASK));
        } else {
            grants.add(new OwaGrant(toAdd, user, OwaPermission.ADMINISTER_MASK));
        }
        grants.add(new OwaGrant(toAdd, user, OwaPermission.READ_MASK));

        grantRepository.saveAll(grants);
        return toAdd.getId();
    }
}
