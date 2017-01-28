package cs3500.music.view;

import javax.swing.JScrollPane;

import cs3500.music.controller.IMusicController;

/**
 * To represent a GuiView.
 */
public interface GuiView extends IMusicView {

  /**
   * To add a KeyListener to the Gui.
   *
   * @param c IMusicController that creates the KeyListener
   */
  void addKeyListener(IMusicController c);

  /**
   * To add a MouseListener to the Gui.
   *
   * @param c IMusicController that creates the MouseListener
   */
  void addMouseListener(IMusicController c);

  /**
   * Gets the DisplayPanel.
   *
   * @return ConcreteGuiViewPanel
   */
  ConcreteGuiViewPanel getDisplayPanel();

  /**
   * Adds the PopUp that allows users to add in note information.
   */
  void addNotePopUp();

  /**
   * Adds the PopUp that allows users to add in repeat information.
   */
  void addRepeatPopUp();

  /**
   * Gets the output as a String.
   *
   * @return String
   */
  String getOutput();

  /**
   * Adds the PopUp that tells the user when they have added in incorrect note information.
   */
  void addPopUpError();

  /**
   * To pause the composition.
   *
   * @param m MidiViewImpl
   */
  void pauseMusic(MidiViewImpl m);

  /**
   * To get the ScrollPane.
   *
   * @return JScrollPane
   */
  JScrollPane getScroll();

  /**
   * To get the Frame width.
   *
   * @return int
   */
  int getFrameWidth();

  /**
   * To get the Frame size.
   *
   * @return int
   */
  int getFrameSize();

}
