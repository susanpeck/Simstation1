package flocking;

import mvc.*;
import simstation.*;

public class Bird extends MobileAgent {
    private int speed;
    private final int RADIUS = 10;

    static {
        sleepTime = 50;
    }

    public Bird() {
        super();
        speed = Utilities.rng.nextInt(20) + 10;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void update() {
        Bird a = (Bird)world.getNeighbor(this, RADIUS);
        if (a != null) {
            this.turn(a.getHeading());
            setSpeed(a.getSpeed());
        }
        move(speed);
    }
}
