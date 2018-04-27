package application;

import javax.swing.*;
import java.awt.*;

public class UIPanel extends JComponent {

    private SettingInputPanel _metrumPanel = new SettingInputPanel("Metrum:");
    private SettingInputPanel _startSoundPanel = new SettingInputPanel("Dżwięk początkowy:");
    private SettingInputPanel _endSoundPanel = new SettingInputPanel("Dżwięk końcowy:");
    private SettingInputPanel _lowestSoundPanel = new SettingInputPanel("Dżwięk najniższy:");
    private SettingInputPanel _highestSoundPanel = new SettingInputPanel("Dżwięk najwyższy:");


    public UIPanel() {
        setPreferredSize(new Dimension(200, 600));
        setLayout(new GridLayout(0, 1));
        add(_metrumPanel);
        add(_startSoundPanel);
        add(_endSoundPanel);
        add(_lowestSoundPanel);
        add(_highestSoundPanel);
        setVisible(true);
    }

}