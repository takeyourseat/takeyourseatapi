package com.stefanini.internship.authorizationserver.dao.classes;

public class PlaceRequest {

    private Long id;

    private java.sql.Timestamp dateOf;
    private User user;
    private Integer placeX;
    private Integer placeY;
    private Integer officeFloor;
    private Integer officeNumber;
    private String officeDescription;
    private Boolean approved;
    private User reviewedBy;
    private java.sql.Timestamp reviewedAt;


    //region 2 Constructors

    public PlaceRequest() {}

    public PlaceRequest(Long id, java.sql.Timestamp dateOf, User user, int requestedX, int requestedY, int requestedOfficeFloor, int getRequestedOfficeNumber, String requestedOfficeDescription, Boolean approved, User reviewdBy, java.sql.Timestamp reviewedAt) {
        this.id = id;
        this.dateOf = dateOf;
        this.user = user;
        this.placeX = requestedX;
        this.placeY = requestedY;
        this.officeFloor = requestedOfficeFloor;
        this.officeNumber = getRequestedOfficeNumber;
        this.officeDescription = requestedOfficeDescription;
        this.approved = approved;
        this.reviewedBy = reviewdBy;
        this.reviewedAt = reviewedAt;
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

    public User getUser() {
        return user;
    }

    public PlaceRequest setUser(User user) {
        this.user = user;
        return this;
    }

    public int getPlaceX() {
        return placeX;
    }

    public PlaceRequest setPlaceX(int placeX) {
        this.placeX = placeX;
        return this;
    }

    public int getPlaceY() {
        return placeY;
    }

    public PlaceRequest setPlaceY(int placeY) {
        this.placeY = placeY;
        return this;
    }

    public int getOfficeFloor() {
        return officeFloor;
    }

    public PlaceRequest setOfficeFloor(int officeFloor) {
        this.officeFloor = officeFloor;
        return this;
    }

    public int getOfficeNumber() {
        return officeNumber;
    }

    public PlaceRequest setOfficeNumber(int officeNumber) {
        this.officeNumber = officeNumber;
        return this;
    }

    public String getOfficeDescription() {
        return officeDescription;
    }

    public PlaceRequest setOfficeDescription(String officeDescription) {
        this.officeDescription = officeDescription;
        return this;
    }

    public Boolean getApproved() {
        return approved;
    }

    public PlaceRequest setApproved(Boolean approved) {
        this.approved = approved;
        return this;
    }

    public User getReviewedBy() {
        return reviewedBy;
    }

    public PlaceRequest setReviewedBy(User reviewedBy) {
        this.reviewedBy = reviewedBy;
        return this;
    }

    public java.sql.Timestamp getReviewedAt() {
        return reviewedAt;
    }

    public PlaceRequest setReviewedAt(java.sql.Timestamp reviewedAt) {
        this.reviewedAt = reviewedAt;
        return this;
    }
    
    //endregion





}
