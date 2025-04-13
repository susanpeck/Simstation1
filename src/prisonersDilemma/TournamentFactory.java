package prisonersDilemma;

import mvc.Model;
import simstation.WorldFactory;

public class TournamentFactory extends WorldFactory {
    public Model makeModel() {return new Tournament();}
    public String getTitle() {return "Prisoner's Dilemma";}
}
