package application;

import console.ConsolePanel;
import music.*;

import javax.swing.*;
import java.awt.*;

class UIPanel extends JComponent {

    private static final int _NAME_LENGTH = 130;
    private static final int _INPUT_LENGTH = 30;
    private static final int _BAR_HEIGHT = 25;
    private static final int _GAP_LENGTH = 10;

    private final SettingInputPanel _metrePanel = new SettingInputPanel("Metrum:", "4/4");
    private final SettingInputPanel _lengthPanel = new SettingInputPanel("Liczba taktów:", "4");
    private final SettingInputPanel _startSoundPanel = new SettingInputPanel("Dżwięk początkowy:", "a'");
    private final SettingInputPanel _endSoundPanel = new SettingInputPanel("Dżwięk końcowy:", "a'");
    private final SettingInputPanel _lowestSoundPanel = new SettingInputPanel("Dżwięk najniższy:", "a");
    private final SettingInputPanel _highestSoundPanel = new SettingInputPanel("Dżwięk najwyższy:", "d'''");
    private final SettingInputPanel[] _pitchProbabilities = new SettingInputPanel[E_Note12.values().length];
    private final SettingInputPanel _tempoPanel = new SettingInputPanel("Tempo [bpm]:", "90");
    private final JButton _generateButton = new JButton("Generuj");
    private final JButton _playButton = new JButton("Graj");
    private final SettingInputPanel[] _intervalProbabilities = new SettingInputPanel[E_Interval26.values().length];
    private final ConsolePanel _consolePanel;

    UIPanel(Window window, MidiPlayer midiPlayer, ConsolePanel console) {
        _consolePanel = console;
        JComponent _comp1 = new JPanel();
        _comp1.setPreferredSize(new Dimension(_NAME_LENGTH + _GAP_LENGTH + _INPUT_LENGTH,
                (6 + _pitchProbabilities.length) * (_BAR_HEIGHT + _GAP_LENGTH)));
        _comp1.setLayout(new GridLayout(6 + _pitchProbabilities.length, 0));
        _comp1.add(_metrePanel);
        _comp1.add(_lengthPanel);
        _comp1.add(_startSoundPanel);
        _comp1.add(_endSoundPanel);
        _comp1.add(_lowestSoundPanel);
        _comp1.add(_highestSoundPanel);
        for (int i = 0; i < _pitchProbabilities.length - 1; i++) {
            _pitchProbabilities[i] = new SettingInputPanel("Szansa na " + new NotePitch(E_Note12.values()[i]).getString() + ":", "1");
            _comp1.add(_pitchProbabilities[i]);
        }
        _comp1.add(_tempoPanel);
        _comp1.setVisible(true);

        JComponent _comp2 = new JPanel();
        _comp2.setPreferredSize(new Dimension(_comp1.getPreferredSize().width,
                (_intervalProbabilities.length) * (_BAR_HEIGHT + _GAP_LENGTH)));
        _comp2.setLayout(new GridLayout(_intervalProbabilities.length, 0));
        for (int i = 0; i < _intervalProbabilities.length; i++) {
            _intervalProbabilities[i] = new SettingInputPanel("Szansa na " + new Interval(E_Interval26.values()[i]).getString() + ":", "1");
            _comp2.add(_intervalProbabilities[i]);
        }
        _comp2.setVisible(true);

        _generateButton.setPreferredSize(new Dimension(_comp1.getPreferredSize().width,
                (_comp2.getPreferredSize().height - _comp1.getPreferredSize().height - 2*_GAP_LENGTH)/2));
        _generateButton.addActionListener(window);
        _playButton.setPreferredSize(new Dimension(_comp1.getPreferredSize().width,
                (_comp2.getPreferredSize().height - _comp1.getPreferredSize().height - 2*_GAP_LENGTH)/2));
        _playButton.addActionListener(midiPlayer);
        setViewToDone();
        JComponent _comp3 = new JPanel();
        _comp3.setPreferredSize(new Dimension(_comp1.getPreferredSize().width,
                _comp2.getPreferredSize().height));
        _comp3.setLayout(new FlowLayout());
        _comp3.add(_comp1);
        _comp3.add(_generateButton);
        _comp3.add(_playButton);
        _comp3.setVisible(true);

        setPreferredSize(new Dimension( _comp3.getPreferredSize().width + _GAP_LENGTH + _comp2.getPreferredSize().width,
                _comp2.getPreferredSize().height));
        setLayout(new FlowLayout());
        add(_comp3);
        add(_comp2);
        setVisible(true);
    }

