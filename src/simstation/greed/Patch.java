package simstation.greed;

import simstation.Agent;

public class Patch extends Agent {
    private int energy = 100; // energy: int < = 100
    public static int growBackRate = 1;
    protected static int patchSize = 10;

    public Patch(String name) {
        super(name);
    }

    @Override
    public void update() {

    }

    public void eatMe(Cow cow, int amt) {

    }
}
