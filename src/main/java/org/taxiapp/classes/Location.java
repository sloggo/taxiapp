package org.taxiapp.classes;

import java.util.List;

public class Location {
    private int x;
    private int y;
    private List<Taxi> taxis;
    private List<User> users;

    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }
}
