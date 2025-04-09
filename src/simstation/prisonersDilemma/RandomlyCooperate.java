package simstation.prisonersDilemma;

import static mvc.Utilities.rng;

public class RandomlyCooperate implements Strategy{

    public boolean cooperate() {
        int r = rng.nextInt(2);
        if (r == 0) {
            return true;
        } else {
            return false;
        }
    }
}
