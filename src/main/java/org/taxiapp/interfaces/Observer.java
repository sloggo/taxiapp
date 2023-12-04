package org.taxiapp.interfaces;

public interface Observer {
    public abstract void update();
    void attach();
    void detach();
}
