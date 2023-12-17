package org.taxiapp.classes.users;

import org.taxiapp.classes.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Pathfinding {
    private LocationNode start, goal, current;
    private LocationNode[][] nodeMap;
    private int mapRadius;
    private LinkedList<LocationNode> openList = new LinkedList<>();
    //private LinkedList<LocationNode> checkedList = new LinkedList<>();
    //private LinkedList<LocationNode> pathNodes = new LinkedList<>();
    boolean goalReached = false;

    private void createMap(){
        this.nodeMap = new LocationNode[mapRadius][mapRadius];
        for(int i = 0; i< mapRadius; i++) { // columns
            for (int j = 0; j < mapRadius; j++) { // rows
                this.nodeMap[i][j] = new LocationNode(i,j); // init new location item;
            }
        }
    }

    private void getCost(LocationNode node){
        //set G cost
        int xDistance = Math.abs(node.getX() - start.getX());
        int yDistance = Math.abs(node.getY() - start.getY());
        node.setgCost(xDistance+yDistance);
        //set H cost
        xDistance = Math.abs(node.getX() - goal.getX());
        yDistance = Math.abs(node.getY() - goal.getY());
        node.sethCost(xDistance+yDistance);
        //set F cost
        node.setfCost(node.getgCost()+node.gethCost());

    }
    public LinkedList<LocationNode> search(int mapRadius, Location startLoc, Location destination){
        this.mapRadius = mapRadius;
        createMap();
        this.start = nodeMap[startLoc.getX()][startLoc.getY()];
        start.setStart();
        this.goal = nodeMap[destination.getX()][destination.getY()];
        goal.setGoal();
        this.current = start;

        getCost(start);
        getCost(goal);

        while (goalReached==false){

            int x = current.getX();
            int y = current.getY();

            current.setChecked();
            //checkedList.append(current);
            openList.remove(current);
            //openList.printList();

            int maxX = nodeMap.length;
            int maxY = nodeMap[0].length;
            //OPEN UP NODE

            if (current.getY() + 1 < maxY) {
                LocationNode upNode = nodeMap[current.getX()][current.getY() + 1];
                getCost(upNode);
                openNode(upNode);
            }
            // Check DOWN neighbor
            if (current.getY() - 1 >= 0) {
                LocationNode downNode = nodeMap[current.getX()][current.getY() - 1];
                getCost(downNode);
                openNode(downNode);
            }

            // Check RIGHT neighbor
            if (current.getX() + 1 < maxX) {
                LocationNode rightNode = nodeMap[current.getX() + 1][current.getY()];
                getCost(rightNode);
                openNode(rightNode);
            }

            // Check LEFT neighbor
            if (current.getX() - 1 >= 0) {
                LocationNode leftNode = nodeMap[current.getX() - 1][current.getY()];
                getCost(leftNode);
                openNode(leftNode);
            }

            //Find best node

            int bestNodeIndex = 0;
            int bestNodeFCost = Integer.MAX_VALUE;
            openList.pointToHead();
            for (int i = 0; i < openList.length(); i++) {
                int fCost = openList.get(i).getfCost();
                int gCost = openList.get(i).getgCost();
                if(fCost<bestNodeFCost){
                    bestNodeFCost = openList.get(i).getfCost();
                    bestNodeIndex = i;
                }

                else if(gCost==bestNodeFCost){
                    if(gCost<openList.get(bestNodeIndex).getgCost()){
                        bestNodeIndex = i;
                    }
                }
                openList.moveForward();
            }
            current = openList.get(bestNodeIndex);

            if(current.isGoal()){
                goalReached=true;
                return trackThePath();
            }
        }
        return null;
    }
    private void openNode(LocationNode node){
        if(!node.isOpen() && !node.isChecked() && !node.isStart()){
            node.setOpen();
            node.setParent(current);
            openList.append(node);
        }
    }
    private LinkedList<LocationNode> trackThePath(){
        LocationNode pathNode = goal;
        List<LocationNode> pathListList = new ArrayList<>();
        LinkedList<LocationNode> pathList = new LinkedList<>();
        while (pathNode!=start){
            pathListList.add((pathNode));
            pathNode = pathNode.getParent();
        }

        for(int i=0; i<pathListList.size(); i++){
            pathList.append(pathListList.get(pathListList.size()-1-i));
        }
        return pathList;
    }

}
