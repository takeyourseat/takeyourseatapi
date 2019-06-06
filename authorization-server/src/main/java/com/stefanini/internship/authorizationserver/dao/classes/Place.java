package com.stefanini.internship.authorizationserver.dao.classes;

public class Place {

    private Long id;

    private Integer coordinateX;
    private Integer coordinateY;
    private Office office;
    private User user;

    //region 2 Constructors

    public Place() {}

    public Place(Long id, int coordinateX, int coordinateY, Office office, User user) {
        this.id = id;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.office = office;
        this.user = user;
    }

    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public Place setId(Long id) {
        this.id = id;
        return this;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public Place setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
        return this;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public Place setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
        return this;
    }

    public Office getOffice() {
        return office;
    }

    public Place setOffice(Office office) {
        this.office = office;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Place setUser(User user) {
        this.user = user;
        return this;
    }


    //endregion
}