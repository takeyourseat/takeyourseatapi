package com.stefanini.internship.authorizationserver.dao;

import javax.persistence.*;

@Entity
@Table(name = "acl_class")
public class AclClass {
    @GeneratedValue
    @Id
    Long id;

    @Column(name = "class", nullable = false, unique = true)
    String classname;

    //region (), (*) constructors

    public AclClass() {}

    public AclClass(String classname) {
        this.classname = classname;
    }

    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public AclClass setId(Long id) {
        this.id = id;
        return this;
    }

    public String getClassname() {
        return classname;
    }

    public AclClass setClassname(String classname) {
        this.classname = classname;
        return this;
    }


    //endregion
}
