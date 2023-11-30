package org.taxiapp.classes;

import org.taxiapp.classes.users.User;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Map {
    private Location[][] grid;
    private int mapRadius;
    public Map(int r){
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
                Location location = grid[i][j];
                System.out.print(location.getMapTile());

                if(location.isUsersOnTile()) {
                    List<User> usersOnTile = location.getUsers();
                    for(User user : usersOnTile){
                        System.out.print(user.getType());
                    }
                }
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
}
