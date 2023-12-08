package org.taxiapp.classes;

import org.taxiapp.classes.users.Customer;
import org.taxiapp.classes.users.Taxi;
import org.taxiapp.classes.users.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Map {
    private Location[][] grid;
    private int mapRadius;
    private List<Taxi> taxis;
    private List<Customer> customers;
    public Map(int r){
        this.customers = new ArrayList<>();
        this.taxis = new ArrayList<>();
        this.grid = new Location[r][r];
        this.mapRadius = r;

        for(int i=0; i<r; i++) { // columns
            for (int j = 0; j < r; j++) { // rows
                this.grid[i][j] = new Location(i, j); // init new location item;
            }
        }
    }

    public void printMap(){
        for(int i=0; i<mapRadius; i++) { // columns
            for (int j = 0; j < mapRadius; j++) { // rows
                Location location = grid[j][i]; // changed as was mixed up
                System.out.print(location.getMapTile());
            }
            System.out.println("");
        }
    }

    public Location getRandomLoc(){
        Random rand = new Random();
        int randomX = rand.nextInt(mapRadius);
        int randomY = rand.nextInt(mapRadius);
        return grid[randomX][randomY];
    }

    public Location getLocation(int x, int y){
        return grid[x][y];
    }
    public int getMapRadius(){
        return mapRadius;
    }
    public void addTaxi(Taxi taxi){ taxis.add(taxi); }
    public void addCustomer(Customer customer) { customers.add(customer); }

    public Taxi[] logCurrentTaxis(){
        Taxi[] currentTaxis = new Taxi[taxis.size()];
        for(int i=0; i<taxis.size(); i++){
            currentTaxis[i] = taxis.get(i);
        }
        return currentTaxis;
    }
    public Customer[] logCurrentCustomers(){
        Customer[] currentCustomers = new Customer[customers.size()];
        for(int i=0; i<customers.size(); i++){
            currentCustomers[i] = customers.get(i);
        }
        return currentCustomers;
    }
}
