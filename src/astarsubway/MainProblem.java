/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astarsubway;

import java.util.ArrayList;

/**
 *
 * @author Caio Serrano
 */
public class MainProblem {
       
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //testing stations//
        ArrayList<Station> stations = new ArrayList<>();
        //First test with 7 stations
        stations.add(new Station(0, 2, 2)); //Id 0 with waiting time plus time to arrive (2+2)
        stations.add(new Station(1, 2, 2)); //
        stations.add(new Station(2, 2, 2)); //
        stations.add(new Station(3, 2, 2));
        stations.add(new Station(4, 2, 2));
        stations.add(new Station(5, 2, 2));
        stations.add(new Station(6, 2, 2));
        stations.add(new Station(7, 2, 2));
      /*  Station[][] matrix ={
            {null,stations.get(0), null, null},
            {stations.get(1), null, stations.get(1), stations.get(1)},
            {null, stations.get(2), null, null},
            {null, stations.get(3), null, null}
        };*/
        int[][] matrixStations1 = {
            {-1, 6,-1,-1,-1,-1,-1},
            { 6,-1, 4,-1,-1,-1,-1},
            {-1, 4,-1, 7, 2,-1,-1},
            {-1,-1, 7,-1,-1,-1,-1},
            {-1,-1, 2,-1,-1, 0, 1},
            {-1,-1,-1,-1, 0,-1,-1},
            {-1,-1,-1,-1, 1,-1,-1}
        };
        
        int[][] matrixStations2 = {
            {-1,12,-1,-1,-1,-1,-1,-1},
            {12,-1,14,10,-1,-1,-1,-1},
            {-1,14,-1,-1,-1,-1,-1,-1},
            {-1,10,-1,-1, 8,-1,-1,-1},
            {-1,-1,-1, 8,-1, 4, 7,-1},
            {-1,-1,-1,-1, 4,-1,-1, 0},
            {-1,-1,-1,-1, 7,-1,-1,-1},
            {-1,-1,-1,-1,-1, 0,-1,-1}
        };
        int[][] matrixStations3 = {
            {-1,10,-1,-1},
            {-1,-1, 5,-1},
            {-1,-1,-1, 0},
            {-1,-1,-1,-1}
        };
        //BFS bfs = new BFS(stations, matrixStations2, 0, 7);
        //Thread bfsThread = new Thread(bfs);
        //bfsThread.start();
        //System.out.println("Best Time: "+bfs.nodeTime);
        //System.out.println(bfs.path);
        Astar astar = new Astar(stations, matrixStations3, 0, 3);
        
        astar.addNodeOnClosedList(-1,0,0);
        astar.addNodesToOpenList(0);
        astar.removeNodeFromOpenList(1);
        
        System.out.println("Going to "+astar.returnBestNodeOpenList());
        System.out.println("Best Station: "+astar.returnBestNodeOpenList()+" Current Station "+ astar.currentStation);
        astar.removeNodeFromOpenList(astar.returnBestNodeOpenList());
        astar.sumCostPath(astar.returnBestNodeOpenList());
        
        astar.addNodesToOpenList(astar.returnBestNodeOpenList());
        System.out.println("Going to "+astar.returnBestNodeOpenList());
        System.out.println("Best Station: "+astar.returnBestNodeOpenList()+" Current Station "+ astar.currentStation);
        astar.removeNodeFromOpenList(astar.returnBestNodeOpenList());
        astar.sumCostPath(astar.returnBestNodeOpenList());
                
        astar.addNodesToOpenList(astar.returnBestNodeOpenList());
        System.out.println("Going to "+astar.returnBestNodeOpenList());
        System.out.println("Best Station: "+astar.returnBestNodeOpenList()+" Current Station "+ astar.currentStation);
        astar.removeNodeFromOpenList(astar.returnBestNodeOpenList());
        astar.sumCostPath(astar.returnBestNodeOpenList());
        
        System.out.println("CP Size: "+ astar.closedList.size());
        System.out.println("OP Size: "+ astar.openList.size());
        
        // TODO code application logic here
        
    }
    
}
