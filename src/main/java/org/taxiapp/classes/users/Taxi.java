package org.taxiapp.classes.users;

import org.taxiapp.classes.Location;
import org.taxiapp.classes.Map;
import org.taxiapp.classes.users.User;
import org.taxiapp.interfaces.Observer;

public class Taxi extends User implements Observer {
    private int rate; // eur/km base rate
    public Taxi(String registration, Map map, int x, int y){
        super(registration, map);
        setLocation(x,y);
        this.rate = 10;
        this.type = "taxi";
        map.addTaxi(this);
    }

    public void setLocation(int x, int y){
        location = map.getLocation(x,y);
        location.addUser(this);
    }

    @Override
    public void update() {

    }

    @Override
    public void attach() {

    }

    @Override
    public void detach() {

    }
}
