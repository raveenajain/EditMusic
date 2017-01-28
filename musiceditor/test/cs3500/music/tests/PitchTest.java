package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.model.Pitch;

import static org.junit.Assert.assertEquals;

/**
 * To test the methods in Pitch.
 */
public class PitchTest {

  @Test
  public void testToString() {
    assertEquals(Pitch.A.toString(), "A");
    assertEquals(Pitch.B.toString(), "B");
    assertEquals(Pitch.AS.toString(), "A#");
    assertEquals(Pitch.GS.toString(), "G#");
    assertEquals(Pitch.G.toString(), "G");
    assertEquals(Pitch.F.toString(), "F");
    assertEquals(Pitch.FS.toString(), "F#");
    assertEquals(Pitch.E.toString(), "E");
    assertEquals(Pitch.DS.toString(), "D#");
    assertEquals(Pitch.CS.toString(), "C#");
    assertEquals(Pitch.C.toString(), "C");
    assertEquals(Pitch.D.toString(), "D");
  }
}
