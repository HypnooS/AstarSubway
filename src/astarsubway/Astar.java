/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astarsubway;

import java.util.*;

/**
 *
 * @author HypnoS
 */
public class Astar implements Runnable{
    public ArrayList<Station> stations;
    public ArrayList<List> openList;
    public ArrayList<List> closedList;
    public int[][] matrix;
    public int nodeTime;
    public String path = "Path: ";
    public boolean alreadyPassHere=false;
    public int stationGoal=0;
    public int fatherStation=-1;
    public int currentStation=0;
    public int currentHeuristic=999999;
    public int totalCostPath=0;
    public int x=0;
    public String bestTracking="";

    public Astar(ArrayList<Station> stations, int[][] matrix, int startStation, int stationGoal) {
        this.stations = stations;
        this.matrix = matrix;
        this.currentStation = startStation;
        this.stationGoal= stationGoal;
        openList = new ArrayList<>();
        closedList = new ArrayList<>();
    }
    
    public void addNodeOnClosedList(int fatherStation ,int numberStation, int heuristic){
        System.out.println("Adding node on Closed List: " + numberStation);
        closedList.add(new List(fatherStation, numberStation, heuristic));
    }
    
    public void addNodeOnOpenList(int fatherStation,int numberStation, int heuristic){
        System.out.println("Adding node on Opened List: " + numberStation);
        openList.add(new List(fatherStation, numberStation, heuristic));
    }
 
    public void returnBestNodeOpenList(){
        
        this.currentHeuristic = openList.get(0).getHeuristic();
        this.currentStation = openList.get(0).getNumberStation();
        this.fatherStation = openList.get(0).getFatherStation();

        for(int i=0; i < openList.size(); i++){
            System.out.println("returning best Station: "+openList.get(i).getNumberStation() +"Heristic: "+ openList.get(i).getHeuristic());
            if(this.currentHeuristic > openList.get(i).getHeuristic()){
                this.currentStation = openList.get(i).getNumberStation();
                this.currentHeuristic = openList.get(i).getHeuristic();
                this.fatherStation = openList.get(i).getFatherStation();
                
            }
        }
  
    }
    public void expandChildrenStations(int childStation){
        System.out.println("Current Station: "+ childStation);
        for(int i=0; i < matrix.length; i++){
            System.out.println("Station: "+childStation+"|"+ i );
            if(matrix[childStation][i] != -1 && thisNodeExistInOpenList(i) == false && thisNodeExistInClosedList(i) == false){
                System.out.println("Station: "+childStation+"|"+ i +" H: "+ matrix[childStation][i] + " + G:" + (this.totalCostPath + stations.get(i).getTime()+stations.get(i).timeWait));
                System.out.println("Heuristic to add "+ (matrix[childStation][i] + this.totalCostPath + (stations.get(i).timeWait + stations.get(i).getTime())));
                addNodeOnOpenList(childStation,i,( matrix[childStation][i] + this.totalCostPath + (stations.get(i).timeWait + stations.get(i).getTime()) ));
            }
        }
    }
    
    public boolean thisNodeExistInOpenList(int station){
        for(int i =0; i < openList.size(); i++){
            if(openList.get(i).getNumberStation() == station){
                System.out.println("Exist in openList: "+ openList.get(i).getNumberStation());
                return true;
            }            
        }
        return false;
    }
    
    public boolean thisNodeExistInClosedList(int station){
        for(int i =0; i < closedList.size(); i++){
            if(closedList.get(i).getNumberStation() == station){
                System.out.println("Exist in closedList: "+ closedList.get(i).getNumberStation());
                return true;
            }            
        }
        return false;
    }
    
    public void removeNodeFromOpenList(int station){
        System.out.println("Removing the nodeStation from the open list: "+ station);
        for(int i =0; i < openList.size(); i++){
            if(openList.get(i).getNumberStation() == station){
                addNodeOnClosedList(openList.get(i).getFatherStation(), openList.get(i).getNumberStation(), openList.get(i).getHeuristic());
                openList.remove(i);
            }            
        }
    }
    public void sumCostPath(int currentStation){
        this.totalCostPath = totalCostPath + (stations.get(currentStation).getTime() + stations.get(currentStation).getTimeWait());
    }
    public void arrive(int currentStation){
        if(currentStation == stationGoal){
            this.alreadyPassHere = true;
        }      
    }
    
    public int backTracking(int lastClosedListNumber){
        
        for(int i=0;i<closedList.size();i++){
            if(lastClosedListNumber==closedList.get(i).getNumberStation()){
            x=i;
        }
        }
        if(lastClosedListNumber!=-1){
            bestTracking= bestTracking+lastClosedListNumber+">-";
            lastClosedListNumber=closedList.get(x).getFatherStation();
            return backTracking(lastClosedListNumber);
        }
            
            return 0;
        
    }
    
    public void search(){
        addNodeOnClosedList(fatherStation,currentStation,0);
        sumCostPath(currentStation);
        expandChildrenStations(currentStation);
        removeNodeFromOpenList( currentStation);
        while(alreadyPassHere!=true) {   
            returnBestNodeOpenList();
            sumCostPath(currentStation);
            expandChildrenStations(currentStation);
            removeNodeFromOpenList( currentStation);
            System.out.println("Going to "+currentStation);
            addNodeOnClosedList(fatherStation,currentStation,totalCostPath);
            arrive(currentStation);
        }
        System.out.println("Total Cost: "+ totalCostPath);
        backTracking(currentStation);
        StringBuffer sb=new StringBuffer(bestTracking);
        sb.reverse();
        System.out.println("Best Way: "+sb);
    }

    @Override
    public void run() {
        search();
    }
}
