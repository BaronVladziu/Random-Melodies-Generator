package music;

public class Note {

    private NotePitch _pitch;
    private NoteDuration _duration;

    public Note(NotePitch pitch, NoteDuration duration) {
        this._pitch = pitch;
        this._duration = duration;
    }

    String getName() {
        return _pitch.getString() + _duration.getString();
    }

}