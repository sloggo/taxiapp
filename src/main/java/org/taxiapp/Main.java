package org.taxiapp;

import org.taxiapp.classes.Location;
import org.taxiapp.classes.Map;
import org.taxiapp.interfaces.VehicleHiringTest;

import java.util.ArrayList;
import java.util.List;

public class Main implements VehicleHiringTest {
    public static void main(String[] args) {
        System.out.println("Test");
        Map map = new Map(10);
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