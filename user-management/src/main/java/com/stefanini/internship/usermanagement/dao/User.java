package com.stefanini.internship.usermanagement.dao;

import com.stefanini.internship.usermanagement.authentication.UserAuthenticationListener;
import com.stefanini.internship.usermanagement.authorization.UserAuthorizationListener;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Entity
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

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    @Transient
    private String password;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(String fName, String lName, String email, String username, String jobTitle, User manager, @NotNull Role role) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.username = username;
        this.jobTitle = jobTitle;
        this.manager = manager;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
}