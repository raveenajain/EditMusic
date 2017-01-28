package cs3500.music.view;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Transmitter;

import cs3500.music.model.Repeat;
import cs3500.music.model.ViewModel;

/**
 * To represent a mock MidiDevice for testing.
 */
public class MidiMock implements MidiDevice {

  // made default to because we were getting style errors
  StringBuilder sb;
  private int repeat;
  private Sequencer sequencer;
  private Receiver receiver;
  private Transmitter transmitter;
  private boolean pause;
  private static final int tickBeat = 10;
  private int tempo;
  int tickPos;

  /**
   * To construct a MidiMock object.
   *
   * @param sb the given stringBuilder
   */
  public MidiMock(StringBuilder sb) {
    this.sb = sb;
    Sequencer s = null;
    try {
      s = MidiSystem.getSequencer();
      transmitter = s.getTransmitter();
      transmitter.setReceiver(new ReceiverMock(sb));
      receiver = transmitter.getReceiver();
      s.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    sequencer = s;
  }

  @Override
  public Info getDeviceInfo() {
    throw new IllegalArgumentException("No.");
  }

  @Override
  public void open() throws MidiUnavailableException {
    throw new IllegalArgumentException("No.");
  }

  @Override
  public void close() {
    throw new IllegalArgumentException("No.");
  }

  @Override
  public boolean isOpen() {
    throw new IllegalArgumentException("No.");
  }

  @Override
  public long getMicrosecondPosition() {
    throw new IllegalArgumentException("No.");
  }

  @Override
  public int getMaxReceivers() {
    throw new IllegalArgumentException("No.");
  }

  @Override
  public int getMaxTransmitters() {
    throw new IllegalArgumentException("No.");
  }

  /**
   * to get the receiver.
   *
   * @return the receiver field.
   */
  @Override
  public Receiver getReceiver() throws MidiUnavailableException {
    return receiver;
  }

  @Override
  public List<Receiver> getReceivers() {
    throw new IllegalArgumentException("No.");
  }

  /**
   * to get the transmitter.
   *
   * @return the transmitter field.
   */
  @Override
  public Transmitter getTransmitter() throws MidiUnavailableException {
    return transmitter;
  }

  @Override
  public List<Transmitter> getTransmitters() {
    throw new IllegalArgumentException("No.");
  }

  /**
   * to get the sequencer.
   *
   * @return the sequencer field
   */
  public Sequencer getSequencer() {
    return sequencer;
  }

  /**
   * .
   */
  public void jumpHome() {
    if (sequencer.isRunning()) {
      sequencer.stop();
    }
    sequencer.setTickPosition(0);
    sequencer.start();
    sequencer.setTempoInMPQ(tempo);
  }

  /**
   * .
   */
  public void jumpEnd() {
    if (sequencer.isRunning()) {
      sequencer.stop();
    }
    sequencer.setTickPosition(50);
    sequencer.start();
    sequencer.setTempoInMPQ(tempo);
  }

  /**
   * .
   */
  public void pauseMusic() {
    pause = !pause;
    if (pause) {
      sequencer.stop();
    } else {
      sequencer.start();
      sequencer.setTempoInMPQ(tempo);
    }
  }

  /**
   * .
   *
   * @return int
   */
  public int getTick() {
    tickPos = (int) sequencer.getTickPosition();
    return tickPos;
  }

  /**
   * .
   *
   * @param m viewModel
   */
  public void repeatMusic(ViewModel m) {
    repeat = m.getRepeats().get(0).getStart();
  }

  /**
   * .
   *
   * @return repeat
   */
  public int getRepeat() {
    return repeat;
  }

  /**
   * .
   *
   * @return boolean
   */
  public boolean getPause() {
    return pause;
  }
}
