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
        observer = new ObserverAgent();
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

    public void startAgent(){
        // not sure if this needs to call changed, or just the start method which then calls changed?
        changed();
    }

    public void stopAgent(){
        changed();
    }

    public void pauseAgent(){
        changed();
    }

    public void resumeAgent(){
        changed();
    }

    public void populate(){
        // why is this method italic in the UML diagram?
        // Populate is an empty method that will be specified
        // in subclasses. It's called by startAgents and
        // populates the simulation.
    }

    public String getStatus(){
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

}
