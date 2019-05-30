package com.stefanini.internship.placemanagement.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stefanini.internship.placemanagement.data.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
