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
        Sequencer sequencer;
        try {
            sequencer = getSequencerFromMidiFile();
            sequencer.open();
            sequencer.start();
            while(true) {
                if(sequencer.isRunning()) {
                    try {
                        Thread.sleep(1000);
                    } catch(InterruptedException ignore) {
                        break;
                    }
                } else {
                    break;
                }
            }
            sequencer.stop();
            sequencer.close();
        } catch(InvalidMidiDataException exception) {
            System.out.println("Invalid Midi data");
        } catch(IOException exception) {
            System.out.println("Failed to read midi file");
        } catch (MidiUnavailableException exception) {
            System.out.println("Midi interface unavailable");
        }
    }

    private Sequencer getSequencerFromMidiFile() throws MidiUnavailableException, InvalidMidiDataException, IOException {
        File midiFile;
        Sequencer sequencer = MidiSystem.getSequencer();
        try {
            midiFile = getMidiFile();
            sequencer.setSequence(MidiSystem.getSequence(midiFile));
        } catch(IOException exception) {
            midiFile = getMidFile();
            sequencer.setSequence(MidiSystem.getSequence(midiFile));
        }
        return sequencer;
    }

    private File getMidFile() {
        return new File(_fileName + ".mid");
    }

    private File getMidiFile() {
        return new File(_fileName + ".midi");
    }

}