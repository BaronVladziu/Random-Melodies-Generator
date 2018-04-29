package music;

public class Note {

    public NotePitch _pitch = new NotePitch();
    public NoteDuration _duration = new NoteDuration();

    public Note() {}

    public Note(NoteDuration duration) {
        this._duration = duration;
    }

    public Note(NotePitch pitch, NoteDuration duration) {
        this._pitch = pitch;
        this._duration = duration;
    }

    public String getName() {
        return _pitch.getString() + _duration.getString();
    }

}