package cs3500.music.tests;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicNote;
import cs3500.music.model.Pitch;
import cs3500.music.model.Repeat;

/**
 * To test the methods in MusicModel.
 */
public class MusicModelTest {

  MusicModel m1 = new MusicModel(1, 2000);
  MusicModel m3 = new MusicModel(3, 22100);
  MusicModel mm = new MusicModel();
  ArrayList<MusicNote> a1 = new ArrayList<MusicNote>();
  ArrayList<MusicNote> a2 = new ArrayList<MusicNote>();
  ArrayList<MusicNote> a3 = new ArrayList<MusicNote>();
  ArrayList<MusicNote> a4 = new ArrayList<MusicNote>();
  ArrayList<MusicNote> a5 = new ArrayList<MusicNote>();
  ArrayList<MusicNote> a6 = new ArrayList<MusicNote>();
  ArrayList<MusicNote> a7 = new ArrayList<MusicNote>();
  ArrayList<Repeat> rep1 = new ArrayList<Repeat>();
  ArrayList<Repeat> rep2 = new ArrayList<Repeat>();
  MusicNote n1 = new MusicNote(Pitch.C, 1, 4, 0, 2, 12);
  MusicNote n2 = new MusicNote(Pitch.CS, 2, 4, 1, 3, 6);
  MusicNote n3 = new MusicNote(Pitch.D, 3, 2, 2, 5, 5);
  MusicNote n4 = new MusicNote(Pitch.DS, 1, 1, 0, 0, 1);
  MusicNote n5 = new MusicNote(Pitch.FS, 2, 6, 3, 22, 22);
  MusicNote n6 = new MusicNote(Pitch.E, 2, 4, 4, 80, 80);
  MusicNote n7 = new MusicNote(Pitch.F, 4, 5, 2, 6, 6);
  MusicNote n8 = new MusicNote(Pitch.B, 1, 2, 1, 2, 2);
  MusicNote n9 = new MusicNote(Pitch.C, 1, 4, 0, 9, 12);
  MusicNote n10 = new MusicNote(Pitch.CS, 2, 4, 1, 6, 6);
  MusicNote n11 = new MusicNote(Pitch.D, 3, 2, 2, 5, 5);
  MusicNote n12 = new MusicNote(Pitch.DS, 1, 1, 0, 0, 1);
  MusicNote n13 = new MusicNote(Pitch.C, 1, 4, 0, 2, 12);
  MusicNote n14 = new MusicNote(Pitch.CS, 2, 4, 1, 3, 6);
  MusicNote n15 = new MusicNote(Pitch.D, 3, 2, 2, 5, 5);
  MusicNote n16 = new MusicNote(Pitch.DS, 1, 1, 0, 0, 1);
  Repeat r1 = new Repeat(4, 5);
  Repeat r2 = new Repeat(4, 16);
  Repeat r3 = new Repeat(5, 20);
  Repeat r4 = new Repeat(3, 15);
  Repeat r5 = new Repeat(20, 24);
  {
    rep1.add(r2);
    rep1.add(r5);
    rep2.add(r2);
    rep2.add(r3);
    rep2.add(r4);
    rep2.add(r1);
    a1.add(n1);
    a1.add(n2);
    a1.add(n3);
    a1.add(n4);
    a4.add(n5);
    a4.add(n6);
    a4.add(n7);
    a4.add(n8);
    a2.add(n2);
    a5.add(n9);
    a5.add(n10);
    a5.add(n11);
    a5.add(n12);
    a6.add(n5);
    a6.add(n6);
    a6.add(n7);
    a6.add(n8);
    a7.add(n13);
    a7.add(n14);
    a7.add(n15);
    a7.add(n16);
    for (int i = 0; i < 6; i = i + 1) {
      a3.add(n4);
    }
  }

