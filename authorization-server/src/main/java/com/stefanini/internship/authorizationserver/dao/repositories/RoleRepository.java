package com.stefanini.internship.authorizationserver.dao.repositories;

import com.stefanini.internship.authorizationserver.dao.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}
