package com.stefanini.internship.usermanagement.authorization;

public class AuthorizationResponse {
    boolean authorized;

    String message;

    //region (), (*) constructors

    public AuthorizationResponse(boolean authorized, String message) {
        this.authorized = authorized;
        this.message = message;
    }

    public AuthorizationResponse() {}

    //endregion

    //region Getters and Setters

    public boolean isAuthorized() {
        return authorized;
    }

    public AuthorizationResponse setAuthorized(boolean authorized) {
        this.authorized = authorized;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AuthorizationResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    //endregion
}
