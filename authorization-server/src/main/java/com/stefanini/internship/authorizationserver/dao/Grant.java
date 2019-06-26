package com.stefanini.internship.authorizationserver.dao;

import javax.persistence.*;

@Entity
@Table(name = "role_permission",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"data_type_id", "permission", "role_id"}))
public class Grant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private DataType dataType;

    @Column(name = "permission")
    private int permission;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Role role;

    //region (), (*) constructors

    public Grant(DataType dataType, int permission, Role sid) {
        this.dataType = dataType;
        this.permission = permission;
        this.role = sid;
    }

    public Grant() {}

    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public Grant setId(Long id) {
        this.id = id;
        return this;
    }

    public DataType getDataType() {
        return dataType;
    }

    public Grant setDataType(DataType dataType) {
        this.dataType = dataType;
        return this;
    }

    public int getPermission() {
        return permission;
    }

    public Grant setPermission(int permission) {
        this.permission = permission;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public Grant setRole(Role role) {
        this.role = role;
        return this;
    }

    //endregion
}