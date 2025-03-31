package simstation;

import mvc.*;
import java.io.Serializable;

public abstract class Agent implements Runnable, Serializable {
    /*
    An agent is an active object. It runs in its own thread (myThread).
     */

    // which variables should be "protected" versus "private"?
    // some of this is printing to a Console? copied from agentLab example, not needed?


    // location of the agent is xc, yc in the World
    protected int xc;
    protected int yc;
    protected String agentName;
    private Boolean paused;
    private Boolean stopped;

    // what is a thread again? do we need something with synchronized?
    protected Thread myThread;
    protected World world; // is the World the "manager"?

    // agent default constructor
    public Agent(String name){
        agentName = name;
        paused = false;
        stopped = false;
        myThread = null;
    }

    public void setWorld(World inputWorld){
        world = inputWorld;
    }

    public String getAgentName(){
        return agentName;
    }

    public synchronized String toString(){
        String result = agentName;
        if(stopped) {
            result += " (stopped)";
        }
        else if(paused){
            result += " (paused)";
        }
        else{
            result += " (running)";
        }
        return result;
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

    // start() is in the UML diagram but also part of thread? confused
    public synchronized void start(){
        // not sure what goes here
    }

    //thread stuff:
    public synchronized void stop(){
        stopped = true;
    }
    public synchronized boolean isStopped(){
        return stopped;
    }
    public synchronized void resume(){
        //what needs to go in here?
        notify();
    }
    public synchronized void pause(){
        paused = true;
        notify();
    }
    public synchronized boolean isPaused(){
        return paused;
    }

    // wait for notification if not stopped and yes paused
    private synchronized void checkPaused(){
        try{
            while(!stopped && paused){
                wait();
                paused = false;
            }
        }
        catch (InterruptedException e){
            Utilities.inform(e.getMessage());
        }
    }

    // wait for me to die:
    private synchronized void join(){
        try {
            if (myThread != null) {
                myThread.join();
            }
        }
        catch (InterruptedException e){
            Utilities.inform(e.getMessage());
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
                Utilities.inform(e.getMessage());
            }
        }
    }

}
