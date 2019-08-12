package com.stefanini.internship.usermanagement.authentication;

import com.stefanini.internship.usermanagement.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityListeners;
import javax.persistence.PostPersist;

import static com.stefanini.internship.usermanagement.authentication.AuthenticationUtils.AUTHENTICATION_URI;

@EntityListeners(User.class)
@Component
public class UserAuthenticationListener {
    private final RestTemplate restTemplate;

    public UserAuthenticationListener(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostPersist
    private void registerUserToAuthentication(User user){
        HttpHeaders authorizationHeader = AuthenticationUtils.getAuthorizationHeader();
        HttpEntity<User> body = new HttpEntity<>(user, authorizationHeader);
        restTemplate.exchange(AUTHENTICATION_URI+"users", HttpMethod.POST,body,Void.TYPE);
    }

}
