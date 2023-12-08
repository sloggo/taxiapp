package org.taxiapp.classes;

import org.taxiapp.classes.users.Customer;
import org.taxiapp.classes.users.Taxi;
import org.taxiapp.interfaces.*;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RideRequestSystem implements Subject {
    private String rideId;
    private Map map;
    private Customer requester;
    private List<Observer> observers = new ArrayList();
    public RideRequestSystem(Map map, Customer requester){
        this.rideId = UUID.randomUUID().toString();
        this.map = map;
        this.requester = requester;
    }
    @Override
    public void attachObserver(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void detachObserver(Observer observer) {
        observers.remove(observer);
    }
    @Override
    public void notifyObservers() {
        for (Observer o :observers) {
            o.update();
        }
    }
    public Taxi closestTaxi(){
        List<Taxi> taxis = requester.getLocation().getTaxis();
        boolean taxisFound = false;
        int range = 1;
        // first pass check customer location for taxis in immediate vicinity
        if(!taxis.isEmpty()){
            taxisFound = true;
            return taxis.get(0);
        } else {
            while(!taxisFound){
                try{
                    taxis = incrementSearch(range);
                    if(!taxis.isEmpty()){
                        taxisFound=true;
                        return taxis.get(0);
                    }
                    range++;
                } catch(IndexOutOfBoundsException e){
                    return null;
                }
            }
        }
        return null;
    }

    public List<Taxi> incrementSearch(int startRange){
        List<Taxi> validTaxis = new ArrayList<>();
        int xStart = requester.getLocation().getX() - startRange;
        int xEnd = requester.getLocation().getX() + startRange;

        int yStart = requester.getLocation().getY() + startRange;
        int yEnd = requester.getLocation().getY() - startRange;

        // in-case range falls off map
        if(xStart < 0) { xStart = 0; };
        if(yEnd < 0) { yEnd = 0; };

        // loop through top row
        for(int i=xStart; i<=xEnd; i++){
            if(i >= map.getMapRadius() || i < 0){continue;};
            System.out.println("X:"+i+", Y:"+yEnd);
            List<Taxi> taxisOnTile = map.getLocation(i,yEnd).getTaxis();
            if(taxisOnTile.isEmpty()){
                System.out.println("Empty TILE");
            }
            validTaxis.addAll(taxisOnTile);
        }

        // loop through bottom row
        for(int i=xStart; i<=xEnd; i++){
            if(i >= map.getMapRadius() || i < 0){continue;};
            System.out.println("X:"+i+", Y:"+yStart);
            List<Taxi> taxisOnTile = map.getLocation(i, yStart).getTaxis();
            if(taxisOnTile.isEmpty()){
                System.out.println("Empty TILE");
            }
            validTaxis.addAll(taxisOnTile);
        }

        // loop through left row
        for(int i=yEnd+1; i<yStart; i++){
            if(i >= map.getMapRadius() || i < 0){continue;};
            System.out.println("X:"+xStart+", Y:"+i);
            List<Taxi> taxisOnTile = map.getLocation(xStart, i).getTaxis();
            if(taxisOnTile.isEmpty()){
                System.out.println("Empty TILE");
            }
            validTaxis.addAll(taxisOnTile);
        }

        // loop through right row
        for(int i=yEnd+1; i<yStart; i++){
            if(i >= map.getMapRadius() || i < 0){continue;};
            System.out.println("X:"+xEnd+", Y:"+i);
            List<Taxi> taxisOnTile = map.getLocation(xEnd, i).getTaxis();
            if(taxisOnTile.isEmpty()){
                System.out.println("Empty TILE");
            }
            validTaxis.addAll(taxisOnTile);
        }

        for(Taxi validTaxi : validTaxis){
            System.out.println("available taxis:");
            System.out.print(validTaxi.getId()+", ");
        }

        return validTaxis;
    }
}
