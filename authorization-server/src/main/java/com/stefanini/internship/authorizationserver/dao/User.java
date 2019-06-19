package com.stefanini.internship.authorizationserver.dao;



import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @ManyToOne(optional = false)
    private Role role;

    //region (), (*) constructors

    public User() {}

    public User(Long id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }


    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    //endregion
}