package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.controller.IMusicController;
import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;
import cs3500.music.controller.MusicController;
import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicNote;
import cs3500.music.model.Pitch;
import cs3500.music.model.ViewModel;
import cs3500.music.view.CompositeView;
import cs3500.music.view.ConsoleViewImpl;
import cs3500.music.view.GuiViewImpl;
import cs3500.music.view.MidiViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * To test the methods inside the MusicController class.
 */
public class MusicControllerTest {
  IMusicController mc = new MusicController();
  IMusicModel m1 = new MusicModel();
  IMusicModel mEmpty = new MusicModel();
  MusicNote n1 = new MusicNote(Pitch.CS, 1, 2, 3, 4, 5);
  MusicNote n2 = new MusicNote(Pitch.GS, 2, 3, 4, 5, 6);
  MusicNote n3 = new MusicNote(Pitch.E, 3, 4, 5, 6, 7);
  MusicNote n4 = new MusicNote(Pitch.A, 4, 5, 6, 7, 8);

  {
    mc.setModel(mEmpty);
    m1.addNote(n1);
    m1.addNote(n2);
    m1.addNote(n3);
    m1.addNote(n4);
  }

  IMusicModel v1 = new ViewModel(m1);

  @Test
  public void testSetAndGetModel() {
    assertEquals(mc.getModel(), mEmpty);
    mc.setModel(m1);
    assertEquals(mc.getModel(), m1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetModelNull() {
    mc.setModel(null);
  }

  @Test
  public void testCreateViewModel() {
    mc.setModel(m1);
    assertTrue(mc.createViewModel() instanceof ViewModel);
    assertEquals(mc.createViewModel().getNotelist(), v1.getNotelist());
    assertEquals(mc.createViewModel().getBeatsPerMeasure(), v1.getBeatsPerMeasure());
    assertEquals(mc.createViewModel().getTempo(), v1.getTempo());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateViewNullModel() {
    mc.createViewModel();
  }

  @Test
  public void createView() {
    assertTrue(mc.createView("midi") instanceof MidiViewImpl);
    assertTrue(mc.createView("console") instanceof ConsoleViewImpl);
    assertTrue(mc.createView("visual") instanceof GuiViewImpl);
    assertTrue(mc.createView("comp") instanceof CompositeView);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateInvalidInput() {
    mc.createView("pizza");
  }

  @Test
  public void testCreateKeyBoardHandler() {
    KeyboardHandler key = mc.createKeyboardHandler();
    assertEquals(key.getPressed().keySet().toString(), "[80, 65, 82, 69, 72, 10]");
    assertEquals(key.getReleased().keySet().toString(), "[]");
    assertEquals(key.getTyped().keySet().toString(), "[]");
  }

  @Test
  public void testCreateMouseHandler() {
    MouseHandler mouse = mc.createMouseHandler();
    assertEquals(mouse.getClicked().keySet().toString(), "[500]");
  }
}