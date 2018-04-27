package music;

public class Interval {

    private E_Interval _interval;

    public Interval(E_Interval interval) {
        _interval = interval;
    }

    public String getString() throws UnsupportedNoteNotationError {
        switch (_interval) {
            case A1: {
                return "A1";
            }
            case A2: {
                return "A2";
            }
            case A3: {
                return "A3";
            }
            case A4: {
                return "A4";
            }
            case A5: {
                return "A5";
            }
            case A6: {
                return "A6";
            }
            case A7: {
                return "A7";
            }
            case d2: {
                return "d2";
            }
            case d3: {
                return "d3";
            }
            case d4: {
                return "d4";
            }
            case d5: {
                return "d5";
            }
            case d6: {
                return "d6";
            }
            case d7: {
                return "d7";
            }
            case d8: {
                return "d8";
            }
            case m2: {
                return "m2";
            }
            case M2: {
                return "M2";
            }
            case m3: {
                return "m3";
            }
            case M3: {
                return "M3";
            }
            case P4: {
                return "P4";
            }
            case P5: {
                return "P5";
            }
            case m6: {
                return "m6";
            }
            case M6: {
                return "M6";
            }
            case m7: {
                return "m7";
            }
            case M7: {
                return "M7";
            }
            case P8: {
                return "P8";
            }
            case P1: {
                return "P1";
            }
            default: {
                throw new UnsupportedNoteNotationError("Unsupported notes interval");
            }
        }
    }

}