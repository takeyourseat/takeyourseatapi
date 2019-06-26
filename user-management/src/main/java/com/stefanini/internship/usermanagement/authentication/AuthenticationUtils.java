package com.stefanini.internship.usermanagement.authentication;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class AuthenticationUtils {

    public static HttpHeaders getAuthorizationHeader(){
        HttpHeaders headers = new HttpHeaders();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth instanceof OAuth2Authentication){
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
            String accessToken = details.getTokenValue();
            headers.setBearerAuth(accessToken);
        }
        return headers;
    }

}
