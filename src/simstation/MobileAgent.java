package simstation;

public abstract class MobileAgent {
    // this class is italic in the UML diagram -> so abstract?



    private Heading heading; // a heading can be N, E, S, W, changed used turn

    // default constructor
    public MobileAgent(){
        super();
        heading = new Heading();
    }
    public void move(int steps){
        /*
        A mobile agent's location can be changed by calling its
        move method. (Note that an agent wraps around the world
        if it's location goes beyond the border.
         */


    }

    public void turn(Heading dir){
        //
    }

    //subclass Heading?
    public class Heading{
        //possible values N,S,E,W
        private String headingValue;

        //default constructor, heading is North
        public Heading(){
            headingValue = "N";
        }

        public Heading(String input){
            headingValue = input;
        }

        public String getHeadingValue(){
            return headingValue;
        }

        public void setHeadingValue(String input){
            headingValue = input;
        }
    }


}
