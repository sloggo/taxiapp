package org.taxiapp.classes.users;

import org.taxiapp.classes.*;
import org.taxiapp.classes.LinkedList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Pathfinding {
    private LocationNode start, goal, current;
    private LocationNode[][] nodeMap;
    private int mapRadius;
    private LinkedList<LocationNode> openList = new LinkedList<>();
    //private LinkedList<LocationNode> checkedList = new LinkedList<>();
    //private LinkedList<LocationNode> pathNodes = new LinkedList<>();
    boolean goalReached = false;

    private List<String> roads;

    private void createMap(){
        roads = CSVToRoad();
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

    public static List<String> CSVToRoad(){
        // Replace "your_file_path.csv" with the actual path to your CSV file
        String filePath = "roads.csv";

        try {
            // Read the CSV file
            Scanner scanner = new Scanner(new File(filePath));
            scanner.useDelimiter("-");

            // Convert the CSV data to a list of strings
            List<String> dataAsList = new ArrayList<>();
            while (scanner.hasNext()) {
                dataAsList.add(scanner.next());
            }

            // Close the scanner
            scanner.close();
            return dataAsList;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
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
        if(current == goal){
            LinkedList<LocationNode> path =  new LinkedList<>();
            path.append(current);
            return path;
        }

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
            openList.getHead();
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
