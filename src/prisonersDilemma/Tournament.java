package prisonersDilemma;

import mvc.AppPanel;
import simstation.Agent;
import simstation.World;
import simstation.WorldPanel;

import java.util.ArrayList;

public class Tournament extends World {
    public static int population = 20;

    // create arrays for different categories of strategies
    protected ArrayList<Prisoner> cheatCount;
    protected ArrayList<Prisoner> coopCount;
    protected ArrayList<Prisoner> randCount;
    protected ArrayList<Prisoner> t4tCount;

    private int cheatFitness;
    private int coopFitness;
    private int randFitness;
    private int t4tFitness;

    public Tournament() {
        super();

        cheatCount = new ArrayList<>();
        coopCount = new ArrayList<>();
        randCount = new ArrayList<>();
        t4tCount = new ArrayList<>();
    }

    public void populate() {
        for(int i = 0; i < population; i++) {
            addAgent(new Prisoner()); //
        }

        // place prisoners into separate arrays for calculating avg fitness
        for (Agent a : getAgents()) {
            if(a.getAgentName() == "Mobile Agent") { // check type first
                Prisoner p = (Prisoner) a;
                int strat = p.getStrategy();

                if(strat == 0) {
                    coopCount.add(p);
                } else if (strat == 1) {
                    cheatCount.add(p);
                } else if (strat == 2) {
                    randCount.add(p);
                } else {
                    t4tCount.add(p);
                }
            }
        }
    }

    @Override
    public String getStatus() {
        return "#prisoners = " + agents.size() + "\n" +
                "#clock = " + clock + "\n" +
                "Average Fitness: \n" +
                "Cooperate: " + coopFitness + "\n" +
                "Cheat: " + cheatFitness + "\n" +
                "Randomly Cooperate: " + randFitness + "\n" +
                "Tit4Tat: " + t4tFitness;
    }

    @Override
    public void updateStatistics() {
        clock++;
        alive = agents.size();

        int cheatFitnessHelper = 0;
        for(Prisoner p : cheatCount) {
            cheatFitnessHelper += p.getFitness();
        }
        cheatFitness = cheatFitnessHelper / cheatCount.size();

        int coopFitnessHelper = 0;
        for(Prisoner p : coopCount) {
            coopFitnessHelper += p.getFitness();
        }
        coopFitness = coopFitnessHelper / coopCount.size();

        int randFitnessHelper = 0;
        for(Prisoner p : randCount) {
            randFitnessHelper += p.getFitness();
        }
        randFitness = randFitnessHelper / randCount.size();

        int t4tFitnessHelper = 0;
        for(Prisoner p : t4tCount) {
            t4tFitnessHelper += p.getFitness();
        }
        t4tFitness = t4tFitnessHelper / t4tCount.size();
    }



    public static void main(String[] args) {
        AppPanel panel = new WorldPanel(new TournamentFactory());
        panel.display();
    }
}
