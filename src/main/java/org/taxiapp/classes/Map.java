package org.taxiapp.classes;

import org.taxiapp.classes.users.Customer;
import org.taxiapp.classes.users.Pathfinding;
import org.taxiapp.classes.users.Taxi;
import org.taxiapp.interfaces.Observer;

import java.io.*;
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
        this.roads = Pathfinding.CSVToRoad();

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
        this.roads = Pathfinding.CSVToRoad();

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
