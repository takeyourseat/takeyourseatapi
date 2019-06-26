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

    @Column(name="enabled")
    private boolean enabled;

    //region (), (*) constructors

    public User() {}

    public User(Long id, String username, Role role, boolean enabled) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.enabled = enabled;
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

    public boolean isEnabled() {
        return enabled;
    }

    public User setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }


    //endregion
}