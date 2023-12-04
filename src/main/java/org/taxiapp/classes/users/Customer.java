package org.taxiapp.classes.users;

import org.taxiapp.classes.Location;

import java.util.UUID;

public class Customer extends User {
    private String name;
    private UUID id;
    public Customer(Location location, String name){
        super(location);
        this.id = UUID.randomUUID();
        this.name = name;
    }


}
