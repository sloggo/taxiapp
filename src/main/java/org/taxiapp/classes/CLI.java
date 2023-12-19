package org.taxiapp.classes;

import org.taxiapp.classes.users.Customer;

import java.io.IOException;
import java.util.Scanner;

public class CLI {
    public static void run() throws IOException, InterruptedException{
        Map map = new Map(15, "main", false);
//        for(int i = 0; i<5; i++){
//            Random random = new Random();
//            String reg = String.valueOf(random.nextInt(10,24))+"LM"+ String.valueOf(random.nextInt(1000,5000));
//            Location loc = map.getRandomLoc();
//            new Taxi(reg,map,loc.getX(),loc.getY());
//        }
        map.printMap();
        Customer current = null;
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;

        while(true){
            if(!loggedIn){
                System.out.println("Welcome to BackSeat! Enter your username:");
            }

            while(!loggedIn){
                String query = scanner.nextLine();
                if(map.findUser(query) != null){
                    current = map.findUser(query);
                    loggedIn = true;
                } else{
                    System.out.println("User does not exist! Try again.");
                }
            }

            System.out.println("Where are we headed to?");
            int xReq = -1;

            while (xReq < 0 || xReq >= map.getMapRadius()) {
                System.out.println("X:");
                xReq = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if (xReq < 0 || xReq >= map.getMapRadius()) {
                    System.out.println("Not a valid coordinate, try between 0 and " + (map.getMapRadius() - 1));
                }
            }

            int yReq = -1;

            while (yReq < 0 || yReq >= map.getMapRadius()) {
                System.out.println("Y:");
                yReq = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if (yReq < 0 || yReq >= map.getMapRadius()) {
                    System.out.println("Not a valid coordinate, try between 0 and " + (map.getMapRadius() - 1));
                }
            }

            Location potentialLoc = map.getLocation(xReq, yReq);
            if(!potentialLoc.isRoad()){
                System.out.println("Not a valid road! Please pick a tile with a * for a road.");
            } else {
                RideRequestSystem rideRequestSystem = new RideRequestSystem(map, current, map.getLocation(xReq, yReq));
                rideRequestSystem.requestRide();
            }

        }
    }
}
