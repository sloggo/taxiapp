package org.taxiapp.classes.users;

import org.taxiapp.classes.Location;
import org.taxiapp.classes.Map;

import java.util.UUID;

public class Customer extends User{
    private String name;
    public Customer(Location location, String name){
        super(location, UUID.randomUUID().toString());
        this.name = name;
        this.type = "customer";
    }


}
