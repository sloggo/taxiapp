package org.taxiapp.classes;

import org.taxiapp.classes.users.Customer;
import org.taxiapp.classes.users.Pathfinding;
import org.taxiapp.classes.users.Taxi;
import org.taxiapp.interfaces.Observer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class Map {
    private boolean test;
    private String id;
    private Location[][] grid;
    private int mapRadius;
    private LinkedList<Taxi> taxis;
    private LinkedList<Customer> customers;
    private List<String> roads;
    public Map(int r){
        this.test = false;
        this.id = UUID.randomUUID().toString();
        this.customers = new LinkedList<>();
        this.taxis = new LinkedList<>();
        this.grid = new Location[r][r];
        this.mapRadius = r;
        this.roads = new ArrayList<>(Arrays.asList(
                "0,0", "1,0", "1,1", "1,2", "1,3", "1,4", "1,5", "1,6", "1,7",
                "2,0", "2,3", "2,8",
                "3,0", "3,1", "3,2", "3,3", "3,4", "3,5", "3,8",
                "4,0", "4,5", "4,8",
                "5,0", "5,5", "5,8",
                "6,0", "6,1", "6,2", "6,3", "6,4", "6,5", "6,6", "6,7", "6,8", "6,9",
                "7,5",
                "8,5",
                "9,5"
        ));

        for(int i = 0; i< getMapRadius(); i++) { // columns
            for (int j = 0; j < getMapRadius(); j++) { // rows
                if(roads.contains(i+","+j)){
                    this.grid[i][j] = new Location(i, j, true); // init new location item;
                } else{
                    this.grid[i][j] = new Location(i, j, false); // init new location item;

                }
            }
        }
    }

    public Map(int r, String id, boolean test) throws IOException {
        this.test = test;
        this.id = id;
        this.customers = new LinkedList();
        this.taxis = new LinkedList();
        this.grid = new Location[r][r];
        this.mapRadius = r;
        this.roads = new ArrayList<>(Arrays.asList(
                "0,2", "1,2", "2,2", "3,2", "4,2", "5,2", "6,2", "7,2", "8,2", "9,2", "10,2", "11,2", "12,2", "13,2", "14,2",
                "0,3", "7,3", "8,3", "14,3",
                "0,4", "7,4", "8,4", "14,4",
                "0,5", "7,5", "8,5", "14,5",
                "0,6", "1,6", "2,6", "3,6", "4,6", "5,6", "6,6", "7,6", "8,6", "9,6", "10,6", "11,6", "12,6", "13,6", "14,6",
                "0,7", "7,7", "8,7", "14,7",
                "0,8", "7,8", "8,8", "14,8",
                "0,9", "7,9", "8,9", "14,9",
                "0,10", "1,10", "2,10", "3,10", "4,10", "5,10", "6,10", "7,10", "8,10", "9,10", "10,10", "11,10", "12,10", "13,10", "14,10",
                "0,11", "7,11", "8,11", "14,11",
                "0,12", "7,12", "8,12", "14,12",
                "0,13", "7,13", "8,13", "14,13",
                "0,14", "1,14", "2,14", "3,14", "4,14", "5,14", "6,14", "7,14", "8,14", "9,14", "10,14", "11,14", "12,14", "13,14", "14,14",
                "0,15", "7,15", "8,15", "14,15",
                "0,16", "7,16", "8,16", "14,16",
                "0,17", "7,17", "8,17", "14,17",
                "0,18", "1,18", "2,18", "3,18", "4,18", "5,18", "6,18", "7,18", "8,18", "9,18", "10,18", "11,18", "12,18", "13,18", "14,18",
                "0,19", "7,19", "8,19", "14,19"
        ));

        for(int i = 0; i< getMapRadius(); i++) { // columns
            for (int j = 0; j < getMapRadius(); j++) { // rows
                if(roads.contains(i+","+j)){
                    this.grid[i][j] = new Location(i, j, true); // init new location item;
                } else{
                    this.grid[i][j] = new Location(i, j, false); // init new location item;

                }
            }
        }

        if(!test){
            importTaxis();
            importCustomers();
        }
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
    public String getId(){
        return id;
    }

    public void removeUser(Taxi taxi){
        taxis.remove(taxi);
    }

    public Customer findUser(String id){
        if(!customers.isEmpty()){
            customers.getHead();
            boolean found = false;
            while (customers.hasNext()) {
                Customer node = customers.retrieveCurrent();
                if(node.getName().equals(id)){
                    found = true;
                    return node;
                }
                customers.moveForward();
            }
            if(!found){
                Customer node = customers.retrieveCurrent();
                if(node.getName().equals(id)){
                    return node;
                }
            }

        }
        return null;
    }

    public void importCustomers() throws IOException {
        File yourFile = new File(id+"-customers.csv");
        yourFile.createNewFile(); // if file already exists will do nothing
        try (BufferedReader reader = new BufferedReader(new FileReader(id+"-customers.csv"))) {
            String newLine;
            while ((newLine = reader.readLine()) != null) {
                // Check if the line is not empty or just whitespace
                if (!newLine.trim().isEmpty()) {
                    String[] values = newLine.split(",");
                    if (values[0].equals(id)) {
                        new Customer(values[1], values[2], this, Integer.parseInt(values[3]), Integer.parseInt(values[4]));
                    }
                }
            }
        }
    }

    public void printMap(){
        for(int i = 0; i< getMapRadius(); i++) { // columns
            for (int j = 0; j < getMapRadius(); j++) { // rows
                Location location = grid[j][i]; // changed as was mixed up
                System.out.print(location.getMapTile());
            }
            System.out.println("");
        }
    }

    public void importTaxis() throws IOException{
        File yourFile = new File(id+"-taxis.csv");
        yourFile.createNewFile(); // if file already exists will do nothing
        try (BufferedReader reader = new BufferedReader(new FileReader(id+"-taxis.csv"))) {
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


    public Location getRandomLoc(){
        boolean valid = false;
        Location loc;
        while(!valid){
            Random rand = new Random();
            int randomX = rand.nextInt(getMapRadius());
            int randomY = rand.nextInt(getMapRadius());
            loc = grid[randomX][randomY];
            if(loc.isRoad()){
                valid = true;
                return loc;
            }
        }
        return null;
    }

    public Location getLocation(int x, int y){
        return grid[x][y];
    }

    public int getMapRadius(){
        return mapRadius;
    }

    public void addTaxi(Taxi taxi){ taxis.append(taxi); }

    public void addCustomer(Customer customer) { customers.append(customer); }

    public Taxi[] logCurrentTaxis(){
        Taxi[] currentTaxis = new Taxi[taxis.length()];
        for(int i=0; i<taxis.length(); i++){
            currentTaxis[i] = taxis.get(i);
        }
        return currentTaxis;
    }
    public Customer[] logCurrentCustomers(){
        Customer[] currentCustomers = new Customer[customers.length()];
        for(int i=0; i<customers.length(); i++){
            currentCustomers[i] = customers.get(i);
        }
        return currentCustomers;
    }
    public boolean isTest(){return test;}
}
