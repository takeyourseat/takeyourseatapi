package com.stefanini.internship.authorizationserver.dao;

import javax.persistence.*;

@Entity
@Table(name = "owa_grant",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"object_id", "permission", "sid_id"}))
public class OwaGrant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private OwaObject object;

    @ManyToOne(fetch = FetchType.LAZY)
    private OwaSid sid;

    @Column
    private int permission;

    //region (), (*) constructors

    public OwaGrant() {}

    public OwaGrant(OwaObject object, OwaSid sid, int permission) {
        this.object = object;
        this.sid = sid;
        this.permission = permission;
    }
    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public OwaGrant setId(Long id) {
        this.id = id;
        return this;
    }

    public OwaObject getObject() {
        return object;
    }

    public OwaGrant setObject(OwaObject object) {
        this.object = object;
        return this;
    }

    public OwaSid getSid() {
        return sid;
    }

    public OwaGrant setSid(OwaSid sid) {
        this.sid = sid;
        return this;
    }

    public int getPermission() {
        return permission;
    }

    public OwaGrant setPermission(int permission) {
        this.permission = permission;
        return this;
    }

    //endregion

}