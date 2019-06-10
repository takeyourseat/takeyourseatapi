package com.stefanini.internship.authorizationserver.dao;

import javax.persistence.*;

@Entity
@Table(name = "owa_class_grant",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"owa_class_id", "permission", "role_id"}))
public class OwaClassGrant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private OwaClass owaClass;

    @Column(name = "permission")
    private int permission;

    @ManyToOne(fetch = FetchType.LAZY)
    private OwaRole role;

    //region (), (*) constructors

    public OwaClassGrant(OwaClass owaClass, int permission, OwaRole sid) {
        this.owaClass = owaClass;
        this.permission = permission;
        this.role = sid;
    }

    public OwaClassGrant() {}

    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public OwaClassGrant setId(Long id) {
        this.id = id;
        return this;
    }

    public OwaClass getOwaClass() {
        return owaClass;
    }

    public OwaClassGrant setOwaClass(OwaClass owaClass) {
        this.owaClass = owaClass;
        return this;
    }

    public int getPermission() {
        return permission;
    }

    public OwaClassGrant setPermission(int permission) {
        this.permission = permission;
        return this;
    }

    public OwaRole getRole() {
        return role;
    }

    public OwaClassGrant setRole(OwaRole role) {
        this.role = role;
        return this;
    }

    //endregion
}