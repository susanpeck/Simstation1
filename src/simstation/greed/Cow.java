package simstation.greed;

import simstation.MobileAgent;

public class Cow extends MobileAgent {
    private int energy = 100;
    public static int greediness = 25;
    private Meadow location;

    // goes to location and calls eatMe, tries to top off energy to 100, eating grass depletes energy of patch
    // several cows can occupy a single patch of grass but every cow can't eat at the same time (bank account situation)
    // patch needs to be threat safe, eatMe is synchronized?
    @Override
    public void update() {
        super.update();
    }
}
