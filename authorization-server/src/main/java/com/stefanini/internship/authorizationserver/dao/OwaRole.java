package com.stefanini.internship.authorizationserver.dao;

import javax.persistence.*;

@Entity
@Table(name = "owa_role")
public class OwaRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public OwaRole setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OwaRole setName(String name) {
        this.name = name;
        return this;
    }

    //endregion

    //region (), (*) constructors

    public OwaRole(String name) {
        this.name = name;
    }

    public OwaRole() {
    }

//endregion
}