package com.stefanini.internship.usermanagement.dao.repository;

import com.stefanini.internship.usermanagement.dao.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
