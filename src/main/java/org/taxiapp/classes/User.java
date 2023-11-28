package org.taxiapp.classes;

public abstract class User {
    private Location location;
    private double rating;
    private Ride currentRide;

    public Location getLocation() {
        return location;
    }

    public double getRating() {
        return rating;
    }

    public Ride getCurrentRide() {
        return currentRide;
    }
}
