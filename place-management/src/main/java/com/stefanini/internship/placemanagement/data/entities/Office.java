package com.stefanini.internship.placemanagement.data.entities;

import com.stefanini.internship.placemanagement.data.Identifiable;

import javax.persistence.*;

@Entity
@Table(name = "offices")
public class Office implements Identifiable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, name = "number")
    private int number;

    @Column(name = "size_x")
    private int sizeX;

    @Column(name = "size_y")
    private int sizeY;

    @Column(name = "floor")
    private int floor;

    @Column(name = "description")
    private String description;

    //region 2 Constructors

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

    //endregion

    //region Getters and setters

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

    //endregion
}
