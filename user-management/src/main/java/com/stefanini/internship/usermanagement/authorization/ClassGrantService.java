package com.stefanini.internship.usermanagement.authorization;

import com.stefanini.internship.usermanagement.authentication.AuthenticationUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.stefanini.internship.usermanagement.authorization.AuthorizationConstants.AUTHORIZATION_API;

@Component("ClassGrantService")
public class ClassGrantService {


    private RestTemplate restTemplate;

    public ClassGrantService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public boolean hasClassGrant(String classname, String permission){
        if ((classname == null) || (permission == null)) {
            return false;
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(AUTHORIZATION_API+"authorize")
                .queryParam("principal", auth.getName())
                .queryParam("permission", permission)
                .queryParam("classname", classname);

        HttpEntity<String> request = new HttpEntity<>(AuthenticationUtils.getAuthorizationHeader());
        ResponseEntity<AuthorizationResponse> authorization = restTemplate.exchange(builder.build().toString(), HttpMethod.GET, request, AuthorizationResponse.class);
        return authorization.getBody().authorized;
    }
}
