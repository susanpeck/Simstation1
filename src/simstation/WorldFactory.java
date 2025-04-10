package simstation;

/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

import mvc.*;

public class WorldFactory implements AppFactory {

    public Model makeModel() {
        return new World();
    }

    public View makeView(Model m) {
        return new WorldView((World) m);
    }

    public String getTitle() {
        return "Sim Station Game";
    }

    public String[] getHelp() {
        return new String[] {
                "Start - populates simulation with agents",
                "Pauses - temporarily pauses agents",
                "Resume - starts the paused agents",
                "Stop - permanently stops agents",
                "Stats - shows stats of agents",
        };
    }

    public String about() {
        return "CS151 Sim Station Group 1 March 2025";
    }

    public String[] getEditCommands() {
        return new String[] {"Start","Pause","Resume","Stop","Stats"};
    }

    public Command makeEditCommand(Model model, String type, Object source) {
        return switch (type) {
            case "Start" -> new StartCommand(model);
            case "Pause" -> new PauseCommand(model);
            case "Resume" -> new ResumeCommand(model);
            case "Stop" -> new StopCommand(model);
            case "Stats" -> new StatsCommand(model);
            default -> null;
        };
    }
}
