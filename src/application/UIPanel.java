package application;

import music.*;

import javax.swing.*;
import java.awt.*;

public class UIPanel extends JComponent {

    private static final int _NAME_LENGTH = 130;
    private static final int _INPUT_LENGTH = 30;
    private static final int _BAR_HEIGHT = 25;
    private static final int _GAP_LENGTH = 10;

    private JComponent _comp1 = new JPanel();
    private SettingInputPanel _metrumPanel = new SettingInputPanel("Metrum:", "4/4");
    private SettingInputPanel _lengthPanel = new SettingInputPanel("Liczba taktów:", "4");
    private SettingInputPanel _startSoundPanel = new SettingInputPanel("Dżwięk początkowy:", "c");
    private SettingInputPanel _endSoundPanel = new SettingInputPanel("Dżwięk końcowy:", "c");
    private SettingInputPanel _lowestSoundPanel = new SettingInputPanel("Dżwięk najniższy:", "a,,");
    private SettingInputPanel _highestSoundPanel = new SettingInputPanel("Dżwięk najwyższy:", "a''");
    private SettingInputPanel _pitchProbabilities[] = new SettingInputPanel[MusicConstants.NUMBER_OF_PITCHES_IN_OCTAVE];
    private JComponent _comp2 = new JPanel();
    private SettingInputPanel _intervalProbabilities[] = new SettingInputPanel[MusicConstants.NUMBER_OF_INTERVALS_IN_OCTAVE];

    public UIPanel() {
        _comp1.setPreferredSize(new Dimension(_NAME_LENGTH + _GAP_LENGTH + _INPUT_LENGTH,
                (6 + _pitchProbabilities.length) * (_BAR_HEIGHT + _GAP_LENGTH)));
        _comp1.setLayout(new GridLayout(6 + _pitchProbabilities.length, 0));
        _comp1.add(_metrumPanel);
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

        _comp2.setPreferredSize(new Dimension(_NAME_LENGTH + _GAP_LENGTH + _INPUT_LENGTH,
                (_intervalProbabilities.length) * (_BAR_HEIGHT + _GAP_LENGTH)));
        _comp2.setLayout(new GridLayout(_intervalProbabilities.length, 0));
        for (int i = 0; i < _intervalProbabilities.length; i++) {
            _intervalProbabilities[i] = new SettingInputPanel("Szansa na " + new Interval(E_Interval.values()[i]).getString() + ":", "1");
            _comp2.add(_intervalProbabilities[i]);
        }
        _comp2.setVisible(true);

        setPreferredSize(new Dimension(2 * (_NAME_LENGTH + _GAP_LENGTH + _INPUT_LENGTH) + _GAP_LENGTH,
                (_intervalProbabilities.length) * (_BAR_HEIGHT + _GAP_LENGTH)));
        setLayout(new FlowLayout());
        add(_comp1);
        add(_comp2);
        setVisible(true);
    }

}