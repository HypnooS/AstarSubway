/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astarsubway;

import java.util.ArrayList;

/**
 *
 * @author HypnoS
 */
public class Astar {
    public ArrayList<Station> stations;
    public ArrayList<List> openList;
    public ArrayList<List> closedList;
    //public CurrentStation currentStations;
    public int[][] matrix;
    public int nodeTime;
    public String path = "Path: ";
    public boolean alreadyPassHere=false;
    public int stationGoal=0;
    public int currentStation=0;
    public int currentHeristic=999999;
    public int intermidiateCurrentStation=9999999;
    public int totalCostPath=0;

    public Astar(ArrayList<Station> stations, int[][] matrix, int startStation, int stationGoal) {
        this.stations = stations;
        this.matrix = matrix;
        this.currentStation = startStation;
        this.stationGoal= stationGoal;
        openList = new ArrayList<>();
        closedList = new ArrayList<>();
    }
    
    public void addNodeOnClosedList(int numberStation, int heuristic){
        System.out.println("Adding node on Closed List: " + numberStation);
        closedList.add(new List(numberStation, heuristic));
    }
    
    public void addNodeOnOpenList(int numberStation, int heuristic){
        System.out.println("Adding node on Opened List: " + numberStation);
        openList.add(new List(numberStation, heuristic));
    }
 
    public int returnBestNodeOpenList(){
        for(int i=0; i < openList.size(); i++){
            if(openList.get(i).getHeuristic() < currentHeristic){
                this.currentStation = openList.get(i).getNumberStation();
                this.currentHeristic = openList.get(i).getHeuristic();
                return openList.get(i).getNumberStation();
            }
        }
        return currentStation;
    }
    public void addNodesToOpenList(int currentStation){
        System.out.println("Current Station: "+ currentStation);
        for(int i=0; i < matrix.length; i++){
            System.out.println("Station: "+currentStation+"|"+ i );
            if(matrix[currentStation][i] != -1 && thisNodeExistInOpenList(i) == false){
                System.out.println("Station: "+currentStation+"|"+ i +" H: "+ matrix[currentStation][i] + " + G:" + totalCostPath);
                addNodeOnOpenList(i,( matrix[currentStation][i] + totalCostPath ));
            }
        }
    }
    
    public boolean thisNodeExistInOpenList(int station){
        for(int i =0; i < openList.size(); i++){
            if(openList.get(i).getNumberStation() == station){
                return true;
            }            
        }
        return false;
    }
    
    public void removeNodeFromOpenList(int nextStation){
        System.out.println("Removing the nodeStation from the open list: "+ nextStation);
        for(int i =0; i < openList.size(); i++){
            if(openList.get(i).getNumberStation() == nextStation){
                closedList.add(openList.get(i));
                openList.remove(i);
            }            
        }
    }
    
    public void sumCostPath(int currentStation){
        this.totalCostPath = totalCostPath + (stations.get(currentStation).getTime() + stations.get(currentStation).getTimeWait());
    }
    
    public void search(){
        
    }
}
