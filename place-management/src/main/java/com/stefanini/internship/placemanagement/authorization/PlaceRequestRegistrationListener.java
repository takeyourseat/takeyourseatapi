package com.stefanini.internship.placemanagement.authorization;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import javax.persistence.PostPersist;

import static com.stefanini.internship.placemanagement.authorization.AuthorizationUtils.AUTHORIZATION_API;

public class PlaceRequestRegistrationListener {

    private RestTemplate restTemplate;

    public PlaceRequestRegistrationListener(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostPersist
    public void registerObjectToAuthorizationServer(final Object persistedEntity) {
        HttpEntity<Object> request = new HttpEntity<>(persistedEntity, AuthorizationUtils.getAuthorizationHeader());
        restTemplate.exchange(AUTHORIZATION_API+"objects/place-requests", HttpMethod.POST, request, Object.class);
    }
}
