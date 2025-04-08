package simstation.prisonersDilemma;

import simstation.MobileAgent;

public class Prisoner extends MobileAgent { // should they be mobile or not?
    private int fitness = 0;
    private boolean partnerCheated = false;

    public Prisoner() {
        super();
    }

    public boolean cooperate(Strategy strat) { // setter, based on set will return result maybe?
        // to access one of the strategies...
        return strat.cooperate();
    }

    public void update() {
        //pick a random neighbor using getNeighbor from world i think
        //play a game of prisoner's dilemma
    }

    public void updateFitness(int amt) {fitness = fitness + amt;}
}
