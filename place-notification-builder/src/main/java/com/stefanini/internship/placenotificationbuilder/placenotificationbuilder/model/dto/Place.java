package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto;

public class Place {

    private int coordinateX;
    private int coordinateY;

    public Place() {
    }

    public Place(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }
}
