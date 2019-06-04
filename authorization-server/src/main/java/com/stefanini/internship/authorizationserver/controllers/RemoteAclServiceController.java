package com.stefanini.internship.authorizationserver.controllers;

import com.stefanini.internship.authorizationserver.AuthorizationResponse;
import com.stefanini.internship.authorizationserver.PublicBasePermission;
import com.stefanini.internship.authorizationserver.foo.AclJacksonParsableImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.jdbc.JdbcAclService;
import org.springframework.security.acls.model.*;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            @RequestParam List<Integer> mask
    ){
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(classname, identifier);
        List<Sid> sids = new ArrayList<>(1);
        sids.add(new PrincipalSid(principal));

        Acl acl = jdbcAclService.readAclById(objectIdentity, sids);

        List<Permission> permission = mask
                .stream()
                .map(PublicBasePermission::new)
                .collect(Collectors.toList());

        try{
            boolean authorized = acl.isGranted(permission,sids,false);
            AuthorizationResponse response = new AuthorizationResponse().setAuthorized(authorized);
            return ResponseEntity.ok().body(response);
        }
        catch (NotFoundException e){
            AuthorizationResponse response = new AuthorizationResponse(false,"One of requested authorizations is not granted ");
            return ResponseEntity.ok(response);
        }




    }


}
