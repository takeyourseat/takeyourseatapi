package com.stefanini.internship.authorizationserver.dto;


import com.stefanini.internship.authorizationserver.dao.Role;

public class PostUserRequest {

    private Long id;

    private String password;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String profileImage;
    private PostUserRequest manager;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private Role role;

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public PostUserRequest setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public PostUserRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public PostUserRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public PostUserRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PostUserRequest setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PostUserRequest setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public PostUserRequest setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public PostUserRequest setProfileImage(String profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    public PostUserRequest getManager() {
        return manager;
    }

    public PostUserRequest setManager(PostUserRequest manager) {
        this.manager = manager;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public PostUserRequest setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public PostUserRequest setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
        return this;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public PostUserRequest setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public PostUserRequest setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public PostUserRequest setRole(Role role) {
        this.role = role;
        return this;
    }

    //endregion

    //region 2 Constructors

    public PostUserRequest(){}

    public PostUserRequest(Long id, String password, String email, String username, String firstName, String lastName, String jobTitle, String profileImage, PostUserRequest manager, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Role role) {
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