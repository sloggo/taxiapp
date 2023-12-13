package org.taxiapp.classes;

import org.taxiapp.classes.users.Customer;
import org.taxiapp.classes.users.Taxi;
import org.taxiapp.classes.users.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Map {
    private boolean test;
    private String id;
    private Location[][] grid;
    private int mapRadius;
    private List<Taxi> taxis;
    private List<Customer> customers;
    public Map(int r) throws IOException {
        this.test = false;
        this.id = UUID.randomUUID().toString();
        this.customers = new ArrayList<>();
        this.taxis = new ArrayList<>();
        this.grid = new Location[r][r];
        this.mapRadius = r;

        for(int i=0; i<r; i++) { // columns
            for (int j = 0; j < r; j++) { // rows
                this.grid[i][j] = new Location(i, j); // init new location item;
            }
        }

        importTaxis();
    }

    public Map(int r, String id, boolean test) throws IOException {
        this.test = test;
        this.id = id;
        this.customers = new ArrayList<>();
        this.taxis = new ArrayList<>();
        this.grid = new Location[r][r];
        this.mapRadius = r;

        for(int i=0; i<r; i++) { // columns
            for (int j = 0; j < r; j++) { // rows
                this.grid[i][j] = new Location(i, j); // init new location item;
            }
        }

        if(!test){
            importTaxis();
        }
    }

    public void importTaxis() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("taxis.csv"))) {
            String newLine;
            while ((newLine = reader.readLine()) != null) {
                // Check if the line is not empty or just whitespace
                if (!newLine.trim().isEmpty()) {
                    String[] values = newLine.split(",");
                    if (values[0].equals(id)) {
                        int[] ratings = new int[values.length - 5];
                        for (int i = 5; i < values.length; i++) {
                            ratings[i - 5] = Integer.parseInt(values[i]);
                        }
                        new Taxi(values[1], this, Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[4]), ratings);
                    }
                }
            }
        }
    }
    public String getId(){
        return id;
    }
    public boolean isTest(){return test;}

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
