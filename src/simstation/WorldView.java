package simstation;

/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

import mvc.Model;
import mvc.View;

import java.awt.*;

public class WorldView extends View{
    private final World mf;

    public WorldView(Model m){
        super(m);
        if (model == null || !(model instanceof World)) {
            throw new IllegalStateException("Model must be a Simstation");
        }
        this.mf = (World) model;
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
        initView();
        repaint();
    }

    public void paintComponent(Graphics gc){

    }

    public void drawAgent(Agent a, Graphics gc){

    }
}
