package music;

public class Note {

    private NotePitch _pitch = new NotePitch();
    private NoteDuration _duration = new NoteDuration();

    public Note() {}

    public Note(NoteDuration duration) {
        this._duration = new NoteDuration(duration);
    }

    public Note(NotePitch pitch, NoteDuration duration) {
        this._pitch = new NotePitch(pitch);
        this._duration = new NoteDuration(duration);
    }

    public String getName() {
        return _pitch.getString() + _duration.getString();
    }

}