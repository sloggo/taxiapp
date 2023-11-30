package org.taxiapp.classes.users;

import org.taxiapp.classes.Location;
import org.taxiapp.classes.Rating;
import org.taxiapp.classes.Ride;

public abstract class User {
    public User(Location location){
        this.location = location;
    }
    protected Location location;
    protected Rating[] ratings;
    protected Ride currentRide;

    public Location getLocation() {
        return location;
    }

    public double getRating() {
        return 0;
    }

    public Ride getCurrentRide() {
        return currentRide;
    }
}
