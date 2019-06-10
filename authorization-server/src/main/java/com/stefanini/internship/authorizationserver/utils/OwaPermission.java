package com.stefanini.internship.authorizationserver.utils;

import org.springframework.security.acls.model.Permission;

public class OwaPermission implements Permission {
    private int mask;

    //region Public static final defaults
    public static final int CREATE_MASK = 0;
    public static final OwaPermission CREATE = new OwaPermission(CREATE_MASK);

    public static final int READ_MASK = 1;
    public static final OwaPermission READ = new OwaPermission(READ_MASK);

    public static final int WRITE_MASK = 2;
    public static final OwaPermission WRITE = new OwaPermission(WRITE_MASK);

    public static final int DELETE_MASK = 3;
    public static final OwaPermission DELETE = new OwaPermission(DELETE_MASK);

    public static final int ADMINISTER_MASK = 4;
    public static final OwaPermission ADMINISTER = new OwaPermission(ADMINISTER_MASK);
    //endregion

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
