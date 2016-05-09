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
    public ArrayList<List> openedList;
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

    public Astar(ArrayList<Station> stations, int[][] matrix, int startStation, int stationGoal) {
        this.stations = stations;
        this.matrix = matrix;
        this.currentStation = startStation;
        this.stationGoal= stationGoal;
        openedList = new ArrayList<>();
        closedList = new ArrayList<>();
    }
    
    public void addNodeOnClosedList(int numberStation, int heuristic){
        System.out.println("Adding node on Closed List: " + numberStation);
        closedList.add(new List(numberStation, heuristic));
    }
    
    public void addNodeOnOpenedList(int numberStation, int heuristic){
        System.out.println("Adding node on Opened List: " + numberStation);
        openedList.add(new List(numberStation, heuristic));
    }
 
    public int returnBestNodeOpenedList(){
        for(int i=0; i < openedList.size(); i++){
            if(openedList.get(i).getHeuristic() < currentHeristic){
                return openedList.get(i).getNumberStation();
            }
        }
        return currentStation;
    }
    public void addNodesAround(int currentStation){
        System.out.println("Current Station: "+ currentStation);
        for(int i=0; i < matrix.length; i++){
            System.out.println("Station: "+currentStation+ "|"+ i +" H: "+matrix[currentStation][i]);
            if(matrix[currentStation][i] != -1){
                
            }
        }
    }
    
}