    void setViewToWorkInProgress() {
        _generateButton.setEnabled(false);
        _generateButton.setText("Pracuję...");
        _generateButton.setBackground(Color.GRAY);
        _playButton.setEnabled(false);
        _playButton.setBackground(Color.GRAY);
    }

    void setViewToDone() {
        _generateButton.setBackground(Color.WHITE);
        _generateButton.setText("Generuj");
        _generateButton.setEnabled(true);
        _playButton.setBackground(Color.WHITE);
        _playButton.setEnabled(true);
    }

    MelodySettings createMelodySettings() throws UnsupportedNoteNotationException {
        MelodySettings settings = new MelodySettings();
        boolean ifNoException = true;

        try {
            settings.setMetre(_metrePanel.getInput());
        }
        catch (UnsupportedNoteNotationException exception) {
            ifNoException = false;
            _consolePanel.display(exception.getMessage());
        }

        try {
            settings.setNumberOfBars(_lengthPanel.getInput());
        }
        catch (UnsupportedNoteNotationException exception) {
            ifNoException = false;
            _consolePanel.display(exception.getMessage());
        }

        try {
            settings.setStartNote(_startSoundPanel.getInput());
        }
        catch (UnsupportedNoteNotationException exception) {
            ifNoException = false;
            _consolePanel.display(exception.getMessage());
        }

        try {
            settings.setEndNote(_endSoundPanel.getInput());
        }
        catch (UnsupportedNoteNotationException exception) {
            ifNoException = false;
            _consolePanel.display(exception.getMessage());
        }

        try {
            settings.setHighestNote(_highestSoundPanel.getInput());
        }
        catch (UnsupportedNoteNotationException exception) {
            ifNoException = false;
            _consolePanel.display(exception.getMessage());
        }

        try {
            settings.setLowestNote(_lowestSoundPanel.getInput());
        }
        catch (UnsupportedNoteNotationException exception) {
            ifNoException = false;
            _consolePanel.display(exception.getMessage());
        }

        if (!settings._startNote.isInRange(settings._lowestNote, settings._highestNote)) {
            ifNoException = false;
            _consolePanel.display("Dźwięk początkowy musi być pomiędzy najniższym i najwyższym!");
        }
        if (!settings._endNote.isInRange(settings._lowestNote, settings._highestNote)) {
            ifNoException = false;
            _consolePanel.display("Dźwięk końcowy musi być pomiędzy najniższym i najwyższym!");
        }

        boolean ifPitchExceptionHappened = false;
        for (int i = 0; i < _pitchProbabilities.length - 1; i++) {
            try {
                settings.setPitchChance(_pitchProbabilities[i].getInput(), i);
            }
            catch (UnsupportedNoteNotationException exception) {
                ifNoException = false;
                if (!ifPitchExceptionHappened) {
                    ifPitchExceptionHappened = true;
                    _consolePanel.display(exception.getMessage());
                }
            }
        }

        try {
            settings.setTempo(_tempoPanel.getInput());
        }
        catch (UnsupportedNoteNotationException exception) {
            ifNoException = false;
            _consolePanel.display(exception.getMessage());
        }

        boolean ifIntervalExceptionHappened = false;
        for (int i = 0; i < _intervalProbabilities.length; i++) {
            try {
                settings.setIntervalChance(_intervalProbabilities[i].getInput(), i);
            }
            catch (UnsupportedNoteNotationException exception) {
                ifNoException = false;
                if (!ifIntervalExceptionHappened) {
                    ifIntervalExceptionHappened = true;
                    _consolePanel.display(exception.getMessage());
                }
            }
        }

        if (!ifNoException) {
            throw new UnsupportedNoteNotationException("Nieprawidłowe ustawienia, nie można kontynuować!");
        }
        return settings;
    }

}