package music;

public class NotePitch {

    private E_Note7 _letter = E_Note7.Rest;
    private int _chromaticShift = 0;
    private int _octaveShift = 0;

    public NotePitch() {
    }

    public NotePitch(NotePitch pitch) {
        _letter = pitch._letter;
        _chromaticShift = pitch._chromaticShift;
        _octaveShift = pitch._octaveShift;
    }

    public NotePitch(NotePitch pitch, Interval interval) {
        //TODO
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

    public NotePitch(String name) throws UnsupportedNoteNotationError {
        setLetter(name);
        setOctaveShift(name, setChromaticShift(name));
    }

    private void setLetter(String name) throws  UnsupportedNoteNotationError {
        switch (name.charAt(0)) {
            case 'r': {
                this._letter = E_Note7.Rest;
                break;
            }
            case 'a': {
                this._letter = E_Note7.A;
                break;
            }
            case 'b': {
                this._letter = E_Note7.B;
                break;
            }
            case 'c': {
                this._letter = E_Note7.C;
                break;
            }
            case 'd': {
                this._letter = E_Note7.D;
                break;
            }
            case 'e': {
                this._letter = E_Note7.E;
                break;
            }
            case 'f': {
                this._letter = E_Note7.F;
                break;
            }
            case 'g': {
                this._letter = E_Note7.G;
                break;
            }
            default: {
                throw new UnsupportedNoteNotationError("Unsupported note letter");
            }
        }
    }

    private int setChromaticShift(String name) throws UnsupportedNoteNotationError {
        int actChar = 1;
        this._chromaticShift = 0;
        while (actChar+1 < name.length() && name.charAt(actChar+1) == 's') {
            if (name.charAt(actChar) == 'i') {
                this._chromaticShift++;
            }
            else if (name.charAt(actChar) == 'e') {
                this._chromaticShift--;
            }
            else {
                throw new UnsupportedNoteNotationError("Unsupported chromatic shift notation");
            }
            actChar += 2;
        }
        return actChar;
    }

    private void setOctaveShift(String name, int actChar) {
        this._octaveShift = 0;
        boolean ifEnded = false;
        while (!ifEnded) {
            if (actChar < name.length()) {
                char act = name.charAt(actChar);
                if (act == '\'') {
                    this._octaveShift++;
                } else if (act == ',') {
                    this._octaveShift--;
                }
                actChar++;
            }
            else {
                ifEnded = true;
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

    public E_Note12 getNote12() throws UnsupportedNoteNotationError {
        int note12Int;
        switch (_letter) {
            case Rest: {
                return E_Note12.Rest;
            }
            case C: {
                note12Int = E_Note12.C.ordinal();
                break;
            }
            case D: {
                note12Int = E_Note12.D.ordinal();
                break;
            }
            case E: {
                note12Int = E_Note12.E.ordinal();
                break;
            }
            case F: {
                note12Int = E_Note12.F.ordinal();
                break;
            }
            case G: {
                note12Int = E_Note12.G.ordinal();
                break;
            }
            case A: {
                note12Int = E_Note12.A.ordinal();
                break;
            }
            case B: {
                note12Int = E_Note12.B.ordinal();
                break;
            }
            default: {
                throw new UnsupportedNoteNotationError("Unsupported note letter");
            }
        }
        note12Int += _chromaticShift;
        note12Int = note12Int % (E_Note12.values().length - 1);
        return E_Note12.values()[note12Int];
    }

}