package com.stefanini.internship.placemanagement.data.entities;

import com.stefanini.internship.placemanagement.data.Identifiable;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(
        name = "places",
        //The combination of X and Y has to be unique. So x=1 , y=1 and x=1,y=2 will both insert
        uniqueConstraints = {@UniqueConstraint(columnNames = {"coordinate_x", "coordinate_y", "office_id"}), @UniqueConstraint(columnNames = {"user"})}
)

public class Place implements Identifiable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "coordinate_x")
    private int coordinateX;

    @Column(name = "coordinate_y")
    private int coordinateY;

    @NotNull
    @ManyToOne
    private Office office;


    private Long user;

    //region 2 Constructors

    public Place() {
    }

    public Place(Long id, int coordinateX, int coordinateY, @NotNull Office office, Long user) {
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

    public Long getUser() {
        return user;
    }

    public Place setUser(Long user) {
        this.user = user;
        return this;
    }

    //endregion
}