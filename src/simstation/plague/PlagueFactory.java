package simstation.plague;

import mvc.Model;
import simstation.WorldFactory;

public class PlagueFactory extends WorldFactory {
    public Model makeModel() { return new PlagueSim(); }
    public String getTitle() { return "Plague";}
}
