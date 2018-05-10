package application;

import console.ConsolePanel;
import music.Melody;
import music.UnsupportedNoteNotationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class Window extends JFrame implements ActionListener {

    private final PicturePanel _picturePanel = new PicturePanel();
    private final Melody _melody = new Melody();
    private final MidiPlayer _midiPlayer = new MidiPlayer(_melody.getOutputFileName());
    private final ConsolePanel _consolePanel = new ConsolePanel();
    private final UIPanel _uiPanel = new UIPanel(this, _midiPlayer, _consolePanel);

    Window() {
        super("Random-Melodies-Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        add(_picturePanel);
        add(_uiPanel);
        add(_consolePanel);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        _uiPanel.setViewToWorkInProgress();
        _consolePanel.clear();
        _consolePanel.display("Sprawdzanie poprawności ustawień...");
        boolean ifNoExceptionHappened = true;
        try {
            _melody.setSettings(_uiPanel.createMelodySettings());
        }
        catch (UnsupportedNoteNotationException exception) {
            ifNoExceptionHappened = false;
        }

        if (ifNoExceptionHappened) {
            _consolePanel.display("Ustawienia sprawdzone");
            _consolePanel.display("Generowanie melodii...");
            try {
                _melody.generate();
            }
            catch (UnsupportedNoteNotationException exception) {
                _consolePanel.display(exception.getMessage());
            }
            try {
                _picturePanel.displayImage(_melody.getOutputFileName() + ".png");
            }
            catch (IOException exception) {
                _consolePanel.display("Nie znaleziono skompilowanego pliku .png!");
            }
            _consolePanel.display("Melodia wygenerowana");
        }
        else {
            _consolePanel.display("Nie udało się wygenerować melodii!");
        }
        _uiPanel.setViewToDone();
    }

}