package simstation;

/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

import mvc.Model;
import mvc.View;

import java.awt.*;

public class SimstationView extends View{
    private final SimstationField mf;

    public SimstationView(Model m){
        super(m);
        if (model == null || !(model instanceof SimstationField)) {
            throw new IllegalStateException("Model must be a Simstation");
        }
        this.mf = (SimstationField) model;
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
}
