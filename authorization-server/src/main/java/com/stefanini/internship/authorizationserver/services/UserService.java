package com.stefanini.internship.authorizationserver.services;

import com.stefanini.internship.authorizationserver.dao.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User getUserManager(String userId){
        return new User(3L,"manager",null, true);
    }
}
