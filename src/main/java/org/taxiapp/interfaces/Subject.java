package org.taxiapp.interfaces;

public interface Subject {
    void attachObserver(Observer o);
    void detachObserver(Observer o);
    void notifyObservers();
}
