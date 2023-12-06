package org.taxiapp.classes.users;

import org.taxiapp.classes.Location;
import org.taxiapp.classes.Map;
import org.taxiapp.classes.Rating;
import org.taxiapp.classes.Ride;

public abstract class User {
    public User(String id){
        this.id = id;
        this.type = "invalid";
    }
    protected Map map;
    protected Location location;
    protected Rating[] ratings;
    protected Ride currentRide;
    protected String id;
    protected String type;

    public Location getLocation() {
        return location;
    }

    public double getRating() {
        return 0;
    }

    public Ride getCurrentRide() {
        return currentRide;
    }
    public String getId() { return id; }

    public String getType() {return type;}
}
