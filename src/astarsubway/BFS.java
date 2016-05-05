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
public class BFS {   
    public ArrayList<Station> stations;
    public CurrentStation currentStations;
    public int[][] matrix;
    public int[] timeHeristic;
    public int nodeTime;
    public String path = "Path: ";
    public boolean alreadyPassHere=false;
    public int firstStation=0;
    public int stationGoal=0;
    public int currentStation=0;
    public int currentHeristic=999999;
    public int currentPath=9999999;

    @Override
    public String toString() {
        return "("+currentStation+")";
    }
    
    
    public BFS(ArrayList<Station> stations, int[][] matrix, int nodeStart, int nodeGoal){
        this.stations = stations;
        this.matrix = matrix;
        this.firstStation = nodeStart;
        this.currentStation = nodeStart;
        this.stationGoal = nodeGoal;
        currentStations = new CurrentStation();
        currentStations.setCurrentPath(-1);
        currentStations.setCurrentStation(nodeStart);
        currentStations.setCurrentHeristic(99999);
        
    }
    
    public int getHeuristic(int numStation){
        return timeHeristic[numStation];
    }

    /**
     *  Depth search method!!!!
     * @deprecated
     */
    @Deprecated
    public void Depth(){
        for(int i=0; i < stationGoal; i++ ){
            for(int j=0; j < matrix.length; j++){
                if(matrix[j][i] == 0){
                    System.out.println("Found!");
                }
                if(matrix[j][i] < currentHeristic && matrix[j][i] != -1 ){
                    this.currentHeristic = matrix[j][i];
                    System.out.println("waking throw Station "+ j);
                }
                System.out.println("Station "+ j);
            }
            this.currentHeristic = 0;
        }
        System.out.println("Station found");
    }
    
    
    public void bestNodeAround(){
        System.out.println("Current Station is "+ currentStation);
        for(int i=0; i < matrix.length; i++){ 
            System.out.println("Station: "+currentStation+ "|"+ i +" H: "+matrix[currentStation][i]);
            if(matrix[currentStation][i] < currentHeristic && matrix[currentStation][i] != -1){
                System.out.println("New best Station: "+i+" H: "+matrix[currentStation][i]);
                this.currentPath=i;
                this.currentHeristic = matrix[currentStation][i];
            }
            if(matrix[i][currentStation] == 0){
                this.alreadyPassHere=true;
            }
        }
        this.currentStation = currentPath;
        System.out.println("Current Station is "+ currentStation);
        nodeCounter(currentStation);
        
    }
    
    public void search(){
        nodeCounter(currentStation);
        path = path.concat(concatPath(currentStation));
        while(alreadyPassHere==false){
            bestNodeAround();
            path = path.concat(concatPath(currentStation));
        }
        path = path.concat("|");
    }
    
    public void nodeCounter(int currentStation){
        nodeTime = stations.get(currentStation).getTime() + nodeTime; //+ stations.get(currentStation).getTimeWait() 
    }
    
    public String concatPath(int currentStation){
        return "["+currentStation+"]->";
    }
    
    //trying to use object for get the data.
    public void bestNodeAround2(){
        System.out.println("Current Station is "+ currentStations.getCurrentStation());
        for(int i=0; i < matrix.length; i++){ 
            System.out.println("Station: "+currentStations.getCurrentStation()+ "|"+ i +" H: "+matrix[i][currentStations.getCurrentStation()]);
            if(matrix[i][currentStations.getCurrentStation()] < currentStations.getCurrentHeristic() && matrix[i][currentStations.getCurrentStation()] != -1){
                System.out.println("Entering in Station: "+i+" H: "+matrix[i][currentStations.getCurrentStation()]);
                currentStations.setCurrentPath(i);
                currentStations.setCurrentHeristic(matrix[i][currentStations.getCurrentStation()]);
                
            }
        }
        currentStations.setCurrentStation(currentStations.getCurrentPath());
        System.out.println("Current Station is "+ currentStation);
    }
    
    
}
