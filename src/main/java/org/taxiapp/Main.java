package org.taxiapp;

import org.taxiapp.classes.*;
import org.taxiapp.classes.users.Customer;
import org.taxiapp.classes.users.Pathfinding;
import org.taxiapp.classes.users.Taxi;
import org.taxiapp.classes.users.User;
import org.taxiapp.interfaces.VehicleHiringTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main implements VehicleHiringTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        CLI.run();
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

    public List<String> CSVToRoad(){
        // Replace "your_file_path.csv" with the actual path to your CSV file
        String filePath = "roads.csv";

        try {
            // Read the CSV file
            Scanner scanner = new Scanner(new File(filePath));
            scanner.useDelimiter("-");

            // Convert the CSV data to a list of strings
            List<String> dataAsList = new ArrayList<>();
            while (scanner.hasNext()) {
                dataAsList.add(scanner.next());
            }

            // Close the scanner
            scanner.close();
            return dataAsList;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}