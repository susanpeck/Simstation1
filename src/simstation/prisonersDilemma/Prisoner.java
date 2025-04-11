package simstation.prisonersDilemma;

import mvc.Utilities;
import simstation.MobileAgent;

public class Prisoner extends MobileAgent { // should they be mobile or not?
    protected int fitness;
    protected Strategy strategy;
    protected int stratID;
    protected boolean partnerCheated ;

    public Prisoner() {
        super();
        this.fitness = 0;
        this.partnerCheated = false;
        stratID = Utilities.rng.nextInt(4);

        // randomly assign strategy to each prisoner
        if (stratID == 0) { strategy = new Cooperate(); }
        else if (stratID == 1) { strategy = new Cheat(); }
        else if (stratID == 2) { strategy = new RandomlyCooperate(); }
        else { strategy = new Tit4Tat(); }

    }

    public boolean cooperate() {
        return this.strategy.cooperate();
    }


    public void play(Prisoner p2) {
        boolean p1move = this.cooperate();
        boolean p2move = p2.cooperate();

        if(p1move && p2move) { // both cooperate
            this.updateFitness(3);
            partnerCheated = false;
        } else if (!p1move && p2move) { // cheat and partner cooperates
            this.updateFitness(5);
            partnerCheated = false;
        } else if (p1move && !p2move) {
            partnerCheated = true;
        } else { // both cheat
            this.updateFitness(1);
            partnerCheated = true;
        } // in case cooperates and partner cheats, no update to fitness

    }

    public void updateFitness(int amt) {
        fitness = fitness + amt;
    }

    /*public void update() {
        //pick a random neighbor using getNeighbor from world i think
        //play a game of prisoner's dilemma
    }*/

    public boolean isPartnerCheated() {
        return partnerCheated;
    }

    public int getStrategy() {
        return stratID;
    }

    public int getFitness() {
        return fitness;
    }

}
