package mvc;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class View extends JPanel implements Subscriber{
    protected Model model;

    public View(Model model) {
        super();
        this.model = model;
        model.subscribe(this);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        setBorder(blackLine);
    }

    @Override
    public void update() {
        this.repaint();
    }

    public void setModel(Model model) {
        if (model != null) {
            model.unsubscribe(this);
        }
        this.model = model;
        if (this.model != null) {
            model.subscribe(this);
            update();
        }
    }
}
