package com.stefanini.internship.usermanagement.services;

import com.stefanini.internship.usermanagement.authorization.AuthorizationUtils;
import com.stefanini.internship.usermanagement.dao.Role;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RoleService {
    public static final String ROLE_SERVICE_URL = "http://localhost:8086/";

    private final RestTemplate restTemplate;

    public RoleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Role> getAllRoles() {
        HttpHeaders oAuthToken = AuthorizationUtils.getAuthorizationHeader();

        HttpEntity<Void> request = new HttpEntity<>(oAuthToken);

//        ResponseEntity response = restTemplate.exchange(ROLE_SERVICE_URL + "api/v01/roles", HttpMethod.GET, request, List.class);
        ResponseEntity response = restTemplate.exchange(ROLE_SERVICE_URL + "api/v01/roles", HttpMethod.GET, request, new ParameterizedTypeReference<List<Role>>() {
        });
        return (List<Role>) response.getBody();

    }
}
