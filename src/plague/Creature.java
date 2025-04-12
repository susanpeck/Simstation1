package plague;

import mvc.*;
import simstation.*;

/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

public class Creature extends MobileAgent {
    private boolean isInfected;
    private int recoveryOrFatalityTime;
    private boolean isFatal;
    private int time;
    private boolean isDead;

    static {
        sleepTime = 20;
    }

    /**
     * Constructor of a Creature which is also a MobileAgent
     *
     * @param recoveryOrFatalityTime the amount of time before the creature
     * @param isFatal boolean value to show if the creatures die after being infected
     * @param isInfected boolean value if the creature is or is not infected
     */
    public Creature(int recoveryOrFatalityTime, boolean isFatal, boolean isInfected) {
        super("Creature");
        this.recoveryOrFatalityTime = recoveryOrFatalityTime;
        this.isFatal = isFatal;
        this.isInfected = isInfected;
        this.isDead = false;
        this.time = 0;
    }


    @Override
    // might be an issue with the move logic since a random agent would just freeze, might be something wrong with the heading
    // also noticed that sometimes agents would disappear so the wrap logic might need some fixing
    public void update() {
        if (!this.isDead) {
            PlagueSim plague = (PlagueSim)world;
            Creature c = (Creature)world.getNeighbor(this, 10);
            if (c != null && !this.isInfected && c.isInfected) {
                // chance of infection
                int rand = Utilities.rng.nextInt(100);
                if (rand < plague.getVIRULENCE()) {
                    rand = Utilities.rng.nextInt(100);
                    if (rand > plague.getRESISTANCE()) {
                        isInfected = true;
                        plague.setINFECTED(plague.getINFECTED() + 1);
                        time = 0;
                        this.recoveryOrFatalityTime = c.getRecoveryOrFatalityTime();
                    }
                }
            }

            if (isInfected) {
                time++;
            }

            if (time >= recoveryOrFatalityTime) {
                if (this.isFatal) {
                    isDead = true;
                }
                isInfected = false;
                if (plague.getINFECTED() > 0) {
                    plague.setINFECTED(plague.getINFECTED() - 1);
                }
                time = 0;
            }
            move(1);
        }
    }

    public boolean isInfected() {
        return isInfected;
    }

    public int getRecoveryOrFatalityTime() {
        return recoveryOrFatalityTime;
    }

    public boolean isDead() {
        return isDead;
    }
}
