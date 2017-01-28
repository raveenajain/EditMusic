package cs3500.music.model;

/**
 * To represent a Repeat in a Music piece
 */
public class Repeat {

  private int start;
  private int end;
  private boolean hasRepeated;

  /**
   * Constructs @{Code Repeat} object.
   *
   * @param start start beat
   * @param end   end beat
   */
  public Repeat(int start, int end) {
    this.start = start;
    this.end = end;
    this.hasRepeated = false;
  }

  /**
   * Is valid if returns true.
   *
   * @param bpm      beats per measure
   * @param lastBeat last beat in music
   * @return boolean
   */
  public boolean isValidRepeat(int bpm, int lastBeat) {
    return start < end && start % bpm == 0 && end % bpm == 0 && start < lastBeat && end < lastBeat
            && start > 0 && end > 0;
  }

  /**
   * Sets the start beat to the given int.
   *
   * @param s given int
   */
  public void setStart(int s) {
    start = s;
  }

  /**
   * Sets the end beat to the given int.
   *
   * @param e given int
   */
  public void setEnd(int e) {
    end = e;
  }

  /**
   * Sets the hasRepeated field.
   *
   * @param h boolean
   */
  public void setHasRepeated(boolean h) {
    hasRepeated = h;
  }

  /**
   * Gets the start field.
   *
   * @return start field
   */
  public int getStart() {
    return start;
  }

  /**
   * Gets the end field.
   *
   * @return end field
   */
  public int getEnd() {
    return end;
  }


  /**
   * Gets the hasRepeated field.
   *
   * @return hasRepeated field
   */
  public boolean getHasRepeated() {
    return hasRepeated;
  }
}
