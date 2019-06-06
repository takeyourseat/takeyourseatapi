package com.stefanini.internship.authorizationserver.dao.repositories;

import com.stefanini.internship.authorizationserver.dao.AclSid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AclSidRepository extends JpaRepository<AclSid, Long> {
    AclSid findBySid(String sid);
}
