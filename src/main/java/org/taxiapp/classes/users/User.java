package org.taxiapp.classes.users;

import org.taxiapp.classes.Location;
import org.taxiapp.classes.Map;
import org.taxiapp.classes.Rating;
import org.taxiapp.classes.Ride;

public abstract class User {
    public User(String id, Map map){
        this.id = id;
        this.type = "invalid";
        this.map = map;
    }
    protected Map map;
    protected Location location;
    protected Ride currentRide;
    protected String id;
    protected String type;

    public Location getLocation() {
        return location;
    }

    public Ride getCurrentRide() {
        return currentRide;
    }
    public void setCurrentRide(Ride cR){currentRide = cR;}
    public String getId() { return id; }

    public String getType() {return type;}
}
