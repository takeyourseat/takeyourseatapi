package com.stefanini.internship.authorizationserver.dto;
import com.sun.istack.NotNull;

public class PlaceRequest  {

    private Long id;
    private java.sql.Timestamp dateOf;
    private Long placeId;
    private Long userId;

    //region 2 Constructors

    public PlaceRequest() {
    }

    public PlaceRequest(Long id, @NotNull java.sql.Timestamp dateOf, Long placeId, Long userId) {
        this.id = id;
        this.dateOf = dateOf;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    //endregion
}
