package org.taxiapp.classes.users;

import org.taxiapp.classes.*;

import java.util.ArrayList;
import java.util.Arrays;
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

    private List<String> roads = new ArrayList<>(Arrays.asList(
            "3,0", "4,0", "5,0", "6,0", "7,0", "8,0", "9,0", "10,0", "11,0", "12,0",
            "0,1", "12,1",
            "0,2", "12,2",
            "0,3", "12,3",
            "0,4", "1,4", "2,4", "3,4", "4,4", "5,4", "6,4", "7,4", "8,4", "9,4", "10,4", "11,4", "12,4",
            "0,5", "12,5",
            "0,6", "1,6", "2,6", "3,6", "4,6", "5,6", "6,6", "7,6", "8,6", "9,6", "10,6", "11,6", "12,6",
            "6,7", "7,7", "8,7", "9,7", "10,7", "11,7", "12,7",
            "6,8", "7,8", "8,8", "9,8", "10,8", "11,8", "12,8",
            "6,9", "7,9", "8,9", "9,9", "10,9", "11,9", "12,9",
            "0,10", "1,10", "2,10", "3,10", "4,10", "5,10", "6,10", "7,10", "8,10", "9,10", "10,10", "11,10", "12,10",
            "0,11", "12,11",
            "0,12", "12,12",
            "0,13", "12,13",
            "0,14", "12,14",
            "0,15", "1,15", "2,15", "3,15", "4,15", "5,15", "6,15", "7,15", "8,15", "9,15", "10,15", "11,15", "12,15"
            ));

    private void createMap(){
        this.nodeMap = new LocationNode[mapRadius][mapRadius];
        for(int i = 0; i< mapRadius; i++) { // columns
            for (int j = 0; j < mapRadius; j++) { // rows
                if(roads.contains(i+","+j)){
                    this.nodeMap[i][j] = new LocationNode(i, j, true); // init new location item;
                } else{
                    this.nodeMap[i][j] = new LocationNode(i, j, false); // init new location item;

                }
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
                if(upNode.isRoad()){
                    getCost(upNode);
                    openNode(upNode);
                }
            }
            // Check DOWN neighbor
            if (current.getY() - 1 >= 0) {
                LocationNode downNode = nodeMap[current.getX()][current.getY() - 1];
                if(downNode.isRoad()){
                    getCost(downNode);
                    openNode(downNode);
                }
            }

            // Check RIGHT neighbor
            if (current.getX() + 1 < maxX) {
                LocationNode rightNode = nodeMap[current.getX() + 1][current.getY()];
                if(rightNode.isRoad()){
                    getCost(rightNode);
                    openNode(rightNode);
                }
            }

            // Check LEFT neighbor
            if (current.getX() - 1 >= 0) {
                LocationNode leftNode = nodeMap[current.getX() - 1][current.getY()];
                if(leftNode.isRoad()){
                    getCost(leftNode);
                    openNode(leftNode);
                }
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
