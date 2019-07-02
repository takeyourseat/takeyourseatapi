package com.stefanini.internship.usermanagement.authorization;


import com.stefanini.internship.usermanagement.authentication.AuthenticationUtils;
import com.stefanini.internship.usermanagement.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityListeners;
import javax.persistence.PostPersist;

import static com.stefanini.internship.usermanagement.authorization.AuthorizationUtils.AUTHORIZATION_API;

@EntityListeners(User.class)
@Component
public class UserAuthorizationListener {

    private final
    RestTemplate restTemplate;

    public UserAuthorizationListener(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostPersist
    private void registerUserAtAuthorizationServer(User user){
        HttpHeaders authorizationHeader = AuthenticationUtils.getAuthorizationHeader();
        HttpEntity<User> body = new HttpEntity<>(user, authorizationHeader);
        restTemplate.exchange(AUTHORIZATION_API+"users", HttpMethod.POST,body,Void.TYPE);
    }
}
