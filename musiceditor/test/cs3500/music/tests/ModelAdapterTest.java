package cs3500.music.tests;


import org.junit.Test;

import java.util.ArrayList;

import cs3500.music.model.ModelAdapter;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicNote;
import cs3500.music.model.Pitch;
import cs3500.music.model.Repeat;

import static org.junit.Assert.assertEquals;

/**
 * Created by McMacBook on 12/5/16.
 */
public class ModelAdapterTest {

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

  {
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

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    ModelAdapter ma = new ModelAdapter(null);
  }

  @Test
  public void testGetNoteType() {
    m1.addNotes(a1);
    ModelAdapter ma1 = new ModelAdapter(m1);
    ModelAdapter maa = new ModelAdapter(mm);
    assertEquals(ma1.getNoteType(2).toString(), "[SUSTAIN, SUSTAIN, HEAD]");
    assertEquals(ma1.getNoteType(20).toString(), "[]");
    assertEquals(maa.getNoteType(0).toString(), "[]");
  }

  @Test
  public void testGetBeatPitchNum() {
    m3.addNotes(a4);
    ModelAdapter ma3 = new ModelAdapter(m3);
    ModelAdapter maa = new ModelAdapter(mm);
    assertEquals(ma3.getBeatPitchNums(6).toString(), "[30, 28, 53]");
    assertEquals(ma3.getBeatPitchNums(10).toString(), "[]");
    assertEquals(maa.getBeatPitchNums(0).toString(), "[]");
  }

  @Test
  public void testGetPitchNums() {
    m1.addNotes(a1);
    m1.addNotes(a2);
    ModelAdapter ma1 = new ModelAdapter(m1);
    ModelAdapter maa = new ModelAdapter(mm);
    assertEquals(ma1.getPitchNums().toString(), "[12, 12, 12, 12, 15, 25, 25, 25, 25, 38, 38]");
    assertEquals(maa.getPitchNums().toString(), "[]");
  }

  @Test
  public void testGetInstuments() {
    m1.addNotes(a4);
    ModelAdapter ma1 = new ModelAdapter(m1);
    ModelAdapter maa = new ModelAdapter(mm);
    assertEquals(ma1.getInstruments(4).toString(), "[22, 80, 6]");
    assertEquals(ma1.getInstruments(6).toString(), "[22, 80, 6]");
    assertEquals(ma1.getInstruments(0).toString(), "[]");
    assertEquals(maa.getInstruments(2).toString(), "[]");
  }

  @Test
  public void testGetVolumes() {
    m1.addNotes(a4);
    ModelAdapter ma1 = new ModelAdapter(m1);
    ModelAdapter maa = new ModelAdapter(mm);
    assertEquals(ma1.getInstruments(4).toString(), "[22, 80, 6]");
    assertEquals(ma1.getInstruments(5).toString(), "[22, 80, 6]");
    assertEquals(ma1.getInstruments(1).toString(), "[2]");
    assertEquals(maa.getInstruments(2).toString(), "[]");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPrintState() {
    ModelAdapter m = new ModelAdapter(m3);
    m.printState();
  }

  @Test
  public void testNumBeats() {
    m1.addNotes(a4);
    m3.addNotes(a5);
    ModelAdapter ma1 = new ModelAdapter(m1);
    ModelAdapter ma3 = new ModelAdapter(m3);
    ModelAdapter maa = new ModelAdapter(mm);
    assertEquals(ma1.numBeats(), 8);
    assertEquals(ma3.numBeats(), 4);
    assertEquals(maa.numBeats(), 0);
  }

  @Test
  public void testPitchNumToString() {
    ModelAdapter m = new ModelAdapter(mm);
    assertEquals(m.pitchNumtoString(0), "C0");
    assertEquals(m.pitchNumtoString(127), "G10");
    assertEquals(m.pitchNumtoString(500), "G#41");
    assertEquals(m.pitchNumtoString(68), "G#5");
    assertEquals(m.pitchNumtoString(30), "F#2");

  }

  // ------

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

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddNote() {
    mm.addNote(new MusicNote(Pitch.C, 10, -6, 2, 55, 9));
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
  public void testAllNotes() {
    // also tests adding repeat notes
    m1.addNotes(a1);
    assertEquals(m1.allNotes().size(), 4);
    m1.addNotes(a7);
    assertEquals(m1.allNotes().size(), 4);
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

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveInvalidNote() {
    m3.removeNote(new MusicNote(null, 5, 2, 2, 9, 5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveInvalidNotes() {
    a2.add(new MusicNote(null, 5, 2, 2, 1, 8));
    m3.removeNotes(a2);
  }

  @Test
  public void testEditNote() {
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
  public void testEditInvalidNote() {
    m1.addNotes(a3);
    m1.editNote(new MusicNote(Pitch.E, 50, 4, -10, 22, 9), Pitch.FS, 2, 2, 2, 2, 2);
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
  public void testGetters() {
    assertEquals(mm.getBeatsPerMeasure(), 4);
    assertEquals(m1.getBeatsPerMeasure(), 1);
    assertEquals(m3.getBeatsPerMeasure(), 3);
    assertEquals(mm.getTempo(), 200000);
    assertEquals(m1.getTempo(), 2000);
    assertEquals(m3.getTempo(), 22100);
  }

  @Test
  public void testGetLastBeat() {
    m1.addNotes(a4);
    m3.addNotes(a5);
    ModelAdapter ma1 = new ModelAdapter(m1);
    ModelAdapter ma3 = new ModelAdapter(m3);
    ModelAdapter maa = new ModelAdapter(mm);
    assertEquals(ma1.getLastBeat(), 8);
    assertEquals(ma3.getLastBeat(), 4);
    assertEquals(maa.getLastBeat(), 0);
  }

  @Test
  public void testGetLowestThenHighest() {
    m1.addNotes(a4);
    m3.addNotes(a5);
    ModelAdapter ma1 = new ModelAdapter(m1);
    ModelAdapter ma3 = new ModelAdapter(m3);
    ArrayList<MusicNote> al1 = new ArrayList<MusicNote>();
    al1.add(n8);
    al1.add(n7);
    ArrayList<MusicNote> al2 = new ArrayList<MusicNote>();
    al2.add(n9);
    al2.add(n11);
    assertEquals(ma1.getLowestThenHighest(), al1);
    assertEquals(ma3.getLowestThenHighest(), al2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetRepeats() {
    ModelAdapter a = new ModelAdapter(m1);
    a.getRepeats();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetRepeats() {
    ModelAdapter a = new ModelAdapter(m1);
    a.setRepeats(new ArrayList<Repeat>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckNestedRepeats() {
    ModelAdapter a = new ModelAdapter(m1);
    a.checkNestedRepeats(new Repeat(4, 8));
  }
}