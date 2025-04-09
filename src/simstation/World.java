package simstation;

import mvc.Model;
import mvc.Utilities;

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
    protected int alive = 0;
    protected int clock = 0;
    protected ArrayList<Agent> agents; //should this be a list?
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
        a.setWorld(this);
    }

    // do we need a removeAgent(Agent a) method?

    public ArrayList<Agent> getAgents(){
        return agents;
    }

    public void startAgents(){
        // not sure if this needs to call changed, or just the start method which then calls changed?
        populate();
        for(Agent a : agents){
            //System.out.println("Starting agent: " + a.getAgentName());
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
            if (!a.isPaused()) {
                a.pause();
            }
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
        return "#agents = " + agents.size() + "\n" + "#living = " + alive + "\n" + "#clock = " + clock;
    }

    public void updateStatistics(){
        /*
        The default implementation of this method increments
        the world clock and the alive attribute, which is the
        number of agents that are still active.
         */
        clock++;
        alive = agents.size();  //i don't know if this actually increments always
    }


    /*
    The main service the simulation provides to agents is getNeighbor. An agent seeking a random nearby partner to interact with might call:
Agent partner = world.getNeighbor(this, 10); // try to find a random agent (not me) within 10 steps
//An efficient implementation of getNeighbor picks a random location in the agents list. Starting at this location it visits each agent in order (wrapping around to the start if necessary) until it either finds a suitable neighbor or until it loops back to the starting point and returns null.
     */

    public Agent getNeighbor(Agent caller, int radius){
        int randomLocation = Utilities.rng.nextInt(agents.size() - 1);
        Agent neighbor = agents.get(randomLocation);

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


}
