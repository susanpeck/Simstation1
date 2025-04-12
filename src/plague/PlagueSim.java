package plague;

import mvc.*;
import simstation.*;
/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

public class PlagueSim extends World {
    public int VIRULENCE = 50; // % chance of infection
    public int RESISTANCE = 2; // % chance of resisting infection
    public int INFECTED_PERCENTAGE = 5; // constant so should be uppercase
    public int INFECTED = 0; // should this be lower case and updated ?
    public int POPULATION = 50; // ditto, every time Start is pressed agents are adding so population isn't a fixed
    public int recoveryORFatalityTime = 200;
    private boolean isFatal = true;

    private int numInfected; // does this duplicate above INFECTED value? used in UpdateStatistics
    private int percentInfected; // number of infected Creatures divided by alive Creatures

    // think we need a constructor because extends World
    public PlagueSim(){
        super();
        // what should be initialized here?
    }

    public void populate() {
        for(int i = 0; i < POPULATION; i++) {
            int rand = Utilities.rng.nextInt(100);
            if (rand < INFECTED_PERCENTAGE) {
                rand = Utilities.rng.nextInt(100);
                if (rand > RESISTANCE) {
                    addAgent(new Creature(recoveryORFatalityTime, isFatal, true));
                    INFECTED++;
                }
            }
            else {
                addAgent(new Creature(recoveryORFatalityTime, isFatal, false));
            }
        }
    }

    /**
     * Displays the instantaneous values of the number of mobile agents,
     * the time for the clock, and the percentages of infected compared to alive
     * @return string that shows current values
     */
    @Override
    public String getStatus() {
        return "#agents = " + alive + "\n" + "#clock = " + clock + "\n" + "% INFECTED = " + percentInfected;
    }

    @Override
    public void updateStatistics(){
        // increment the clock
        clock++;
        // recount the number of alive and infected agents
        int aliveCount = 0;
        int infectedCount = 0;

        for(Agent a : agents){
            if( !(a instanceof ObserverAgent) && ((Creature)a).isInfected() ){
                infectedCount++;
            }
            else if( !(a instanceof ObserverAgent) && !((Creature)a).isDead() ){
                aliveCount++;
            }
        }
        alive = aliveCount;
        numInfected = infectedCount;
        percentInfected = numInfected / alive / 100;
    }

    public void setFatal(boolean fatal) {
        isFatal = fatal;
    }

    public boolean isFatal() {
        return isFatal;
    }

    public void setVIRULENCE(int VIRULENCE) {
        this.VIRULENCE = VIRULENCE;
    }

    public void setRESISTANCE(int RESISTANCE) {
        this.RESISTANCE = RESISTANCE;
    }

    public void setINFECTED_PERCENTAGE(int INFECTED_PERCENTAGE) {
        this.INFECTED_PERCENTAGE = INFECTED_PERCENTAGE;
        changed();
    }

    public void setINFECTED(int INFECTED) {
        this.INFECTED = INFECTED;
    }

    public void setPOPULATION(int POPULATION) {
        this.POPULATION = POPULATION;
    }

    public void setRecoveryORFatalityTime(int recoveryORFatalityTime) {
        this.recoveryORFatalityTime = recoveryORFatalityTime;
        changed();
    }

    public int getVIRULENCE() {
        return VIRULENCE;
    }

    public int getRESISTANCE() {
        return RESISTANCE;
    }

    public int getINFECTED_PERCENTAGE() {
        return INFECTED_PERCENTAGE;
    }

    public int getINFECTED() {
        return INFECTED;
    }

    public int getPOPULATION() {
        return POPULATION;
    }

    public int getRecoveryORFatalityTime() {
        return recoveryORFatalityTime;
    }
}
