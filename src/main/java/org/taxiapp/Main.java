package org.taxiapp;

import org.taxiapp.classes.LinkedList;
import org.taxiapp.classes.Location;
import org.taxiapp.classes.Map;
import org.taxiapp.classes.RideRequestSystem;
import org.taxiapp.classes.users.Customer;
import org.taxiapp.classes.users.Pathfinding;
import org.taxiapp.classes.users.Taxi;
import org.taxiapp.classes.users.User;
import org.taxiapp.interfaces.VehicleHiringTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main implements VehicleHiringTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        Map map = new Map(20, "main", false);
//        for(int i = 0; i<5; i++){
//            Random random = new Random();
//            String reg = String.valueOf(random.nextInt(10,24))+"LM"+ String.valueOf(random.nextInt(1000,5000));
//            int x = random.nextInt(19);
//            int y = random.nextInt(19);
//
//            new Taxi(reg,map,x,y);
//        }
        map.printMap();
    }

    public boolean testAddToMap(Map map, String reg, Location loc){
        if(loc.getTaxiRegs().contains(reg)){
            return false;
        } else{
            new Taxi(reg,map,loc.getX(), loc.getY());
            return true;
        }
    }

    public boolean testMoveVehicle(Map map, Taxi taxi, Location loc){
        if(!map.getTaxiRegs().contains(taxi.getId())){
            return false;
        } else{
            taxi.setLocation(loc.getX(), loc.getY());
            return true;
        }
    }

    public boolean testRemoveVehicle(Map map, Taxi taxi){
        if(!map.getTaxiRegs().contains(taxi.getId())){
            return false;
        } else{
            taxi.getLocation().removeUser(taxi);
            map.removeUser(taxi);
            return true;
        }
    }

    public Location testGetVehicleLoc(Map map, Taxi taxi){
        if(!map.getTaxiRegs().contains(taxi.getId())){
            return null;
        } else{
            return taxi.getLocation();
        }
    }

    public LinkedList<Taxi> testGetVehiclesInRange(Map map, Location loc, int r){
        RideRequestSystem system = new RideRequestSystem(map, new Customer("test",map,loc.getX(),loc.getY()), map.getLocation(0,0));
        return system.allTaxisInRange(r);
    }
}