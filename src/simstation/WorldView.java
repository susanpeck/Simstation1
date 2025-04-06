package simstation;

/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

import mvc.Model;
import mvc.View;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class WorldView extends View{
    public static int AGENT_DIAMETER = 10;

    public WorldView(Model m){
        super(m);
        if (model == null || !(model instanceof World)) {
            throw new IllegalStateException("Model must be a World");
        }
        this.setBackground(Color.GRAY);
    }


    public void paintComponent(Graphics gc){
        super.paintComponent(gc);
        //paint all the agents in the World
        for(Agent a : ((World)model).getAgents()){
            drawAgent(a, (Graphics2D)gc);
        }
    }

    public void drawAgent(Agent a, Graphics2D gc) {
        // marked off in Turtle Graphics for View accessing or changing things it shouldn't

        // draw a diameter 10 red filled oval at Agents a's location
        int xCoordinate = a.getXc();
        int yCoordinate = a.getYc();
        Ellipse2D.Double circle = new Ellipse2D.Double(
                xCoordinate - (AGENT_DIAMETER/2),
                yCoordinate - (AGENT_DIAMETER/2),
                AGENT_DIAMETER,
                AGENT_DIAMETER
        );
        gc.setColor(Color.RED);
        gc.fill(circle);
        gc.draw(circle);
    }
}
