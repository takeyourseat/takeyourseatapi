package com.stefanini.internship.authorizationserver.dao;

import javax.persistence.*;

@Entity
@Table(name = "owa_object")
public class OwaObject {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private OwaClass owaClass;

    @Column(name = "identifier", nullable = false)
    private Long identifier;

    //region (), (*) Constructors

    public OwaObject(OwaClass owaClass, Long identifier) {
        this.owaClass = owaClass;
        this.identifier = identifier;
    }

    public OwaObject() {}

    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public OwaObject setId(Long id) {
        this.id = id;
        return this;
    }

    public OwaClass getOwaClass() {
        return owaClass;
    }

    public OwaObject setOwaClass(OwaClass owaClass) {
        this.owaClass = owaClass;
        return this;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public OwaObject setIdentifier(Long identifier) {
        this.identifier = identifier;
        return this;
    }

    //endregion


}