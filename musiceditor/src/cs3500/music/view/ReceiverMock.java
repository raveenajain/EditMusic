package cs3500.music.view;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * To represent a mock Reciever for testing.
 */
public class ReceiverMock implements Receiver {

  private StringBuilder sb;

  /**
   * to construct a receiver mock for testing.
   *
   * @param sb the given string builder
   */
  public ReceiverMock(StringBuilder sb) {
    this.sb = sb;
  }

  /**
   * Adds the short message's two data parameters and indicates
   * whether it's a note or an instrument to the sb field.
   *
   * @param message   the given MidiMessage
   * @param timeStamp the given long
   */
  @Override
  public void send(MidiMessage message, long timeStamp) {
    ShortMessage shortM = (ShortMessage) message;
    if (shortM.getCommand() == ShortMessage.PROGRAM_CHANGE) {
      sb.append("Instrument" + "\n");
    } else if (shortM.getCommand() == ShortMessage.NOTE_ON
            || shortM.getCommand() == ShortMessage.NOTE_OFF) {
      sb.append("Note, " + shortM.getData1() + ", " + shortM.getData2() + "\n");
    }
  }

  @Override
  public void close() {
    throw new IllegalArgumentException("No.");
  }
}
