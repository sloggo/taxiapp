package org.taxiapp.classes.users;

import org.taxiapp.classes.LinkedList;
import org.taxiapp.classes.Location;
import org.taxiapp.classes.LocationNode;
import org.taxiapp.classes.Map;
import org.taxiapp.classes.users.User;
import org.taxiapp.interfaces.Observer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Taxi extends User implements Observer {
    private int rate; // eur/km base rate
    private int[] ratings;

    public Taxi(String registration, Map map, int x, int y){
        super(registration, map);
        setLocation(x,y);
        this.rate = 10;
        this.type = "taxi";
        this.ratings = new int[0];
        map.addTaxi(this);
        addTaxiToCSV();
    }
    public Taxi(String registration, Map map, int x, int y, int rate, int[] ratings){
        super(registration, map);
        setLocation(x,y);
        this.rate = rate;
        this.type = "taxi";
        this.ratings = ratings;
        map.addTaxi(this);
    }

    public void addTaxiToCSV(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(map.getId()+"-taxis.csv", true))){
            // mapid, registration, x , y, rate, ratings
            String[] values = new String[5+ratings.length];
            values[0] = map.getId();
            values[1] = id;
            values[2] = String.valueOf(location.getX());
            values[3] = String.valueOf(location.getY());
            values[4] = String.valueOf(rate);
            for(int i=5; i<values.length; i++){
                values[i] = String.valueOf(ratings[i-5]);
            }
            String line = String.join(",",values);
            writer.write(line);
            System.out.println(line);
            writer.newLine();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setLocation(int x, int y){
        location = map.getLocation(x,y);
        location.addUser(this);
    }
    public void calcPath(Location destination){
        Pathfinding p = new Pathfinding();
        LinkedList<LocationNode> path = p.search(map.getMapRadius(),this.location,destination);
        path.pointToHead();
        path.printList();
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
