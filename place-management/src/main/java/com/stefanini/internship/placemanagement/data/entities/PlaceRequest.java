package com.stefanini.internship.placemanagement.data.entities;

import com.stefanini.internship.placemanagement.data.Identifiable;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "place_requests")
public class PlaceRequest implements Identifiable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "date_of", nullable = false)
    private java.sql.Timestamp dateOf;

    @ManyToOne()
    private Place place;

    @Column(name = "approved")
    private Boolean approved;

    @Column(name = "reviewed_at")
    private java.sql.Timestamp reviewedAt;

    @Column(name = "user")
    private String username;

    @Column(name = "reviewed_by")
    private String managerUsername;

    //region 2 Constructors

    public PlaceRequest() {
    }

    public PlaceRequest(Long id, @NotNull java.sql.Timestamp dateOf, Place place, Boolean approved, java.sql.Timestamp reviewedAt, String username, String managerUsername) {
        this.id = id;
        this.dateOf = dateOf;
        this.approved = approved;
        this.reviewedAt = reviewedAt;
        this.username = username;
        this.managerUsername = managerUsername;
        this.place = place;
    }

    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDateOf() {
        return dateOf;
    }

    public void setDateOf(Timestamp dateOf) {
        this.dateOf = dateOf;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Timestamp getReviewedAt() {
        return reviewedAt;
    }

    public void setReviewedAt(Timestamp reviewedAt) {
        this.reviewedAt = reviewedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getManagerUsername() {
        return managerUsername;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    //endregion
}
