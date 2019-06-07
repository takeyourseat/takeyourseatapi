package com.stefanini.internship.placemanagement.data.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "place_requests")
public class PlaceRequest {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "date_of", nullable = false)
    private java.sql.Timestamp dateOf;

    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "approved")
    private Boolean approved;

    @Column(name = "reviewed_at")
    private java.sql.Timestamp reviewedAt;

    @Column(name = "user")
    private Long userId;

    @Column(name = "reviewed_by")
    private Long managerId;

    //region 2 Constructors

    public PlaceRequest() {
    }

    public PlaceRequest(Long id, @NotNull java.sql.Timestamp dateOf, Long placeId, Boolean approved, java.sql.Timestamp reviewedAt, Long userId, Long managerId) {
        this.id = id;
        this.dateOf = dateOf;
        this.approved = approved;
        this.reviewedAt = reviewedAt;
        this.userId = userId;
        this.managerId = managerId;
        this.placeId = placeId;
    }

    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public PlaceRequest setId(Long id) {
        this.id = id;
        return this;
    }

    public java.sql.Timestamp getDateOf() {
        return dateOf;
    }

    public PlaceRequest setDateOf(java.sql.Timestamp dateOf) {
        this.dateOf = dateOf;
        return this;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public PlaceRequest setPlaceId(Long placeId) {
        this.placeId = placeId;
        return this;
    }


    public Boolean getApproved() {
        return approved;
    }

    public PlaceRequest setApproved(Boolean approved) {
        this.approved = approved;
        return this;
    }

    public java.sql.Timestamp getReviewedAt() {
        return reviewedAt;
    }

    public PlaceRequest setReviewedAt(java.sql.Timestamp reviewedAt) {
        this.reviewedAt = reviewedAt;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    //endregion
}
