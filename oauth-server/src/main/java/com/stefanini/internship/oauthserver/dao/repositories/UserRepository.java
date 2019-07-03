package com.stefanini.internship.oauthserver.dao.repositories;

import com.stefanini.internship.oauthserver.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByUsername(String name);

    boolean existsById(Long id);

    boolean existsByUsername(String username);

}
