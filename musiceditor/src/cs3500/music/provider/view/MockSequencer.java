package cs3500.music.provider.view;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.sound.midi.Transmitter;

/**
 * A Mock MidiDevice, used for testing.
 * Keeps a StringBuilder callLog that records information from shortMessages sent to this Sequencer.
 */
public class MockSequencer implements Sequencer {

  StringBuilder callLog;

  /**
   * Constructor for this MockSequencer class.
   */
  public MockSequencer() {
    this.callLog = new StringBuilder();
  }

  @Override
  public void setSequence(Sequence sequence) throws InvalidMidiDataException {
    Track[] tracks = sequence.getTracks();
    for (int k = 0; k < tracks.length; k++) {
      Track track = tracks[k];
      for (int j = 0; j < track.size(); j++) {
        MidiEvent event = track.get(j);
        try {
          ShortMessage mess = (ShortMessage) event.getMessage();
          this.callLog.append(event.getTick() + " " + mess.getCommand() + " "
                  + mess.getChannel() + " " + mess.getData1() + " " + mess.getData2() + " \n");
        } catch (ClassCastException e) {
          MidiMessage mess = event.getMessage();
          this.callLog.append("");
        }
      }
    }
  }

  @Override
  public void setSequence(InputStream stream) throws IOException, InvalidMidiDataException {
    throw new InvalidMidiDataException("NO");
  }

  @Override
  public Sequence getSequence() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public void start() {
    System.out.print(this.callLog.toString());
  }

  @Override
  public void stop() {
    return;
  }

  @Override
  public boolean isRunning() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public void startRecording() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public void stopRecording() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public boolean isRecording() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public void recordEnable(Track track, int channel) {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public void recordDisable(Track track) {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public float getTempoInBPM() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public void setTempoInBPM(float bpm) {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public float getTempoInMPQ() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public void setTempoInMPQ(float mpq) {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public void setTempoFactor(float factor) {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public float getTempoFactor() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public long getTickLength() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public long getTickPosition() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public void setTickPosition(long tick) {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public long getMicrosecondLength() {
    return 0;
  }

  @Override
  public Info getDeviceInfo() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public void open() throws MidiUnavailableException {
    return;
  }

  @Override
  public void close() {
    return;
  }

  @Override
  public boolean isOpen() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public long getMicrosecondPosition() {
    return 0;
  }

  @Override
  public int getMaxReceivers() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public int getMaxTransmitters() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public Receiver getReceiver() throws MidiUnavailableException {
    throw new IllegalArgumentException("NO");

  }

  @Override
  public List<Receiver> getReceivers() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public Transmitter getTransmitter() throws MidiUnavailableException {
    return null;
  }

  @Override
  public List<Transmitter> getTransmitters() {
    return null;
  }

  @Override
  public void setMicrosecondPosition(long microseconds) {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public void setMasterSyncMode(SyncMode sync) {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public SyncMode getMasterSyncMode() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public SyncMode[] getMasterSyncModes() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public void setSlaveSyncMode(SyncMode sync) {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public SyncMode getSlaveSyncMode() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public SyncMode[] getSlaveSyncModes() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public void setTrackMute(int track, boolean mute) {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public boolean getTrackMute(int track) {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public void setTrackSolo(int track, boolean solo) {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public boolean getTrackSolo(int track) {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public boolean addMetaEventListener(MetaEventListener listener) {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public void removeMetaEventListener(MetaEventListener listener) {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public int[] addControllerEventListener(ControllerEventListener listener, int[] controllers) {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public int[] removeControllerEventListener(ControllerEventListener listener, int[] controllers) {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public void setLoopStartPoint(long tick) {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public long getLoopStartPoint() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public void setLoopEndPoint(long tick) {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public long getLoopEndPoint() {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public void setLoopCount(int count) {
    throw new IllegalArgumentException("NO");
  }

  @Override
  public int getLoopCount() {
    throw new IllegalArgumentException("NO");
  }
}