package simstation;

import java.io.Serializable;

public abstract class Agent implements Runnable, Serializable {
    /*
    An agent is an active object. It runs in its own thread (myThread).
     */

    // which variables should be "protected" versus "private"?

    // location of the agent is xc, yc in the World
    private int xc;
    private int yc;
    private String agentName;
    private Boolean paused;
    private Boolean stopped;

    // what is a thread again? do we need something with synchronized?
    private Thread myThread;
    protected World world; // is the World the "manager"?

    // agent default constructor
    public Agent(String name){
        agentName = name;
        paused = false;
        stopped = false;
        myThread = null;
    }

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

    public synchronized void start(){

    }

    public synchronized void stop(){
        stopped = true;
    }

    public synchronized void resume(){
        notify();
    }

    public synchronized boolean isStopped(){
        return stopped;
    }

    public synchronized boolean isPaused(){
        return paused;
    }

    private synchronized void checkPaused(){
        try{
            while(!stopped && paused){
                wait();
                paused = false;
            }
        }
        catch (InterruptedException e){
            world.println(e.getMessage());
        }
    }
    
    public abstract void update();

    public void run(){
        //The run method repeatedly calls the abstract update method.
        myThread = Thread.currentThread();
        while(!isStopped()){
            try {
                update();
                Thread.sleep(1000);
                checkPaused();
            }
            catch(InterruptedException e){
                world.println(e.getMessage());
            }
        }
        world.stdout.println(name + " stopped");
    }

}
