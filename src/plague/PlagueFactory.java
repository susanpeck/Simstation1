package plague;

import mvc.*;
import simstation.*;
/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

public class PlagueFactory extends WorldFactory {
    public Model makeModel() { return new PlagueSim(); }

    public View makeView(Model m) {
        return new PlagueView((World) m);
    }

    public String getTitle() { return "Plague";}

    public String[] getHelp() {
        return new String[] {
                "Start - populates simulation with agents",
                "Pauses - pauses agents",
                "Resume - starts the paused agents",
                "Stop - stops agents",
                "Stats - shows stats of agents",
                "Not Fatal - changes fatality time to recovery time",
        };
    }

    public Command makeEditCommand(Model model, String type, Object source) {
        return switch (type) {
            case "Start" -> new StartCommand(model);
            case "Pause" -> new PauseCommand(model);
            case "Resume" -> new ResumeCommand(model);
            case "Stop" -> new StopCommand(model);
            case "Stats" -> new StatsCommand(model);
            case "Not Fatal" -> new FatalCommand(model);
            default -> null;
        };
    }
}
