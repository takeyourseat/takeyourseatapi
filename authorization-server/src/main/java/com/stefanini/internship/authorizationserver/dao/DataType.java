package com.stefanini.internship.authorizationserver.dao;

import javax.persistence.*;

@Entity
@Table(name = "data_type")
public class DataType {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    //region (), (*) constructors

    public DataType() {}

    public DataType(String classname) {
        this.name = classname;
    }

    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public DataType setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DataType setName(String name) {
        this.name = name;
        return this;
    }


    //endregion
}
