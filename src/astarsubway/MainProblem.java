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
        //testing stations
        ArrayList<Station> stations = new ArrayList<>();
        stations.add(new Station(2, 2));
        stations.add(new Station(3, 2));
        int[][] matrix = {{0,1},{1,0}};
        
        Subway subway = new Subway(stations, matrix);
        System.out.println(stations.get(0).getTime());
        System.out.println(stations.get(1).getTime());

        
        
        
        
        // TODO code application logic here
        
    }
    
}
