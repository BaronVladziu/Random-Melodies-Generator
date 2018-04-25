package application;

import javax.swing.*;
import java.awt.*;

public class UIPanel extends JComponent {

    private JFormattedTextField _metrumField = new JFormattedTextField();
    private JButton _generateButton = new JButton("Generate");

    public UIPanel() {
        setPreferredSize(new Dimension(200, 600));
        setLayout(new GridLayout(0, 1));
        _metrumField.setEditable(false);
        _metrumField.setBackground(Color.LIGHT_GRAY);
        _metrumField.setText("Metrum: ");
        add(_metrumField);
        add(_generateButton);
        setVisible(true);
    }

}