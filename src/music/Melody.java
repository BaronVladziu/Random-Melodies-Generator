package music;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class Melody {

    private MelodySettings _settings;
    private LinkedList<Note> _notes = new LinkedList<>();
    private final String _outputFileName = "melody";
    private final Random _random = new Random();

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

    public void generate() throws UnsupportedNoteNotationException {
        _notes.clear();
        generateRandomDurations();
        shuffleDurations();
        groupNotes(_settings._metreBeatValue.getInt(),
                _settings._metreTimeSignature * _settings._metreBeatValue.getInt());
        //TODO: Correct grouping when metre != 4/4
        //TODO: Correct UI
        setRandomPitches();
        generateLilyPondFile();
        compileLilyPondFile();
    }

    private void generateRandomDurations() {
        int remainingMelodyLength = _settings._numberOfBars * _settings._metreTimeSignature * _settings._metreBeatValue.getInt();
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
//        System.out.println("Durations:");
//        printNotes();
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

    private void setRandomPitches() throws UnsupportedNoteNotationException {
        NotePitch actPitch = new NotePitch(_settings._startNote);
        Interval nextInterval;
        int numberOfNotes = _notes.size();
        NotePitch upBorder = new NotePitch(_settings._endNote);
        NotePitch bottomBorder = new NotePitch(_settings._endNote);
        int maxIntervalID = _settings._intervalChances.length - 1;
        while (_settings._intervalChances[maxIntervalID] == 0) {
            maxIntervalID--;
        }
        for (int i = 2; i < numberOfNotes; i++) {
            upBorder.jump(new Interval(E_Interval26.values()[maxIntervalID]), true);
            bottomBorder.jump(new Interval(E_Interval26.values()[maxIntervalID]), false);
        }
        int numberOfInterval26values = E_Interval26.values().length;
        int nextIntervalChances[] = new int[numberOfInterval26values * 2];
        int sum;
        int drawnValue;
        int nextIntervalID;
        NotePitch nextPitch;
        Note note;
        System.out.println("Sounds & Intervals:");
        ListIterator<Note> it = _notes.listIterator();
        it.next().setPitch(actPitch);
        while (it.hasNext()) {
            sum = 0;
            for (int i = 0; i < nextIntervalChances.length; i++) {
                nextIntervalChances[i] = _settings._intervalChances[i/2] * _settings._intervalChances[i/2];
                nextPitch = new NotePitch(actPitch, new Interval(E_Interval26.values()[i/2]), i%2 == 1);
                if (nextPitch.isChromaticShiftCorrect() &&
                        nextPitch.isInRange(_settings._lowestNote, _settings._highestNote) &&
                        nextPitch.isInRange(bottomBorder, upBorder)) {
                    nextIntervalChances[i] *= _settings._pitchChances[nextPitch.getNote12().ordinal()];
                }
                else {
                    nextIntervalChances[i] = 0;
                }
                sum += nextIntervalChances[i];
            }
            if (sum <= 0) {
                throw new UnsupportedNoteNotationException("Could not generate whole melody. Please check your settings.");
            }
            drawnValue = _random.nextInt(sum) + 1;
            nextIntervalID = -1;
            while (drawnValue > 0) {
                nextIntervalID++;
                drawnValue -= nextIntervalChances[nextIntervalID];
            }
            nextInterval = new Interval(E_Interval26.values()[nextIntervalID/2]);
            actPitch = new NotePitch(actPitch, nextInterval, nextIntervalID%2 == 1);
            note = it.next();
            note.setPitch(actPitch);
            System.out.println(">--" + nextInterval.getString() + "--> \t" + note.getName());
            bottomBorder.jump(new Interval(E_Interval26.values()[maxIntervalID]), true);
            upBorder.jump(new Interval(E_Interval26.values()[maxIntervalID]), false);
        }
        System.out.print("\n");
    }

    private void generateLilyPondFile() {
        try {
            PrintWriter writer = new PrintWriter(_outputFileName + ".ly", "UTF-8");
            writer.println("\\version \"2.18.2\"");
            writer.println("\\score {");
            writer.println("\t{ \\time " + _settings.getMetre());
            writer.print("\t\t");
            for (Note note : _notes) {
                writer.print(note.getName() + " ");
            }
            writer.println("\\bar \"|.\"\n\t}");
            writer.println("\t\\layout { }");
            writer.println("\t\\midi { \\tempo 4 = " + _settings._tempo + " }");
            writer.println("}");
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