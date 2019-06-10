package com.stefanini.internship.authorizationserver.dao;

import javax.persistence.*;

@Entity
@Table(name = "owa_sid")
public class OwaSid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "principal")
    boolean principal;

    @Column(name = "sid", nullable = false)
    String sid;

    //region (), (*) constructors

    public OwaSid() {}

    public OwaSid(boolean principal, String sid) {
        this.principal = principal;
        this.sid = sid;
    }

    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public OwaSid setId(Long id) {
        this.id = id;
        return this;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public OwaSid setPrincipal(boolean principal) {
        this.principal = principal;
        return this;
    }

    public String getSid() {
        return sid;
    }

    public OwaSid setSid(String sid) {
        this.sid = sid;
        return this;
    }


    //endregion
}