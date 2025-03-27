package simstation;

import java.io.Serializable;

public abstract class Agent implements Runnable, Serializable {
    /*
    An agent is an active object. It runs in its own thread (myThread).
     */

    // location of the agent is xc, yc in the World
    private int xc;
    private int yc;
    private String agentName;

    private Boolean paused = false;
    private Boolean stopped = false;

    private Thread myThread;
    private World world;

    public int getXc(){
        // should be between 0 and world SIZE
        // if beyond the border, wraps around
        return xc;
    }
    public int getYc(){
        // should be between 0 and world SIZE
        // if beyond the border, wraps around
        return yc;
    }
    public String getAgentName(){
        return agentName;
    }

    public void start(){

    }

    public void stop(){

    }

    public void resume(){

    }


    public void update(){
        //this method is italic in the UML diagram
        // abstract method

    }

    public void run(){
        //The run method repeatedly calls the abstract update method.
        update();
    }

}
