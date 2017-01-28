package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.model.MusicNote;
import cs3500.music.model.Pitch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * To test the methods in MusicNote.
 */
public class MusicNoteTest {

  MusicNote n1 = new MusicNote(Pitch.E, 5, 6, 7, 8, 9);
  MusicNote n2 = new MusicNote(Pitch.E, 5, 6, 7, 8, 9);
  MusicNote n3 = new MusicNote(Pitch.F, 5, 6, 7, 8, 9);
  MusicNote n4 = new MusicNote(Pitch.E, 10, 6, 7, 19, 1);
  MusicNote n5 = new MusicNote(Pitch.E, 5, 10, 7, 2, 5);
  MusicNote n6 = new MusicNote(Pitch.E, 5, 6, 25, 8, 22);
  MusicNote n7 = new MusicNote(Pitch.C, 10, 1, 1, 1, 1);
  MusicNote n8 = new MusicNote(Pitch.A, 2, 1, 1, 22, 1);
  MusicNote n9 = new MusicNote(Pitch.FS, 10, 8, 8, 6, 4);
  MusicNote n10 = new MusicNote(Pitch.DS, 89, 21, 22, 14, 100);
  MusicNote n11 = new MusicNote(Pitch.DS, 89, 21, 0, 100, 100);

  @Test(expected = IllegalArgumentException.class)
  public void testNullPitchNote() {
    new MusicNote(null, 5, 5, 5, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void test0DurNote() {
    new MusicNote(Pitch.CS, 4, -12, 2, 6, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegDurNote() {
    new MusicNote(Pitch.E, 25, -10, 2, 1, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegStartNote() {
    new MusicNote(Pitch.CS, 1, 1, -3, 2, 9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooSmallOctNote() {
    new MusicNote(Pitch.C, -300, 7, 7, 10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooLargeOctNote() {
    new MusicNote(Pitch.CS, 12345, 2, 3, 4, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooLrgVolume() {
    new MusicNote(Pitch.C, -300, 7, 7, 150, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooSmallVolume() {
    new MusicNote(Pitch.C, -300, 7, 7, -2, 2);
  }

  @Test
  public void testBoundaryOctNote1() {
    // also tests getOctave
    assertEquals(new MusicNote(Pitch.E, -99, 2, 10, 22, 22).getOctave(), -99);
    assertEquals(new MusicNote(Pitch.CS, 999, 1, 2, 2, 1).getOctave(), 999);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBoundaryOctNote2() {
    new MusicNote(Pitch.E, -100, 2, 10, 22, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBoundaryOctNote3() {
    new MusicNote(Pitch.CS, 1000, 1, 2, 5, 5);
  }

  @Test
  public void testEquals() {
    assertTrue(n1.equals(n2));
    assertTrue(!n2.equals(n3));
    assertTrue(!n1.equals(n4));
    assertTrue(!n1.equals(n5));
    assertTrue(!n2.equals(n6));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEquals1() {
    n1.equals(new MusicNote(null, 6, 6, 6, 6, 6));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEquals2() {
    new MusicNote(null, 6, 6, 6, 6, 6).equals(n4);
  }

  @Test
  public void testIsLowerNote() {
    assertTrue(n1.isLowerNote(n3));
    assertTrue(n5.isLowerNote(n4));
    assertTrue(n1.isLowerNote(n7));
    assertTrue(n8.isLowerNote(n2));
    assertTrue(n1.isLowerNote(n2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLower() {
    n1.isLowerNote(new MusicNote(null, 6, 6, 6, 6, 6));
  }

  @Test
  public void testIsHigherNote() {
    assertTrue(n3.isHigherNote(n1));
    assertTrue(n4.isHigherNote(n5));
    assertTrue(n7.isHigherNote(n1));
    assertTrue(n2.isHigherNote(n8));
    assertTrue(n1.isHigherNote(n2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidHigher() {
    new MusicNote(Pitch.F, -100, 2, 4, 5, 9).isHigherNote(n6);
  }

  @Test
  public void testCombinePitchOctave() {
    assertEquals(n1.combinePitchOctave(), "E5");
    assertEquals(n3.combinePitchOctave(), "F5");
    assertEquals(n7.combinePitchOctave(), "C10");
    assertEquals(n8.combinePitchOctave(), "A2");
    assertEquals(n9.combinePitchOctave(), "F#10");
    assertEquals(n10.combinePitchOctave(), "D#89");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCombine() {
    new MusicNote(null, 22222, 2, 2, 22, 22).combinePitchOctave();
  }

  @Test
  public void testIsStartOfNote() {
    assertTrue(n2.isStartOfNote(7));
    assertTrue(n10.isStartOfNote(22));
    assertTrue(!n6.isStartOfNote(4));
    assertTrue(!n7.isStartOfNote(2));
    assertTrue(n11.isStartOfNote(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidIsStart() {
    new MusicNote(Pitch.DS, 10, 20, -4, 12, 40).isStartOfNote(-4);
  }

  @Test
  public void testGetters() {
    assertEquals(n1.getStartBeat(), 7);
    assertEquals(n1.getPitch(), Pitch.E);
    assertEquals(n1.getDuration(), 6);
    assertEquals(n1.getOctave(), 5);
    assertEquals(n1.getVolume(), 8);
    assertEquals(n1.getInstrument(), 9);
    assertEquals(n10.getStartBeat(), 22);
    assertEquals(n10.getPitch(), Pitch.DS);
    assertEquals(n10.getDuration(), 21);
    assertEquals(n10.getOctave(), 89);
    assertEquals(n10.getInstrument(), 100);
    assertEquals(n10.getVolume(), 14);
  }

  @Test
  public void testSetters() {
    n2.setPitch(2);
    n2.setOctave(2);
    n2.setDuration(20);
    n2.setStartBeat(10);
    n2.setInstrument(100);
    n2.setVolume(60);
    assertEquals(n2.getStartBeat(), 10);
    assertEquals(n2.getPitch(), Pitch.D);
    assertEquals(n2.getDuration(), 20);
    assertEquals(n2.getOctave(), 2);
    assertEquals(n2.getVolume(), 60);
    assertEquals(n2.getInstrument(), 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetInvalidOct() {
    n6.setOctave(100000);
  }

  @Test(expected = ArrayIndexOutOfBoundsException.class)
  public void testSetInvalidPitch() {
    n6.setPitch(100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetInvalidStart() {
    n6.setStartBeat(-22);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetInvalidDur() {
    n6.setDuration(-62);
  }
}