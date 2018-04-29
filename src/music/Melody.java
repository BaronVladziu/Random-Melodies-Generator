package music;

import java.io.IOException;
import java.util.*;
import java.io.PrintWriter;

public class Melody {

    private MelodySettings _settings;
    private ArrayList<Note> _notes = new ArrayList<>();
    private String _outputFileName = "melody";

    public Melody() {}

    public Melody(MelodySettings settings) {
        _settings = settings;
    }

    public void setSettings(MelodySettings settings) {
        _settings = settings;
    }

    public String getOutputFileName() {
        return _outputFileName;
    }

    public void generate() {
        _notes.clear();
        generateRandomDurations();
        generateLilyPondFile();
        compileLilyPondFile();
    }

    private void generateRandomDurations() {
        int remainingMelodyLength = _settings._numberOfBars * _settings._metreTimeSignature * _settings._metreBeatValue.getInt();
        while (remainingMelodyLength > 0) {
            _notes.add(new Note(_settings._metreBeatValue));
            remainingMelodyLength -= _settings._metreBeatValue.getInt();
        }
    }

    private void generateLilyPondFile() {
        try {
            PrintWriter writer = new PrintWriter(_outputFileName + ".ly", "UTF-8");
            writer.println("\\time " + _settings.getMetre());
            writer.println("{");
            for (Note note : _notes) {
                writer.print(note.getName() + " ");
            }
            writer.println("\n}");
            writer.close();
        }
        catch (Exception exception) {
            System.out.println("Failed to create file for some reason");
        }
    }

    private void compileLilyPondFile() {
        java.lang.Runtime runtime = java.lang.Runtime.getRuntime();
        try {
            java.lang.Process process = runtime.exec("lilypond --png --pdf " + _outputFileName + ".ly");
            process.waitFor();
        }
        catch (IOException exception) {
            System.out.println("Failed to find lilypond file");
        }
        catch (InterruptedException exception) {
            System.out.println("Failed to compile lilypond file");
        }
    }

}