package application;

import music.Melody;
import music.UnsupportedNoteNotationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Window extends JFrame implements ActionListener {

    private PicturePanel _picturePanel = new PicturePanel();
    private UIPanel _uiPanel = new UIPanel(this);
    private Melody _melody = new Melody();

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
            _melody.setSettings(_uiPanel.createMelodySettings());
        }
        catch (UnsupportedNoteNotationException exception) {
            ifNoExceptionHappened = false;
        }

        if (ifNoExceptionHappened) {
            System.out.println("Melody settings generated");
            System.out.println("Generating melody...");
            try {
                _melody.generate();
            }
            catch (UnsupportedNoteNotationException exception) {
                System.out.println(exception.getMessage());
            }
            try {
                _picturePanel.displayImage(_melody.getOutputFileName() + ".png");
            }
            catch (IOException exception) {
                System.out.println("Failed to find compiled file");
            }
            System.out.println("Melody generated");
        }
        else {
            System.out.println("Generating melody settings failed!");
        }
        _uiPanel.setViewToDone();
    }

}