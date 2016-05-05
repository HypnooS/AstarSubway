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
public class CurrentStation {
    private int currentNode;
    private int currentHeristic;
    private int currentStation;

    public CurrentStation() {
        
    }

  
    
    

    /**
     * @return the currentNode
     */
    public int getCurrentPath() {
        return currentNode;
    }

    /**
     * @param currentPath the currentNode to set
     */
    public void setCurrentPath(int currentPath) {
        this.currentNode = currentPath;
    }

    /**
     * @return the currentHeristic
     */
    public int getCurrentHeristic() {
        return currentHeristic;
    }

    /**
     * @param currentHeristic the currentHeristic to set
     */
    public void setCurrentHeristic(int currentHeristic) {
        this.currentHeristic = currentHeristic;
    }

    /**
     * @return the currentStation
     */
    public int getCurrentStation() {
        return currentStation;
    }

    /**
     * @param currentStation the currentStation to set
     */
    public void setCurrentStation(int currentStation) {
        this.currentStation = currentStation;
    }

   
}
