package com.stefanini.internship.placemanagement.services;

import com.stefanini.internship.placemanagement.authorization.AuthorizationUtils;
import com.stefanini.internship.placemanagement.data.dto.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    public static final String USER_SERVICE_URL = "http://user-management:8085/";

    private final RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getAllUsers() {
        HttpHeaders oAuthToken = AuthorizationUtils.getAuthorizationHeader();

        HttpEntity<Void> request = new HttpEntity<>(oAuthToken);

        ResponseEntity response = restTemplate.exchange(USER_SERVICE_URL + "users", HttpMethod.GET, request, List.class);
        return (List<User>) response.getBody();
    }

    public User getUserByUsername(String username) {
        HttpHeaders oAuthToken = AuthorizationUtils.getAuthorizationHeader();

        HttpEntity<Void> request = new HttpEntity<>(oAuthToken);

        ResponseEntity<User> response = restTemplate.exchange(USER_SERVICE_URL + "users/{username}", HttpMethod.GET, request, User.class, username);

        return response.getBody();
    }

    public List<User> getUsersByManagerUsername(String manager) {
        HttpHeaders oAuthToken = AuthorizationUtils.getAuthorizationHeader();

        HttpEntity<Void> request = new HttpEntity<>(oAuthToken);

        ResponseEntity response = restTemplate.exchange(USER_SERVICE_URL + "users?manager=" + manager, HttpMethod.GET, request, List.class);

        return (List<User>) response.getBody();
    }
}
