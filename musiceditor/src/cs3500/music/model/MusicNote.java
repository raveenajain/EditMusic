package cs3500.music.model;

import java.util.Objects;

/**
 * To represent a note.
 */
public class MusicNote {

  protected Pitch pitch;
  protected int octave;
  protected int duration;
  protected int startBeat;
  protected int volume;
  protected int instrument;

  /**
   * constructor for a MusicNote.
   *
   * @param pitch     one of the 12 pitches
   * @param octave    ranges from -99 to 999
   * @param duration  the length of the note. in this case only an integral number of beats long
   * @param startBeat when the note begins playing
   */
  public MusicNote(Pitch pitch, int octave, int duration, int startBeat, int volume,
                   int instrument) {
    this.pitch = pitch;
    this.octave = octave;
    this.duration = duration;
    this.startBeat = startBeat;
    this.volume = volume;
    this.instrument = instrument;
    validNote();
  }

  /**
   * invalid if a duration is less than 0, if the start beat is negative,
   * if the pitch is null, or if the octave is less than -99 or greater than 999.
   *
   * @throws IllegalArgumentException if one of the above conditions are not met
   */
  private void validNote() {
    if (duration < 0 || startBeat < 0 || pitch == null || octave < -99 || octave > 999
            || volume < 0 || volume > 127) {
      throw new IllegalArgumentException("Invalid Input.");
    }
  }

  /**
   * to override the method equals to compare two MusicNotes.
   *
   * @param o the object this is being compared to
   * @return true if this and the given object are equal
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof MusicNote)) {
      return false;
    }
    MusicNote that = (MusicNote) o;
    return this.pitch == that.pitch && this.octave == that.octave && this.duration == that.duration
            && this.startBeat == that.startBeat && this.volume == that.volume
            && this.instrument == that.instrument;
  }

  /**
   * to override the hashCode of a MusicNote.
   *
   * @return the new hashCode
   */
  @Override
  public int hashCode() {
    return Objects.hash(pitch, octave, duration, startBeat, volume, instrument);
  }

  /**
   * to find if this note is lower than the given note.
   *
   * @param mn the given MusicNote to be compared to
   * @return true if this note is lower than the given note
   */
  public boolean isLowerNote(MusicNote mn) {
    if (octave == mn.octave) {
      return pitch.ordinal() <= mn.pitch.ordinal();
    } else {
      return octave <= mn.octave;
    }
  }

  /**
   * to get the higher of this note and the given note.
   *
   * @param mn the given MusicNote to be compared to
   * @return true if this note is higher than the given note
   */
  public boolean isHigherNote(MusicNote mn) {
    if (octave == mn.octave) {
      return pitch.ordinal() >= mn.pitch.ordinal();
    } else {
      return octave >= mn.octave;
    }
  }

  /**
   * to combine a MusicNote's pitch and octave fields into a single string.
   *
   * @return this notes pitch and octave as a string
   */
  public String combinePitchOctave() {
    return this.pitch.toString() + this.octave;
  }

  /**
   * is the given int the startBeat of this note.
   *
   * @param i the potential startBeat of a note
   * @return true if the given int is the startbeat of this note
   */
  public boolean isStartOfNote(int i) {
    return startBeat == i;
  }

  /**
   * to get this MusicNote's pitch.
   *
   * @return this notes pitch value
   */
  public Pitch getPitch() {
    return pitch;
  }

  /**
   * to get this MusicNote's octave.
   *
   * @return this notes octave value
   */
  public int getOctave() {
    return octave;
  }

  /**
   * to get this MusicNote's duration.
   *
   * @return this notes duration value
   */
  public int getDuration() {
    return duration;
  }

  /**
   * to get this MusicNote's startBeat.
   *
   * @return this notes startBeat value
   */
  public int getStartBeat() {
    return startBeat;
  }

  /**
   * to get this MusicNote's volume.
   *
   * @return this notes volume value
   */
  public int getVolume() {
    return volume;
  }

  /**
   * to get this MusicNote's instrument.
   *
   * @return this notes instrument value
   */
  public int getInstrument() {
    return instrument;
  }

  /**
   * to set the pitch to the value of the enumeration at the given int.
   *
   * @param p the given int for the pitch value
   * @throws IllegalArgumentException if the new note is invalid
   */
  public void setPitch(int p) {
    pitch = Pitch.values()[p];
    validNote();
  }

  /**
   * to set the octave to the given int.
   *
   * @param o the new octave value
   * @throws IllegalArgumentException if the new note is invalid
   */
  public void setOctave(int o) {
    octave = o;
    validNote();
  }

  /**
   * to set the startBeat to the given int.
   *
   * @param sb the new startBeat value
   * @throws IllegalArgumentException if the new note is invalid
   */
  public void setStartBeat(int sb) {
    startBeat = sb;
    validNote();
  }

  /**
   * to set the duration to the given int.
   *
   * @param d the new duration value
   * @throws IllegalArgumentException if the new note is invalid
   */
  public void setDuration(int d) {
    duration = d;
    validNote();
  }

  /**
   * to set the instrument to the given int.
   *
   * @param i the new instrument value
   * @throws IllegalArgumentException if the new note is invalid
   */
  public void setInstrument(int i) {
    instrument = i;
    validNote();
  }

  /**
   * to set the volume to the given int.
   *
   * @param v the new octave value
   * @throws IllegalArgumentException if the new note is invalid
   */
  public void setVolume(int v) {
    volume = v;
    validNote();
  }
}

