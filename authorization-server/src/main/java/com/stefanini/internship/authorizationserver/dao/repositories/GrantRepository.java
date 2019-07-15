package com.stefanini.internship.authorizationserver.dao.repositories;

import com.stefanini.internship.authorizationserver.dao.DataType;
import com.stefanini.internship.authorizationserver.dao.Grant;
import com.stefanini.internship.authorizationserver.dao.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrantRepository extends JpaRepository<Grant,Long> {
    Grant findByDataTypeAndRole(DataType dataType, Role role);
    List<Grant> getByRoleNameIgnoreCase(String roleName);
}
