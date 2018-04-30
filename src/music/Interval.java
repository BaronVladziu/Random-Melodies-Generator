package music;

public class Interval {

    private E_Interval8 _interval;
    private E_IntervalType4 _intervalType4;
    private boolean _ifPerfect;

    public Interval(E_Interval8 interval, E_IntervalType4 intervalType, boolean ifPerfect) {
        _interval = interval;
        _intervalType4 = intervalType;
        _ifPerfect = ifPerfect;
    }

    public Interval(E_Interval26 interval) throws UnsupportedNoteNotationError {
        switch (interval) {
            case A1: {
                _interval = E_Interval8.Unison;
                _intervalType4 = E_IntervalType4.Augmented;
                _ifPerfect = true;
                break;
            }
            case A2: {
                _interval = E_Interval8.Second;
                _intervalType4 = E_IntervalType4.Augmented;
                _ifPerfect = false;
                break;
            }
            case A3: {
                _interval = E_Interval8.Third;
                _intervalType4 = E_IntervalType4.Augmented;
                _ifPerfect = false;
                break;
            }
            case A4: {
                _interval = E_Interval8.Fourth;
                _intervalType4 = E_IntervalType4.Augmented;
                _ifPerfect = true;
                break;
            }
            case A5: {
                _interval = E_Interval8.Fifth;
                _intervalType4 = E_IntervalType4.Augmented;
                _ifPerfect = true;
                break;
            }
            case A6: {
                _interval = E_Interval8.Sixth;
                _intervalType4 = E_IntervalType4.Augmented;
                _ifPerfect = false;
                break;
            }
            case A7: {
                _interval = E_Interval8.Seventh;
                _intervalType4 = E_IntervalType4.Augmented;
                _ifPerfect = false;
                break;
            }
            case d2: {
                _interval = E_Interval8.Second;
                _intervalType4 = E_IntervalType4.Diminished;
                _ifPerfect = false;
                break;
            }
            case d3: {
                _interval = E_Interval8.Third;
                _intervalType4 = E_IntervalType4.Diminished;
                _ifPerfect = false;
                break;
            }
            case d4: {
                _interval = E_Interval8.Fourth;
                _intervalType4 = E_IntervalType4.Diminished;
                _ifPerfect = true;
                break;
            }
            case d5: {
                _interval = E_Interval8.Fifth;
                _intervalType4 = E_IntervalType4.Diminished;
                _ifPerfect = true;
                break;
            }
            case d6: {
                _interval = E_Interval8.Sixth;
                _intervalType4 = E_IntervalType4.Diminished;
                _ifPerfect = false;
                break;
            }
            case d7: {
                _interval = E_Interval8.Seventh;
                _intervalType4 = E_IntervalType4.Diminished;
                _ifPerfect = false;
                break;
            }
            case d8: {
                _interval = E_Interval8.Octave;
                _intervalType4 = E_IntervalType4.Diminished;
                _ifPerfect = true;
                break;
            }
            case P1: {
                _interval = E_Interval8.Unison;
                _intervalType4 = E_IntervalType4.Major;
                _ifPerfect = true;
                break;
            }
            case m2: {
                _interval = E_Interval8.Second;
                _intervalType4 = E_IntervalType4.Minor;
                _ifPerfect = false;
                break;
            }
            case M2: {
                _interval = E_Interval8.Second;
                _intervalType4 = E_IntervalType4.Major;
                _ifPerfect = false;
                break;
            }
            case m3: {
                _interval = E_Interval8.Third;
                _intervalType4 = E_IntervalType4.Minor;
                _ifPerfect = false;
                break;
            }
            case M3: {
                _interval = E_Interval8.Third;
                _intervalType4 = E_IntervalType4.Major;
                _ifPerfect = false;
                break;
            }
            case P4: {
                _interval = E_Interval8.Fourth;
                _intervalType4 = E_IntervalType4.Major;
                _ifPerfect = true;
                break;
            }
            case P5: {
                _interval = E_Interval8.Fifth;
                _intervalType4 = E_IntervalType4.Major;
                _ifPerfect = true;
                break;
            }
            case m6: {
                _interval = E_Interval8.Sixth;
                _intervalType4 = E_IntervalType4.Minor;
                _ifPerfect = false;
                break;
            }
            case M6: {
                _interval = E_Interval8.Sixth;
                _intervalType4 = E_IntervalType4.Major;
                _ifPerfect = false;
                break;
            }
            case m7: {
                _interval = E_Interval8.Seventh;
                _intervalType4 = E_IntervalType4.Minor;
                _ifPerfect = false;
                break;
            }
            case M7: {
                _interval = E_Interval8.Seventh;
                _intervalType4 = E_IntervalType4.Major;
                _ifPerfect = false;
                break;
            }
            case P8: {
                _interval = E_Interval8.Octave;
                _intervalType4 = E_IntervalType4.Major;
                _ifPerfect = true;
                break;
            }
            default: {
                throw new UnsupportedNoteNotationError("Unsupported notes interval");
            }
        }
    }

    public E_Interval8 getInterval8() {
        return _interval;
    }

    public E_IntervalType4 getIntervalType4() {
        return _intervalType4;
    }

    public boolean isPerfect() {
        return _ifPerfect;
    }

    public String getString() throws UnsupportedNoteNotationError {
        String name = "";
        switch (_intervalType4) {
            case Diminished: {
                name += "d";
                break;
            }
            case Minor: {
                if (_ifPerfect) {
                    name += "P";
                }
                else {
                    name += "m";
                }
                break;
            }
            case Major: {
                if (_ifPerfect) {
                    name += "P";
                }
                else {
                    name += "M";
                }
                break;
            }
            case Augmented: {
                name += "A";
                break;
            }
            default: {
                throw new UnsupportedNoteNotationError("Unsupported interval notation");
            }
        }
        switch (_interval) {
            case Unison: {
                return name + "1";
            }
            case Second: {
                return name + "2";
            }
            case Third: {
                return name + "3";
            }
            case Fourth: {
                return name + "4";
            }
            case Fifth: {
                return name + "5";
            }
            case Sixth: {
                return name + "6";
            }
            case Seventh: {
                return name + "7";
            }
            case Octave: {
                return name + "8";
            }
            default: {
                throw new UnsupportedNoteNotationError("Unsupported interval notation");
            }
        }
    }

}