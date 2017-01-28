package cs3500.music.model;

/**
 * To represent the 12 distinct pitches.
 * C is the lowest pitch.
 * B is the highest pitch.
 */
public enum Pitch {
  C("C"), CS("C#"), D("D"), DS("D#"), E("E"), F("F"), FS("F#"), G("G"), GS("G#"),
  A("A"), AS("A#"), B("B");

  private final String pitch;

  Pitch(String pitch) {
    this.pitch = pitch;
  }

  /**
   * to convert this pitch to a string.
   *
   * @return this pitch as a string
   */
  @Override
  public String toString() {
    return this.pitch;
  }

  public boolean startingWithPitch(String s) {
    boolean b = false;
    for (Pitch p: Pitch.values()) {
      if (s.equals(p.toString())) {
        b = true;
      }
    }
    return b;
  }
}
