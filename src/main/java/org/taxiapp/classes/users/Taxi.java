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

    public Taxi(String registration, Map map, int x, int y, int rate, int[] ratings){ //loading
        super(registration, map);
        setLocation(x,y);
        this.rate = 10;
        this.type = "taxi";
        this.rate = rate;
        map.loadTaxi(this);
        this.ratings = ratings;
    }
    public Taxi(String registration, Map map, int x, int y, int rate){
        super(registration, map);
        setLocation(x,y);
        this.rate = 10;
        this.type = "taxi";
        this.rate = rate;
        map.loadTaxi(this);
    }

    public void setLocation(int x, int y){
        location = map.getLocation(x,y);
        location.addUser(this);
    }

    public int getRate(){
        return rate;
    }

    public int[] getRatings(){
        return ratings;
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
