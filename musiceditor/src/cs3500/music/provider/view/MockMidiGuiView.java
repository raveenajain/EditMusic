package cs3500.music.provider.view;

import java.io.IOException;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;
import cs3500.music.model.ImmutableMusicEditorModel;

/**
 * A Mock MidiGuiView class.
 * Writes to a stored Appendable object instead of invoking the wanted behavior for method calls.
 */
public class MockMidiGuiView implements GuiView {

  Appendable ap;

  MockMidiGuiView(MidiViewImpl midi, GuiViewFrame gui, Appendable ap) {
    this.ap = ap;
  }

  @Override
  public void startScrollRight() {
    try {
      this.ap.append("scrollingRight ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void startScrollLeft() {
    try {
      this.ap.append("scrollingLeft ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void jumpToBegin() {
    try {
      this.ap.append("jumpToBeginning ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void jumpToEnd() {
    try {
      this.ap.append("jumpToEnd ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void addMouseHandler(MouseHandler actionListener) {
    try {
      this.ap.append("addingMouseHandler ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void addKeyHandler(KeyboardHandler keyboardHandler) {
    try {
      this.ap.append("addingKeyHandler ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getInput() {
    try {
      this.ap.append("gettingInput ");
    } catch (IOException e) {
      e.printStackTrace();
    }

    return this.ap.toString();
  }

  @Override
  public void resetFocus() {
    // needed to add this after providers sent us new gui rendering code**************************
  }

  @Override
  public void initialize() {
    try {
      this.ap.append("initializing ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setModel(ImmutableMusicEditorModel model) {
    try {
      this.ap.append("setModel ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}