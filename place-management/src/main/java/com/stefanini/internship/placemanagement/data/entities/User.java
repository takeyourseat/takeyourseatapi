package com.stefanini.internship.placemanagement.data.entities;

import javax.persistence.*;
import com.stefanini.internship.placemanagement.data.Identifiable;

@Entity
@Table(name = "users")
public class User implements Identifiable{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "jobtitle")
    private String jobTitle;

    @Column(name = "profileimage")
    private String profileImage;

    @Column(name = "manager_username")
    private String managerUsername;

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

    public String getManagerUsername() {
        return managerUsername;
    }

    public User setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
        return this;
    }
    //endregion

    //region 2 Constructors

    public User() {
    }

    public User(Long id, String username, String firstName, String lastName, String jobTitle, String profileImage, String managerUsername) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.profileImage = profileImage;
        this.managerUsername = managerUsername;
    }
    //endregion

}
