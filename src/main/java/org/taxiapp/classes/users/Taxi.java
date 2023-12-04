package org.taxiapp.classes.users;

import org.taxiapp.classes.Location;
import org.taxiapp.classes.users.User;

public class Taxi extends User {
    private String registration;
    private int rate; // eur/km base rate
    public Taxi(Location location, String registration){
        super(location);
        this.registration = registration;
        this.rate = 10;
    }
}
