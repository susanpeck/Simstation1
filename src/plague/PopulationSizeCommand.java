package plague;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

public class PopulationSizeCommand extends Command {
    Integer value = null;

    public PopulationSizeCommand(Model model) {
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
        else if (value > 200) {
            value = 200;
        }
        plague.setPopulation(value);
    }
}
