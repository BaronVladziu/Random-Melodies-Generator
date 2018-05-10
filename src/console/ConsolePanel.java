package console;

import javax.swing.*;
import java.awt.*;

public class ConsolePanel extends JPanel {

    private JTextArea _textArea = new JTextArea();

    public ConsolePanel() {
        setPreferredSize(new Dimension(400, 900));
        _textArea.setPreferredSize(new Dimension(380, 880));
        _textArea.setEditable(false);
        add(_textArea);
    }

    public void display(String text) {
        _textArea.append(text + "\n");
    }

    public void clear() {
        _textArea.setText("");
    }

}