package com.stefanini.internship.authorizationserver.dao.repositories;

import com.stefanini.internship.authorizationserver.dao.OwaClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwaClassRepository extends JpaRepository<OwaClass, Long> {
    OwaClass findByClassname(String classname);
}
