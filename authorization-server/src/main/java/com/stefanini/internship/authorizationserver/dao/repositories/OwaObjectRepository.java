package com.stefanini.internship.authorizationserver.dao.repositories;

import com.stefanini.internship.authorizationserver.dao.OwaObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwaObjectRepository extends JpaRepository<OwaObject, Long> {
    OwaObject findByOwaClassClassnameAndIdentifier(String classname, Long identifier);
}
