package com.linkedladies.haven.models;

public class Checkin {
    private int id;
    private int checkins;
    private int injured;

    public Checkin(int checkins, int id, int injured) {
        this.checkins = checkins;
        this.id = id;
        this.injured = injured;
    }
}
