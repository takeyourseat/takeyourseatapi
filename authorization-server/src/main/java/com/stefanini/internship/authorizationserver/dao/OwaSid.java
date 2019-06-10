package com.stefanini.internship.authorizationserver.dao;

import javax.persistence.*;

@Entity
@Table(name = "owa_sid")
public class OwaSid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sid", nullable = false, unique = true)
    private String sid;

    @ManyToOne(optional = false)
    private OwaRole role;

    //region (), (*) constructors

    public OwaSid() {}

    public OwaSid(String sid, OwaRole role) {
        this.sid = sid;
        this.role = role;
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


    public String getSid() {
        return sid;
    }

    public OwaSid setSid(String sid) {
        this.sid = sid;
        return this;
    }

    public OwaRole getRole() {
        return role;
    }

    public OwaSid setRole(OwaRole role) {
        this.role = role;
        return this;
    }

    //endregion
}