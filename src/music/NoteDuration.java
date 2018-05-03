package music;

public class NoteDuration {

    private E_NoteDuration _duration;
    private boolean _dotted;

    NoteDuration() {
        this._duration = E_NoteDuration.Note4;
        this._dotted = false;
    }

    NoteDuration(NoteDuration duration) {
        _duration = duration._duration;
        _dotted = duration._dotted;
    }

    NoteDuration(E_NoteDuration duration, boolean dotted) {
        this._duration = duration;
        this._dotted = dotted;
        correctDuration();
    }

    void setDuration(E_NoteDuration duration, boolean dotted) {
        this._duration = duration;
        this._dotted = dotted;
        correctDuration();
    }

    public void setDuration(E_NoteDuration duration) {
        this._duration = duration;
        correctDuration();
    }

    public void setIfDotted(boolean dotted) {
        this._dotted = dotted;
        correctDuration();
    }

    private void correctDuration() {
        if (_duration == E_NoteDuration.Note16) {
            _dotted = false;
        }
    }

    int getInt() throws UnsupportedNoteNotationError {
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
        String string = "";
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
        if (_dotted) {
            return ".";
        }
        else {
            return "";
        }
    }

}