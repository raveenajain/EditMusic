package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.model.NoteType;

import static org.junit.Assert.assertEquals;

/**
 * Created by McMacBook on 12/5/16.
 */
public class NoteTypeTest {

  @Test
  public void testToString() {
    assertEquals(NoteType.HEAD.toString(), "HEAD");
    assertEquals(NoteType.SUSTAIN.toString(), "SUSTAIN");
  }
}