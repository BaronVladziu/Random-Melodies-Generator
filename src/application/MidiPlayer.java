package application;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MidiPlayer implements ActionListener {

    String _fileName;

    public MidiPlayer(String fileName) {
        _fileName = fileName;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        playMidiFile();
    }

    public void playMidiFile() {
        try {
            File _midiFile = new File(_fileName + ".midi");
            Sequencer _sequencer = MidiSystem.getSequencer();
            _sequencer.setSequence(MidiSystem.getSequence(_midiFile));
            _sequencer.open();
            _sequencer.start();
            while(true) {
                if(_sequencer.isRunning()) {
                    try {
                        Thread.sleep(1000);
                    } catch(InterruptedException ignore) {
                        break;
                    }
                } else {
                    break;
                }
            }
            _sequencer.stop();
            _sequencer.close();
        } catch(InvalidMidiDataException exception) {
            System.out.println("Invalid Midi data");
        } catch(IOException exception) {
            System.out.println("Failed to read midi file");
        } catch (MidiUnavailableException exception) {
            System.out.println("Midi interface unavailable");
        }
    }

}