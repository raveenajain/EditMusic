package cs3500.music.provider.view;

import java.io.IOException;

import cs3500.music.model.ImmutableMusicEditorModel;

/**
 * Represents an IMusicView as a textual output in the console.
 * Beat numbers run down the left side, pitches are named and run along the first line of text.
 * Noteheads are marked with an 'X', and note continuations are marked using '|'.
 */
public class TextView implements IMusicView {

  ImmutableMusicEditorModel mod;
  Appendable ap;

  /**
   * Creates a new TextView that prints to the console.
   */
  public TextView() {
    this.ap = System.out;
    return;
  }

  /**
   * Creates a new TextView that prints to the given appendable object.
    * @param ap Appendable object
   */
  public TextView(Appendable ap) {
    this.ap = ap;
  }


  @Override
  public void initialize() {
    try {
      ap.append(mod.printState());
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setModel(ImmutableMusicEditorModel model) {
    this.mod = model;
  }
}