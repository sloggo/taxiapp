package org.taxiapp.classes;

import org.taxiapp.interfaces.*;


import java.util.ArrayList;
import java.util.List;

public class RideRequestSystem implements Subject {
    private List<Observer> observers = new ArrayList();
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
