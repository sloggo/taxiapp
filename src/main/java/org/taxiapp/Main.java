package org.taxiapp;

import org.taxiapp.classes.Location;
import org.taxiapp.classes.Map;
import org.taxiapp.classes.RideRequestSystem;
import org.taxiapp.classes.users.Customer;
import org.taxiapp.classes.users.Taxi;
import org.taxiapp.classes.users.User;
import org.taxiapp.interfaces.VehicleHiringTest;

import java.util.ArrayList;
import java.util.List;

public class Main implements VehicleHiringTest {
    public static void main(String[] args) {
        Map map = new Map(5);
        Customer customer = new Customer("sloggo", map.getLocation(1,1));
        Taxi taxi = new Taxi("12mh4084", map.getLocation(0,1));
        map.printMap();

        RideRequestSystem r = new RideRequestSystem(map, customer);
        Taxi closest = r.closestTaxi();
        System.out.println("Closest: "+closest.getId());
    }

    public boolean testAddToMap(String reg, Location loc){
        return false;
    }

    public boolean testMoveVehicle(String reg, Location loc){
        return false;
    }

    public boolean testRemoveVehicle(String reg){
        return false;
    }

    public Location testGetVehicleLoc(String reg){
        Location loc = new Location(0,0);
        return loc;
    }

    public List<String> testGetVehiclesInRange(Location loc, int r){
        List<String> vehicles = new ArrayList<>();
        return vehicles;
    }
}