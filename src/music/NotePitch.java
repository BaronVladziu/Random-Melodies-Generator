package music;

public class NotePitch {

    private E_Note7 _letter = E_Note7.Rest;
    private int _chromaticShift = 0;
    private int _octaveShift = 0;

    NotePitch() {
    }

    NotePitch(NotePitch pitch) {
        _letter = pitch._letter;
        _chromaticShift = pitch._chromaticShift;
        _octaveShift = pitch._octaveShift;
    }

    NotePitch(NotePitch pitch, Interval interval, boolean ifUp) {
        _letter = pitch._letter;
        _chromaticShift = pitch._chromaticShift;
        _octaveShift = pitch._octaveShift;
        jump(interval, ifUp);
    }

    void jump(Interval interval, boolean ifUp) {
        int letterInt = _letter.ordinal();
        int numberOfLetters = E_Note7.values().length - 1;
        if (interval.isPerfect()) {
            if (ifUp) {
                letterInt += interval.getInterval8().ordinal();
                if (letterInt >= numberOfLetters) {
                    letterInt -= numberOfLetters;
                    _octaveShift++;
                }
                _letter = E_Note7.values()[letterInt];
                switch (interval.getIntervalType4()) {
                    case Diminished: {
                        _chromaticShift--;
                        break;
                    }
                    case Augmented: {
                        _chromaticShift++;
                        break;
                    }
                }
            }
            else {
                letterInt -= interval.getInterval8().ordinal();
                if (letterInt < 0) {
                    letterInt += numberOfLetters;
                    _octaveShift--;
                }
                _letter = E_Note7.values()[letterInt];
                switch (interval.getIntervalType4()) {
                    case Diminished: {
                        _chromaticShift++;
                        break;
                    }
                    case Augmented: {
                        _chromaticShift--;
                        break;
                    }
                }
            }
        }
        else {
            E_IntervalType4 typeInKey = checkWhichIntervalTypeIsInKey(interval.getInterval8(), ifUp);
            if (ifUp) {
                letterInt += interval.getInterval8().ordinal();
                if (letterInt >= numberOfLetters) {
                    letterInt -= numberOfLetters;
                    _octaveShift++;
                }
                _letter = E_Note7.values()[letterInt];
                _chromaticShift += interval.getIntervalType4().ordinal() - typeInKey.ordinal();
            }
            else {
                letterInt -= interval.getInterval8().ordinal();
                if (letterInt < 0) {
                    letterInt += numberOfLetters;
                    _octaveShift--;
                }
                _letter = E_Note7.values()[letterInt];
                _chromaticShift -= interval.getIntervalType4().ordinal() - typeInKey.ordinal();
            }
        }
    }

