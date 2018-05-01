package music;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;

public class Melody {

    private MelodySettings _settings;
    private LinkedList<Note> _notes = new LinkedList<>();
    private String _outputFileName = "melody";
    private Random _random = new Random();
    private int _melodyLength;

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
        shuffleDurations();
        groupNotes(_settings._metreBeatValue.getInt(),
                _settings._metreTimeSignature * _settings._metreBeatValue.getInt());
        setRandomPitches();
        //TODO: End note
        //TODO: Generate midi
        //TODO: Add double line
        generateLilyPondFile();
        compileLilyPondFile();
    }

    private void generateRandomDurations() {
        _melodyLength = _settings._numberOfBars * _settings._metreTimeSignature * _settings._metreBeatValue.getInt();
        int remainingMelodyLength = _melodyLength;
        NoteDuration randomDuration = new NoteDuration();
        int randomDurationInt;
        while (remainingMelodyLength > 0) {
            randomDuration.setDuration(E_NoteDuration.values()[_random.nextInt(E_NoteDuration.values().length)],
                    _random.nextBoolean());
            randomDurationInt = randomDuration.getInt();
            if (randomDurationInt <= remainingMelodyLength) {
                _notes.add(new Note(randomDuration));
                remainingMelodyLength -= randomDurationInt;
            }
        }
    }

    private void shuffleDurations() {
        LinkedList<Note> shuffledNotes = new LinkedList<>();
        while (_notes.size() > 0) {
            int i = _random.nextInt(_notes.size());
            shuffledNotes.add(_notes.remove(i));
        }
        _notes = shuffledNotes;
        System.out.println("Durations:");
        printNotes();
    }

    private void printNotes() {
        for (Note note : _notes) {
            System.out.println(note.getName());
        }
    }

    private void groupNotes(int groupLength, int barLength) {
        int remainingBar = barLength;
        int remainingGroup = groupLength;
        int noteLength;
        for (Note note : _notes) {
            noteLength = note.groupNote(remainingGroup, remainingBar, groupLength);
            remainingBar -= noteLength;
            while (remainingBar <= 0) {
                remainingBar += barLength;
            }
            remainingGroup -= noteLength;
            while (remainingGroup <= 0) {
                remainingGroup += groupLength;
            }
        }
    }

    private void setRandomPitches() {
        NotePitch actPitch = _settings._startNote;
        Interval nextInterval;
        int numberOfInterval26values = E_Interval26.values().length;
        int nextIntervalChances[] = new int[numberOfInterval26values * 2];
        int sum;
        int drawnValue;
        int nextIntervalID;
        NotePitch nextPitch;
        System.out.println("Sounds & Intervals:");
        for (Note note : _notes) {
            note.setPitch(actPitch);
            System.out.print(note.getName());
            sum = 0;
            for (int i = 0; i < nextIntervalChances.length; i++) {
                nextIntervalChances[i] = _settings._intervalChances[i/2] * _settings._intervalChances[i/2];
                nextPitch = new NotePitch(actPitch, new Interval(E_Interval26.values()[i/2]), i%2 == 1);
                if (nextPitch.isChromaticShiftCorrect() && nextPitch.isInRange(_settings._lowestNote, _settings._highestNote)) {
                    nextIntervalChances[i] *= _settings._pitchChances[nextPitch.getNote12().ordinal()];
                }
                else {
                    nextIntervalChances[i] = 0;
                }
                sum += nextIntervalChances[i];
            }
            drawnValue = _random.nextInt(sum);
            nextIntervalID = -1;
            while (drawnValue > 0) {
                nextIntervalID++;
                drawnValue -= nextIntervalChances[nextIntervalID];
            }
            nextInterval = new Interval(E_Interval26.values()[nextIntervalID/2]);
            actPitch = new NotePitch(actPitch, nextInterval, nextIntervalID%2 == 1);
            System.out.println("  >--" + nextInterval.getString() + "--> ");
        }
    }

    private void generateLilyPondFile() {
        try {
            PrintWriter writer = new PrintWriter(_outputFileName + ".ly", "UTF-8");
            writer.println("\\version \"2.18.2\"");
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