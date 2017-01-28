package cs3500.music.view;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.JScrollPane;

import cs3500.music.controller.IMusicController;
import cs3500.music.model.ModelAdapter;
import cs3500.music.model.ViewModel;

/**
 * To represent a GuiViewAdapter to get the providers code to work.
 */
public class GuiViewAdapter implements GuiView {

  cs3500.music.provider.view.GuiView adaptee;

  /**
   * to construct a GuiViewAdapter.
   *
   * @param adaptee the providers guiview that our code is being adapted to
   */
  public GuiViewAdapter(cs3500.music.provider.view.GuiView adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * .
   *
   * @param c IMusicController that creates the KeyListener
   */
  @Override
  public void addKeyListener(IMusicController c) {
    adaptee.addKeyHandler(c.createKeyboardHandler());
  }

  /**
   * .
   *
   * @param c IMusicController that creates the MouseListener
   */
  @Override
  public void addMouseListener(IMusicController c) {
    adaptee.addMouseHandler(c.createMouseHandler());
  }

  /**
   * .
   *
   * @throws IllegalArgumentException as the provider does not have a similar method
   */
  @Override
  public ConcreteGuiViewPanel getDisplayPanel() {
    throw new IllegalArgumentException("Not for Provider.");
  }

  /**
   * .
   *
   * @throws IllegalArgumentException as the provider does not have a similar method
   */
  @Override
  public void addNotePopUp() {
    throw new IllegalArgumentException("Not for Provider.");
  }

  /**
   * .
   */
  @Override
  public void addRepeatPopUp() {
    throw new IllegalArgumentException("Not for Provider.");
  }

  /**
   * .
   *
   * @return String
   */
  @Override
  public String getOutput() {
    return adaptee.getInput();
  }

  /**
   * .
   *
   * @throws IllegalArgumentException as the provider does not have a similar method
   */
  @Override
  public void addPopUpError() {
    throw new IllegalArgumentException("Not for Provider.");
  }

  /**
   * .
   *
   * @throws IllegalArgumentException as the provider does not have a similar method
   */
  @Override
  public void pauseMusic(MidiViewImpl m) {
    throw new IllegalArgumentException("Not for Provider.");
  }

  /**
   * .
   *
   * @return JScrollPane
   */
  @Override
  public JScrollPane getScroll() {
    throw new IllegalArgumentException("Not for Provider.");
  }

  /**
   * .
   *
   * @throws IllegalArgumentException as the provider does not have a similar method
   */
  @Override
  public int getFrameWidth() {
    throw new IllegalArgumentException("Not for Provider.");
  }

  /**
   * .
   *
   * @throws IllegalArgumentException as the provider does not have a similar method
   */
  @Override
  public int getFrameSize() {
    throw new IllegalArgumentException("Not for Provider.");
  }

  /**
   * .
   *
   * @param model the given model to be made into one of three views.
   */
  @Override
  public void drawModel(ViewModel model) throws InvalidMidiDataException {
    adaptee.setModel(new ModelAdapter(model));
    adaptee.initialize();
  }

  /**
   * .
   */
  @Override
  public void jumpHome() {
    adaptee.jumpToBegin();
  }

  /**
   * .
   */
  @Override
  public void jumpEnd() {
    adaptee.jumpToEnd();
  }
}
