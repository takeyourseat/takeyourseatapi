package com.stefanini.internship.authorizationserver.services;

import com.stefanini.internship.authorizationserver.dao.User;
import com.stefanini.internship.authorizationserver.utils.AuthenticationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private final RestTemplate restTemplate;
    public static final String USER_MANAGEMENT_URL = "http://localhost:8085/";

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public User getUserManager(String username){

        HttpHeaders authorizationHeader = AuthenticationUtils.getAuthorizationHeader();
        HttpEntity<Void> request = new HttpEntity<>(authorizationHeader);

        ResponseEntity<User> response = restTemplate.exchange(USER_MANAGEMENT_URL+"getUserManager?username="+username, HttpMethod.GET, request, User.class);

        return  response.getBody();

    }
}
