package application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class PicturePanel extends JPanel {

    private BufferedImage image;

    PicturePanel() {
        setPreferredSize(new Dimension(800, 900));
    }

    void displayImage(String name) throws IOException {
        File imageFile = new File(name);
        image = ImageIO.read(imageFile);
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(image, 0, 0, this);
    }

}