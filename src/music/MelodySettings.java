package music;

public class MelodySettings {

    public int _metreTimeSignature;
    public NoteDuration _metreBeatValue;
    public int _numberOfBars;
    public NotePitch _startNote;
    public NotePitch _endNote;
    public NotePitch _highestNote;
    public NotePitch _lowestNote;
    public int[] _intervalChances = new int[MusicConstants.NUMBER_OF_INTERVALS_IN_OCTAVE];
    public int[] _pitchChances = new int[MusicConstants.NUMBER_OF_PITCHES_IN_OCTAVE];

    public MelodySettings() {
    }

    public void setMetre(String metre) throws UnsupportedNoteNotationException {
        String[] numbers = metre.split("/");
        int beatValue;
        if (numbers.length != 2) {
            throw new UnsupportedNoteNotationException("Metre notation not understood");
        }
        try {
            _metreTimeSignature = Integer.parseInt(numbers[0]);
            beatValue = Integer.parseInt(numbers[1]);
        }
        catch (Exception exception) {
            throw new UnsupportedNoteNotationException("Metre notation not understood");
        }
        switch (beatValue) {
            case 1: {
                _metreBeatValue = new NoteDuration(E_NoteDuration.Note1, false);
                break;
            }
            case 2: {
                _metreBeatValue = new NoteDuration(E_NoteDuration.Note2, false);
                break;
            }
            case 4: {
                _metreBeatValue = new NoteDuration(E_NoteDuration.Note4, false);
                break;
            }
            case 8: {
                _metreBeatValue = new NoteDuration(E_NoteDuration.Note8, false);
                break;
            }
            case 16: {
                _metreBeatValue = new NoteDuration(E_NoteDuration.Note16, false);
                break;
            }
            default: {
                throw new UnsupportedNoteNotationException("Metre notation not understood");
            }
        }
    }

    public void setNumberOfBars(String numberOfBars) throws UnsupportedNoteNotationException {
        try {
            _numberOfBars = Integer.parseInt(numberOfBars);
        }
        catch (Exception exception) {
            throw new UnsupportedNoteNotationException("Number of bars must be an integer");
        }
    }

    public void setStartNote(String startNote) throws UnsupportedNoteNotationException {
        //TODO
    }

}