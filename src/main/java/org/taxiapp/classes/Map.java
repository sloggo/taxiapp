package org.taxiapp.classes;
import java.io.*;

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

        loadCSVs();
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
    public void addTaxi(Taxi taxi){
        taxis.add(taxi);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("taxis.csv", true))) {
            String[] values = formatTaxiToCSV(taxi);
            writer.write(String.join(",", values));
            writer.newLine();

            System.out.println("taxi added to db");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadTaxi(Taxi taxi){
        taxis.add(taxi);
    }
    public void addCustomer(Customer customer) {
        customers.add(customer);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("customers.csv", true))) {
            String[] values = formatCustomerToCSV(customer);
            writer.write(String.join(",", values));
            writer.newLine();
            System.out.println("customer added to db");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadCustomer(Customer customer){
        customers.add(customer);
    }


    public Taxi[] logCurrentTaxis(){
        Taxi[] currentTaxis = new Taxi[taxis.size()];
        for(int i=0; i<taxis.size(); i++){
            currentTaxis[i] = taxis.get(i);
            System.out.println(currentTaxis[i].getId());
        }
        return currentTaxis;
    }
    public Customer[] logCurrentCustomers(){
        Customer[] currentCustomers = new Customer[customers.size()];
        for(int i=0; i<customers.size(); i++){
            currentCustomers[i] = customers.get(i);
            System.out.println(currentCustomers[i].getId());
        }
        return currentCustomers;
    }


    public void resetCSVs(){
        String filepathT = "taxis.csv";
        String filepathC = "customers.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepathT))) {
            for(Taxi taxi: taxis) {
                String[] values = formatTaxiToCSV(taxi);
                writer.write(String.join(",", values));
                writer.newLine();
            }

            System.out.println("CSV file created successfully at: " + filepathT);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepathC))) {
            for(Customer customer: customers){
                String[] values = formatCustomerToCSV(customer);
                writer.write(String.join(",", values));
                writer.newLine();
            }

            System.out.println("CSV file created successfully at: " + filepathC);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadCSVs() {
        String filepathT = "taxis.csv";
        String filepathC = "customers.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(filepathT))) {
            String line;
            while ((line = reader.readLine()) != null) {
                csvLineToTaxi(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filepathC))) {
            String line;
            while ((line = reader.readLine()) != null) {
                csvLineToCustomer(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] formatTaxiToCSV(Taxi taxi){
        String[] values;
        //id, rate, x,y, ratings
        if(taxi.getRatings() != null){
            String[] ratings = new String[taxi.getRatings().length];
            int j = 0;
            for(int rating: taxi.getRatings()){
                ratings[j] = String.valueOf(rating);
                j++;
            }
            values = new String[4+ratings.length];
            for(int i=0; i<taxi.getRatings().length; i++){
                values[i+3] = ratings[i];
            }
        } else{
            values = new String[4];
        }

        values[0] = taxi.getId();
        values[1] = String.valueOf(taxi.getRate());
        values[2] = String.valueOf(taxi.getLocation().getX());
        values[3] = String.valueOf(taxi.getLocation().getY());

        return values;
    }

    public String[] formatCustomerToCSV(Customer customer){
        String[] values;
        //id, name, x,y
        values = new String[4];

        values[0] = customer.getId();
        values[1] = customer.getName();
        values[2] = String.valueOf(customer.getLocation().getX());
        values[3] = String.valueOf(customer.getLocation().getY());

        return values;
    }
    public void csvLineToTaxi(String line){
        String[] values = line.split(",");
        int[] ratings = new int[values.length-4];
        for(int i=4; i<values.length; i++){
            ratings[i-4] = Integer.parseInt(values[i]);
        }
        Taxi taxi = new Taxi(values[0], this, Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[1]), ratings);
    }

    public void csvLineToCustomer(String line){
        String[] values = line.split(",");
        Customer customer = new Customer(values[1], this, Integer.parseInt(values[2]), Integer.parseInt(values[3]), values[0]);
    }
}
