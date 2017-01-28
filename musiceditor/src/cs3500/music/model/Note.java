package cs3500.music.model;

/**
 * To represent our providers note.
 */
public class Note {

  public int beat;
  public int pitchNumber;
  public NoteType noteType;
  public int instrument;
  public int volume;

  /**
   * constructor for a note.
   *
   * @param beat the beat the note is played on
   * @param pitchNumber the midi note value
   * @param noteType whether the note is a head or sustain
   * @param instrument the midi instrument value
   * @param volume the volume of the note
   */
  public Note(int beat, int pitchNumber, NoteType noteType, int instrument, int volume) {
    this.beat = beat;
    this.pitchNumber = pitchNumber;
    this.noteType = noteType;
    this.instrument = instrument;
    this.volume = volume;
    validNote();

  }

  /**
   * validity based on conditions stated by providers in readme txt.
   *
   * @throws IllegalArgumentException if a note is invalid
   */
  private void validNote() {
    if (pitchNumber < 0 || instrument < 0 || volume < 0) {
      throw new IllegalArgumentException("Invalid Note.");
    }
  }
}
