package prisonersDilemma;

import mvc.AppPanel;
import simstation.Agent;
import simstation.World;
import simstation.WorldPanel;

import java.util.ArrayList;

public class Tournament extends World {
    public static int population = 40;
    // do with 10 each strategy
    //

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
            //System.out.println("populating agents");
            if(i < (population/4)) { // first quarter
                addAgent(new Prisoner(0));
            } else if (i >= population/4 && i < population/2) { // second quarter
                addAgent(new Prisoner(1));
            } else if (i >= population/2 && i < (population/2 + population/4)) { // thir quarter
                addAgent(new Prisoner(2));
            } else {
                addAgent(new Prisoner(3));
            }
        }

        // place prisoners into separate arrays for calculating avg fitness
        for (Agent a : getAgents()) {
            //System.out.println("Strategies being divided up");
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
        return "#prisoners = " + alive + "\n" +
                "#clock = " + clock + "\n" +
                "Average Fitness: \n" +
                "Cooperate: " + coopFitness + "\n" +
                "Cheat: " + cheatFitness + "\n" +
                "Randomly Cooperate: " + randFitness + "\n" +
                "Tit4Tat: " + t4tFitness;
    }

    @Override
    public void updateStatistics() { // updateStatistics being called prematurely on new
        clock++;
        alive = agents.size();

        int cheatFitnessHelper = 0;
        for(Prisoner p : cheatCount) {
            cheatFitnessHelper += p.getFitness();
        }
        cheatFitness = cheatFitnessHelper / (population/4);

        int coopFitnessHelper = 0;
        for(Prisoner p : coopCount) {
            coopFitnessHelper += p.getFitness();
        }
        coopFitness = coopFitnessHelper / (population/4);

        int randFitnessHelper = 0;
        for(Prisoner p : randCount) {
            randFitnessHelper += p.getFitness();
        }
        randFitness = randFitnessHelper / (population/4);

        int t4tFitnessHelper = 0;
        for(Prisoner p : t4tCount) {
            t4tFitnessHelper += p.getFitness();
        }
        t4tFitness = t4tFitnessHelper / (population/4);
    }



    public static void main(String[] args) {
        AppPanel panel = new WorldPanel(new TournamentFactory());
        panel.display();
    }
}
