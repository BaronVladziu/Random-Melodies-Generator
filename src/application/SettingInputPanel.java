package application;

import javax.swing.*;
import java.awt.*;

public class SettingInputPanel extends JComponent {

    private JFormattedTextField _nameField = new JFormattedTextField();
    private JFormattedTextField _inputField = new JFormattedTextField();

    public SettingInputPanel(String name, String defaultValue) {
        setLayout(new FlowLayout());
        _nameField.setPreferredSize(new Dimension(130, 25));
        _nameField.setEditable(false);
        _nameField.setBackground(Color.LIGHT_GRAY);
        _nameField.setText(name);
        add(_nameField);
        _inputField.setPreferredSize(new Dimension(30, 25));
        _inputField.setText(defaultValue);
        add(_inputField);
        setVisible(true);
    }

    public String getInput() {
        return _inputField.getText();
    }

}