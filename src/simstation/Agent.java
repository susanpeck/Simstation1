package simstation;

import mvc.Utilities;

import java.io.Serializable;

import static mvc.Utilities.rng;

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
    public static int sleepTime = 200;

    // what is a thread again? do we need something with synchronized?

    /*
    Threads are not serializable, so the myThread field in the Agent class needs to be declared transient:
     */

    transient protected Thread myThread;
    protected World world; // is the World the "manager"?, yes i think so

    // agent default constructor
    public Agent(String name){

        agentName = name;
        paused = false;
        stopped = false;
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
        if(myThread == null) {
            myThread = new Thread(this);
            myThread.start();
            //System.out.println(getAgentName() + "thread started.");
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
        //what needs to go in here? see: https://www.cs.sjsu.edu/faculty/pearce/modules/lectures/ood4/threads/agentLab/src/Agent.java
        notify();
    }
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
            world.updateStatistics();
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
