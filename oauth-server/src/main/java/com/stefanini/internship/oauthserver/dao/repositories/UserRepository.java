package com.stefanini.internship.oauthserver.dao.repositories;

import com.stefanini.internship.oauthserver.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String name);

    boolean existsById(Long id);

    boolean existsByUsername(String username);

}
