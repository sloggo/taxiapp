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

public class Main implements VehicleHiringTest {
    public static void main(String[] args) throws IOException {
        Map map = new Map(20, "main", true);
        Customer sloggo = new Customer("sloggo",map, 1,1);
        Taxi taxi1 = new Taxi("taxi1",map, 6,8);
        Taxi taxi2 = new Taxi("taxi2",map, 15,18);

        RideRequestSystem s = new RideRequestSystem(map, sloggo, map.getLocation(0,0));
        s.requestRide();

        map.printMap();
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