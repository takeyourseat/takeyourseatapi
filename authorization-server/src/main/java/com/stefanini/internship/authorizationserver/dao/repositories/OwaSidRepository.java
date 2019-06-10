package com.stefanini.internship.authorizationserver.dao.repositories;

import com.stefanini.internship.authorizationserver.dao.OwaSid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwaSidRepository extends JpaRepository<OwaSid, Long> {
    OwaSid findBySid(String sid);
}
