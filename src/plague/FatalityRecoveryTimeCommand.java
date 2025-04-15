package plague;

import mvc.*;
/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

public class FatalityRecoveryTimeCommand extends Command {
    Integer value = null;

    public FatalityRecoveryTimeCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {

        if (!(model instanceof PlagueSim plague)) {
            throw new Exception("Model must be a World.");
        }

        if(value == null){
            if(plague.isFatal()){
                String response = Utilities.ask("What is the fatality time?");
                value = Integer.valueOf(response);
            }
            else{
                String response = Utilities.ask("What is the recovery time?");
                value = Integer.valueOf(response);
            }
        }
        if (value < 0) {
            value = 0;
        }
        else if (value > 500) {
            value = 500;
        }
        plague.setRecoveryORFatalityTime(value);
    }
}

