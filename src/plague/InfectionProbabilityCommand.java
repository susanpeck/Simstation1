package plague;

import mvc.*;
/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

public class InfectionProbabilityCommand extends Command {
    Integer value = null;

    public InfectionProbabilityCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        if (!(model instanceof PlagueSim plague)) {
            throw new Exception("Model must be a World.");
        }

        if(value == null){
            String response = Utilities.ask("What should the infection probability be?");
            value = Integer.valueOf(response);
        }
        if (value < 0) {
            value = 0;
        }
        else if (value > 100) {
            value = 100;
        }
        plague.setVIRULENCE(value);

    }
}
