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
            {16,-1, 9, 7,-1,-1,-1,-1},
            {-1,10,-1,-1,-1,-1,-1,-1},
            {-1,10,-1,-1, 6,-1,-1,-1},
            {-1,-1,-1, 8,-1, 4, 7,-1},
            {-1,-1,-1,-1, 6,-1,-1, 0},
            {-1,-1,-1,-1, 9,-1,-1,-1},
            {-1,-1,-1,-1,-1, 0,-1,-1}
        };
        int[][] matrixStations3 = {
            {-1,10,-1,-1},
            {12,-1, 5, 6},
            {-1,-1,-1, 0},
            {-1,-1, 8,-1}
        };
        
        int[][] matrixStation = createMatrixForProblem1();
        createSubwayProblem1At6PM(stations);
        //BFS bfs = new BFS(stations, matrixStations2, 0, 7);
        //Thread bfsThread = new Thread(bfs);
        //bfs.search();
        //bfsThread.start();
        //System.out.println("Best Time: "+bfs.nodeTime);
        //System.out.println(bfs.path);
        Astar astar = new Astar(stations, matrixStation, 0, 22);
        Thread astarThread = new Thread(astar);
        astarThread.setPriority(5);
        astarThread.start();
        //astar.search();
        //GreedyBestFirst bfs = new GreedyBestFirst(stations, matrixStation, 0, 22);
        // Thread bfsThread = new Thread(bfs);
        //bfsThread.setPriority(5);
        //bfsThread.start();
        //bfs.search();
        // TODO code application logic here
        
    }

    public static void methodTestStationObj(ArrayList<Station> stations) {
        //First test with 7 stations
        stations.add(new Station(0, 2, 2)); //Id 0 with waiting time plus time to arrive (2+2)
        stations.add(new Station(1, 2, 2)); //
        stations.add(new Station(2, 2, 2)); //
        stations.add(new Station(3, 2, 2));
        stations.add(new Station(4, 2, 2));
        stations.add(new Station(5, 2, 2));
        stations.add(new Station(6, 2, 2));
        stations.add(new Station(7, 2, 2));
    }
    public static int[][] createMatrixForProblem1() {
        int[][] subwayMatrix = {
            {-1,44,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//Blue line Start
            {50,-1,40,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,44,-1,36,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,40,-1,34,-1,-1,-1,-1,-1,-1,-1,-1,-1,22,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,36,-1,30,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,34,-1,24,-1,-1,-1,-1,-1,-1,-1,-1,26,32,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,30,-1,20,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,24,-1,18,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,20,-1,16,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,18,-1,19,-1,-1,-1,-1,-1,-1,-1,-1,14,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,16,-1,24,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,24,-1,32,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,24,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//Blue line End
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,22,-1,-1,-1,-1,-1,-1,-1,-1},//Red line Start
            {-1,-1,-1,36,-1,-1,-1,-1,-1,-1,-1,-1,-1,30,-1,26,-1,10,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,30,-1,-1,-1,-1,-1,-1,-1,-1,22,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,30,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//Red line End
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,22,-1,-1,-1,35,-1,12,06,-1},//Green line-Consolação
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,10,-1,-1,-1,-1,-1},//
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,16,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,12,-1,-1},//Green line Start - Brigadeiro
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,10,-1,-1,14,-1,-1},//--Trianon Masp
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,10,-1,-1,-1,-1,00},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,06,-1} //Green line End - V. Madalena
        }; 
        return subwayMatrix;
    }

    public static void createSubwayProblem1At6PM(ArrayList<Station> stations) {
        System.out.println("Creating the stations in Subway at 6PM ");
        stations.add(new Station(0, 2, 1));
        stations.add(new Station(1, 2, 1));
        stations.add(new Station(2, 2, 1));
        stations.add(new Station(3, 2, 1));
        stations.add(new Station(4, 2, 1));
        stations.add(new Station(5, 2, 10));
        stations.add(new Station(6, 2, 1));
        stations.add(new Station(7, 2, 1));
        stations.add(new Station(8, 2, 1));
        stations.add(new Station(9, 2, 1));
        stations.add(new Station(10, 2, 1));
        stations.add(new Station(11, 2, 1));
        stations.add(new Station(12, 2, 2));
        stations.add(new Station(13, 30, 20));
        stations.add(new Station(14, 30, 60));
        stations.add(new Station(15, 30, 30));
        stations.add(new Station(16, 30, 20));
        stations.add(new Station(17, 2, 2));
        stations.add(new Station(18, 2, 2));
        stations.add(new Station(19, 2, 2));
        stations.add(new Station(20, 2, 2));
        stations.add(new Station(21, 2, 2));
        stations.add(new Station(22, 2, 2));
        System.out.println("Go to go!!!");
    }
    public static void createSubwayProblem1At4AM(ArrayList<Station> stations) {
        System.out.println("Creating the stations in Subway at 4AM ");
        stations.add(new Station(0, 2, 2));
        stations.add(new Station(1, 2, 2));
        stations.add(new Station(2, 2, 2));
        stations.add(new Station(3, 2, 2));
        stations.add(new Station(4, 2, 2));
        stations.add(new Station(5, 2, 4));
        stations.add(new Station(6, 2, 2));
        stations.add(new Station(7, 2, 2));
        stations.add(new Station(8, 2, 2));
        stations.add(new Station(9, 2, 2));
        stations.add(new Station(10, 2, 2));
        stations.add(new Station(11, 2, 2));
        stations.add(new Station(12, 2, 2));
        stations.add(new Station(13, 2, 2));
        stations.add(new Station(14, 2, 4));
        stations.add(new Station(15, 2, 2));
        stations.add(new Station(16, 2, 2));
        stations.add(new Station(17, 2, 2));
        stations.add(new Station(18, 2, 2));
        stations.add(new Station(19, 2, 2));
        stations.add(new Station(20, 2, 2));
        stations.add(new Station(21, 2, 2));
        stations.add(new Station(22, 2, 2));
        System.out.println("Go to go!!!");
    }
    
}
