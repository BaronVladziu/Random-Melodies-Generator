package application;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PicturePanel extends JPanel {

    public PicturePanel() {
        setPreferredSize(new Dimension(600, 600));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        Rectangle2D rectangle2D = new Rectangle2D.Float(100, 100, 200, 200);

        graphics2D.draw(rectangle2D);
    }

}