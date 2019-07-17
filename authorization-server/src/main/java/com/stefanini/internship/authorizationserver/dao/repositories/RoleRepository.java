package com.stefanini.internship.authorizationserver.dao.repositories;

import com.stefanini.internship.authorizationserver.dao.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNameIgnoreCaseAndEnabledIsTrue(String name);
    List<Role> findAllByEnabledIsTrue();
    boolean existsByNameIgnoreCase(String name);
}
