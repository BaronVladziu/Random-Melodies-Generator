package music;

public class NotePitch {

    private E_Note7 _letter = E_Note7.Rest;
    private int _chromaticShift = 0;
    private int _octaveShift = 0;

    public NotePitch() {
    }

    public NotePitch(E_Note7 letter, int chromaticShift, int octaveShift) {
        this._letter = letter;
        this._chromaticShift = chromaticShift;
        this._octaveShift = octaveShift;
    }

    public NotePitch(E_Note12 letter) throws UnsupportedNoteNotationError {
        switch (letter) {
            case Rest: {
                this._letter = E_Note7.Rest;
                break;
            }
            case A: {
                this._letter = E_Note7.A;
                break;
            }
            case B: {
                this._letter = E_Note7.B;
                break;
            }
            case C: {
                this._letter = E_Note7.C;
                break;
            }
            case D: {
                this._letter = E_Note7.D;
                break;
            }
            case E: {
                this._letter = E_Note7.E;
                break;
            }
            case F: {
                this._letter = E_Note7.F;
                break;
            }
            case G: {
                this._letter = E_Note7.G;
                break;
            }
            case Ais: {
                this._letter = E_Note7.A;
                this._chromaticShift = 1;
                break;
            }
            case Cis: {
                this._letter = E_Note7.C;
                this._chromaticShift = 1;
                break;
            }
            case Dis: {
                this._letter = E_Note7.D;
                this._chromaticShift = 1;
                break;
            }
            case Fis: {
                this._letter = E_Note7.F;
                this._chromaticShift = 1;
                break;
            }
            case Gis: {
                this._letter = E_Note7.G;
                this._chromaticShift = 1;
                break;
            }
            default: {
                throw new UnsupportedNoteNotationError("Unsupported note letter");
            }
        }
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
        String string = "";
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
        String string = "";
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