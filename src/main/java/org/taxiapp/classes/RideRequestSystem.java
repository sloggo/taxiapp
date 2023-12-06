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
        // first pass check customer location for taxis in immediate vicinity
        if(!taxis.isEmpty()){
            return taxis.get(0);
        } else {
            taxis = incrementSearch(1);
            System.out.println(taxis.get(0).getId());
            return null;
        }
    }

    public List<Taxi> incrementSearch(int startRange){
        List<Taxi> validTaxis = new ArrayList<>();
        int xStart = requester.getLocation().getX() - startRange;
        int xEnd = requester.getLocation().getX() + startRange;

        int yStart = requester.getLocation().getY() + startRange;
        int yEnd = requester.getLocation().getY() - startRange;

        // loop through top row
        for(int i=xStart; i<=xEnd; i++){
            System.out.println("X:"+i+", Y:"+yEnd);
            List<Taxi> taxisOnTile = map.getLocation(i, yEnd).getTaxis();
            validTaxis.addAll(taxisOnTile);
        }

        // loop through bottom row
        for(int i=xStart; i<=xEnd; i++){
            System.out.println("X:"+i+", Y:"+yStart);
            List<Taxi> taxisOnTile = map.getLocation(i, yStart).getTaxis();
            validTaxis.addAll(taxisOnTile);
        }

        for(Taxi validTaxi : validTaxis){
            System.out.println("available taxis:");
            System.out.print(validTaxi.getId()+", ");
        }

        return validTaxis;
    }
}
