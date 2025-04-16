package prisonersDilemma;

import mvc.Utilities;
import simstation.Heading;
import simstation.MobileAgent;

public class Prisoner extends MobileAgent { // should they be mobile or not?
    protected int fitness;
    protected Strategy strategy;
    protected int stratID;
    protected boolean partnerCheated ;

    static {
        sleepTime = 150;
    }

    public Prisoner(int stratID) {
        super();
        this.fitness = 0;
        this.partnerCheated = false;
        this.stratID = stratID;

        //stratID = Utilities.rng.nextInt(4);

        if (this.stratID == 0) { this.strategy = new Cooperate(); }
        else if (this.stratID == 1) { this.strategy = new Cheat(); }
        else if (this.stratID == 2) { this.strategy = new RandomlyCooperate(); }
        else { this.strategy = new Tit4Tat(); }

        this.strategy.setPrisoner(this);

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

    @Override
    public void update() {
        //pick a random neighbor using getNeighbor from world i think
        //play a game of prisoner's dilemma

        Prisoner opponent = (Prisoner) world.getNeighbor(this, 100);
        if (opponent != null) {
            play(opponent);
        }
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(75) + 1;
        move(steps);
        //move(5);
    }

    public boolean isPartnerCheated() {
        return partnerCheated;
    }

    /*public void setStrategy(int setStratID) {
        this.stratID = setStratID;

        if (this.stratID == 0) { this.strategy = new Cooperate(); }
        else if (this.stratID == 1) { this.strategy = new Cheat(); }
        else if (this.stratID == 2) { this.strategy = new RandomlyCooperate(); }
        else { this.strategy = new Tit4Tat(); }
    }*/

    public int getStrategy() {
        return stratID;
    }

    public int getFitness() {
        return fitness;
    }

}
