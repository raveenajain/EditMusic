package cs3500.music.model;

/**
 * To represent our providers two distinct note types.
 * HEAD denotes a note that is on an initial beat (startBeat).
 * SUSTAIN denotes a note that is not on an initial beat (duration).
 */
public enum NoteType {
  HEAD("HEAD"), SUSTAIN("SUSTAIN");

  private final String noteType;

  NoteType(String noteType) {
    this.noteType = noteType;
  }

  /**
   * to convert this noteType to a string.
   *
   * @return this noteType as a string
   */
  @Override
  public String toString() {
    return this.noteType;
  }
}
