package com.stefanini.internship.authorizationserver;

import org.springframework.security.acls.domain.BasePermission;

public class PublicBasePermission extends BasePermission {
    public PublicBasePermission(int mask) {
        super(mask);
    }

    public PublicBasePermission(int mask, char code) {
        super(mask, code);
    }
}
