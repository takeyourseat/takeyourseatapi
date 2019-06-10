package com.stefanini.internship.authorizationserver.dao.classes;

import com.stefanini.internship.authorizationserver.dao.OwaRole;

public class User  {

    private Long id;

    private String password;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String profileImage;
    private User manager;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private OwaRole role;

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

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public User setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public User setProfileImage(String profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    public User getManager() {
        return manager;
    }

    public User setManager(User manager) {
        this.manager = manager;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public User setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public User setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
        return this;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public User setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public User setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public OwaRole getRole() {
        return role;
    }

    public User setRole(OwaRole role) {
        this.role = role;
        return this;
    }

    //endregion

    //region 2 Constructors

    public User(){}

    public User(Long id, String password, String email, String username, String firstName, String lastName, String jobTitle, String profileImage, User manager, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, OwaRole role) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.profileImage = profileImage;
        this.manager = manager;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.role = role;
    }

    //endregion

}