package cs3500.music.provider.view;

import cs3500.music.model.ImmutableMusicEditorModel;

/**
 * The view interface.
 */
public interface IMusicView {

  /**
   * Initializes the view.
   */
  void initialize();

  /**
   * Sets this view's model.
   * @param model the view's model
   */
  void setModel(ImmutableMusicEditorModel model);
}
