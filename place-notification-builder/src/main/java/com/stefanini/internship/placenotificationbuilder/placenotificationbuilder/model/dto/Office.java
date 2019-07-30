package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto;

public class Office {
    private Long id;
    private int number;
    private int sizeX;
    private int sizeY;

    private int floor;
    private String description;


    public Office() {
    }

    public Office(Long id, int number, int sizeX, int sizeY, int floor, String description) {
        this.id = id;
        this.number = number;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.floor = floor;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Office setId(Long id) {
        this.id = id;
        return this;
    }

    public int getNumber() {
        return number;
    }

    public Office setNumber(int number) {
        this.number = number;
        return this;
    }

    public int getSizeX() {
        return sizeX;
    }

    public Office setSizeX(int sizeX) {
        this.sizeX = sizeX;
        return this;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Office setSizeY(int sizeY) {
        this.sizeY = sizeY;
        return this;
    }

    public int getFloor() {
        return floor;
    }

    public Office setFloor(int floor) {
        this.floor = floor;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Office setDescription(String description) {
        this.description = description;
        return this;
    }

}
