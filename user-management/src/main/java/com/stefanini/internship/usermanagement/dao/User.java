package com.stefanini.internship.usermanagement.dao;

import com.stefanini.internship.usermanagement.authentication.UserAuthenticationListener;
import com.stefanini.internship.usermanagement.authorization.UserAuthorizationListener;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "users")
@EntityListeners({UserAuthorizationListener.class, UserAuthenticationListener.class})

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "first_name")
    private String fName;

    @Column(name = "last_name")
    private String lName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NaturalId
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "job_title")
    private String jobTitle;

    @ManyToOne(fetch = FetchType.EAGER)
    private User manager;

    @Transient
    private Role role;

    @Transient
    private String password;

    public User() {
    }

    public User(String fName, String lName, String email, String username, String jobTitle, User manager, Role role) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.username = username;
        this.jobTitle = jobTitle;
        this.manager = manager;
        this.role = role;
    }

    //region Getters and Setters
    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getfName() {
        return fName;
    }

    public User setfName(String fName) {
        this.fName = fName;
        return this;
    }

    public String getlName() {
        return lName;
    }

    public User setlName(String lName) {
        this.lName = lName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public User setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public User getManager() {
        return manager;
    }

    public User setManager(User manager) {
        this.manager = manager;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
    //endregion
}
