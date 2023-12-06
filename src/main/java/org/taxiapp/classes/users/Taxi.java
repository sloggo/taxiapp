package org.taxiapp.classes.users;

import org.taxiapp.classes.Location;
import org.taxiapp.classes.Map;
import org.taxiapp.classes.users.User;
import org.taxiapp.interfaces.Observer;

public class Taxi extends User implements Observer {
    private int rate; // eur/km base rate
    public Taxi(String registration, Location location){
        super(registration);
        setLocation(location);
        this.rate = 10;
        this.type = "taxi";
    }

    public void setLocation(Location locationIn){
        location = locationIn;
        locationIn.addUser(this);
    }

    @Override
    public void update() {

    }

    @Override
    public void attach() {

    }

    @Override
    public void detach() {

    }
}
