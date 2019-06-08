package com.stefanini.internship.authorizationserver.dao.repositories;

import com.stefanini.internship.authorizationserver.dao.OwaGrant;
import com.stefanini.internship.authorizationserver.dao.OwaObject;
import com.stefanini.internship.authorizationserver.dao.OwaSid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwaGrantRepository extends JpaRepository<OwaGrant, Long> {
    boolean existsByObjectAndSidAndPermission(OwaObject object, OwaSid sid, int permission);
}
