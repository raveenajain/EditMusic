package cs3500.music.controller;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.ViewModel;
import cs3500.music.view.IMusicView;

/**
 * To represent the Controller component of the Music Editor.
 */
public interface IMusicController {

  /**
   * to get the controller's model field.
   *
   * @return the controller's model field
   */
  IMusicModel getModel();

  /**
   * to set the controller's MusicModel field to the given model.
   *
   * @param m the given model to set the field to
   */
  void setModel(IMusicModel m);

  /**
   * to create a viewModel using the controller's updated model field.
   *
   * @return a new viewModel
   */
  ViewModel createViewModel();

  /**
   * to create one of the three IMusicViews based on the string input.
   *
   * @param s input specifying which view to create
   * @return a new IMusicView
   */
  IMusicView createView(String s);

  /**
   * to create a keyboard handler with multiple possible key events.
   *
   * @return a keyboardhandler that allows for different key events
   */
  KeyboardHandler createKeyboardHandler();

  /**
   * to create a keyboard handler with mouse events.
   *
   * @return a mousehandler that allows for mouse events
   */
  MouseHandler createMouseHandler();
}
