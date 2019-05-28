package com.stefanini.internship.oauthserver.repositories;

import com.stefanini.internship.oauthserver.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String name);

}
