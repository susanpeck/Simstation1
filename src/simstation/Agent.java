package simstation;

import mvc.*;
import java.io.Serializable;
import static mvc.Utilities.rng;

/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

public abstract class Agent implements Runnable, Serializable {
    /*
    An agent is an active object. An Agent has a location, a name, and
    two boolean values to keep track of whether the agent is temporarily
    paused or permanently stopped. It runs in its own thread (myThread).
    The location of the agent is xc, yc in the World
     */
    protected int xc;
    protected int yc;
    protected String agentName;
    private Boolean paused;
    private Boolean stopped;
    public static int sleepTime = 200;

    /*
    Threads are not serializable, so the myThread field in the Agent class needs to be declared transient:
     */
    transient protected Thread myThread;
    protected World world; // The world manages the agents

    // agent default constructor, agent is not pause or stopped
    public Agent(String name){
        agentName = name;
        paused = false;
//        stopped = false;
        stopped = true;
        myThread = null;
    }

    public void setWorld(World inputWorld){
        world = inputWorld;
        xc = rng.nextInt(world.getSize());
        yc = rng.nextInt(world.getSize());
    }

    public String getAgentName(){
        return agentName;
    }

    public synchronized String toString(){
        String result = agentName;
        if(stopped) { result += " (stopped)";}
        else if(paused){ result += " (paused)";}
        else{ result += " (running)";}
        return result;
    }

    /**
     * Returns the value of xc, the x location of the agent in the
     * world.
     * @return xc integer value between 0 and world SIZE
     */
    public int getXc(){
        return xc;
    }
    /**
     * Returns the value of yc, the y location of the agent in the
     * world.
     * @return xc integer value between 0 and world SIZE
     */
    public int getYc(){
        return yc;
    }

    public synchronized void start(){
        if(myThread == null) {
            myThread = new Thread(this);
            myThread.start();
        }
        paused = false;
        stopped = false;
    }

    //thread stuff:
    public synchronized void stop(){
        stopped = true;
    }
    public synchronized boolean isStopped(){
        return stopped;
    }
    public synchronized void resume(){
        notify();
    }

    /**
     * Sets the value of paused for an agent to be TRUE.
     * Agents will be unpaused when notify() is called in resume()
     */
    public synchronized void pause(){
        paused = true;
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
            // world.updateStatistics() commenting out for now should be in Observer update() method
            try {
                update();
                Thread.sleep(sleepTime);
                checkPaused();
            }
            catch(InterruptedException e){
                Utilities.inform(e.getMessage());
            }
        }
    }
}
