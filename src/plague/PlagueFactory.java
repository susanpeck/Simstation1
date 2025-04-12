package plague;

import mvc.Command;
import mvc.Model;
import mvc.View;
import simstation.*;

import javax.swing.*;
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

    public String[] getEditCommands() {
        return new String[] {"Start","Pause","Resume","Stop","Stats","Infection Probability:","Fatality/Recovery Time:"};
    }

    public String[] getHelp() {
        return new String[] {
                "Start - adds more Creatures to the simulation",
                "Pauses - temporarily pauses the Creaturs",
                "Resume - starts the paused the Creatures",
                "Stop - permanently stops the Creatures",
                "Stats - show how many Creatures are alive and percent infected",
                "Initial % Infected - the percentage of Creatures are infected at the start",
                "Infection Probability - how likely that a Creature infects its neighbors",
                "Initial Population Size - how many Creatures are added when you press Start",
                "Fatality/Recovery Time - how long the Creature is infected before it dies or recovers",
                "Not Fatal - changes if the infection is fatal to just a recovery time"
        };
    }

    public Command makeEditCommand(Model model, String type, Object source) {
        Command cmmd = super.makeEditCommand(model, type, source);
        if (cmmd == null) {
            if (type.equals("Infection Probability:")) {
                cmmd = new InfectionProbabilityCommand(model);
                if (source instanceof JSlider) {
                    ((InfectionProbabilityCommand)cmmd).value = ((JSlider)source).getValue();
                }
            }
            else if (type.equals("Fatality/Recovery Time:")) {
                cmmd = new FatalityRecoveryTimeCommand(model);
                if (source instanceof JSlider) {
                    ((FatalityRecoveryTimeCommand)cmmd).value = ((JSlider)source).getValue();
                }
            }
            else if(type.equals("Not Fatal")){
                cmmd = new FatalCommand(model);
            }
        }
        return cmmd;


// using Professors Code from Tournament to listen to changes and add two slider commands to Edit menu
//        return switch (type) {
//            case "Start" -> new StartCommand(model);
//            case "Pause" -> new PauseCommand(model);
//            case "Resume" -> new ResumeCommand(model);
//            case "Stop" -> new StopCommand(model);
//            case "Stats" -> new StatsCommand(model);
//            case "Infection Probability:" -> new InfectionProbabilityCommand(model);
//            case "Fatality/Recovery Time:" -> new FatalityRecoveryTimeCommand(model);
//            case "Not Fatal" -> new FatalCommand(model);
//
//            default -> null;

    }
}
