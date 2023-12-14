package org.taxiapp.classes.users;

import org.taxiapp.classes.Location;

import static java.lang.Math.abs;

public class UserLocation {
    public String id;
    public int x;
    public int y;
    public boolean checked;
    public int gCost;
    public int fCost;
    public int hCost;
    public boolean open;
    public UserLocation parent;

    public UserLocation(int x, int y, String id){
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public void updateValues(UserLocation start, UserLocation destination) {
        // current and parent
        if(parent != null){
            int gDifX = abs(x - parent.x);
            int gDifY = abs(y - parent.y);
            gCost = parent.gCost + gDifX + gDifY;
        } else{
            gCost = 0;
        }


        // current and end
        int hDifX = abs(x - destination.x);
        int hDifY = abs(y - destination.y);
        hCost = hDifX + hDifY;

        fCost = gCost + hCost;
    }

    public boolean equals(UserLocation location){
        if(location.id.equals(id)){
            return true;
        }else{
            return false;
        }
    }
}
