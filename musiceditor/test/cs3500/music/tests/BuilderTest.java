package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.model.MusicModel;

import static org.junit.Assert.assertEquals;

/**
 * To test the methods inside of the nested builder class.
 */
public class BuilderTest {

  MusicModel.Builder b = new MusicModel.Builder();

  @Test
  public void testAllMethods1() {
    b.setTempo(10);
    b.addNote(1, 4, 10, 22, 100);
    b.addNote(0, 2, 30, 40, 50);
    assertEquals(b.build().getTempo(), 10);
    assertEquals(b.build().getNotelist().size(), 4);
  }

  @Test
  public void testAllMethods2() {
    b.setTempo(10000);
    b.addNote(1, 2, 3, 4, 5);
    b.addNote(2, 3, 4, 5, 6);
    b.addNote(3, 4, 5, 6, 7);
    b.addNote(4, 5, 6, 7, 8);
    assertEquals(b.build().getTempo(), 10000);
    assertEquals(b.build().getNotelist().size(), 4);
  }
}