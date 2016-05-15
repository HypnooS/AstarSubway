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
public class GreedyBestFirst implements Runnable{
    
    public ArrayList<Station> stations;
    public ArrayList<List> bestWay;
    public ArrayList<List> blackList;
    public int[][] matrix;
    public int currentStation;
    public int currentHeuristic;
    public int currentFather;
    public int stationGoal;
    private int intermediateFather;
    private int intermediateStation;
    private boolean foundGoal;
    private int totalCostPath;
    private String bestTracking;
    public int counterTrack;

    public GreedyBestFirst(ArrayList<Station> stations, int[][] matrix, int currentStation, int stationGoal) {
        this.stations = stations;
        this.matrix = matrix;
        this.currentStation = currentStation;
        this.currentHeuristic=99999;
        this.currentFather=-1;
        this.stationGoal = stationGoal;
        bestWay = new ArrayList<>();
        blackList = new ArrayList<>();
    }
    
    private void addInBestWay(int fatherStation, int currentStation){
        System.out.println("Adding the "+currentStation+" on Best Way List");
        bestWay.add(new List(fatherStation,currentStation));
    }
    
    private void addInBlackList(int currentStation){
        System.out.println("Removing the stationt : "+ currentStation);
        for(int i =0; i < bestWay.size(); i++){
            if(bestWay.get(i).getNumberStation() == currentStation){
                blackList.add(new List(bestWay.get(i).getFatherStation(), bestWay.get(i).getNumberStation()));
                this.currentStation=bestWay.get(i).getFatherStation();
                bestWay.remove(i);
            }            
        }
    }
    
    public void findBestStation(){
        System.out.println("Current Station: "+ currentStation);
        for(int i=0; i < matrix.length; i++){
            System.out.println("Station: "+currentStation+"|"+ i );
            if(matrix[currentStation][i] < currentHeuristic && matrix[currentStation][i] != -1 && thisStationExistInBlackList(i) == false ){
                System.out.println("Station: "+currentStation+"|"+ i +" H: "+ matrix[currentStation][i]);
                this.intermediateFather = currentStation;
                this.intermediateStation = i;
                this.currentHeuristic = matrix[currentStation][i];
            }
        }
        this.currentStation = intermediateStation;
        this.currentFather = intermediateFather;
        //System.out.println("Father: "+currentFather +" Station: "+currentStation+"|"+ currentStation +" H: "+ currentHeuristic);
        addInBestWay(currentFather, currentStation);
        sumCostPath(currentStation);
        if(currentStation == stationGoal){
            this.foundGoal = true;
        }
    }
    
    public void tryExpendStation(int currentStation){
        int counterError=0;
        System.out.println("What do i need to do in Station "+ currentStation + " now?");
        for(int i=0; i < matrix.length; i++){
        System.out.println("Station: "+currentStation+"|"+ i +" H: "+ matrix[currentStation][i]);
            if(matrix[currentStation][i] == -1 || thisStationExistInBlackList(i) == true ){
                counterError++;
                System.out.println("Error: "+ counterError);
            }
        }
       
    }

    private void returnAction(int counterError, int currentStation1) {
        if (counterError==matrix.length) {
            System.out.println("Warning... possible Dead End in  "+ currentStation);
            addInBlackList(currentStation1);
            this.currentStation = currentFather;
        } else if(counterError!=matrix.length){
            System.out.println("You can go to next Station");
            findBestStation();
        }
    }
    
    
    private boolean thisStationExistInBlackList(int i) {
        for(int j=0; i < blackList.size(); i++){
            if(blackList.get(i).getNumberStation() == currentStation){
                return true;
            }            
        }
        return false;
    }
    public void sumCostPath(int currentStation){
        this.totalCostPath = totalCostPath + (stations.get(currentStation).getTime() + stations.get(currentStation).getTimeWait());
    }
    public int backTracking(int lastClosedListNumber){
        
        for(int i=0;i<bestWay.size();i++){
            if(lastClosedListNumber==bestWay.get(i).getNumberStation()){
            counterTrack =i;
            }
        }
        if(lastClosedListNumber!=-1){
            bestTracking= bestTracking+lastClosedListNumber+"]-[";
            lastClosedListNumber=bestWay.get(counterTrack).getFatherStation();
            return backTracking(lastClosedListNumber);
        }
        return 0;
        
    }
    
    public void search(){
        addInBestWay(currentFather, currentStation);
        sumCostPath(currentStation);
        while(foundGoal!=true){
            findBestStation();
        }
        System.out.println("Total Cost: "+ totalCostPath);
        backTracking(currentStation);
        String sb=bestTracking;
        System.out.println("Best Way: [START]-["+sb+"END]");
    }

    @Override
    public void run() {
        
        search();
    }
    
    
}
