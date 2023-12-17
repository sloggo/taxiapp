package org.taxiapp.interfaces;

public interface Observer {
    public abstract void update(Subject s);
    public abstract void attach(Subject s);
    public abstract void detach(Subject s);

}
