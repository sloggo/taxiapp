package org.taxiapp.classes;

public class LocationNode {
    private int x;
    private int y;
    private int gCost;
    private int hCost;
    private int fCost;
    private LocationNode parent;
    private boolean checked;
    private boolean open;
    private boolean goal;
    private boolean start;
    private boolean road;

    public LocationNode(int x, int y, boolean road) {
        this.x = x;
        this.y = y;
        this.road = road;
    }
    public boolean isRoad(){return road;}

    public boolean isChecked() {
        return checked;
    }

    public void setChecked() {
        if(!isStart() && !isGoal()){
            checked = true;
            }
        }

    public boolean isOpen() { return open; }

    public void setOpen() { open = true; }

    public boolean isGoal() { return goal; }

    public void setGoal() { goal = true; }

    public boolean isStart() { return start; }

    public void setStart() { start = true; }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getgCost() {
        return gCost;
    }

    public void setgCost(int gCost) {
        this.gCost = gCost;
    }

    public int gethCost() {
        return hCost;
    }

    public void sethCost(int hCost) {
        this.hCost = hCost;
    }

    public int getfCost() {
        return fCost;
    }

    public void setfCost(int fCost) {
        this.fCost = fCost;
    }

    public LocationNode getParent() {
        return parent;
    }

    public void setParent(LocationNode parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)",x,y);
    }
}
