/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astarsubway;

/**
 *
 * @author HypnoS
 */
public class List {
    private int numberStation;
    private int heuristic;

    public List(int numberStation, int heuristic) {
        this.numberStation = numberStation;
        this.heuristic = heuristic;
    }

    /**
     * @return the numberStation
     */
    public int getNumberStation() {
        return numberStation;
    }

    /**
     * @param numberStation the numberStation to set
     */
    public void setNumberStation(int numberStation) {
        this.numberStation = numberStation;
    }

    /**
     * @return the heuristic
     */
    public int getHeuristic() {
        return heuristic;
    }

    /**
     * @param heuristic the heuristic to set
     */
    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }
}
