package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.model.Repeat;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * To test the method in Repeat.
 */
public class RepeatTest {
  Repeat r1 = new Repeat(4, 5);
  Repeat r2 = new Repeat(4, 16);
  Repeat r3 = new Repeat(5, 20);
  Repeat r4 = new Repeat(3, 15);

  @Test
  public void testIsValid() {
    assertFalse(r1.isValidRepeat(4, 64));
    assertTrue(r2.isValidRepeat(4, 64));
    assertFalse(r1.isValidRepeat(1, 4));
    assertFalse(r1.isValidRepeat(1, 3));
    assertFalse(r3.isValidRepeat(4, 64));
    assertFalse(r4.isValidRepeat(4, 64));
  }

  @Test
  public void testSetStart() {
    // also tests getStart
    r2.setStart(8);
    assertEquals(r2.getStart(), 8);
    r3.setStart(12);
    assertEquals(r3.getStart(), 12);
    r4.setStart(4);
    assertEquals(r4.getStart(), 4);
  }

  @Test
  public void testSetEnd() {
    // also tests getEnd
    r2.setEnd(32);
    assertEquals(r2.getEnd(), 32);
    r3.setEnd(64);
    assertEquals(r3.getEnd(), 64);
    r4.setEnd(10);
    assertEquals(r4.getEnd(), 10);
  }

  @Test
  public void testSetHasRepeated() {
    // also tests getHasRepeated
    r2.setHasRepeated(false);
    assertFalse(r2.getHasRepeated());
    r3.setHasRepeated(true);
    assertTrue(r3.getHasRepeated());
  }


}