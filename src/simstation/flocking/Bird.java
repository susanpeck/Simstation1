package simstation.flocking;

import mvc.Utilities;
import simstation.Agent;
import simstation.Heading;
import simstation.MobileAgent;

public class Bird extends MobileAgent {
    private int speed;
    private final int RADIUS = 10;

    public Bird() {
        super();
        heading = Heading.random();
        speed = Utilities.rng.nextInt(20) + 1;
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
