package com.stefanini.internship.authorizationserver.dto;

public class RoleDto {
    private String name;

    public RoleDto() {
    }

    public RoleDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public RoleDto setName(String name) {
        this.name = name;
        return this;
    }
}
