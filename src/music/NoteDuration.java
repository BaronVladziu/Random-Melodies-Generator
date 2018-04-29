package music;

public class NoteDuration {

    private E_NoteDuration _duration;
    private boolean _dotted;

    public NoteDuration() {
        this._duration = E_NoteDuration.Note4;
        this._dotted = false;
    }

    public NoteDuration(E_NoteDuration duration, boolean dotted) {
        this._duration = duration;
        this._dotted = dotted;
        correctDuration();
    }

    public void setDuration(E_NoteDuration duration) {
        this._duration = duration;
        correctDuration();
    }

    private void correctDuration() {
        if (_duration == E_NoteDuration.Note16) {
            _dotted = false;
        }
    }

    public int getInt() throws UnsupportedNoteNotationError {
        int duration;
        switch (_duration) {
            case Note1: {
                duration = 16;
                break;
            }
            case Note2: {
                duration = 8;
                break;
            }
            case Note4: {
                duration = 4;
                break;
            }
            case Note8: {
                duration = 2;
                break;
            }
            case Note16: {
                duration = 1;
                break;
            }
            default: {
                throw new UnsupportedNoteNotationError("Unsupported note duration");
            }
        }
        if (_dotted) {
            duration *= 1.5;
        }
        return duration;
    }

    public String getString() {
        return getDurationString() + getDotString();
    }

    private String getDurationString() throws UnsupportedNoteNotationError {
        String string = new String();
        switch (_duration) {
            case Note1: {
                string += "1";
                break;
            }
            case Note2: {
                string += "2";
                break;
            }
            case Note4: {
                string += "4";
                break;
            }
            case Note8: {
                string += "8";
                break;
            }
            case Note16: {
                string += "16";
                break;
            }
            default: {
                throw new UnsupportedNoteNotationError("Unsupported note duration");
            }
        }
        return string;
    }

    private String getDotString() {
        if (_dotted == true) {
            return ".";
        }
        else {
            return "";
        }
    }

}