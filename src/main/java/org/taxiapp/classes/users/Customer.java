package org.taxiapp.classes.users;

import org.taxiapp.classes.Location;
import org.taxiapp.classes.Map;

import java.util.UUID;

public class Customer extends User {
    private String name;
    public Customer(String name, Location location){
        super(UUID.randomUUID().toString());
        setLocation(location);
        this.name = name;
        this.type = "customer";
    }

    public void setLocation(Location locationIn){
        location = locationIn;
        locationIn.addUser(this);
    }


}
