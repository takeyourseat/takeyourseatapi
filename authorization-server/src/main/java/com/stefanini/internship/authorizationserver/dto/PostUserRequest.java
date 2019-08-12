package com.stefanini.internship.authorizationserver.dto;

import com.stefanini.internship.authorizationserver.dao.Role;

public class PostUserRequest {

    private Long id;

    private String password;
    private String email;
    private String username;
    private String fName;
    private String lName;
    private String jobTitle;
    private String profileImage;
    private PostUserRequest manager;
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

    public String getEmail() {
        return email;
    }

    public PostUserRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getfName() {
        return fName;
    }

    public PostUserRequest setfName(String fName) {
        this.fName = fName;
        return this;
    }

    public String getlName() {
        return lName;
    }

    public PostUserRequest setlName(String lName) {
        this.lName = lName;
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

    public PostUserRequest(Long id, String password, String email, String username, String firstName, String lastName, String jobTitle, String profileImage, PostUserRequest manager, Role role) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.username = username;
        this.fName = firstName;
        this.lName = lastName;
        this.jobTitle = jobTitle;
        this.profileImage = profileImage;
        this.role = role;
        this.manager = manager;

    }

    //endregion

}
