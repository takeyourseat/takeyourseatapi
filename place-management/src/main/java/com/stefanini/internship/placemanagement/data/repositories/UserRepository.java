package com.stefanini.internship.placemanagement.data.repositories;

import com.stefanini.internship.placemanagement.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long id);

    List<User> getUsersByManagerUsername(String managerUsername);
}
