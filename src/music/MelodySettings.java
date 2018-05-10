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
            throw new UnsupportedNoteNotationException("Nie udało się odczytać metrum!");
        }
        try {
            _metreTimeSignature = Integer.parseInt(numbers[0]);
            if (_metreTimeSignature <= 0) {
                throw new UnsupportedNoteNotationException("Górna liczba w metrum musi być większa od 0!");
            }
            beatValue = Integer.parseInt(numbers[1]);
        }
        catch (Exception exception) {
            throw new UnsupportedNoteNotationException("Nie udało się odczytać metrum!");
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
                throw new UnsupportedNoteNotationException("Nie udało się odczytać metrum!");
            }
        }
        if (beatValue != 4) {
            throw new UnsupportedNoteNotationException("Wartości podstawy metrum inne niż 4 nie są obsługiwane!");
        }
    }

    String getMetre() {
        return Integer.toString(_metreTimeSignature) + "/" + _metreBeatValue.getString();
    }

    public void setNumberOfBars(String numberOfBars) throws UnsupportedNoteNotationException {
        try {
            _numberOfBars = Integer.parseInt(numberOfBars);
            if (_numberOfBars <= 0) {
                throw new UnsupportedNoteNotationException("Liczba taktów musi być większa od 0");
            }
        }
        catch (Exception exception) {
            throw new UnsupportedNoteNotationException("Nie udało się odczytać liczby taktów!");
        }
    }

    public void setStartNote(String startNote) throws UnsupportedNoteNotationException {
        try {
            _startNote = new NotePitch(startNote);
        }
        catch (UnsupportedNoteNotationError error) {
            throw new UnsupportedNoteNotationException("Nie udało się odczytać dźwięku początkowego!");
        }
    }

    public void setEndNote(String endNote) throws UnsupportedNoteNotationException {
        try {
            _endNote = new NotePitch(endNote);
        }
        catch (UnsupportedNoteNotationError error) {
            throw new UnsupportedNoteNotationException("Nie udało się odczytać dźwięku końcowego!");
        }
    }

    public void setHighestNote(String highestNote) throws UnsupportedNoteNotationException {
        try {
            _highestNote = new NotePitch(highestNote);
        }
        catch (UnsupportedNoteNotationError error) {
            throw new UnsupportedNoteNotationException("Nie udało się odczytać dźwięku najwyższego!");
        }
    }

    public void setLowestNote(String lowestNote) throws UnsupportedNoteNotationException {
        try {
            _lowestNote = new NotePitch(lowestNote);
        }
        catch (UnsupportedNoteNotationError error) {
            throw new UnsupportedNoteNotationException("Nie udało się odczytać dźwięku najniższego!");
        }
    }

    public void setIntervalChance(String intervalChance, int index) throws UnsupportedNoteNotationException {
        try {
            _intervalChances[index] = Integer.parseInt(intervalChance);
            if (_intervalChances[index] < 0) {
                throw new UnsupportedNoteNotationException("Szansa żadnego interwału nie może być mniejsza od 0!");
            }
        } catch (Exception exception) {
            throw new UnsupportedNoteNotationException("Nie udało się odczytać szans wszystkich interwałów!");
        }
    }

    public void setPitchChance(String pitchChance, int index) throws UnsupportedNoteNotationException {
        try {
            _pitchChances[index] = Integer.parseInt(pitchChance);
            if (_pitchChances[index] < 0) {
                throw new UnsupportedNoteNotationException("Szansa żadnego dźwięku nie może być mniejsza od 0!");
            }
        } catch (Exception exception) {
            throw new UnsupportedNoteNotationException("Nie udało się odczytać szans wszystkich dźwięków!");
        }
    }

    public void setTempo(String tempo) throws UnsupportedNoteNotationException {
        try {
            _tempo = Integer.parseInt(tempo);
            if (_tempo <= 0) {
                throw new UnsupportedNoteNotationException("Tempo musi być większe od 0!");
            }
        }
        catch (Exception exception) {
            throw new UnsupportedNoteNotationException("Nie udało się odczytać tempa!");
        }
    }

}