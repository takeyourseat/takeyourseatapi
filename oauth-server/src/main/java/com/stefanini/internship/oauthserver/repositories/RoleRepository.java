package com.stefanini.internship.oauthserver.repositories;

import com.stefanini.internship.oauthserver.dao.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
