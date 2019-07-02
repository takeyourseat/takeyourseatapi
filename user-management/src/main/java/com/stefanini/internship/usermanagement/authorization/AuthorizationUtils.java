package com.stefanini.internship.usermanagement.authorization;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class AuthorizationUtils {
    public static final String AUTHORIZATION_API = "http://localhost:8086/api/v01/";

}
