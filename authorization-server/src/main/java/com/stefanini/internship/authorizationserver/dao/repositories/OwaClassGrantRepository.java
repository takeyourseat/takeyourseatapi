package com.stefanini.internship.authorizationserver.dao.repositories;

import com.stefanini.internship.authorizationserver.dao.OwaClass;
import com.stefanini.internship.authorizationserver.dao.OwaClassGrant;
import com.stefanini.internship.authorizationserver.dao.OwaRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwaClassGrantRepository extends JpaRepository<OwaClassGrant, Long> {
    boolean existsByOwaClassAndRoleAndPermission(OwaClass owaClass, OwaRole sid, int permission);
}
