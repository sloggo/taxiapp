package org.taxiapp.interfaces;

import org.taxiapp.classes.Location;

public interface Subject {
    void attachObserver(Observer o);
    void detachObserver(Observer o);
    void notifyObservers();
    Location getLocation();
}
