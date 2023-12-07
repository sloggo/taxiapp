package org.taxiapp;

import org.taxiapp.classes.LinkedList;
import org.taxiapp.classes.Location;
import org.taxiapp.classes.Map;
import org.taxiapp.classes.users.Customer;
import org.taxiapp.classes.users.Taxi;
import org.taxiapp.classes.users.User;
import org.taxiapp.interfaces.VehicleHiringTest;

import java.util.ArrayList;
import java.util.List;

public class Main implements VehicleHiringTest {
    public static void main(String[] args) {
        Map map = new Map(20);
        Customer customer = new Customer(map.getRandomLoc(),"sloggo");
        Taxi taxi = new Taxi(map.getRandomLoc(),"12mh4084");
        Taxi taxi2 = new Taxi(map.getRandomLoc(),"11D0392");
        Taxi taxi3 = new Taxi(map.getRandomLoc(),"231LI1928");



        map.printMap();

        /*LinkedList<Integer> list = new LinkedList<>();
        list.insert(1,1);

        list.insert(50,1);

        list.insert(2003, 200);

        list.printList();
        list.insert(200,0);
        list.printList();*/

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