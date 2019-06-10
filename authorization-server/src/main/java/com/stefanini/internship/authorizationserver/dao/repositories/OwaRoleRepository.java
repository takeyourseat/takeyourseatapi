package com.stefanini.internship.authorizationserver.dao.repositories;

import com.stefanini.internship.authorizationserver.dao.OwaRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwaRoleRepository extends JpaRepository<OwaRole, Long> {
    OwaRole findByName(String name);
}
