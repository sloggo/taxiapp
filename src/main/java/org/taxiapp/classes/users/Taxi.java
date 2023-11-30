package org.taxiapp.classes.users;

import org.taxiapp.classes.Location;
import org.taxiapp.classes.Map;
import org.taxiapp.classes.users.User;

public class Taxi extends User {
    private int rate; // eur/km base rate
    public Taxi(Location location, String registration){
        super(location, registration);
        this.rate = 10;
        this.type = "taxi";
    }
}
