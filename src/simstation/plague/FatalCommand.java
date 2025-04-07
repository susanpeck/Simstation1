package simstation.plague;

import mvc.Command;
import mvc.Model;

public class FatalCommand extends Command {
    public FatalCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        if (!(model instanceof PlagueSim plauge)) {
            throw new Exception("Model must be a World.");
        }
        plauge.setFatal(!plauge.isFatal());
    }
}