    private E_IntervalType4 checkWhichIntervalTypeIsInKey(E_Interval8 interval, boolean ifUp) throws UnsupportedNoteNotationError {
        switch (_letter) {
            case C: {
                switch (interval) {
                    case Second: {
                        if (ifUp) {
                            return E_IntervalType4.Major;
                        } else {
                            return E_IntervalType4.Minor;
                        }
                    }
                    case Third: {
                        if (ifUp) {
                            return E_IntervalType4.Major;
                        } else {
                            return E_IntervalType4.Minor;
                        }
                    }
                    case Sixth: {
                        if (ifUp) {
                            return E_IntervalType4.Major;
                        } else {
                            return E_IntervalType4.Minor;
                        }
                    }
                    case Seventh: {
                        if (ifUp) {
                            return E_IntervalType4.Major;
                        } else {
                            return E_IntervalType4.Minor;
                        }
                    }
                    default: {
                        throw new UnsupportedNoteNotationError("Only imperfect intervals allowed");
                    }
                }
            }
            case D: {
                switch (interval) {
                    case Second: {
                        if (ifUp) {
                            return E_IntervalType4.Major;
                        } else {
                            return E_IntervalType4.Major;
                        }
                    }
                    case Third: {
                        if (ifUp) {
                            return E_IntervalType4.Minor;
                        } else {
                            return E_IntervalType4.Minor;
                        }
                    }
                    case Sixth: {
                        if (ifUp) {
                            return E_IntervalType4.Major;
                        } else {
                            return E_IntervalType4.Major;
                        }
                    }
                    case Seventh: {
                        if (ifUp) {
                            return E_IntervalType4.Minor;
                        } else {
                            return E_IntervalType4.Minor;
                        }
                    }
                    default: {
                        throw new UnsupportedNoteNotationError("Only imperfect intervals allowed");
                    }
                }
            }
            case E: {
                switch (interval) {
                    case Second: {
                        if (ifUp) {
                            return E_IntervalType4.Minor;
                        } else {
                            return E_IntervalType4.Major;
                        }
                    }
                    case Third: {
                        if (ifUp) {
                            return E_IntervalType4.Minor;
                        } else {
                            return E_IntervalType4.Major;
                        }
                    }
                    case Sixth: {
                        if (ifUp) {
                            return E_IntervalType4.Minor;
                        } else {
                            return E_IntervalType4.Major;
                        }
                    }
                    case Seventh: {
                        if (ifUp) {
                            return E_IntervalType4.Minor;
                        } else {
                            return E_IntervalType4.Minor;
                        }
                    }
                    default: {
                        throw new UnsupportedNoteNotationError("Only imperfect intervals allowed");
                    }
                }
            }
            case F: {
                switch (interval) {
                    case Second: {
                        if (ifUp) {
                            return E_IntervalType4.Major;
                        } else {
                            return E_IntervalType4.Minor;
                        }
                    }
                    case Third: {
                        if (ifUp) {
                            return E_IntervalType4.Major;
                        } else {
                            return E_IntervalType4.Minor;
                        }
                    }
                    case Sixth: {
                        if (ifUp) {
                            return E_IntervalType4.Major;
                        } else {
                            return E_IntervalType4.Minor;
                        }
                    }
                    case Seventh: {
                        if (ifUp) {
                            return E_IntervalType4.Major;
                        } else {
                            return E_IntervalType4.Minor;
                        }
                    }
                    default: {
                        throw new UnsupportedNoteNotationError("Only imperfect intervals allowed");
                    }
                }
            }
            case G: {
                switch (interval) {
                    case Second: {
                        if (ifUp) {
                            return E_IntervalType4.Major;
                        } else {
                            return E_IntervalType4.Major;
                        }
                    }
                    case Third: {
                        if (ifUp) {
                            return E_IntervalType4.Major;
                        } else {
                            return E_IntervalType4.Minor;
                        }
                    }
                    case Sixth: {
                        if (ifUp) {
                            return E_IntervalType4.Major;
                        } else {
                            return E_IntervalType4.Minor;
                        }
                    }
                    case Seventh: {
                        if (ifUp) {
                            return E_IntervalType4.Minor;
                        } else {
                            return E_IntervalType4.Minor;
                        }
                    }
                    default: {
                        throw new UnsupportedNoteNotationError("Only imperfect intervals allowed");
                    }
                }
            }
            case A: {
                switch (interval) {
                    case Second: {
                        if (ifUp) {
                            return E_IntervalType4.Major;
                        } else {
                            return E_IntervalType4.Major;
                        }
                    }
                    case Third: {
                        if (ifUp) {
                            return E_IntervalType4.Minor;
                        } else {
                            return E_IntervalType4.Major;
                        }
                    }
                    case Sixth: {
                        if (ifUp) {
                            return E_IntervalType4.Minor;
                        } else {
                            return E_IntervalType4.Major;
                        }
                    }
                    case Seventh: {
                        if (ifUp) {
                            return E_IntervalType4.Minor;
                        } else {
                            return E_IntervalType4.Minor;
                        }
                    }
                    default: {
                        throw new UnsupportedNoteNotationError("Only imperfect intervals allowed");
                    }
                }
            }
            case B: {
                switch (interval) {
                    case Second: {
                        if (ifUp) {
                            return E_IntervalType4.Minor;
                        } else {
                            return E_IntervalType4.Major;
                        }
                    }
                    case Third: {
                        if (ifUp) {
                            return E_IntervalType4.Minor;
                        } else {
                            return E_IntervalType4.Major;
                        }
                    }
                    case Sixth: {
                        if (ifUp) {
                            return E_IntervalType4.Minor;
                        } else {
                            return E_IntervalType4.Major;
                        }
                    }
                    case Seventh: {
                        if (ifUp) {
                            return E_IntervalType4.Minor;
                        } else {
                            return E_IntervalType4.Major;
                        }
                    }
                    default: {
                        throw new UnsupportedNoteNotationError("Only imperfect intervals allowed");
                    }
                }
            }
            default: {
                throw new UnsupportedNoteNotationError("Unsupported note pitch notation");
            }
        }
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

    NotePitch(String name) throws UnsupportedNoteNotationError {
        setLetter(name);
        setOctaveShift(name, setChromaticShift(name));
    }

    boolean isChromaticShiftCorrect() {
        return _chromaticShift >= -2 && _chromaticShift <= 2;
    }

    public boolean isInRange(NotePitch bottom, NotePitch up) {
        return bottom.getMidi() <= this.getMidi() &&
                up.getMidi() >= this.getMidi();
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
        StringBuilder string = new StringBuilder();
        if (_chromaticShift >= 0) {
            for (int i = _chromaticShift; i > 0; i--) {
                string.append("is");
            }
        }
        else {
            for (int i = _chromaticShift; i < 0; i++) {
                string.append("es");
            }
        }
        return string.toString();
    }

    private String getOctaveString() {
        StringBuilder string = new StringBuilder();
        if (_octaveShift >= 0) {
            for (int i = _octaveShift; i > 0; i--) {
                string.append("'");
            }
        }
        else {
            for (int i = _octaveShift; i < 0; i++) {
                string.append(",");
            }
        }
        return string.toString();
    }

    E_Note12 getNote12() throws UnsupportedNoteNotationError {
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
        if (note12Int < 0) {
            note12Int += E_Note12.values().length - 1;
        }
        else {
            note12Int = note12Int % (E_Note12.values().length - 1);
        }
        return E_Note12.values()[note12Int];
    }

    private int getMidi() throws UnsupportedNoteNotationError {
        int midi;
        switch (_letter) {
            case Rest: {
                throw new UnsupportedNoteNotationError("Cannot convert rest into MIDI value");
            }
            case C: {
                midi = 48;
                break;
            }
            case D: {
                midi = 50;
                break;
            }
            case E: {
                midi = 52;
                break;
            }
            case F: {
                midi = 53;
                break;
            }
            case G: {
                midi = 55;
                break;
            }
            case A: {
                midi = 57;
                break;
            }
            case B: {
                midi = 59;
                break;
            }
            default: {
                throw new UnsupportedNoteNotationError("Unsupported note letter");
            }
        }
        midi += _chromaticShift;
        midi += 12 * _octaveShift;
        return midi;
    }

}