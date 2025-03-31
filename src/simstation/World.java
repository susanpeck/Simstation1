package simstation;

import mvc.Model;

import java.util.ArrayList;

/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

public class World extends Model {
    /*
    World is the base class for all agent environments.
    It provides a list of agents. It's startAgents, stopAgents,
    pauseAgents, and resumeAgents methods call the corresponding
    start, stop, suspend, and resume methods of its agents.
     */

    private static final int SIZE = 500;
    private int alive = 0;
    private int clock = 0;
    private ArrayList<Agent> agents; //should this be a list?
    private ObserverAgent observer;

    // default constructor
    public World() {
        agents = new ArrayList<Agent>();
        observer = new ObserverAgent("The Observer");
    }

    // specialized constructor
    public World(ArrayList<Agent> newAgentArray, ObserverAgent newObserver, int time, int numAlive) {
        agents = newAgentArray;
        observer = newObserver;
        clock = time;
        alive = numAlive;
    }

    public void addAgent(Agent a){
        agents.add(a);
    }

    // do we need a removeAgent(Agent a) method?

    public ArrayList<Agent> getAgents(){
        return agents;
    }

    public void startAgents(){
        // not sure if this needs to call changed, or just the start method which then calls changed?
        populate();
        for(Agent a : agents){
            a.start();
        }
    }

    public void stopAgents(){
        for(Agent a : agents){
            a.stop();
        }
    }

    public void pauseAgents(){
        for(Agent a : agents){
            a.pause();
        }
    }

    public void resumeAgents(){
        for(Agent a : agents){
            a.resume();
        }
    }

    public void populate(){
        // Populate is an empty method that will be specified in subclasses.
        // It's called by startAgents and populates the simulation
    }

    public String getStatus(){
        // is this what is called when Stats button is pressed?
        return "";
    }

    public void updateStatistics(){
        /*
        The default implementation of this method increments
        the world clock and the alive attribute, which is the
        number of agents that are still active.
         */
        clock++;
        alive++;
    }

    public Agent getNeighbor(Agent caller, int radius){
        return null;
    }

    public int getSize(){
        return SIZE;
    }

}
