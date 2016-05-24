/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astarsubway;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Caio Serrano
 */
public class MainProblem {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Station> stations = new ArrayList<>();
        int choose;
        System.out.println("Choose the scenario [1 or 2]");
        Scanner scanner = new Scanner(System.in);
        choose = scanner.nextInt();
        String se;
        int startStation=0;
        int endStation=0;
        int[][] matrixStation=null;
        switch(choose){
            case 1:
                matrixStation = createMatrixForProblem1();
                System.out.println("Choose the problem to resolve [1 to 4AM or 2 to 6PM]");
                choose = scanner.nextInt();
                switch(choose){
                    case 1:
                        createSubwayProblem1At4AM(stations);
                        break;
                    case 2:
                        createSubwayProblem1At6PM(stations);
                        break;
                    default:
                        System.err.println("Wrong Option!!!");
                        break;
                }
                endStation= 22;
                break;
            case 2:
                matrixStation = createMatrixForProblem2();
                System.out.println("Choose the problem to resolve [1 to 4AM or 2 to 6PM]");
                choose = scanner.nextInt();
                switch(choose){
                    case 1:
                        createSubwayProblem2At4AM(stations);
                        break;
                    case 2:
                        createSubwayProblem2At6PM(stations);
                        break;
                    default:
                        System.err.println("Wrong Option!!!");
                        break;
                }
                endStation=19;
                break;
            default:
                System.err.println("Wrong Option!!");
                break;
        }
        System.out.println("Choose the search type [BFS or ASTAR]");
        Scanner scanner2 = new Scanner(System.in);
        se = scanner2.nextLine();
        if(se.equals("BFS")){
            GreedyBestFirst search = new GreedyBestFirst(stations, matrixStation, startStation,endStation);
            Thread searchThread = new Thread(search);      
            searchThread.setPriority(5);
            searchThread.start();
        }else
        if(se.equals("ASTAR")){
            Astar search = new Astar(stations, matrixStation, startStation, endStation);
            Thread searchThread = new Thread(search);      
            searchThread.setPriority(5);
            searchThread.start();
        }
        else{
            System.err.println("No search type detect!!!");
        }

    }

    public static Thread createBFSSearch(ArrayList<Station> stations, int[][] matrixStation, int startStation, int endStation) {;
        GreedyBestFirst search = new GreedyBestFirst(stations, matrixStation, startStation, endStation);
        Thread searchThread = new Thread(search);
        return searchThread;
    }

    public static Thread createAStarSearch(ArrayList<Station> stations, int[][] matrixStation, int startStation, int endStation) {
        Astar search = new Astar(stations, matrixStation, startStation, endStation);
        Thread searchThread = new Thread(search);
        return searchThread;
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
    public static int[][] createMatrixForProblemBackTracking() {
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
            {-1,-1,-1,36,-1,-1,-1,-1,-1,-1,-1,-1,-1,30,-1,-1,-1,-1,-1,-1,-1,-1,-1},
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
    
    public static int[][] createMatrixForProblem2(){
        int[][] subwayMatrix = {
            {-1,45,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {48,-1,43,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,45,-1,41,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,43,-1,38,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,41,-1,36,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,38,-1,34,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,36,-1,31,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,34,-1,30,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,31,-1,27,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,30,-1,31,-1,-1,21,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,27,-1,28,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,31,-1,25,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,28,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,34,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,27,-1,-1,-1,-1,16,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,21,-1,19,11,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,16,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,16,-1,-1,7,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,11,-1,4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,7,-1,0,6,10,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,18,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,18,-1,-1,-1,13,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,10,-1,16,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,13,-1,19,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,16,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,9,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,28,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,26,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,12,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,26,-1,-1,30,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,19,-1,-1,34,-1,32,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,30,-1,34},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,32,-1},
        };
        return subwayMatrix; 
    }
    
    public static void createSubwayProblem2At4AM(ArrayList<Station> stations) {
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
        stations.add(new Station(23, 2, 2));
        stations.add(new Station(24, 2, 2));
        stations.add(new Station(25, 2, 2));
        stations.add(new Station(26, 2, 2));
        stations.add(new Station(27, 2, 2));
        stations.add(new Station(28, 2, 2));
        stations.add(new Station(29, 2, 2));
        stations.add(new Station(30, 2, 2));
        System.out.println("Go to go!!!");
    }
    
     public static void createSubwayProblem2At6PM(ArrayList<Station> stations) {
        System.out.println("Creating the stations in Subway at 4AM ");
        stations.add(new Station(0, 2, 1));
        stations.add(new Station(1, 2, 1));
        stations.add(new Station(2, 2, 1));
        stations.add(new Station(3, 2, 1));
        stations.add(new Station(4, 2, 1));
        stations.add(new Station(5, 2, 1));
        stations.add(new Station(6, 2, 1));
        stations.add(new Station(7, 2, 1));
        stations.add(new Station(8, 2, 1));
        stations.add(new Station(9, 3, 1));
        stations.add(new Station(10, 2, 1));
        stations.add(new Station(11, 2, 1));
        stations.add(new Station(12, 2, 1));
        stations.add(new Station(13, 20, 10));
        stations.add(new Station(14, 20, 10));
        stations.add(new Station(15, 5, 5));
        stations.add(new Station(16, 20, 10));
        stations.add(new Station(17, 20, 20));
        stations.add(new Station(18, 2, 1));
        stations.add(new Station(19, 2, 1));
        stations.add(new Station(20, 2, 1));
        stations.add(new Station(21, 2, 1));
        stations.add(new Station(22, 2, 1));
        stations.add(new Station(23, 2, 1));
        stations.add(new Station(24, 2, 1));
        stations.add(new Station(25, 2, 2));
        stations.add(new Station(26, 2, 2));
        stations.add(new Station(27, 2, 1));
        stations.add(new Station(28, 2, 2));
        stations.add(new Station(29, 2, 2));
        stations.add(new Station(30, 2, 2));
        System.out.println("Go to go!!!");
    }
}
