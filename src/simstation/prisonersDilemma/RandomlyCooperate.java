package simstation.prisonersDilemma;

import static mvc.Utilities.rng;

public class RandomlyCooperate extends Strategy{

    public boolean cooperate() {
        int r = rng.nextInt(2);
        if (r == 0) {
            return true;
        } else {
            return false;
        }
    }
}
