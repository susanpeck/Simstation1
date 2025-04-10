package simstation.plague;

import mvc.Utilities;
import simstation.*;
/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

public class PlagueSim extends World {
    public int VIRULENCE = 50; // % chance of infection
    public int RESISTANCE = 2; // % chance of resisting infection
    public int INFECTED_PERCENTAGE = 5;
    public int INFECTED = 0;
    public int POPULATION = 50;
    public int TIME = 200;
    private boolean isFatal = true;

    public void populate() {
        for(int i = 0; i < POPULATION; i++) {
            int rand = Utilities.rng.nextInt(100);
            if (rand < INFECTED_PERCENTAGE) {
                rand = Utilities.rng.nextInt(100);
                if (rand > RESISTANCE) {
                    addAgent(new Creature(TIME, isFatal, true));
                    INFECTED++;
                }
            }
            else {
                addAgent(new Creature(TIME, isFatal, false));
            }
        }
    }

    @Override
    public String getStatus() {
        return "#agents = " + agents.size() + "\n" + "#clock = " + clock + "\n" + "% INFECTED = " + INFECTED;
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
    }

    public void setINFECTED(int INFECTED) {
        this.INFECTED = INFECTED;
    }

    public void setPOPULATION(int POPULATION) {
        this.POPULATION = POPULATION;
    }

    public void setTIME(int TIME) {
        this.TIME = TIME;
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

    public int getTIME() {
        return TIME;
    }
}
