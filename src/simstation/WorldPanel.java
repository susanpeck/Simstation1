package simstation;

import mvc.*;
import javax.swing.*;
import java.awt.*;

/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

public class WorldPanel extends AppPanel {

    // create the buttons to add to the control panel
    private JButton start;
    private JButton pause;
    private JButton resume;
    private JButton stop;
    private JButton stats;

    // changes frame size
    static {
        AppPanel.FRAME_HEIGHT = 550;
        AppPanel.FRAME_WIDTH = 1000;
    }

    // constructor
    public WorldPanel(AppFactory factory) {
        super(factory);

        // initialize buttons with names
        start = new JButton("Start");
        pause = new JButton("Pause");
        resume = new JButton("Resume");
        stop = new JButton("Stop");
        stats = new JButton("Stats");

        // add the buttons to action listener
        start.addActionListener(this);
        pause.addActionListener(this);
        resume.addActionListener(this);
        stop.addActionListener(this);
        stats.addActionListener(this);

        // set the customized control panel layout
        controlPanel.setLayout(new GridLayout(3,3)); // CHANGE THIS
        controlPanel.setBackground(Color.PINK);

        // add the buttons to the control panel in order of location (start top left, across the row, etc.)
        Panel p = new Panel();
        p.add(start);
        p.add(pause);
        p.add(resume);
        p.add(stop);
        p.add(stats);
        controlPanel.add(p);
    }

    @Override
    public void setModel(Model newModel) {
        super.setModel(newModel);
        this.remove(view);
        view = factory.makeView(model);
        this.add(view);
    }

    public static void main(String[] Args){
        AppFactory factory = new WorldFactory();
        AppPanel panel = new WorldPanel(factory);
        panel.display();
    }
}
