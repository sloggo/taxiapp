package org.taxiapp.classes;

import org.taxiapp.classes.users.Customer;
import org.taxiapp.classes.users.Taxi;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class CLI {
    private static boolean loggedIn = false;
    private static Customer current = null;
    private static Map map = null;
    public static void bookATaxi() throws IOException, InterruptedException{
        Scanner scanner = new Scanner(System.in);
        loggedIn = true;

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
            String size = "no";
            while(size!=null && size.equals("no")){
                System.out.println("Choose a size; 0 for any, 1 for small, 2 for medium, 3 for large");
                int input = scanner.nextInt();

                if(input >= 0 && input <=3){
                    switch (input) {
                        case (0):
                            size = null;
                            break;
                        case (1):
                            size="small";
                            break;
                        case (2):
                            size = "medium";
                            break;
                        case (3):
                            size = "large";
                            break;
                    };
                }
            }

            RideRequestSystem rideRequestSystem = new RideRequestSystem(map, current, map.getLocation(xReq, yReq), size);
            rideRequestSystem.requestRide();
        }

    }

    public static void run() throws IOException, InterruptedException {
        map = new Map(15, "main", false);
//        for(int i = 0; i<5; i++){
//            Random random = new Random();
//            String reg = String.valueOf(random.nextInt(10,24))+"LM"+ String.valueOf(random.nextInt(1000,5000));
//            Location loc = map.getRandomLoc();
//            new Taxi(reg,map,loc.getX(),loc.getY());
//        }
        map.printMap();
        Scanner scanner = new Scanner(System.in);

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

        while(true){
            bookATaxi();
        }
    }
}
