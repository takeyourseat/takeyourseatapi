package com.stefanini.internship.usermanagement.dao.repository;

import com.stefanini.internship.usermanagement.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
