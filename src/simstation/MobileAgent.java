package simstation;

/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

public abstract class MobileAgent extends Agent {

    protected Heading heading; // a heading can be N, E, S, W, changed used turn

    // default constructor
    public MobileAgent(){
        super("Mobile Agent");
        heading = Heading.random();
    }

    public MobileAgent(String name) {
        super(name);
        heading = Heading.random();
    }

    public void move(int steps){
        /*
        A mobile agent's location can be changed by calling its move method.
        An agent wraps around the world if it's location goes beyond the border.
         */
        // move one step at a time even if the number of steps is large
        for(int i = 1; i <= steps; i++){
            if(heading == Heading.NORTH){
                yc = yc - 1;
            }
            else if(heading == Heading.SOUTH){
                yc = yc + 1;
            }
            else if(heading == Heading.WEST){
                xc = xc - 1;
            }
            else if(heading == Heading.EAST){
                xc = xc + 1;
            }
            // use mod to make sure the world coordinates wrap around
            int worldSize = world.getSize();
            yc = ((yc % worldSize) + worldSize) % worldSize;
            xc = ((xc % worldSize) + worldSize) % worldSize;
            // if mobile agents move, then we need to call changed() method
            world.changed();
        }
    }

    // change the mobile agent's heading to a specific direction
    public void turn(Heading dir){
        this.heading = dir;
    }

    public Heading getHeading(){
        return heading;
    }

    @Override
    public void update() {
        move(1);
    }
}
