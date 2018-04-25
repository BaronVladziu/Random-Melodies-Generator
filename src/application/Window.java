package application;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window() {
        super("Random-Melodies-Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        PicturePanel picturePanel = new PicturePanel();
        add(picturePanel);
        UIPanel uiPanel = new UIPanel();
        add(uiPanel);

        pack();
        setVisible(true);
    }

}