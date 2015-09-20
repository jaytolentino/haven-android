package com.linkedladies.haven.models;


import com.esri.core.geometry.Point;

public class Shelter {

    private String name;
    private Point coordinates;

    public Shelter(Point coordinates, String name) {
        this.coordinates = coordinates;
        this.name = name;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public String getName() {
        return name;
    }
}
