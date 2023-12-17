package org.taxiapp.classes;

import org.taxiapp.classes.users.Customer;
import org.taxiapp.classes.users.Pathfinding;
import org.taxiapp.classes.users.Taxi;

import java.util.Scanner;
import java.util.UUID;

public class Ride {
    private Map map;
    private float cost;
    private String rideId;
    private Customer passenger;
    private Taxi driver;
    private Location destination;

    public Ride(String rideId, Map map, Customer customer, Taxi driver, Location destination, float cost){
        this.map = map;
        this.rideId = rideId;
        this.passenger = customer;
        this.driver = driver;
        this.destination = destination;
        this.cost = cost;
        customer.setCurrentRide(this);
        driver.setCurrentRide(this);
    }

    public void startRide() throws InterruptedException {
        // taxi moves to customer
        LinkedList<LocationNode> routeToCustomer = getTaxiToCustomerRoute();
        driver.moveTo(routeToCustomer);
        System.out.println("Arrived! Get in the car with reg: "+driver.getId());
        Thread.sleep(1000);

        // begin to move together
        setUpDriverCustomerObserver();
        LinkedList<LocationNode> taxiRoute = getTaxiRoute();
        driver.moveTo(taxiRoute);

        System.out.println("You have arrived at your destination!");
        System.out.println("Your bill comes to a total of; "+cost);
        rateTaxi();
    }

    public void setUpDriverCustomerObserver(){
        driver.attachObserver(passenger);
    }

    public LinkedList<LocationNode> getTaxiRoute(){
        Pathfinding pathfinder = new Pathfinding();
        return pathfinder.search(map.getMapRadius(), passenger.getLocation(), destination);
    }

    public LinkedList<LocationNode> getTaxiToCustomerRoute(){
        Pathfinding pathfinder = new Pathfinding();
        return pathfinder.search(map.getMapRadius(), driver.getLocation(), passenger.getLocation());
    }

    public void rateTaxi(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please rate your taxi from 1-5:");
        boolean valid = false;
        int rating = 0;
        while(!valid){
            rating = scanner.nextInt();
            if(rating > 5 || rating < 0){
                System.out.println("Invalid rating, try again.");
            } else{
                valid = true;
            }
        }
        driver.addRating(rating);

    }
}
