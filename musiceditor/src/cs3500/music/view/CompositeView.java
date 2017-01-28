package cs3500.music.view;

import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import cs3500.music.controller.IMusicController;
import cs3500.music.model.ViewModel;

/**
 * To represent a view that scrolls as the music plays.
 */
public class CompositeView implements GuiView {

  private GuiView gui;
  private MidiViewImpl midi;

  /**
   * to Construct a {@Code CompositeView} object.
   *
   * @param gui  GuiView
   * @param midi MidiViewImpl
   */
  public CompositeView(GuiView gui, MidiViewImpl midi) {
    this.gui = gui;
    this.midi = midi;
  }

  /**
   * .
   *
   * @param c IMusicController that creates the KeyListener
   */
  @Override
  public void addKeyListener(IMusicController c) {
    gui.addKeyListener(c);
  }

  /**
   * .
   *
   * @param c IMusicController that creates the MouseListener
   */
  @Override
  public void addMouseListener(IMusicController c) {
    gui.addMouseListener(c);
  }

  /**
   * .
   */
  @Override
  public void jumpHome() {
    gui.jumpHome();
    midi.jumpHome();
  }

  /**
   * .
   */
  @Override
  public void jumpEnd() {
    gui.jumpEnd();
    midi.jumpEnd();
  }

  /**
   * .
   */
  @Override
  public ConcreteGuiViewPanel getDisplayPanel() {
    return gui.getDisplayPanel();
  }

  /**
   * .
   */
  @Override
  public void addNotePopUp() {
    gui.addNotePopUp();
  }

  /**
   * .
   */
  @Override
  public void addRepeatPopUp() {
    gui.addRepeatPopUp();
  }

  /**
   * .
   *
   * @return String
   */
  @Override
  public String getOutput() {
    return gui.getOutput();
  }

  /**
   * .
   */
  @Override
  public void addPopUpError() {
    gui.addPopUpError();
  }

  /**
   * .
   *
   * @param m MidiViewImpl
   */
  @Override
  public void pauseMusic(MidiViewImpl m) {
    gui.pauseMusic(midi);
  }

  /**
   * .
   *
   * @return JScrollPane
   */
  @Override
  public JScrollPane getScroll() {
    return gui.getScroll();
  }

  /**
   * .
   *
   * @return int
   */
  @Override
  public int getFrameWidth() {
    return gui.getFrameWidth();
  }

  /**
   * .
   *
   * @return int
   */
  @Override
  public int getFrameSize() {
    return gui.getFrameSize();
  }

  /**
   * .
   *
   * @param model the given model to be made into one of three views.
   */
  @Override
  public void drawModel(ViewModel model) throws InvalidMidiDataException {
    Timer t = new Timer();
    TimerTask tt = new TimerTask() {
      // to scroll automatically as the composition plays
      @Override
      public void run() {
        JViewport vp = gui.getScroll().getViewport();
        int tick = midi.getTick();
        if (tick > 10) {
          gui.getDisplayPanel().setLineInit(false);
        }
        gui.getDisplayPanel().setxLine(tick);
        if (tick != 0 && (tick * (17 / 8)) % (gui.getFrameWidth() - 20) == 0) {
          vp.setViewPosition(new Point((tick * (17 / 8)), (int) vp.getViewPosition().getY()));
          gui.getDisplayPanel().repaint();
          gui.getDisplayPanel().revalidate();
        } else {
          vp.setViewPosition(new Point((int) (vp.getViewPosition().getX()),
                  (int) vp.getViewPosition().getY()));
          gui.getDisplayPanel().repaint();
          gui.getDisplayPanel().revalidate();
        }
      }
    };
    t.schedule(tt, 0, 1);
    gui.drawModel(model);
    if (!midi.getPause()) {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    midi.drawModel(model);
  }
}
