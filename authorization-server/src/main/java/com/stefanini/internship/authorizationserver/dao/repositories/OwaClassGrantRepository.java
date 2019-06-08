package com.stefanini.internship.authorizationserver.dao.repositories;

import com.stefanini.internship.authorizationserver.dao.OwaClass;
import com.stefanini.internship.authorizationserver.dao.OwaClassGrant;
import com.stefanini.internship.authorizationserver.dao.OwaSid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwaClassGrantRepository extends JpaRepository<OwaClassGrant, Long> {
    boolean existsByOwaClassAndSidAndPermission(OwaClass owaClass, OwaSid sid, int permission);
}
