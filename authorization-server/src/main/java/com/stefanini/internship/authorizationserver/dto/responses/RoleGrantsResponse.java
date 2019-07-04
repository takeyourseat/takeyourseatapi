package com.stefanini.internship.authorizationserver.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class RoleGrantsResponse {
    private String role;
    private Map<String, Integer> grants;

}
