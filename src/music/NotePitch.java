package music;

public class NotePitch {

    private E_Note7 _letter;
    private int _chromaticShift;
    private int _octaveShift;

    public NotePitch() {
        this._letter = E_Note7.Rest;
        this._chromaticShift = 0;
        this._octaveShift = 0;
    }

    public NotePitch(E_Note7 letter, int chromaticShift, int octaveShift) {
        this._letter = letter;
        this._chromaticShift = chromaticShift;
        this._octaveShift = octaveShift;
    }

    public String getString() {
        return getLetterString() + getChromaticString() + getOctaveString();
    }

    private String getLetterString() throws UnsupportedNoteNotationError {
        switch (_letter) {
            case Rest: {
                return "r";
            }
            case A: {
                return "a";
            }
            case B: {
                return "b";
            }
            case C: {
                return "c";
            }
            case D: {
                return "d";
            }
            case E: {
                return "e";
            }
            case F: {
                return "f";
            }
            case G: {
                return "g";
            }
            default: {
                throw new UnsupportedNoteNotationError("Unsupported note letter");
            }
        }
    }

    private String getChromaticString() {
        String string = getLetterString();
        if (_chromaticShift >= 0) {
            for (int i = _chromaticShift; i > 0; i--) {
                string += "is";
            }
        }
        else {
            for (int i = _chromaticShift; i < 0; i++) {
                string += "es";
            }
        }
        return string;
    }

    private String getOctaveString() {
        String string = getLetterString();
        if (_octaveShift >= 0) {
            for (int i = _octaveShift; i > 0; i--) {
                string += "'";
            }
        }
        else {
            for (int i = _octaveShift; i < 0; i++) {
                string += ",";
            }
        }
        return string;
    }

}