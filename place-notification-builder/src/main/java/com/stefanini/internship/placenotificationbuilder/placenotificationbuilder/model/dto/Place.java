package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto;

public class Place {

    private int coordinateX;
    private int coordinateY;
    private Office office;

    public Place() {
    }

    public Place(int coordinateX, int coordinateY, Office office) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.office = office;
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

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
