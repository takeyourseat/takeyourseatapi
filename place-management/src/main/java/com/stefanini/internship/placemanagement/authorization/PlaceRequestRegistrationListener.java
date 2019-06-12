package com.stefanini.internship.placemanagement.authorization;

import org.springframework.web.client.RestTemplate;

import javax.persistence.PostPersist;

import static com.stefanini.internship.placemanagement.authorization.AuthorizationConstants.AUTHORIZATION_API;

public class PlaceRequestRegistrationListener {

    private RestTemplate restTemplate;

    public PlaceRequestRegistrationListener(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostPersist
    public void registerObjectToAuthorizationServer(final Object persistedEntity) {
       restTemplate.postForEntity(AUTHORIZATION_API+"objects/place-requests",persistedEntity,Object.class);
    }
}
