package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto;

import java.sql.Timestamp;

public class PlaceRequest {
    private Long id;

    private Timestamp dateOf;

    private Place place;

    private Boolean approved;

    private Timestamp reviewedAt;

    private String username;

    private String reviewer;

    public PlaceRequest() {
    }

    public PlaceRequest(Long id, Timestamp dateOf, Place place, Boolean approved, Timestamp reviewedAt, String username, String reviewer) {
        this.id = id;
        this.dateOf = dateOf;
        this.approved = approved;
        this.reviewedAt = reviewedAt;
        this.username = username;
        this.reviewer = reviewer;
        this.place = place;
    }

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

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    @Override
    public String toString() {
        return "PlaceRequest{" +
                "id=" + id +
                ", dateOf=" + dateOf +
                ", place=" + place +
                ", approved=" + approved +
                ", reviewedAt=" + reviewedAt +
                ", username='" + username + '\'' +
                ", reviewer='" + reviewer + '\'' +
                '}';
    }
}
