package org.taxiapp.classes;

import org.taxiapp.interfaces.*;


import java.util.ArrayList;
import java.util.List;

public class RideRequestSystem  {
    private List<Observer> observers = new ArrayList();
    public void attachObserver(Observer observer) {
        observers.add(observer);
    }

    public void detachObserver(Observer observer) {
        observers.remove(observer);
    }
    
    public void notifyObservers() {
        for (Observer o :observers) {
            o.update();
        }
    }
}
