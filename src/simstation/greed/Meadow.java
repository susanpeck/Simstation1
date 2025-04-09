package simstation.greed;

import simstation.World;

public class Meadow extends World {
    private int waitPenalty = 5;
    private int moveEnergy = 10;
    private int numCows = 50;
    private int dim = World.SIZE/Patch.patchSize;

    public Patch getPatch(int xc, int yc) {
        return null;
    }
}
