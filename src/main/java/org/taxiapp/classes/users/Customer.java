package org.taxiapp.classes.users;

import org.taxiapp.classes.Location;
import org.taxiapp.classes.Map;

import java.util.UUID;

public class Customer extends User{
    private String name;
    public Customer(String name, Map map, int x, int y){
        super(UUID.randomUUID().toString(), map);
        setLocation(x,y);
        this.name = name;
        this.type = "customer";
        map.addCustomer(this);
    }

    public void setLocation(int x, int y){
        location = map.getLocation(x,y);
        location.addUser(this);
    }


}
