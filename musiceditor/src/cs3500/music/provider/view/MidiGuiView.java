package cs3500.music.provider.view;

import java.util.Objects;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;
import cs3500.music.model.ImmutableMusicEditorModel;

/**
 * Represents a a visual and audible format of a music editor model.
 */
public class MidiGuiView implements GuiView {
  private MidiViewImpl midi;
  private GuiViewFrame gui;

  /**
   * Constructs a new MidiGuiView with the given midi and gui views.
   * @param midi the given midi view
   * @param gui the given gui view
   */
  public MidiGuiView(MidiViewImpl midi, GuiViewFrame gui) {
    this.midi = Objects.requireNonNull(midi);
    this.gui = Objects.requireNonNull(gui);
  }

  @Override
  public void startScrollRight() {
    this.gui.startScrollRight();
  }

  @Override
  public void startScrollLeft() {
    this.gui.startScrollLeft();
  }

  @Override
  public void jumpToBegin() {
    this.midi.jumpToBegin();
    this.gui.jumpToBegin();
  }

  @Override
  public void jumpToEnd() {
    this.gui.jumpToEnd();
    this.midi.jumpToEnd();
  }

  @Override
  public void addMouseHandler(MouseHandler mouseListener) {
    this.gui.addMouseHandler(mouseListener);
  }


  @Override
  public void addKeyHandler(KeyboardHandler keyboardHandler) {
    this.gui.addKeyHandler(keyboardHandler);
  }

  @Override
  public String getInput() {
    return this.gui.getInput();
  }

  @Override
  public void resetFocus() {
    this.gui.resetFocus();
  }

  @Override
  public void initialize() {
    gui.initialize();
    midi.initialize();
    this.playMidi();
  }

  /**
   * Begins to play the midi track.
   */
  private void playMidi() {
    Objects.requireNonNull(this.midi);
    Objects.requireNonNull(this.midi.sequencer);

    this.midi.sequencer.start();
    this.midi.sequencer.setTempoInBPM(this.midi.bpm);

    while (this.midi.sequencer.getMicrosecondLength()
            > this.midi.sequencer.getMicrosecondPosition()) {
      this.gui.setTime(this.midi.sequencer.getMicrosecondPosition());
    }

    this.midi.sequencer.stop();
    this.midi.sequencer.close();

  }

  @Override
  public void setModel(ImmutableMusicEditorModel model) {
    midi.setModel(model);
    gui.setModel(model);
  }
}
