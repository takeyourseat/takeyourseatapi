package com.stefanini.internship.usermanagement.authorization;

import com.stefanini.internship.usermanagement.authentication.AuthenticationUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.stefanini.internship.usermanagement.authorization.AuthorizationUtils.AUTHORIZATION_API;


@Service("AuthorizationService")
public class AuthorizationService {


    private RestTemplate restTemplate;

    public AuthorizationService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public boolean hasPermission(String classname, String permission){
        if ((classname == null) || (permission == null)) {
            return false;
        }

        String requestURI = AUTHORIZATION_API+"authorizations/datatypes/"+classname+"/permissions/"+permission;

        HttpEntity<Void> request = new HttpEntity<>(AuthenticationUtils.getAuthorizationHeader());
        ResponseEntity<AuthorizationResponse> authorization = restTemplate.exchange(requestURI, HttpMethod.GET, request, AuthorizationResponse.class);

        return authorization.getBody().authorized;
    }
}
