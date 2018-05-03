package music;

public class MelodySettings {

    int _metreTimeSignature;
    NoteDuration _metreBeatValue;
    int _numberOfBars;
    public NotePitch _startNote;
    public NotePitch _endNote;
    public NotePitch _highestNote;
    public NotePitch _lowestNote;
    final int[] _intervalChances = new int[E_Interval26.values().length];
    final int[] _pitchChances = new int[E_Note12.values().length];
    int _tempo;

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
            if (_metreTimeSignature <= 0) {
                throw new UnsupportedNoteNotationException("Time signature must be greater than 0");
            }
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

    String getMetre() {
        return Integer.toString(_metreTimeSignature) + "/" + _metreBeatValue.getString();
    }

    public void setNumberOfBars(String numberOfBars) throws UnsupportedNoteNotationException {
        try {
            _numberOfBars = Integer.parseInt(numberOfBars);
            if (_numberOfBars <= 0) {
                throw new UnsupportedNoteNotationException("Number of bars must be greater than 0");
            }
        }
        catch (Exception exception) {
            throw new UnsupportedNoteNotationException("Number of bars must be an integer");
        }
    }

    public void setStartNote(String startNote) throws UnsupportedNoteNotationException {
        try {
            _startNote = new NotePitch(startNote);
        }
        catch (UnsupportedNoteNotationError error) {
            throw new UnsupportedNoteNotationException("Unsupprted start note notation: " + error.getMessage());
        }
    }

    public void setEndNote(String endNote) throws UnsupportedNoteNotationException {
        try {
            _endNote = new NotePitch(endNote);
        }
        catch (UnsupportedNoteNotationError error) {
            throw new UnsupportedNoteNotationException("Unsupprted end note notation: " + error.getMessage());
        }
    }

    public void setHighestNote(String highestNote) throws UnsupportedNoteNotationException {
        try {
            _highestNote = new NotePitch(highestNote);
        }
        catch (UnsupportedNoteNotationError error) {
            throw new UnsupportedNoteNotationException("Unsupprted highest note notation: " + error.getMessage());
        }
    }

    public void setLowestNote(String lowestNote) throws UnsupportedNoteNotationException {
        try {
            _lowestNote = new NotePitch(lowestNote);
        }
        catch (UnsupportedNoteNotationError error) {
            throw new UnsupportedNoteNotationException("Unsupprted lowest note notation: " + error.getMessage());
        }
    }

    public void setIntervalChance(String intervalChance, int index) throws UnsupportedNoteNotationException {
        try {
            _intervalChances[index] = Integer.parseInt(intervalChance);
            if (_intervalChances[index] < 0) {
                throw new UnsupportedNoteNotationException("All interval chances must be greater or equal to 0");
            }
        } catch (Exception exception) {
            throw new UnsupportedNoteNotationException("All interval chances must be integers");
        }
    }

    public void setPitchChance(String pitchChance, int index) throws UnsupportedNoteNotationException {
        try {
            _pitchChances[index] = Integer.parseInt(pitchChance);
            if (_pitchChances[index] < 0) {
                throw new UnsupportedNoteNotationException("All pitch chances must be greater or equal to 0");
            }
        } catch (Exception exception) {
            throw new UnsupportedNoteNotationException("All pitch chances must be integers");
        }
    }

    public void setTempo(String tempo) throws UnsupportedNoteNotationException {
        try {
            _tempo = Integer.parseInt(tempo);
            if (_tempo <= 0) {
                throw new UnsupportedNoteNotationException("Tempo must be greater than 0");
            }
        }
        catch (Exception exception) {
            throw new UnsupportedNoteNotationException("Tempo must be an integer");
        }
    }

}