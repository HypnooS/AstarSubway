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
public class Station {
    private int time;
    private int timeWait;
    public Station(int time, int timeWait){
        this.time = time;
        this.timeWait = timeWait;
    }

    /**
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * @return the timeWait
     */
    public int getTimeWait() {
        return timeWait;
    }

    /**
     * @param timeWait the timeWait to set
     */
    public void setTimeWait(int timeWait) {
        this.timeWait = timeWait;
    }
    
}
