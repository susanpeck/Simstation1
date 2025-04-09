package simstation.greed;

import mvc.Model;
import mvc.View;
import simstation.World;
import simstation.WorldFactory;

public class GreedFactory extends WorldFactory {
    @Override
    public Model makeModel() {
        return new Meadow();
    }

    @Override
    public View makeView(Model m) {
        return new GreedView((World) m);
    }

    @Override
    public String getTitle() {
        return "Greed";
    }

    @Override
    public String[] getHelp() {
        return new String[] {
                "Start - populates simulation with agents",
                "Pauses - pauses agents",
                "Resume - starts the paused agents",
                "Stop - stops agents",
                "Stats - shows stats of agents",
        };
    }
}
