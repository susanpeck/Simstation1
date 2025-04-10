package simstation.prisonersDilemma;

import mvc.AppPanel;
import simstation.World;
import simstation.WorldPanel;

public class Tournament extends World {
    public static int population = 20;

    // use to calculate average fitness:
    protected int cheatCount = 0;
    protected int cooperateCount = 0;
    protected int randCoopCount = 0;
    protected int tit4TatCount = 0;


    public void populate() {
        for(int i = 0; i < population; i++) {
            addAgent(new Prisoner()); //
        }
    }

    @Override
    public String getStatus() {
        return "#agents = " + agents.size() + "\n" + "#clock = " + clock + "\n" + " " + ;
    }

    public static void main(String[] args) {
        AppPanel panel = new WorldPanel(new TournamentFactory());
        panel.display();
    }
}
