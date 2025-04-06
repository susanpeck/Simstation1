package simstation.plague;

import mvc.Model;
import mvc.View;
import simstation.World;
import simstation.WorldFactory;

public class PlagueFactory extends WorldFactory {
    public Model makeModel() { return new PlagueSim(); }
    public View makeView(Model m) {
        return new PlagueView((World) m);
    }
    public String getTitle() { return "Plague";}
}
