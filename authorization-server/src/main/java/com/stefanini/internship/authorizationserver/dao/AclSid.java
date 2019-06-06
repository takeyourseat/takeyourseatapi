package com.stefanini.internship.authorizationserver.dao;

import javax.persistence.*;

@Entity
@Table(name = "acl_sid")
public class AclSid {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "principal")
    boolean principal;

    @Column(name = "sid", nullable = false)
    String sid;

    //region (), (*) constructors

    public AclSid() {}

    public AclSid(boolean principal, String sid) {
        this.principal = principal;
        this.sid = sid;
    }

    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public AclSid setId(Long id) {
        this.id = id;
        return this;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public AclSid setPrincipal(boolean principal) {
        this.principal = principal;
        return this;
    }

    public String getSid() {
        return sid;
    }

    public AclSid setSid(String sid) {
        this.sid = sid;
        return this;
    }


    //endregion
}