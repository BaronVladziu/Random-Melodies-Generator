package application;

import javax.swing.*;
import java.awt.*;

public class SettingInputPanel extends JComponent {

    private JFormattedTextField _nameField = new JFormattedTextField();
    private JFormattedTextField _inputField = new JFormattedTextField();

    public SettingInputPanel(String name) {
        setLayout(new GridLayout(1, 2));
        _nameField.setEditable(false);
        _nameField.setBackground(Color.LIGHT_GRAY);
        _nameField.setText(name);
        add(_nameField);
        add(_inputField);
        setVisible(true);
    }

    public String getInput() {
        return _inputField.getText();
    }

}