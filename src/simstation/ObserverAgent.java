package simstation;

/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

public class ObserverAgent extends Agent{

    public ObserverAgent(){
        super("ObserverAgent");
    }

    @Override // do we need override? we have it in mobile agent
    public void update(){
        // this update method calls the world's updateStatistics method
        world.updateStatistics();
    }
}
