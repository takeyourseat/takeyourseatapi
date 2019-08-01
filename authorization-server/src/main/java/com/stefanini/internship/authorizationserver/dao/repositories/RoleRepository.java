package com.stefanini.internship.authorizationserver.dao.repositories;

import com.stefanini.internship.authorizationserver.dao.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNameIgnoreCaseAndEnabledIsTrue(String name);
    List<Role> findAllByEnabledIsTrue();
    boolean existsByNameIgnoreCase(String name);
   }
