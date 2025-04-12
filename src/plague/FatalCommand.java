package plague;

import mvc.*;
/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

public class FatalCommand extends Command {
    public FatalCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        if (!(model instanceof PlagueSim plague)) {
            throw new Exception("Model must be a World.");
        }
        plague.setFatal(!plague.isFatal());
    }
}
