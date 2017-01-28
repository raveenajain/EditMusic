package cs3500.music.view;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.ViewModel;

/**
 * To represent the View for the MusicEditor.
 */
public interface IMusicView {

  /**
   * To draw the given model in one of the three views.
   *
   * @param model the given model to be made into one of three views.
   */
  void drawModel(ViewModel model) throws InvalidMidiDataException;

  /**
   * Jumps to the beginning of the composition.
   */
  void jumpHome();

  /**
   * Jumps to the end of the composition.
   */
  void jumpEnd();

}
