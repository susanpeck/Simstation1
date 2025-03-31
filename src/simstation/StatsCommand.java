package simstation;

import mvc.*;

/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

public class StatsCommand extends Command {

    public StatsCommand(Model model) {
        super(model);
    }

    public void execute() throws Exception {
        if (!(model instanceof World)) {
            throw new Exception("Model must be a World.");
        }
        World worldModel = (World) model;

        try {
            // call the method in the model
            // is this the correct method?
            Utilities.inform(worldModel.getStatus());
        }

        // catch and throw exceptions to the AppPanel
        catch (Exception e) {
            Utilities.error(e.getMessage());
        }
    }
}
