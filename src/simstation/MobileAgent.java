package simstation;

public abstract class MobileAgent extends Agent {

    private Heading heading; // a heading can be N, E, S, W, changed used turn

    // default constructor
    public MobileAgent(String name){
        super(name);
        heading = Heading.random();
    }

    public void move(int steps){
        /*
        A mobile agent's location can be changed by calling its
        move method. (Note that an agent wraps around the world
        if it's location goes beyond the border.
         */

        /*
        "In my version an agent moves one step at a time for n steps, calling world.changed after each step"
        -professor pearce
         */

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

            // world coordinates wrap around
            if(yc > world.getSize()){
                yc = world.getSize() - yc;
            }
            if(xc > world.getSize()){
                xc = world.getSize() - xc;
            }

            // if mobile agents move, then we need to call changed() method
            world.changed();
        }
    }

    public void turn(String direction){
        //guessing that we can only turn left or right?
        if(direction.equals("right")){
            if(heading == Heading.NORTH){
                heading = Heading.EAST;
            }
            else if(heading == Heading.EAST){
                heading = Heading.SOUTH;
            }
            else if(heading == Heading.SOUTH){
                heading = Heading.WEST;
            }
            else if(heading == Heading.WEST){
                heading = Heading.NORTH;
            }
            else {
                // what goes here?
            }
        }
        else if (direction.equals("left")){
            if(heading == Heading.NORTH){
                heading = Heading.WEST;
            }
            else if(heading == Heading.EAST){
                heading = Heading.NORTH;
            }
            else if(heading == Heading.SOUTH){
                heading = Heading.EAST;
            }
            else if(heading == Heading.WEST){
                heading = Heading.SOUTH;
            }
            else {
                // what goes here?
            }
        }
    }
}
