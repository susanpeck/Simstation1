package simstation.prisonersDilemma;

import simstation.Agent;

public class Prisoner extends Agent {
    private int fitness = 0;
    private boolean partnerCheated = false;

    public Prisoner(String name) {
        super(name);
    }

    public boolean cooperate() {
        // to access one of the strategies...
    }

    public void update() {
        //pick a random neighbor using getNeighbor from world i think
        //play a game of prisoner's dilemma
    }

    public void updateFitness(int amt) {fitness = fitness + amt;}
}
