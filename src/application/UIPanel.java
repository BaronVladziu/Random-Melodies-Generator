package application;

import music.E_Interval;
import music.E_Note12;
import music.MusicConstants;
import music.NotePitch;

import javax.swing.*;
import java.awt.*;

public class UIPanel extends JComponent {

    private SettingInputPanel _metrumPanel = new SettingInputPanel("Metrum:");
    private SettingInputPanel _lengthPanel = new SettingInputPanel("Liczba taktów:");
    private SettingInputPanel _startSoundPanel = new SettingInputPanel("Dżwięk początkowy:");
    private SettingInputPanel _endSoundPanel = new SettingInputPanel("Dżwięk końcowy:");
    private SettingInputPanel _lowestSoundPanel = new SettingInputPanel("Dżwięk najniższy:");
    private SettingInputPanel _highestSoundPanel = new SettingInputPanel("Dżwięk najwyższy:");

    private SettingInputPanel _pitchProbabilities[] = new SettingInputPanel[MusicConstants.NUMBER_OF_PITCHES_IN_OCTAVE];

    public UIPanel() {
        setPreferredSize(new Dimension(200, 600));
        setLayout(new GridLayout(0, 1));
        add(_metrumPanel);
        add(_lengthPanel)
        add(_startSoundPanel);
        add(_endSoundPanel);
        add(_lowestSoundPanel);
        add(_highestSoundPanel);
        for (int i = 0; i < MusicConstants.NUMBER_OF_PITCHES_IN_OCTAVE; i++) {
            _pitchProbabilities[i] = new SettingInputPanel("Szansa na " + NotePitch(i).getName());
        }


        setVisible(true);
    }

}