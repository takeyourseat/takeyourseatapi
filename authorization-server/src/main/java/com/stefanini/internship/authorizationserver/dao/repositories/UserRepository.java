package com.stefanini.internship.authorizationserver.dao.repositories;

import com.stefanini.internship.authorizationserver.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByRoleName(String role);
}
