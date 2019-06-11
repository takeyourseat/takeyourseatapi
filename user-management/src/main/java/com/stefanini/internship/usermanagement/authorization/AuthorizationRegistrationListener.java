package com.stefanini.internship.usermanagement.authorization;

import org.springframework.web.client.RestTemplate;

import javax.persistence.PostPersist;

import static com.stefanini.internship.usermanagement.authorization.AuthorizationConstants.AUTHORIZATION_API;

public class AuthorizationRegistrationListener {

    private RestTemplate restTemplate = new RestTemplate();

    public AuthorizationRegistrationListener(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostPersist
    public void registerObjectToAuthorizationServer(final Object persistedEntity) {
       Object weird =  restTemplate.postForObject(AUTHORIZATION_API+"objects/users",persistedEntity, Object.class);
    }
}