  @Test
  public void testBPM() {
    assertEquals(mm.getBeatsPerMeasure(), 4);
    assertEquals(m1.getBeatsPerMeasure(), 1);
    assertEquals(m3.getBeatsPerMeasure(), 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void test0BeatsPerMin() {
    new MusicModel(0, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegBeatsPerMin() {
    new MusicModel(-6, 5000);
  }

  @Test
  public void testTempo() {
    assertEquals(mm.getTempo(), 200000);
    assertEquals(m1.getTempo(), 2000);
    assertEquals(m3.getTempo(), 22100);
  }

  @Test
  public void testAddNote() {
    // also tests getNotelist
    mm.addNote(n1);
    assertEquals(mm.getNotelist().size(), 4);
    mm.addNote(n2);
    mm.addNote(n3);
    mm.addNote(n4);
    assertEquals(mm.getNotelist().size(), 5);
    assertEquals(mm.getNotelist().keySet().toString(), "[0, 1, 2, 3, 4]");
    assertEquals(mm.getNotelist().get(0).size(), 2);
    assertEquals(mm.getNotelist().get(2).size(), 3);
  }

  @Test
  public void testAddSameNote() {
    mm.addNote(n1);
    assertEquals(mm.getNotelist().size(), 4);
    mm.addNote(n2);
    mm.addNote(n3);
    mm.addNote(n4);
    mm.addNote(n1);
    mm.addNote(n1);
    assertEquals(mm.getNotelist().size(), 5);
    assertEquals(mm.getNotelist().keySet().toString(), "[0, 1, 2, 3, 4]");
    assertEquals(mm.getNotelist().get(0).size(), 4);
    assertEquals(mm.getNotelist().get(1).size(), 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddNote() {
    mm.addNote(new MusicNote(Pitch.C, 10, -6, 2, 55, 9));
  }

  @Test
  public void testAddSameNotes() {
    mm.addNotes(a3);
    assertEquals(mm.getNotelist().size(), 1);
    assertEquals(mm.getNotelist().keySet().toString(), "[0]");
    assertEquals(mm.getNotelist().get(0).size(), 6);
  }

  @Test
  public void testAddNotes1() {
    mm.addNotes(a1);
    assertEquals(mm.getNotelist().size(), 5);
    assertEquals(mm.getNotelist().keySet().toString(), "[0, 1, 2, 3, 4]");
    assertEquals(mm.getNotelist().get(0).size(), 2);
  }

  @Test
  public void testAddNotes2() {
    mm.addNotes(a2);
    assertEquals(mm.getNotelist().size(), 4);
    assertEquals(mm.getNotelist().keySet().toString(), "[1, 2, 3, 4]");
    assertEquals(mm.getNotelist().get(1).size(), 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddNotes() {
    a2.add(new MusicNote(Pitch.C, 10, 2, -4, 6, 7));
    mm.addNotes(a2);
  }

  @Test
  public void testEmptyAddNotes() {
    mm.addNotes(new ArrayList<MusicNote>());
    assertEquals(mm.getNotelist().keySet().toString(), "[]");
  }

  @Test
  public void testAllNotes() {
    // also tests adding repeat notes
    m1.addNotes(a1);
    assertEquals(m1.allNotes().size(), 4);
    m1.addNotes(a7);
    assertEquals(m1.allNotes().size(), 4);
  }

  @Test
  public void testNullModelAllNotes() {
    assertEquals(m3.allNotes().toString(), "[]");
  }

  @Test
  public void testRemoveNote() {
    m3.addNote(n1);
    m3.addNote(n2);
    m3.addNote(n3);
    m3.addNote(n4);
    assertEquals(m3.getNotelist().get(0).size(), 2);
    m3.removeNote(n1);
    assertEquals(m3.getNotelist().size(), 5);
    assertEquals(m3.getNotelist().keySet().toString(), "[0, 1, 2, 3, 4]");
    assertEquals(m3.getNotelist().get(0).size(), 1);
    assertEquals(m3.getNotelist().get(2).size(), 2);
    m3.removeNote(n3);
    assertEquals(m3.getNotelist().size(), 5);
    assertEquals(m3.getNotelist().keySet().toString(), "[0, 1, 2, 3, 4]");
    assertEquals(m3.getNotelist().get(2).size(), 1);
  }

  @Test
  public void testRemoveAllNotes() {
    m1.addNote(n1);
    m1.addNote(n2);
    m1.addNote(n3);
    m1.addNote(n4);
    m1.removeNotes(a1);
    assertEquals(m1.getNotelist().get(0), null);
    assertEquals(m1.getNotelist().get(1), null);
    assertEquals(m1.getNotelist().get(2), null);
  }

  @Test
  public void testRemoveNotes() {
    m1.addNote(n1);
    m1.addNote(n2);
    m1.addNote(n3);
    m1.addNote(n4);
    a2.add(n4);
    m1.removeNotes(a2);
    assertEquals(m1.getNotelist().size(), 4);
    assertEquals(m1.getNotelist().keySet().toString(), "[0, 1, 2, 3]");
    assertEquals(m1.getNotelist().get(0).size(), 1);
    assertEquals(m1.getNotelist().get(1).size(), 1);
    assertEquals(m1.getNotelist().get(2).size(), 2);
  }

  @Test
  public void testEmptyRemoveNotes() {
    m3.addNotes(a1);
    m3.removeNotes(new ArrayList<MusicNote>());
    assertEquals(m3.getNotelist().size(), 5);
    assertEquals(m3.getNotelist().keySet().toString(), "[0, 1, 2, 3, 4]");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveInvalidNote() {
    m3.removeNote(new MusicNote(null, 5, 2, 2, 9, 5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveInvalidNotes() {
    a2.add(new MusicNote(null, 5, 2, 2, 1, 8));
    m3.removeNotes(a2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNonExistentNote() {
    mm.addNotes(a1);
    mm.removeNote(new MusicNote(Pitch.E, 20, 10, 5, 30, 45));
  }

  @Test
  public void testEditNote1() {
    // also tests findNote
    m3.addNotes(a1);
    m3.removeNote(n4);
    m3.editNote(n2, Pitch.A, 10, 3, 20, 6, 9);
    assertEquals(m3.getNotelist().keySet().toString(), "[0, 1, 2, 3, 20, 21, 22]");
    assertEquals(m3.getNotelist().get(20).size(), 1);
    assertEquals(m3.getNotelist().get(21).size(), 1);
    assertEquals(m3.getNotelist().get(22).size(), 1);
  }

  @Test
  public void testEditNote2() {
    MusicNote n = new MusicNote(Pitch.A, 10, 3, 20, 22, 12);
    m3.addNotes(a1);
    m3.editNote(n2, n.getPitch(), n.getOctave(), n.getDuration(), n.getStartBeat(), n.getVolume(),
            n.getInstrument());
    assertEquals(m3.getNotelist().keySet().toString(), "[0, 1, 2, 3, 20, 21, 22]");
    m3.editNote(n, Pitch.A, 20, 4, 25, 60, 92);
    assertEquals(m3.getNotelist().keySet().toString(), "[0, 1, 2, 3, 25, 26, 27, 28]");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRepeatEdit() {
    m3.addNotes(a1);
    m3.editNote(n2, Pitch.A, 10, 3, 20, 90, 90);
    m3.editNote(n2, Pitch.AS, 11, 4, 21, 60, 66);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditNonExistentNote() {
    m3.addNotes(a1);
    m3.removeNote(n4);
    m3.editNote(n4, Pitch.FS, 2, 2, 2, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditNull() {
    m1.editNote(n4, Pitch.FS, 2, 2, 2, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditInvalidNote() {
    m1.addNotes(a3);
    m1.editNote(new MusicNote(Pitch.E, 50, 4, -10, 22, 9), Pitch.FS, 2, 2, 2, 2, 2);
  }

  @Test
  public void testGetLastBeat() {
    m1.addNotes(a4);
    m3.addNotes(a5);
    assertEquals(m1.getLastBeat(), 8);
    assertEquals(m3.getLastBeat(), 4);
    assertEquals(mm.getLastBeat(), 0);
  }

  @Test
  public void testGetLowestThenHighest() {
    m1.addNotes(a4);
    m3.addNotes(a5);
    ArrayList<MusicNote> al1 = new ArrayList<MusicNote>();
    al1.add(n8);
    al1.add(n7);
    ArrayList<MusicNote> al2 = new ArrayList<MusicNote>();
    al2.add(n9);
    al2.add(n11);
    assertEquals(m1.getLowestThenHighest(), al1);
    assertEquals(m3.getLowestThenHighest(), al2);
  }

  @Test
  public void testMergeModels() {
    m1.addNotes(a1);
    m3.addNotes(a4);
    m1.mergeModels(m3);
    assertEquals(m1.getNotelist().keySet().toString(), "[0, 1, 2, 3, 4, 5, 6, 7, 8]");
    assertEquals(m3.getNotelist().keySet().toString(), "[1, 2, 3, 4, 5, 6, 7, 8]");
    assertEquals(m1.getNotelist().get(0).size(), 2);
    assertEquals(m1.getNotelist().get(3).size(), 5);
  }

  @Test
  public void testMergeModelsSame() {
    m1.addNotes(a1);
    m3.addNotes(a1);
    m1.mergeModels(m3);
    assertEquals(m1.getNotelist().keySet().toString(), "[0, 1, 2, 3, 4]");
    assertEquals(m1.getNotelist().get(0).size(), 4);
    assertEquals(m1.getNotelist().get(3).size(), 6);
  }

  @Test
  public void testNullMergeModels1() {
    m1.addNotes(a4);
    m1.mergeModels(m3);
    assertEquals(m1.getNotelist().keySet().toString(), "[1, 2, 3, 4, 5, 6, 7, 8]");
    assertEquals(m1.getNotelist().get(3).size(), 2);
  }

  @Test
  public void testNullMergeModels2() {
    mm.mergeModels(m3);
    assertEquals(m1.getNotelist().keySet().toString(), "[]");
  }

  @Test
  public void testAddModelAtEnd() {
    m1.addNotes(a4);
    m3.addNotes(a5);
    m3.addModelAtEnd(m1);
    assertEquals(m3.getNotelist().keySet().toString(), "[0, 1, 2, 3, 4, 6, 7,"
            + " 8, 9, 10, 11, 12, 13]");
    assertEquals(m3.getNotelist().get(0).size(), 2);
    assertEquals(m3.getNotelist().get(3).size(), 3);
    assertEquals(m3.getNotelist().get(8).size(), 2);
    assertEquals(m3.getNotelist().get(11).size(), 3);
    assertEquals(m3.getNotelist().get(13).size(), 1);
  }

  @Test
  public void testAddModelAtEndSameNotes() {
    m1.addNotes(a1);
    m3.addNotes(a5);
    m1.addModelAtEnd(m3);
    assertEquals(m1.getNotelist().keySet().toString(), "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]");
    assertEquals(m1.getNotelist().get(0).size(), 2);
    assertEquals(m1.getNotelist().get(3).size(), 3);
    assertEquals(m1.getNotelist().get(5).size(), 2);
    assertEquals(m1.getNotelist().get(8).size(), 3);
    assertEquals(m1.getNotelist().get(9).size(), 1);
  }

  @Test
  public void testNullAddModelAtEnd() {
    mm.mergeModels(m3);
    assertEquals(mm.getNotelist().keySet().toString(), "[]");
  }

  @Test
  public void testSetters() {
    m1.addNotes(a2);
    m1.setNoteList(a1);
    m1.setTempo(221221);
    assertEquals(m1.getTempo(), 221221);
    assertEquals(m1.getNotelist().keySet().toString(), "[0, 1, 2, 3, 4]");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetInvalidNote() {
    a1.add(new MusicNote(Pitch.AS, 3, 10, 0, 200, 120));
    m1.setNoteList(a1);
  }

  @Test
  public void testSetRepeats(){
    // also tests getRepeats
    m1.setRepeats(rep1);
    assertEquals(m1.getRepeats(), rep1);
    m1.setRepeats(rep2);
    assertEquals(m1.getRepeats(),rep2);
  }

  @Test
  public void testNestedRepeats(){
    m1.setRepeats(rep1);
    assertTrue(m1.checkNestedRepeats(new Repeat(8, 12)));
    assertFalse(m1.checkNestedRepeats(new Repeat(32, 36)));
  }
}
