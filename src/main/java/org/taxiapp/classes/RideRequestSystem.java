package org.taxiapp.classes;

import org.taxiapp.classes.users.Customer;
import org.taxiapp.classes.users.Taxi;
import org.taxiapp.interfaces.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class RideRequestSystem implements Subject {
    private LinkedList<String> declinedTaxis;
    private Location destination;
    private int currentRadius;
    private int radiusLimit;
    private String rideId;
    private Map map;
    private Customer requester;
    private LinkedList<Observer> observers = new LinkedList<>();
    public RideRequestSystem(Map map, Customer requester, Location destination){
        this.rideId = UUID.randomUUID().toString();
        this.map = map;
        this.requester = requester;
        this.destination = destination;
        this.currentRadius = 1;
        this.radiusLimit = map.getMapRadius();
        this.declinedTaxis = new LinkedList<>();
    }
    @Override
    public void attachObserver(Observer observer) {
        observers.append(observer);
    }
    @Override
    public void detachObserver(Observer observer) {
        observers.remove(observer);
    }
    @Override
    public void notifyObservers() {
        observers.getHead();
        while (observers.hasNext()) {
            Observer o = observers.retrieveCurrent();
            o.update();
            observers.moveForward();
        }
    }

    public Taxi closestTaxi(){
        LinkedList<Taxi> taxis = requester.getLocation().getTaxis();
        boolean taxisFound = false;
        // first pass check customer location for taxis in immediate vicinity
        if(!taxis.isEmpty()){
            taxisFound = true;
            return taxis.get(0);
        } else {
            while(!taxisFound && currentRadius < radiusLimit){
                try{
                    taxis = incrementSearch();
                    if(!taxis.isEmpty()){
                        taxisFound=true;
                        return taxis.get(0);
                    }
                } catch(IndexOutOfBoundsException e){
                    return null;
                }
            }
        }
        return null;
    }

    public LinkedList<Taxi> incrementSearch() {
        LinkedList<Taxi> validTaxis = new LinkedList<>();
        int xStart = requester.getLocation().getX() - currentRadius;
        int xEnd = requester.getLocation().getX() + currentRadius;

        int yStart = requester.getLocation().getY() + currentRadius;
        int yEnd = requester.getLocation().getY() - currentRadius;

        // in-case range falls off map
        if(xStart < 0) { xStart = 0; };
        if(yEnd < 0) { yEnd = 0; };
        if(xEnd < 0) { xEnd = 0; };
        if(yStart < 0) { yStart = 0; };
        if(xStart >= map.getMapRadius()) { xStart = map.getMapRadius()-1; };
        if(yEnd >= map.getMapRadius()) { yEnd = map.getMapRadius()-1; };
        if(xEnd >= map.getMapRadius()) { xEnd = map.getMapRadius()-1; };
        if(yStart >= map.getMapRadius()) { yStart = map.getMapRadius()-1; };


        // loop through top and bottom row
        for(int i=xStart; i<=xEnd; i++){
            Location topLocation = map.getLocation(i,yEnd);
            Location bottomLocation = map.getLocation(i, yStart);
            Location[] locationsToSearch = {topLocation, bottomLocation};

            for(Location location: locationsToSearch){
                if(location.isTaxisOnTile()){ // improve efficiency by not searching empty tiles, improves from 27ms to 25ms
                    LinkedList<Taxi> taxisOnTileT = location.getTaxis();
                    taxisOnTileT.printList();
                    if(!taxisOnTileT.isEmpty()){
                        return taxisOnTileT;
                    }
                }
            }
        }

        // loop through left and right row
        for(int i=yEnd+1; i<yStart; i++){
            validTaxis.pointToHead();
            Location leftLocation = map.getLocation(xStart, i);
            Location rightLocation = map.getLocation(xEnd, i);
            Location[] locationsToSearch = {leftLocation, rightLocation};

            for(Location location: locationsToSearch){ // get all taxis from both sides
                if(location.isTaxisOnTile()){
                    LinkedList<Taxi> taxisOnTile = location.getTaxis();
                    taxisOnTile.printList();
                    if(!taxisOnTile.isEmpty()){
                        return taxisOnTile;
                    }
                }
            }
        }
        currentRadius++;
        return validTaxis;
    }

    public void requestRide(){
        radiusLimit = map.getMapRadius();
        currentRadius = 1;
        boolean userAccepted = false;
        boolean noTaxisLeft = false;
        while(!userAccepted && !noTaxisLeft){
            Taxi potentialTaxi = closestTaxi();
            if(currentRadius == radiusLimit || potentialTaxi == null){noTaxisLeft = true; continue;}
            if(declinedTaxis.contains(potentialTaxi.getId())){continue;}
            boolean isAccepted = offerTaxi(potentialTaxi);
            if(isAccepted){
                new Ride(rideId, map, requester, potentialTaxi, destination);
            } else{
                declinedTaxis.append(potentialTaxi.getId());
            }
            currentRadius++;
        }
        if(noTaxisLeft){
            System.out.println("No Taxis found in the given area at the time; Try again later!");
        }
    }

    private boolean offerTaxi(Taxi taxi) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Taxi found!");
        System.out.println("------------------------");
        System.out.println(taxi.getId());
        System.out.println(taxi.getRating());

        System.out.println("\n Accept? Y/N");
        boolean validInput = false;
        while(!validInput){
            String input = scanner.nextLine().toLowerCase().trim();
            if(input.equals("y")){
                validInput=true;
                return true;
            } else if(input.equals("n")){
                validInput=true;
                return false;
            }
        }
        return false;
    }

}
