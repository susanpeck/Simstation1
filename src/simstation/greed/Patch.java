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

    public synchronized void eatMe(Cow cow, int amt) {
        // what prof had
//        if(cow.isStopped()) return;
//        while (energy < amt) {
//            try {
//                wait();
//                //cow.decEnergy(Meadow.waitPenalty);
//                if (cow.isStopped()) break;
//            }
//            catch (InterruptedException e) {
//                System.err.println(e.getMessage());
//            }
//            if (cow.energy???) {
//                do stuff lmao i didnt get everything
//            }
//        }
    }
}
