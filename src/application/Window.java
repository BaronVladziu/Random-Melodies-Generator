package application;

import music.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {

    private PicturePanel _picturePanel = new PicturePanel();
    private UIPanel _uiPanel = new UIPanel(this);

    public Window() {
        super("Random-Melodies-Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        add(_picturePanel);
        add(_uiPanel);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        _uiPanel.setViewToWorkInProgress();
        System.out.println("Generating melody settings...");
        boolean ifNoExceptionHappened = true;
        try {
            Melody melody = new Melody(_uiPanel.createMelodySettings());
        }
        catch (UnsupportedNoteNotationException exception) {
            ifNoExceptionHappened = false;
        }

        if (ifNoExceptionHappened) {
            System.out.println("Melody settings generated");
        }
        else {
            System.out.println("Generating melody settings failed!");
        }
        _uiPanel.setViewToDone();
    }

}