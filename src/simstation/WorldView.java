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
    private final World worldModel;

    public WorldView(Model m){
        super(m);
        if (model == null || !(model instanceof World)) {
            throw new IllegalStateException("Model must be a World");
        }
        this.worldModel = (World) model;
        this.setBackground(Color.GRAY);
        initView();
    }

    private void initView() {
        //will we need an initView for this project?
    }

    @Override
    public void update(){
        repaint();
    }

    public void setModel(Model newModel) {
        super.setModel(newModel);
        //initView();
        repaint();
    }

    public void paintComponent(Graphics gc){
        super.paintComponent(gc);
        Graphics2D g2d = (Graphics2D) gc;

        //paint all the agents in the World
        synchronized(worldModel) {
            for(Agent a : worldModel.getAgents()){
                drawAgent(a, g2d);
            }
        }
    }

    public void drawAgent(Agent a, Graphics2D gc){
        // marked off in Turtle Graphics for View accessing or changing things it shouldn't

        // draw a diameter 10 red filled oval at Agents a's location
        int xCoordinate = a.getXc();
        int yCoordinate = a.getYc();
        Ellipse2D.Double circle = new Ellipse2D.Double(xCoordinate-5, yCoordinate-5, 10, 10);
        gc.setColor(Color.RED);
        gc.fill(circle);
        gc.draw(circle);
    }
}
