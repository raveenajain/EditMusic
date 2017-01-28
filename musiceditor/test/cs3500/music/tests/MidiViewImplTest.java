package cs3500.music.tests;

import org.junit.Test;

import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicNote;
import cs3500.music.model.Pitch;
import cs3500.music.model.Repeat;
import cs3500.music.model.ViewModel;
import cs3500.music.view.IMusicView;
import cs3500.music.view.MidiMock;
import cs3500.music.view.MidiViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * To test the MidiViewImpl class through the use of mocks.
 */
public class MidiViewImplTest {

  MidiMock mm1;
  MidiMock mm2;
  MidiMock mmE;
  Receiver rm1;
  Receiver rm2;
  Receiver rmE;
  Sequencer s1;
  Sequencer s2;
  Sequencer sE;
  StringBuilder sb1;
  StringBuilder sb2;
  StringBuilder sbE;

  {
    sb1 = new StringBuilder();
    sb2 = new StringBuilder();
    sbE = new StringBuilder();
    mm1 = new MidiMock(sb1);
    mm2 = new MidiMock(sb2);
    mmE = new MidiMock(sbE);
    try {
      rm1 = mm1.getReceiver();
      rm2 = mm2.getReceiver();
      rmE = mmE.getReceiver();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    s1 = mm1.getSequencer();
    s2 = mm2.getSequencer();
    sE = mmE.getSequencer();
  }

  IMusicView view1 = new MidiViewImpl(s1);
  IMusicView view2 = new MidiViewImpl(s2);
  IMusicView viewE = new MidiViewImpl(sE);
  IMusicModel m1 = new MusicModel();
  IMusicModel m2 = new MusicModel();
  IMusicModel m3 = new MusicModel();
  IMusicModel m4 = new MusicModel();
  IMusicModel mEmpty = new MusicModel();
  MusicNote n1 = new MusicNote(Pitch.A, 1, 2, 3, 4, 5);
  MusicNote n2 = new MusicNote(Pitch.DS, 2, 4, 6, 8, 10);
  MusicNote n3 = new MusicNote(Pitch.G, 0, 1, 2, 3, 4);
  MusicNote n4 = new MusicNote(Pitch.D, 1, 3, 5, 7, 9);
  MusicNote n5 = new MusicNote(Pitch.C, -1, 1, 0, 127, 1);

  {
    m1.addNote(n1);
    m1.addNote(n2);
    m2.addNote(n3);
    m2.addNote(n4);
    m3.addNote(n1);
    m3.addNote(n2);
    m3.addNote(n3);
    m3.addNote(n4);
    m4.addNote(n5);
  }

  ViewModel v1 = new ViewModel(m1);
  ViewModel vEmpty = new ViewModel(mEmpty);
  ViewModel v2 = new ViewModel(m3);

  @Test
  public void testJumpHome() {
    Sequence s;
    try {
      s = new Sequence(Sequence.PPQ, 10);
      mm1.getSequencer().setSequence(s);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    mm1.jumpHome();
    assertEquals(mm1.getSequencer().getTickPosition(), 0);
  }

  @Test
  public void testJumpHomeEmpty() {
    Sequence s;
    try {
      s = new Sequence(Sequence.PPQ, 10);
      mmE.getSequencer().setSequence(s);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    mmE.jumpHome();
    assertEquals(mmE.getSequencer().getTickPosition(), 0);
  }

  @Test
  public void testJumpEnd() {
    Sequence s;
    try {
      s = new Sequence(Sequence.PPQ, 10);
      mm1.getSequencer().setSequence(s);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    mm1.jumpEnd();
    assertEquals(mm1.getSequencer().getTickPosition(), 50);
  }

  @Test
  public void testJumpEndEmpty() {
    Sequence s;
    try {
      s = new Sequence(Sequence.PPQ, 10);
      mmE.getSequencer().setSequence(s);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    mmE.jumpEnd();
    assertEquals(mmE.getSequencer().getTickPosition(), 50);
  }

  @Test
  public void testPause() {
    // also tests getPause
    Sequence s;
    try {
      s = new Sequence(Sequence.PPQ, 10);
      mm2.getSequencer().setSequence(s);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    mm2.pauseMusic();
    assertEquals(mm2.getPause(), true);
    mm2.pauseMusic();
    assertEquals(mm2.getPause(), false);
    mm2.pauseMusic();
    assertEquals(mm2.getPause(), true);
    mm2.pauseMusic();
    assertEquals(mm2.getPause(), false);
  }

  @Test
  public void testGetTick() {
    Sequence s;
    try {
      s = new Sequence(Sequence.PPQ, 10);
      mm2.getSequencer().setSequence(s);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    assertEquals(mm2.getTick(), 0);
    mm2.getSequencer().setTickPosition(100);
    assertEquals(mm2.getTick(), 100);
  }

  @Test
  public void testDrawModel1() {
    try {
      view1.drawModel(v1);
      Thread.sleep(2500);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    assertEquals(sb1.toString(), "Instrument" + "\n" + "Note, 33, 4" + "\n" + "Note, 33, 4"
            + "\n" + "Instrument" + "\n" + "Note, 39, 8" + "\n" + "Note, 39, 8" + "\n"
            + "Instrument" + "\n");
  }

  @Test
  public void testDrawModel2() {
    try {
      view2.drawModel(v2);
      Thread.sleep(3000);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    assertEquals(sb2.toString(), "Instrument" + "\n" + "Note, 19, 3" + "\n" + "Instrument"
            + "\n" + "Note, 33, 4" + "\n" + "Note, 19, 3" + "\n" + "Instrument" + "\n"
            + "Note, 26, 7" + "\n" + "Note, 33, 4" + "\n" + "Instrument" + "\n"
            + "Note, 39, 8" + "\n" + "Note, 26, 7" + "\n" + "Note, 39, 8" + "\n" + "Instrument"
            + "\n");
  }

  @Test
  public void testEmptyDraw() {
    try {
      viewE.drawModel(vEmpty);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    assertEquals(sbE.toString(), "");
  }

  @Test
  public void testRepeatMusic() {
    ArrayList<Repeat> ar = new ArrayList<Repeat>();
    ArrayList<Repeat> ar2 = new ArrayList<Repeat>();
    {
      ar.add(new Repeat(4, 12));
      ar.add(new Repeat(16, 20));
      ar2.add(new Repeat(16, 20));
    }
    v1.setRepeats(ar);
    mm1.repeatMusic(v1);
    assertEquals(mm1.getRepeat(), 4);
    v2.setRepeats(ar2);
    mm2.repeatMusic(v2);
    assertEquals(mm2.getRepeat(), 16);
  }
}