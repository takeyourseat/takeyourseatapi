package com.stefanini.internship.authorizationserver.dao.repositories;

import com.stefanini.internship.authorizationserver.dao.AclClassPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AclClassPermissionRepository  extends JpaRepository<AclClassPermission, Long> {
    AclClassPermission findByAclClassAndSidAndMask(long aclClass, long sid, int mask);
}
