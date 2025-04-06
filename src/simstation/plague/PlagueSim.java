package simstation.plague;

import simstation.*;

public class PlagueSim extends World {
    public static int VIRULENCE = 50; // % chance of infection
    public static int RESISTANCE = 2; // % chance of resisting infection
    private int infected = 0;

    @Override
    public String getStatus() {
        return "#agents = " + agents.size() + "\n" + "#clock = " + clock + "\n" + "% infected = " + infected;
    }

    public void incrementInfected() {
        infected++;
    }
}
