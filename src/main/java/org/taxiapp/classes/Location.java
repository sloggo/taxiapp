package org.taxiapp.classes;

import org.taxiapp.classes.users.Customer;
import org.taxiapp.classes.users.Taxi;
import org.taxiapp.classes.users.User;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private boolean road;
    private int x;
    private int y;
    private LinkedList<Taxi> taxis;
    private LinkedList<Customer> customers;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public LinkedList<Customer> getCustomers() {
        return customers;
    }

    public LinkedList<Taxi> getTaxis(){ return taxis; }

    public Location(int x, int y, boolean road){
        this.x = x;
        this.y = y;
        this.customers = new LinkedList<>();
        this.taxis = new LinkedList<>();
        this.road = road;
    }

    public String getMapTile() {
        if (isTaxisOnTile()) {
            return "\u001B[31m" + "t " + "\u001B[0m"; // Red color for taxis
        } else if (isCustomersOnTile()) {
            return "\u001B[34m" + "c " + "\u001B[0m"; // Blue color for customers
        } else if (road) {
            return "* ";
        } else {
            return ". ";
        }
    }


    public void addUser(Customer customer){
        System.out.println(customer.getId()+" added");
        customers.append(customer);
    }

    public void addUser(Taxi taxi){
        System.out.println(taxi.getId()+" added to x:"+x+", y:"+y);
        taxis.append(taxi);
    }

    public void removeUser(Taxi taxi){
        taxis.remove(taxi);
    }
    public void removeUser(Customer customer){
        customers.remove(customer);
    }
    public boolean isUsersOnTile(){
        return customers.isEmpty() && taxis.isEmpty();
    }

    public boolean isTaxisOnTile(){
        return !taxis.isEmpty();
    }

    public boolean isCustomersOnTile(){
        return !customers.isEmpty();
    }

    public LinkedList<String> getTaxiRegs(){
        LinkedList<String> taxiRegs = new LinkedList<>();
        if(!taxiRegs.isEmpty()){
            taxis.getHead();
            while (taxis.hasNext()) {
                Taxi node = taxis.retrieveCurrent();
                taxiRegs.append(node.getId());

                taxis.moveForward();
            }
            Taxi node = taxis.retrieveCurrent();
            taxiRegs.append(node.getId());

            taxis.moveForward();
        }
        return taxiRegs;
    }

    public LinkedList<User> getUsers(){
        LinkedList<User> users = new LinkedList<>();
        users.addAll(taxis);
        users.addAll(customers);

        return users;
    }

    public boolean isRoad() {
        return road;
    }
}
