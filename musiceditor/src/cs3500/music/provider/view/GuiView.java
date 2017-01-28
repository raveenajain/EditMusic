package cs3500.music.provider.view;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;

/**
 * .
 */
public interface GuiView extends IMusicView {
  void startScrollRight();

  void startScrollLeft();

  void jumpToBegin();

  void jumpToEnd();

  void addMouseHandler(MouseHandler actionListener);

  void addKeyHandler(KeyboardHandler keyboardHandler);

  String getInput();

  void resetFocus();
}
