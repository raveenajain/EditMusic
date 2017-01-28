package cs3500.music.tests;

import org.junit.Test;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;

import cs3500.music.view.MidiMock;

import static org.junit.Assert.assertEquals;

/**
 * To test the methods inside of the RecieverMock class.
 */
public class ReceiverMockTest {

  MidiMock mm;
  Receiver rm;
  Sequencer s;
  StringBuilder sb;

  {
    sb = new StringBuilder();
    mm = new MidiMock(sb);
    try {
      rm = mm.getReceiver();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    s = mm.getSequencer();
  }

  @Test
  public void testSendNoteOn() {
    try {
      rm.send(new ShortMessage(ShortMessage.NOTE_ON, 0, 4, 6), 8);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    assertEquals(sb.toString(), "Note, 4, 6" + "\n");
  }

  @Test
  public void testSendNoteOff() {
    try {
      rm.send(new ShortMessage(ShortMessage.NOTE_OFF, 5, 10, 15), 20);
      rm.send(new ShortMessage(ShortMessage.NOTE_OFF, 0, 1, 51), 2);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    assertEquals(sb.toString(), "Note, 10, 15" + "\n" + "Note, 1, 51" + "\n");
  }

  @Test
  public void testSendPC() {
    try {
      rm.send(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 1, 2, 3), 4);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    assertEquals(sb.toString(), "Instrument" + "\n");
  }

  @Test
  public void testSendMixed() {
    try {
      rm.send(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 1, 2, 3), 4);
      rm.send(new ShortMessage(ShortMessage.NOTE_OFF, 5, 10, 15), 20);
      rm.send(new ShortMessage(ShortMessage.NOTE_ON, 0, 4, 6), 8);
      rm.send(new ShortMessage(ShortMessage.NOTE_ON, 10, 9, 8), 7);
      rm.send(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 2, 2, 1), 8);
      rm.send(new ShortMessage(ShortMessage.NOTE_OFF, 9, 10, 11), 12);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    assertEquals(sb.toString(), "Instrument" + "\n" + "Note, 10, 15" + "\n" + "Note, 4, 6"
            + "\n" + "Note, 9, 8" + "\n" + "Instrument" + "\n" + "Note, 10, 11" + "\n");
  }

  @Test
  public void testSendInvalidMesage() {
    try {
      rm.send(new ShortMessage(ShortMessage.CHANNEL_PRESSURE, 2, 2, 1), 8);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    assertEquals(sb.toString(), "");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testClose() {
    rm.close();
  }
}
