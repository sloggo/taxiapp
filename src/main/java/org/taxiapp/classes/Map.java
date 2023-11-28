package org.taxiapp.classes;

import java.lang.reflect.Array;
import java.util.Arrays;

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

        printMap();
    }

    public void printMap(){
        for(int i=0; i<mapRadius; i++) { // columns
            for (int j = 0; j < mapRadius; j++) { // rows
                System.out.print(grid[i][j].getMapTile());
            }
            System.out.println("");
        }
    }
}
