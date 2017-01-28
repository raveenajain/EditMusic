package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.model.Note;
import cs3500.music.model.NoteType;

/**
 * To test the creation of a valid note.
 */
public class NoteTest {

  @Test(expected = IllegalArgumentException.class)
  public void testValidNotePitchNum() {
    new Note(10, -2, NoteType.HEAD, 100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValidNoteInst() {
    new Note(10, 20, NoteType.SUSTAIN, -221, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValidNoteVol() {
    new Note(10, 20, NoteType.SUSTAIN, 100, -8);
  }
}