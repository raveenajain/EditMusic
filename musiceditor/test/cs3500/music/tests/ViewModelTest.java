package cs3500.music.tests;

import org.junit.Test;

import java.util.ArrayList;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicNote;
import cs3500.music.model.Pitch;
import cs3500.music.model.ViewModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * To test the methods inside of ViewModel.
 */
public class ViewModelTest {

  IMusicModel m = new MusicModel();
  MusicModel m2 = new MusicModel(10, 100);
  IMusicModel v = new ViewModel(m);
  IMusicModel v2 = new ViewModel(m2);
  MusicNote n1 = new MusicNote(Pitch.G, 1, 2, 3, 4, 5);
  MusicNote n2 = new MusicNote(Pitch.CS, 0, 2, 4, 6, 8);
  MusicNote n3 = new MusicNote(Pitch.CS, 1, 0, 1, 0, 1);
  ArrayList<MusicNote> a = new ArrayList<MusicNote>();

  {
    a.add(n1);
    a.add(n2);
    m.addNotes(a);
    m2.addNote(n3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNote() {
    v.addNote(n1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNotes() {
    v.addNotes(a);
  }

  @Test
  public void testAllNotes() {
    assertEquals(v.allNotes().size(), 2);
    assertTrue(v.allNotes().get(0) == n1);
    assertTrue(v.allNotes().get(1) == n2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNote() {
    v.addNote(n2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNotes() {
    v.addNote(n2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditNote() {
    v.editNote(n2, Pitch.A, 2, 2, 1, 8, 0);
  }

  @Test
  public void testGetLastBeat() {
    assertEquals(v.getLastBeat(), 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMergeModels() {
    v.mergeModels(m2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddModelAtEnd() {
    v.addModelAtEnd(m2);
  }

  @Test
  public void testGetBeatsPerMeasure() {
    assertEquals(v.getBeatsPerMeasure(), 4);
    assertEquals(v2.getBeatsPerMeasure(), 10);
  }

  @Test
  public void testGetNoteList() {
    assertEquals(v.getNotelist().keySet().toString(), "[3, 4, 5]");
    assertEquals(v2.getNotelist().keySet().toString(), "[]");
  }

  @Test
  public void testGetTempo() {
    assertEquals(v.getTempo(), 200000);
    assertEquals(v2.getTempo(), 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetNotelist() {
    v2.setNoteList(a);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetTempo() {
    v.setTempo(50000);
  }
}