package simstation.greed;

import mvc.AppFactory;
import simstation.WorldPanel;

public class GreedPanel extends WorldPanel {
    public GreedPanel(AppFactory factory) {
        super(factory);
    }

    public static void main (String[] args) {
        GreedPanel panel = new GreedPanel(new GreedFactory());
        panel.display();
    }
}
