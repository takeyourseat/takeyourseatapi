package com.stefanini.internship.authorizationserver.controllers;

import com.stefanini.internship.authorizationserver.AuthorizationResponse;
import com.stefanini.internship.authorizationserver.PublicBasePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.jdbc.JdbcAclService;
import org.springframework.security.acls.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

        Acl acl = jdbcAclService.readAclById(objectIdentity, sids);
        List<Sid> sids = Arrays.asList(new PrincipalSid(principal));


        try{
            boolean authorized = acl.isGranted(permission,sids,false);
            AuthorizationResponse response = new AuthorizationResponse(authorized, "Requested authorization granted");
            return ResponseEntity.ok().body(response);
        }
        catch (NotFoundException e){
            AuthorizationResponse response = new AuthorizationResponse(false,"Requested authorization is not granted ");
            return ResponseEntity.ok(response);
        }




    }


}
