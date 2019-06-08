package com.stefanini.internship.authorizationserver.utils;

import org.springframework.security.acls.model.Permission;

public class OwaPermission implements Permission {
    private int mask;

    public static final OwaPermission CREATE = new OwaPermission(0);
    public static final OwaPermission READ = new OwaPermission(1);
    public static final OwaPermission WRITE = new OwaPermission(2);
    public static final OwaPermission DELETE = new OwaPermission(3);
    public static final OwaPermission ADMINISTER = new OwaPermission(4);

    private OwaPermission(int mask){
        this.mask = mask;
    }

    @Override
    public int getMask() {
        return mask;
    }

    @Override
    public String getPattern() {
        return "";
    }

}
