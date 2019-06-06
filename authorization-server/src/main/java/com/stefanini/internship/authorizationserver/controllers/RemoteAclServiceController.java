package com.stefanini.internship.authorizationserver.controllers;

import com.stefanini.internship.authorizationserver.dao.*;
import com.stefanini.internship.authorizationserver.dao.repositories.AclClassPermissionRepository;
import com.stefanini.internship.authorizationserver.dao.repositories.AclClassRepository;
import com.stefanini.internship.authorizationserver.dao.repositories.AclSidRepository;
import com.stefanini.internship.authorizationserver.utils.AuthorizationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acls.domain.*;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RemoteAclServiceController {

    @Autowired
    JdbcMutableAclService jdbcAclService;

    @Autowired
    AclClassPermissionRepository classPermissionRepo;

    @Autowired
    AclClassRepository classRepo;

     @Autowired
     AclSidRepository sidRepo;

    @GetMapping(value = "/get-authorization", params = {"classname", "principal","identifier","mask"})
    public ResponseEntity<AuthorizationResponse> checkAuthorization (
            @RequestParam String classname,
            @RequestParam Long identifier,
            @RequestParam String principal,
            @RequestParam Integer mask
    ){

        ObjectIdentity objectIdentity = new ObjectIdentityImpl(classname, identifier);
        List<Sid> sids = Arrays.asList(new PrincipalSid(principal));

        Acl acl;
        try {
            acl = jdbcAclService.readAclById(objectIdentity, sids);
        }catch (Exception e){
            HttpHeaders notFoundHeader = new HttpHeaders();
            notFoundHeader.add("message","Could not find object identity for class "+classname+" with identifier = "+identifier);
            return ResponseEntity.notFound().headers(notFoundHeader).build();
        }

        PermissionFactory permissionFactory = new DefaultPermissionFactory(BasePermission.class);
        Permission permission = permissionFactory.buildFromMask(mask);
        List<Permission> permissions = Arrays.asList(permission);

        try{
            boolean authorized = acl.isGranted(permissions,sids,false);
            AuthorizationResponse response = new AuthorizationResponse(authorized, "Requested authorization granted");
            return ResponseEntity.ok().body(response);
        }
        catch (NotFoundException e){
            AuthorizationResponse response = new AuthorizationResponse(false,"Requested authorization is not granted ");
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping(value = "/get-authorization", params = {"classname", "principal","mask"})
    public ResponseEntity<AuthorizationResponse> checkClassAuthorization (
            @RequestParam String classname,
            @RequestParam String principal,
            @RequestParam Integer mask
    ){
        AclSid sid = sidRepo.findBySid(principal);

        if(sid == null){
            HttpHeaders notFoundHeader = new HttpHeaders();
            notFoundHeader.add("message","Could not find SID for principal="+principal);
            return ResponseEntity.notFound().headers(notFoundHeader).build();
        }

        AclClass aclClass = classRepo.findByClassname(classname.toUpperCase());

        if(aclClass == null){
            HttpHeaders notFoundHeader = new HttpHeaders();
            notFoundHeader.add("message","Could not find AclClass for classname="+classname);
            return ResponseEntity.notFound().headers(notFoundHeader).build();
        }

        AclClassPermission classPermission = classPermissionRepo.findByAclClassAndSidAndMask(aclClass.getId(), sid.getId(), mask);

        AuthorizationResponse response = new AuthorizationResponse();
        if(classPermission==null){
            response.setAuthorized(false).setMessage("User is not granted requested authorization");
        }else {
            response.setAuthorized(true).setMessage("User is granted requested authorization");
        }
        return ResponseEntity.ok(response);
    }
}
