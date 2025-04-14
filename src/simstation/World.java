package simstation;

import mvc.*;
import java.util.*;
/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

public class World extends Model {
    /*
    World is the base class for all agent environments.
    It provides a list of agents. It's startAgents, stopAgents,
    pauseAgents, and resumeAgents methods call the corresponding
    start, stop, pause, and resume methods of its agents.
     */

    private static final int SIZE = 500;
    protected int alive;
    protected int clock;
    protected ArrayList<Agent> agents;
    private ObserverAgent observer;

    // default constructor
    public World() {
        super();
        agents = new ArrayList<Agent>();
        observer = new ObserverAgent();
        addAgent(observer); // new code to try and fix statistics
        alive = 0;
        clock = 0;
    }

    // specialized constructor
    public World(ArrayList<Agent> newAgentArray, ObserverAgent newObserver, int time, int numAlive) {
        agents = newAgentArray;
        observer = newObserver;
        addAgent(observer); // new code to try and fix statistics
        clock = time;
        alive = numAlive;
    }

    public void addAgent(Agent a){
        agents.add(a);
        a.setWorld(this);
    }
    public void removeAgent(Agent a){
        agents.remove(a);
        a.setWorld(this);
    }
    public ArrayList<Agent> getAgents(){
        return agents;
    }

    public void startAgents(){
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
        return "#agents = " + agents.size() + "\n" + "#living = " + alive + "\n" + "#clock = " + clock;
    }

    public void updateStatistics(){
        /*
        The default implementation of this method increments
        the world clock and the alive attribute, which is the
        number of agents that are still active.
         */
        clock++;
        alive = agents.size();
    }

    public Agent getNeighbor(Agent caller, int radius){
        int randomLocation = Utilities.rng.nextInt(agents.size() - 1);
        Agent neighbor = agents.get(randomLocation);

        // if the agent is the Observer, choose the next random agent in the list
        // continue checking until not an observeragent
        while(neighbor instanceof ObserverAgent){
            randomLocation = Utilities.rng.nextInt(agents.size() - 1);
            neighbor = agents.get(randomLocation);
        }

        // number of steps from caller agent to random agent in list
        int distance = Math.abs(caller.getXc() - neighbor.getXc()) + Math.abs(caller.getYc() - neighbor.getYc());

        int attempts = 0;
        while(distance > radius && attempts < agents.size()){
            randomLocation++; // index of next agent in list
            if(randomLocation > agents.size() - 1){
                randomLocation = randomLocation - agents.size() + 1; //wrap to beginning of list
            }
            neighbor = agents.get(randomLocation);
            distance = Math.abs(caller.getXc() - neighbor.getXc()) + Math.abs(caller.getYc() - neighbor.getYc());
        }
        return (attempts == agents.size())? null : neighbor; // if no neighbors found, return null
    }

    public int getSize(){
        return SIZE;
    }

    public Iterator<Agent> iterator() {
        return agents.iterator();
    }
}
