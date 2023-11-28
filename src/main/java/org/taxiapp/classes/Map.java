package org.taxiapp.classes;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Map {
    private Location[][] grid;
    public Map(int r){
        this.grid = new Location[r][r];

        for(int i=0; i<r; i++) { // columns
            for (int j = 0; j < r; j++) { // rows
                this.grid[i][j] = new Location(i, j); // init new location item
                System.out.println(this.grid[i][j].getX()+", "+this.grid[i][j].getY());
            }
        }
    }
}
