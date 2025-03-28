package simstation;

public class ObserverAgent extends Agent{



    public void update(){

        // this update method calls the world's updateStatistics method
        world.updateStatistics();
    }


}
