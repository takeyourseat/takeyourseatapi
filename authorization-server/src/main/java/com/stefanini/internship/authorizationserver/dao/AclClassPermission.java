package com.stefanini.internship.authorizationserver.dao;

import javax.persistence.*;

@Entity
@Table(name = "acl_class_permission")
public class AclClassPermission {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "acl_class_id")
    private long aclClass;

    @Column(name = "sid_id")
    private long sid;

    @Column(name = "mask")
    private int mask;

    //region (), (*) constructors

    public AclClassPermission(Long id, int aclClass, int sid, int mask) {
        this.id = id;
        this.aclClass = aclClass;
        this.sid = sid;
        this.mask = mask;
    }

    public AclClassPermission() {}

    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public AclClassPermission setId(Long id) {
        this.id = id;
        return this;
    }

    public long getAclClass() {
        return aclClass;
    }

    public AclClassPermission setAclClass(long aclClass) {
        this.aclClass = aclClass;
        return this;
    }

    public long getSid() {
        return sid;
    }

    public AclClassPermission setSid(long sid) {
        this.sid = sid;
        return this;
    }

    public int getMask() {
        return mask;
    }

    public AclClassPermission setMask(int mask) {
        this.mask = mask;
        return this;
    }


    //endregion
}