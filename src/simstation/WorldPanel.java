package simstation;

import mvc.*;
import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

public class WorldPanel extends AppPanel {
    /*
        WorldPanel creates a thread control subpanel (threadPanel) and
        adds it to the northern region of the control panel
        (which is an AppPanel field.) Subclasses can add additional
        controls to the other regions pof the control panel.
    */

    // create the subpanel for the thread buttons
    JPanel threadPanel = new JPanel();

    // create the buttons to add to the thread part of the control panel
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
        controlPanel.setLayout(new BorderLayout());
        controlPanel.setBackground(Color.PINK);

        // add the buttons to the thread panel in order of location (start top left, across the row, etc.)
        threadPanel.setLayout(new GridLayout(1,5));
        threadPanel.setBackground(Color.DARK_GRAY);
        threadPanel.add(start);
        threadPanel.add(pause);
        threadPanel.add(resume);
        threadPanel.add(stop);
        threadPanel.add(stats);

        // add the thread panel to the top of the control panel
        controlPanel.add(threadPanel, BorderLayout.NORTH);

        // other extensions will add to the control panel in other regions
    }

    public void setModel(Model newModel) {
        super.setModel(newModel);
        World w = (World) newModel;
        Iterator<Agent> it = w.iterator();
        while(it.hasNext()) {
            Thread t = new Thread(it.next());
            t.start();
        }
    }
}

