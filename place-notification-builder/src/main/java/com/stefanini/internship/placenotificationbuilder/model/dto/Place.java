package com.stefanini.internship.placenotificationbuilder.model.dto;

public class Place {

    private int coordinateX;
    private int coordinateY;
    private Office office;
    private String username;

    public Place() {
    }

    public Place(int coordinateX, int coordinateY, Office office, String username) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.office = office;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public Place setUsername(String username) {
        this.username = username;
        return this;
    }
}
