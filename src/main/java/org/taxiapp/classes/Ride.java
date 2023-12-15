package org.taxiapp.classes;

import org.taxiapp.classes.users.Customer;
import org.taxiapp.classes.users.Taxi;

import java.util.UUID;

public class Ride {
    private Map map;
    private String rideId;
    private Customer passenger;
    private Taxi driver;
    private Location destination;
    private Bill bill;

    public Ride(String rideId, Map map, Customer customer, Taxi driver, Location destination){
        this.map = map;
        this.rideId = rideId;
        this.passenger = customer;
        this.driver = driver;
        this.destination = destination;
        this.bill = new Bill();
    }
}
