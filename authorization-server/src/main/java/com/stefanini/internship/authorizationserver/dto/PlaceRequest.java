package com.stefanini.internship.authorizationserver.dto;

public class PlaceRequest {

    private Long id;
    private Boolean approved;
    private String username;
    private String reviewedBy;

    //region 2 Constructors

    public PlaceRequest() {
    }

    public PlaceRequest(Long id, Boolean approved, String username, String reviewedBy) {
        this.id = id;
        this.approved = approved;
        this.username = username;
        this.reviewedBy = reviewedBy;
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

    public Boolean getApproved() {
        return approved;
    }

    public PlaceRequest setApproved(Boolean approved) {
        this.approved = approved;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public PlaceRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getReviewedBy() {
        return reviewedBy;
    }

    public PlaceRequest setReviewedBy(String reviewedBy) {
        this.reviewedBy = reviewedBy;
        return this;
    }

    //endregion
}
