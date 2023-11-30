package org.taxiapp.classes.users;

import org.taxiapp.classes.Location;
import org.taxiapp.classes.Map;
import org.taxiapp.classes.Rating;
import org.taxiapp.classes.Ride;

public abstract class User {
    public User(Location location, String id){
        setLocation(location);
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
    public void setLocation(Location locationIn) {
        location = locationIn;
        locationIn.addUser(this);
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
