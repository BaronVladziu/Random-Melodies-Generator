package application;

import music.*;

import javax.swing.*;
import java.awt.*;

public class UIPanel extends JComponent {

    private static final int _NAME_LENGTH = 130;
    private static final int _INPUT_LENGTH = 30;
    private static final int _BAR_HEIGHT = 25;
    private static final int _GAP_LENGTH = 10;

    private JComponent _comp3 = new JPanel();
    private JComponent _comp1 = new JPanel();
    private SettingInputPanel _metrePanel = new SettingInputPanel("Metrum:", "4/4");
    private SettingInputPanel _lengthPanel = new SettingInputPanel("Liczba taktów:", "4");
    private SettingInputPanel _startSoundPanel = new SettingInputPanel("Dżwięk początkowy:", "c");
    private SettingInputPanel _endSoundPanel = new SettingInputPanel("Dżwięk końcowy:", "c");
    private SettingInputPanel _lowestSoundPanel = new SettingInputPanel("Dżwięk najniższy:", "a,,");
    private SettingInputPanel _highestSoundPanel = new SettingInputPanel("Dżwięk najwyższy:", "a''");
    private SettingInputPanel _pitchProbabilities[] = new SettingInputPanel[MusicConstants.NUMBER_OF_PITCHES_IN_OCTAVE];
    private JButton _button = new JButton("Generate");
    private JComponent _comp2 = new JPanel();
    private SettingInputPanel _intervalProbabilities[] = new SettingInputPanel[MusicConstants.NUMBER_OF_INTERVALS_IN_OCTAVE];

    public UIPanel(Window window) {
        _comp1.setPreferredSize(new Dimension(_NAME_LENGTH + _GAP_LENGTH + _INPUT_LENGTH,
                (6 + _pitchProbabilities.length) * (_BAR_HEIGHT + _GAP_LENGTH)));
        _comp1.setLayout(new GridLayout(6 + _pitchProbabilities.length, 0));
        _comp1.add(_metrePanel);
        _comp1.add(_lengthPanel);
        _comp1.add(_startSoundPanel);
        _comp1.add(_endSoundPanel);
        _comp1.add(_lowestSoundPanel);
        _comp1.add(_highestSoundPanel);
        for (int i = 0; i < _pitchProbabilities.length; i++) {
            _pitchProbabilities[i] = new SettingInputPanel("Szansa na " + new NotePitch(E_Note12.values()[i]).getString() + ":", "1");
            _comp1.add(_pitchProbabilities[i]);
        }
        _comp1.setVisible(true);

        _comp2.setPreferredSize(new Dimension(_comp1.getPreferredSize().width,
                (_intervalProbabilities.length) * (_BAR_HEIGHT + _GAP_LENGTH)));
        _comp2.setLayout(new GridLayout(_intervalProbabilities.length, 0));
        for (int i = 0; i < _intervalProbabilities.length; i++) {
            _intervalProbabilities[i] = new SettingInputPanel("Szansa na " + new Interval(E_Interval.values()[i]).getString() + ":", "1");
            _comp2.add(_intervalProbabilities[i]);
        }
        _comp2.setVisible(true);

        _button.setPreferredSize(new Dimension(_comp1.getPreferredSize().width,
                _comp2.getPreferredSize().height - _comp1.getPreferredSize().height - 2*_GAP_LENGTH));
        _button.addActionListener(window);
        setViewToDone();
        _comp3.setPreferredSize(new Dimension(_comp1.getPreferredSize().width,
                _comp2.getPreferredSize().height));
        _comp3.setLayout(new FlowLayout());
        _comp3.add(_comp1);
        _comp3.add(_button);
        _comp3.setVisible(true);

        setPreferredSize(new Dimension( _comp3.getPreferredSize().width + _GAP_LENGTH + _comp2.getPreferredSize().width,
                _comp2.getPreferredSize().height));
        setLayout(new FlowLayout());
        add(_comp3);
        add(_comp2);
        setVisible(true);
    }

    public void setViewToWorkInProgress() {
        _button.setEnabled(false);
        _button.setText("Working...");
        _button.setBackground(Color.GRAY);
    }

    public void setViewToDone() {
        _button.setBackground(Color.WHITE);
        _button.setText("Generate");
        _button.setEnabled(true);
    }

    public MelodySettings createMelodySettings() throws UnsupportedNoteNotationException {
        MelodySettings settings = new MelodySettings();
        boolean ifNoException = true;

        try {
            settings.setMetre(_metrePanel.getInput());
        }
        catch (UnsupportedNoteNotationException exception) {
            ifNoException = false;
            System.out.println(exception.getMessage());
        }

        try {
            settings.setNumberOfBars(_lengthPanel.getInput());
        }
        catch (UnsupportedNoteNotationException exception) {
            ifNoException = false;
            System.out.println(exception.getMessage());
        }

        try {
            settings.setStartNote(_startSoundPanel.getInput());
        }
        catch (UnsupportedNoteNotationException exception) {
            ifNoException = false;
            System.out.println(exception.getMessage());
        }

        try {
            settings.setEndNote(_endSoundPanel.getInput());
        }
        catch (UnsupportedNoteNotationException exception) {
            ifNoException = false;
            System.out.println(exception.getMessage());
        }

        try {
            settings.setHighestNote(_highestSoundPanel.getInput());
        }
        catch (UnsupportedNoteNotationException exception) {
            ifNoException = false;
            System.out.println(exception.getMessage());
        }

        try {
            settings.setLowestNote(_lowestSoundPanel.getInput());
        }
        catch (UnsupportedNoteNotationException exception) {
            ifNoException = false;
            System.out.println(exception.getMessage());
        }

        boolean ifPitchExceptionHappened = false;
        for (int i = 0; i < _pitchProbabilities.length; i++) {
            try {
                settings.setPitchChance(_pitchProbabilities[i].getInput(), i);
            }
            catch (UnsupportedNoteNotationException exception) {
                ifNoException = false;
                if (!ifPitchExceptionHappened) {
                    ifPitchExceptionHappened = true;
                    System.out.println(exception.getMessage());
                }
            }
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
                    System.out.println(exception.getMessage());
                }
            }
        }

        if (!ifNoException) {
            throw new UnsupportedNoteNotationException("Wrong input, cannot proceed");
        }
        return settings;
    }

}