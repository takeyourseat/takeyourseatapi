package com.stefanini.internship.usermanagement.dao.repository;

import com.stefanini.internship.usermanagement.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFNameContainingOrLNameContaining(String firstArg, String secondArg);

    List<User> findUsersByManagerUsername(String firstArg);

    User findUserByUsername(String firstArg);

}

