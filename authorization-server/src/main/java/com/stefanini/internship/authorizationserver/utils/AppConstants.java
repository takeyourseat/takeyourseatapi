package com.stefanini.internship.authorizationserver.utils;

import java.util.HashMap;
import java.util.Map;

public class AppConstants {
    public static final String API_ROOT_VERSION = "/api/v01/";


    private static final Map<String, Integer> DEFAULTS = new HashMap<>();
    static{
        DEFAULTS.put("read", 1);
        DEFAULTS.put("write", 2);
    }
    public static int getPermission(String name){
        return DEFAULTS.get(name);
    }

    private AppConstants(){}
}
