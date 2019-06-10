package com.stefanini.internship.authorizationserver.dao;

import javax.persistence.*;

@Entity
@Table(name = "owa_class")
public class OwaClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(name = "class", nullable = false, unique = true)
    String classname;

    //region (), (*) constructors

    public OwaClass() {}

    public OwaClass(String classname) {
        this.classname = classname;
    }

    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public OwaClass setId(Long id) {
        this.id = id;
        return this;
    }

    public String getClassname() {
        return classname;
    }

    public OwaClass setClassname(String classname) {
        this.classname = classname;
        return this;
    }


    //endregion
}
