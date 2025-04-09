package simstation.prisonersDilemma;

import mvc.Utilities;
import simstation.MobileAgent;

public class Prisoner extends MobileAgent { // should they be mobile or not?
    private int fitness = 0;
    private boolean partnerCheated = false;
    protected Strategy strategy;

    public Prisoner() {
        super();
        int rand = Utilities.rng.nextInt(4);

        // randomly assign strategy to each prisoner
        if (rand == 0) { strategy = new Cooperate(); }
        else if (rand == 1) { strategy = new Cheat(); }
        else if (rand == 2) { strategy = new RandomlyCooperate(); }
        else { strategy = new Tit4Tat(); }

    }

    public boolean cooperate() { // setter, based on set will return result maybe?
        // to access one of the strategies...
        return this.strategy.cooperate();
    }

    public void update() {
        //pick a random neighbor using getNeighbor from world i think
        //play a game of prisoner's dilemma
    }

    public void play(Prisoner p2) { // does play replace update?

    }

    public void updateFitness(int amt) {fitness = fitness + amt;}
}
