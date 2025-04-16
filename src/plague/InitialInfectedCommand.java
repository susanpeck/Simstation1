package plague;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;
/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */
public class InitialInfectedCommand extends Command {
    Integer value = null;

    public InitialInfectedCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        if (!(model instanceof PlagueSim plague)) {
            throw new Exception("Model must be a PlagueSim.");
        }

        if(value == null){
            String response = Utilities.ask("What should the initial infected percentage be?");
            value = Integer.valueOf(response);
        }

        if (value < 0) {
            value = 0;
        }
        else if (value > 100) {
            value = 100;
        }
        plague.setInfectedPercent(value);
    }
}
