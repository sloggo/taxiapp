package org.taxiapp.classes.users;

import org.taxiapp.classes.Map;
import org.taxiapp.interfaces.Observer;
import org.taxiapp.interfaces.Subject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class Customer extends User implements Observer {
    private String name;
    public Customer(String name, Map map, int x, int y){
        super(UUID.randomUUID().toString(), map);
        setLocation(x,y);
        this.name = name;
        this.type = "customer";
        map.addCustomer(this);

        if(!map.isTest()){
            addCustomerToCSV();
        }
    }

    public Customer(String id, String name, Map map, int x, int y){ // loading
        super(id, map);
        setLocation(x,y);
        this.name = name;
        this.type = "customer";
        map.addCustomer(this);
    }

    public void addCustomerToCSV(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(map.getId()+"-customers.csv", true))){
            // mapid, id, name, x, y
            String[] values = new String[5];
            values[0] = map.getId();
            values[1] = id;
            values[2] = name;
            values[3] = String.valueOf(location.getX());
            values[4] = String.valueOf(location.getY());

            String line = String.join(",",values);
            writer.write(line);
            writer.newLine();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setLocation(int x, int y){
        if(location != null){
            location.removeUser(this);
        }
        location = map.getLocation(x,y);
        location.addUser(this);
    }

    @Override
    public void update(Subject s){
        setLocation(s.getLocation().getX(), s.getLocation().getY());
    }
    @Override
    public void attach(Subject s){
        s.attachObserver(this);
    }
    @Override
    public void detach(Subject s){
        s.detachObserver(this);
    }
}
