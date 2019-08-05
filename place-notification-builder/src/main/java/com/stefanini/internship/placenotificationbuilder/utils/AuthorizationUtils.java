package com.stefanini.internship.placenotificationbuilder.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class AuthorizationUtils {

    public static String getAuthToken(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth instanceof OAuth2Authentication){
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
            String accessToken = details.getTokenValue();
            return "Bearer " + accessToken;
        }
        return null;
    }

}
