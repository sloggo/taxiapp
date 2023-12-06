package org.taxiapp.classes;

import org.taxiapp.classes.users.Customer;
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
}
