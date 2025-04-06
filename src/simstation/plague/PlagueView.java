package simstation.plague;

import mvc.Model;
import simstation.Agent;
import simstation.WorldView;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class PlagueView extends WorldView {
    public PlagueView(Model m) {
        super(m);
    }

    @Override
    public void drawAgent(Agent a, Graphics2D gc) {
        int xCoordinate = a.getXc();
        int yCoordinate = a.getYc();
        Ellipse2D.Double circle = new Ellipse2D.Double(
                xCoordinate - (AGENT_DIAMETER/2),
                yCoordinate - (AGENT_DIAMETER/2),
                AGENT_DIAMETER,
                AGENT_DIAMETER
        );
        if (((Creature)a).isInfected()) {
            gc.setColor(Color.RED);
        }
        else {
            gc.setColor(Color.GREEN);
        }
        gc.fill(circle);
        gc.draw(circle);
    }
}
