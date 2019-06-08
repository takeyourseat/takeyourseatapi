package com.stefanini.internship.authorizationserver.dao.repositories;

import com.stefanini.internship.authorizationserver.dao.OwaClass;
import com.stefanini.internship.authorizationserver.dao.OwaObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwaObjectRepository extends JpaRepository<OwaObject, Long> {
    OwaObject findByOwaClassAndIdentifier(OwaClass owaClass, Long identifier);
}
