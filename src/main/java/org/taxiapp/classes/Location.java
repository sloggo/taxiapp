package org.taxiapp.classes;

import org.taxiapp.classes.users.Customer;
import org.taxiapp.classes.users.Taxi;
import org.taxiapp.classes.users.User;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private int x;
    private int y;
    private List<Taxi> taxis;
    private List<Customer> customers;
    private String mapTile;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Taxi> getTaxis(){
        for(Taxi taxi: taxis){
            System.out.println(taxi.getId());
        }
        return taxis;
    }

    public String getMapTile() {
        return mapTile;
    }

    public Location(int x, int y){
        this.x = x;
        this.y = y;
        this.mapTile = "|_|";
        this.customers = new ArrayList<>();
        this.taxis = new ArrayList<>();
    }

    public void addUser(Customer customer){
        System.out.println(customer.getId()+" added");
        customers.add(customer);
    }

    public void addUser(Taxi taxi){
        System.out.println(taxi.getId()+" added to x:"+x+", y:"+y);
        taxis.add(taxi);
    }

    public boolean isUsersOnTile(){
        if(!(customers.isEmpty() && taxis.isEmpty())){
            return false;
        } else{
            return true;
        }
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        users.addAll(taxis);
        users.addAll(customers);

        return users;
    }
}
