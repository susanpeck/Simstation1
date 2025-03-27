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
        return new String[] {"Direction line one", "Direction line two"};
    }

    public String about() {
        return "CS151 Sim Station Group 1 March 2025";
    }

    public String[] getEditCommands() {
        return new String[] {"Start","Pause","Resume","Stop","Stats"};
    }

    public Command makeEditCommand(Model model, String type, Object source) {
        // in minefield we had a single command class
        // we might need more than one, move and stats?
        // or five individual?

        if(type.equals("Start")) {
            return new MoveCommand(model, 0, -1);
        }
        else if(type.equals("Pause")) {
            return new MoveCommand(model, 0, 1);
        }
        else if(type.equals("Resume")) {
            return new MoveCommand(model, -1, 0);
        }
        else if(type.equals("Stop")) {
            return new MoveCommand(model, 1, 0);
        }
        else if(type.equals("Stats")) {
            return new MoveCommand(model, 1, -1);
        }
        return null;
    }
}
