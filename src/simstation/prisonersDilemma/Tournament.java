package simstation.prisonersDilemma;

import mvc.AppPanel;
import simstation.World;
import simstation.WorldPanel;

public class Tournament extends World {
    public static int population = 50;
    public void populate() {
        for(int i = 0; i < population; i++) {
            addAgent(new Prisoner()); //
        }
    }

    public static void main(String[] args) {
        AppPanel panel = new WorldPanel(new TournamentFactory());
        panel.display();
    }
}
