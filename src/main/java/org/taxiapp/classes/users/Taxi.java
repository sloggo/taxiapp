package org.taxiapp.classes.users;

import org.taxiapp.classes.Location;
import org.taxiapp.classes.Map;
import org.taxiapp.classes.Rating;
import org.taxiapp.classes.users.User;
import org.taxiapp.interfaces.Observer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Taxi extends User implements Observer {
    private int rate; // eur/km base rate
    private int[] ratings;
    private boolean currentlyPathfinding;
    private boolean done;
    private UserLocation destination;
    private List<UserLocation> openTiles;
    private List<UserLocation> checked;
    public Taxi(String registration, Map map, int x, int y){
        super(registration, map);
        setLocation(x,y);
        this.rate = 10;
        this.type = "taxi";
        this.ratings = new int[0];
        this.currentlyPathfinding = false;
        map.addTaxi(this);

        if(!map.isTest()){
            addTaxiToCSV();
        }
    }
    public Taxi(String registration, Map map, int x, int y, int rate, int[] ratings){ // load taxi
        super(registration, map);
        setLocation(x,y);
        this.rate = rate;
        this.type = "taxi";
        this.ratings = ratings;
        this.currentlyPathfinding = false;
        map.addTaxi(this);
    }

    public void setLocation(int x, int y){
        location = map.getLocation(x,y);
        location.addUser(this);
    }

    public void addTaxiToCSV(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("taxis.csv", true))){
            // mapid, registration, x , y, rate, ratings
            String[] values = new String[5+ratings.length];
            values[0] = map.getId();
            values[1] = id;
            values[2] = String.valueOf(location.getX());
            values[3] = String.valueOf(location.getY());
            values[4] = String.valueOf(rate);
            for(int i=5; i<values.length; i++){
                values[i] = String.valueOf(ratings[i-5]);
            }
            String line = String.join(",",values);
            writer.write(line);
            writer.newLine();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void resetPathfinding(){
        currentlyPathfinding = false;
        destination = null;
        openTiles = new ArrayList<>();
    }

    public void pathfindTo(Location d){
        destination = new UserLocation(d.getX(),d.getY(), d.getId());
        Location start = location;
        UserLocation currentTile = new UserLocation(start.getX(), start.getY(), start.getId());
        currentTile.parent = null;
        openTiles = new ArrayList<>();
        checked = new ArrayList<>();

        search(currentTile, currentTile);
    }
    private void search(UserLocation currentTile, UserLocation start){
        checked.add(currentTile);
        String xPos = "("+(currentTile.x+1)+","+(currentTile.y)+")";
        String yPos = "("+(currentTile.x)+","+(currentTile.y+1)+")";
        String xNeg = "("+(currentTile.x-1)+","+(currentTile.y)+")";
        String yNeg = "("+(currentTile.x)+","+(currentTile.y-1)+")";

        if(!userLocationListContains(checked, xPos) && currentTile.x+1 < map.getMapRadius()){
            UserLocation newLoc = new UserLocation(currentTile.x+1, currentTile.y, xPos);
            openTiles.add(newLoc);
        }
        if(!userLocationListContains(checked, yPos) && currentTile.y+1 < map.getMapRadius()){
            UserLocation newLoc = new UserLocation(currentTile.x, currentTile.y+1, yPos);
            openTiles.add(newLoc);
        }
        if(!userLocationListContains(checked, xNeg) && currentTile.x-1 >= 0){
            UserLocation newLoc = new UserLocation(currentTile.x-1, currentTile.y, xNeg);
            openTiles.add(newLoc);
        }
        if(!userLocationListContains(checked, yNeg) && currentTile.y-1 >= 0){
            UserLocation newLoc = new UserLocation(currentTile.x, currentTile.y-1, yNeg);
            openTiles.add(newLoc);
        }

        for(UserLocation tile: openTiles){
            tile.updateValues(start, destination);
        }

        int bestIndex = -1;
        int bestFValue = 9999;

        for (int i = 0; i < openTiles.size(); i++) {
            if (currentTile == openTiles.get(i)) {
                continue;
            }
            UserLocation currentComparison = openTiles.get(i);
            if (currentComparison.fCost < bestFValue) {
                bestIndex = i;
                bestFValue = currentComparison.fCost;
            } else if (currentComparison.fCost == bestFValue) {
                if (currentComparison.gCost < openTiles.get(bestIndex).gCost) {
                    bestIndex = i;
                }
            }
        }
        UserLocation oldCurrentTile = currentTile;
        System.out.println("Current: "+currentTile.id);
        openTiles.get(bestIndex).parent = currentTile;

        currentTile = openTiles.get(bestIndex);

        System.out.println("Next best: "+currentTile.id);
        openTiles.remove(currentTile);

        if(currentTile.equals(destination)){
            destination.parent = oldCurrentTile;
            System.out.println("Destination Found");
            List<UserLocation> path = retracePath();
        } else{
            search(currentTile, start);
        }
    }

    public boolean userLocationListContains(List<UserLocation> list, String id){
        boolean found = false;
        for(UserLocation item: list){
            if(item.id.equals(id)){
                found = true;
            }
        }
        return found;
    }

    public List<UserLocation> retracePath() {
        List<UserLocation> path = new ArrayList<>();
        UserLocation current = destination;

        while (current != null && !current.id.equals(location.getId())) {
            path.add(current);
            current = current.parent;
        }

        Collections.reverse(path);

        for (UserLocation loc : path) {
            System.out.println("(" + loc.x + "," + loc.y + ")");
        }

        return path;
    }

    @Override
    public void update() {

    }

    @Override
    public void attach() {

    }

    @Override
    public void detach() {

    }
}
