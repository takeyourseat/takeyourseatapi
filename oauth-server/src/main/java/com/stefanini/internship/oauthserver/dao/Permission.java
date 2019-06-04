package com.stefanini.internship.oauthserver.dao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name = "name",nullable = false, unique = true)
    private String name;

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public Permission setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Permission setName(String name) {
        this.name = name;
        return this;
    }


    //endregion

    //region 2 Constructors

    public Permission(){}

    public Permission(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    //endregion

}