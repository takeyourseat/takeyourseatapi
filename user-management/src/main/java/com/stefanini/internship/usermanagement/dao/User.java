package com.stefanini.internship.usermanagement.dao;

import com.stefanini.internship.usermanagement.authorization.AuthorizationRegistrationListener;
import org.hibernate.annotations.NaturalId;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "users")
@EntityListeners(AuthorizationRegistrationListener.class)
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(unique = true, nullable = false, name = "email")
    private String email;

    @NaturalId
    @Column(unique = true, name = "username", nullable = false)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "profile_image")
    private String profileImage;

    @ManyToOne(fetch = FetchType.LAZY)
    private User manager;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    //region Equals and Hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getEmail().equals(user.getEmail()) &&
                getUsername().equals(user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getUsername());
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

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }



    //endregion


    //region 2 Constructors

    public User(){}

    public User(Long id, String password, String email, String username, @NotNull String firstName, @NotNull String lastName, String job_title, String profileImage, User managerId, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Role role) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = job_title;
        this.profileImage = profileImage;
        this.manager = managerId;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.role = role;
    }

    //endregion

}