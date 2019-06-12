package com.stefanini.internship.authorizationserver.controllers;

import com.stefanini.internship.authorizationserver.dao.*;
import com.stefanini.internship.authorizationserver.dao.classes.PlaceRequest;
import com.stefanini.internship.authorizationserver.dao.classes.User;
import com.stefanini.internship.authorizationserver.dao.repositories.*;
import com.stefanini.internship.authorizationserver.utils.EntityValidation;
import com.stefanini.internship.authorizationserver.utils.OwaPermission;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.stefanini.internship.authorizationserver.utils.AppConstants.API_ROOT_VERSION;

@RestController
@RequestMapping(API_ROOT_VERSION+"objects")
public class ObjectIdentitiesController {

    private OwaGrantRepository grantRepository;
    private OwaClassRepository classRepository;
    private OwaSidRepository sidRepository;
    private OwaObjectRepository objectRepository;
    private OwaRoleRepository roleRepository;

    public ObjectIdentitiesController(OwaGrantRepository grantRepository, OwaClassRepository classRepository, OwaSidRepository sidRepository, OwaObjectRepository objectRepository, OwaRoleRepository roleRepository) {
        this.grantRepository = grantRepository;
        this.classRepository = classRepository;
        this.sidRepository = sidRepository;
        this.objectRepository = objectRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/users")
    @Transactional
    public ResponseEntity createUserAndSid(@RequestBody User user){
        Long identifier = user.getId();
        OwaClass owaClass = classRepository.findByClassname("USER");

        if(user.getRole() != null) {
            OwaRole userRole = roleRepository.findByName(user.getRole().getName());
            user.setRole(userRole);
        }

        OwaSid userSid = new OwaSid(user.getId(), user.getUsername(), user.getRole());

        sidRepository.save(userSid);

        OwaObject userObject = new OwaObject(owaClass, identifier);
        objectRepository.save(userObject);

        List<OwaGrant> grants = new ArrayList<>();
        if(user.getManager()!=null){
            String manager = user.getManager().getUsername();
            OwaSid managerSid = sidRepository.findBySid(manager);

            EntityValidation.AssertValidResult(managerSid,manager);

            grants.add(new OwaGrant(userObject,managerSid, OwaPermission.READ_MASK));
            grants.add(new OwaGrant(userObject,managerSid, OwaPermission.ADMINISTER_MASK));
        }
        grants.add(new OwaGrant(userObject,userSid, OwaPermission.READ_MASK));
        grantRepository.saveAll(grants);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/place-requests")
    @Transactional
    public ResponseEntity createPlaceRequest(@RequestBody PlaceRequest placeRequest){
        OwaClass owaClass = classRepository.findByClassname("PLACEREQUEST");

        String username = placeRequest.getUser().getUsername();
        OwaSid user = sidRepository.findBySid(username);

        EntityValidation.AssertValidResult(user,username);

        OwaObject toAdd = new OwaObject(owaClass,placeRequest.getId());
        objectRepository.save(toAdd);

        List<OwaGrant> grants = new ArrayList<>(3);

        if(placeRequest.getUser().getManager()!= null){
            String managerUsername = placeRequest.getUser().getManager().getUsername();
            OwaSid manager = sidRepository.findBySid(managerUsername);
            EntityValidation.AssertValidResult(manager,managerUsername);

            grants.add(new OwaGrant(toAdd,manager,OwaPermission.READ_MASK));
            grants.add(new OwaGrant(toAdd,manager,OwaPermission.ADMINISTER_MASK));
        }
        else{
            grants.add(new OwaGrant(toAdd, user,OwaPermission.ADMINISTER_MASK ));
        }
        grants.add(new OwaGrant(toAdd,user,OwaPermission.READ_MASK));

        grantRepository.saveAll(grants);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
