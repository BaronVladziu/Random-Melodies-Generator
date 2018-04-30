package music;

import java.util.LinkedList;
import java.util.ListIterator;

public class Note {

    private NotePitch _pitch = new NotePitch();
    private LinkedList<NoteDuration> _durations = new LinkedList<>();

    public Note() {
        this._durations.add(new NoteDuration());
    }

    public Note(NoteDuration duration) {
        this._durations.add(new NoteDuration(duration));
    }

    public Note(NotePitch pitch, NoteDuration duration) {
        this._pitch = new NotePitch(pitch);
        this._durations.add(new NoteDuration(duration));
    }

    public void setPitch(NotePitch pitch) {
        this._pitch = new NotePitch(pitch);
    }

    public String getName() {
        String name = "";
        ListIterator<NoteDuration> prev = _durations.listIterator();
        ListIterator<NoteDuration> next = _durations.listIterator(1);
        while (next.hasNext()) {
            name += _pitch.getString() + prev.next().getString() + "~ ";
            next.next();
        }
        name += _pitch.getString() + prev.next().getString() + " ";
        return name;
    }

    public int groupNote(int remainingGroup, int remainingBar, int groupLength) {
        int noteDuration = _durations.getFirst().getInt();
        int remainingNoteDuration = noteDuration;
        if (remainingNoteDuration <= remainingGroup) {
            return noteDuration;
        }
//        if (remainingNoteDuration <= remainingBar &&
//                (remainingGroup % groupLength == 0 || remainingNoteDuration % groupLength == 0) ) {
//            return noteDuration;
//        }
        _durations.clear();
        NoteDuration tmpDur;
        int tmpDurInt;
        int i = 0;
        while (remainingGroup > 0) {
            tmpDur = new NoteDuration(E_NoteDuration.values()[i], false);
            tmpDurInt = tmpDur.getInt();
            if (remainingGroup % (tmpDurInt*2) != 0) {
                _durations.add(tmpDur);
                remainingGroup -= tmpDurInt;
                remainingNoteDuration -= tmpDurInt;
                remainingBar -= tmpDurInt;
            }
            i++;
        }
        if (remainingNoteDuration > remainingBar) {
            while (remainingBar > 0) {
                tmpDur = new NoteDuration(E_NoteDuration.values()[i], false);
                tmpDurInt = tmpDur.getInt();
                if (remainingBar % (tmpDurInt*2) != 0) {
                    _durations.add(tmpDur);
                    remainingNoteDuration -= tmpDurInt;
                    remainingBar -= tmpDurInt;
                }
                i++;
            }
        }
        for (i = E_NoteDuration.values().length - 1; i >= 0 ; i--) {
            tmpDur = new NoteDuration(E_NoteDuration.values()[i], false);
            tmpDurInt = tmpDur.getInt();
            if (remainingNoteDuration >= tmpDurInt) {
                _durations.add(tmpDur);
                remainingNoteDuration -= tmpDur.getInt();
            }
        }
        return noteDuration;
    }

}