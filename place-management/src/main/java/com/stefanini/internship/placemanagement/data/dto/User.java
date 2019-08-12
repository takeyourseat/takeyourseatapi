package com.stefanini.internship.placemanagement.data.dto;

import com.stefanini.internship.placemanagement.data.Identifiable;


public class User implements Identifiable {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String jobTitle;

    private String profileImage;

    private User manager;

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


    //endregion

    //region 2 Constructors

    public User() {
    }

    public User(Long id, String username, String firstName, String lastName, String jobTitle, String profileImage, User manager) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.profileImage = profileImage;
        this.manager = manager;
    }

    //endregion
}
