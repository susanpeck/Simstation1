package plague;

import mvc.*;
import simstation.*;
/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

public class PlagueSim extends World {
    public int VIRULENCE = 50; // % chance of dying from infection
    public int RESISTANCE = 2; // % chance of resisting infection (not getting infected from neighbor?)
    public int infectedPercent = 5; // the % of infected Creatures when Start is pressed
    public double INFECTED = 0; // the number of infected Creatures
    public double numAlive = 0; //keep track of the Creatures that remain alive
    public int population = 50; // add this many new Creatures to the View when Start is pressed
    public int recoveryORFatalityTime = 200; // the time it takes for a Creature to die or recovery
    private boolean isFatal = true; // true when an infected Creature will eventually die, false if the Creature will recover
    private double percentInfected; // number of infected Creatures divided by alive Creatures

    public PlagueSim(){
        super();
    }

    public void populate() {
        numAlive = numAlive + population; //the number of Creatures alive are the previous ones plus the new population
        for(int i = 0; i < population; i++) {
            int rand = Utilities.rng.nextInt(100);
            if (rand < infectedPercent) { // selected as a new infected Creature
                rand = Utilities.rng.nextInt(100);
                if (rand > RESISTANCE) { // the Creature does not resist the infection
                    addAgent(new Creature(recoveryORFatalityTime, isFatal, true)); // add an infected Creature
                    INFECTED++; // keep track of how many infected creatures total when Start is pressed
                }
            }
            else {
                addAgent(new Creature(recoveryORFatalityTime, isFatal, false)); // add a non-infected Creature
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
        return "Number of Creatures Alive: " + (int)numAlive + "\n" + "Clock: " + clock + "\n" + "Percent Infected Creatures: " + String.format("%.2f", percentInfected) +"%";
    }

    @Override
    public void updateStatistics(){
        // increment the clock
        clock++;
        if(numAlive == 0) {
            percentInfected = 0;
        }
        else {
            percentInfected =  INFECTED / numAlive * 100;
        }
    }

    public void setFatal(boolean fatal) {
        isFatal = fatal;
        changed();
    }

    public boolean isFatal() {
        return isFatal;
    }

    public void setVIRULENCE(int VIRULENCE) {
        this.VIRULENCE = VIRULENCE;
        changed();
    }

    public void setRESISTANCE(int RESISTANCE) {
        this.RESISTANCE = RESISTANCE;
        changed();
    }

    public void setInfectedPercent(int infectedPercent) {
        this.infectedPercent = infectedPercent;
        changed();
    }

    public void setINFECTED(double INFECTED) {
        this.INFECTED = INFECTED;
        changed();
    }

    public void setPopulation(int population) {
        this.population = population;
        changed();
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

    public int getInfectedPercent() {
        return infectedPercent;
    }

    public double getINFECTED() {
        return INFECTED;
    }

    public int getPopulation() {
        return population;
    }

    public int getRecoveryORFatalityTime() {
        return recoveryORFatalityTime;
    }
}
