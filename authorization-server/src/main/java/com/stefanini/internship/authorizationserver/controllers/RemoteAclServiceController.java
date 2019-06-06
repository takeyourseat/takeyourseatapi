package com.stefanini.internship.authorizationserver.controllers;

import com.stefanini.internship.authorizationserver.utils.AuthorizationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.jdbc.JdbcAclService;
import org.springframework.security.acls.domain.*;
import org.springframework.security.acls.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RemoteAclServiceController {

    @Autowired
    JdbcAclService jdbcAclService;

    @GetMapping(value = "/get-authorization", params = {"classname", "principal","identifier","mask"})
    public ResponseEntity<AuthorizationResponse> chechAuthorization (
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


}
