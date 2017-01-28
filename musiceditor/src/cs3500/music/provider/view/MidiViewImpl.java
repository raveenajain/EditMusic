package cs3500.music.provider.view;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Receiver;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Instrument;

import cs3500.music.model.ImmutableMusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.model.NoteType;

import static javax.sound.midi.Sequence.PPQ;

/**
 * Represents an IMusicView as an auditory output using MIDI.
 */
public class MidiViewImpl implements IMusicView {
  //changed visibility from private
  protected final Sequencer sequencer;
  protected Sequence sequence;
  protected int bpm;
  private final Synthesizer synth;
  private ImmutableMusicEditorModel mod;
  private List<List<Note>> pitchNumsStart;
  private List<List<Note>> pitchNumsEnd;

  /**
   * The Constructor for a MidiViewImpl.
   * Mod is set to null until setModel is called.
   */
  public MidiViewImpl() {
    try {
      this.sequencer = MidiSystem.getSequencer();
      this.sequence = new Sequence(PPQ, 0);
      this.synth = MidiSystem.getSynthesizer();
      this.sequencer.open();
      this.synth.open();
    } catch (Exception e) {
      throw new IllegalStateException("Can't create this MidiView");
    }

    this.pitchNumsStart = new ArrayList<>();
    this.pitchNumsEnd = new ArrayList<>();
  }

  /**
   * Constructor for testing. Takes in a mock sequencer.
   *
   * @param mock The mock sequencer
   */
  public MidiViewImpl(MockSequencer mock) {
    try {
      this.sequencer = mock;
      this.sequencer.open();
      this.synth = MidiSystem.getSynthesizer();
      this.synth.open();
    } catch (MidiUnavailableException e) {
      throw new IllegalArgumentException("Can't create this MidiView");
    }

    this.pitchNumsStart = new ArrayList<>();
    this.pitchNumsEnd = new ArrayList<>();

  }

  /**
   * Relevant classes and methods from the javax.sound.midi library:
   * <ul>
   * <li>{@link MidiSystem#getSynthesizer()}</li>
   * <li>{@link Synthesizer}
   * <ul>
   * <li>{@link Synthesizer#open()}</li>
   * <li>{@link Synthesizer#getReceiver()}</li>
   * <li>{@link Synthesizer#getChannels()}</li>
   * </ul>
   * </li>
   * <li>{@link Receiver}
   * <ul>
   * <li>{@link Receiver#send(MidiMessage, long)}</li>
   * <li>{@link Receiver#close()}</li>
   * </ul>
   * </li>
   * <li>{@link MidiMessage}</li>
   * <li>{@link ShortMessage}</li>
   * <li>{@link MidiChannel}
   * <ul>
   * <li>{@link MidiChannel#getProgram()}</li>
   * <li>{@link MidiChannel#programChange(int)}</li>
   * </ul>
   * </li>
   * </ul>
   *
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI"> https://en.wikipedia.org/wiki/General_MIDI
   * </a>
   */

  private void playNote() throws InvalidMidiDataException {

    Track[] tracks = new Track[16];
    for (int k = 0; k < 16; k++) {
      Track track = sequence.createTrack();
      tracks[k] = track;
    }

    Soundbank soundbank = synth.getDefaultSoundbank();
    Instrument[] instruments = soundbank.getInstruments();
    synth.loadAllInstruments(soundbank);

    for (int k = 0; k < 16; k++) {
      MidiMessage c = new ShortMessage(ShortMessage.PROGRAM_CHANGE, k,
              instruments[k].getPatch().getProgram(), 0);
      MidiEvent ce = new MidiEvent(c, -1);
      tracks[k].add(ce);
    }

    long time = sequencer.getMicrosecondLength();
    for (int k = 0; k < this.mod.numBeats(); k++) {
      for (Note note : this.pitchNumsStart.get(k)) {
        MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, note.instrument - 1,
                note.pitchNumber, note.volume);
        MidiEvent startE = new MidiEvent(start, time);
        tracks[note.instrument - 1].add(startE);
      }
      for (Note note : this.pitchNumsEnd.get(k)) {
        MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, note.instrument - 1,
                note.pitchNumber, note.volume);
        MidiEvent stopE = new MidiEvent(stop, time + mod.getTempo());
        tracks[note.instrument - 1].add(stopE);
      }
      time = time + mod.getTempo();
    }

    this.sequencer.setSequence(this.sequence);
    /*
    this.sequencer.start();
    this.sequencer.setTempoInBPM(bpm);

    while(this.sequencer.getMicrosecondLength()>this.sequencer.getMicrosecondPosition())

  {
    this.sequencer.getMicrosecondPosition()
  }

        this.sequencer.stop();
        this.sequencer.close();
        */
  }


  @Override
  public void initialize() {
    if (this.mod == null) {
      throw new IllegalStateException();
    }

    try {
      this.playNote();
    } catch (InvalidMidiDataException e) {
      throw new IllegalStateException();
    }
  }

  //determines if the note at the given index is the end of a note
  //returns false if the note continues into the next beat, true otherwise
  private boolean isLastNote(int beatIndex, int noteIndex, int pitch) {
    if (this.mod == null) {
      throw new IllegalStateException();
    }

    //if this note is the last in the list
    if (this.mod.numBeats() < beatIndex + 2) {
      return true;
    }

    //the list of pitchNumbers in the next beat
    List<Integer> nextNotePitches = this.mod.getBeatPitchNums(beatIndex + 1);
    //the list of noteTypes in the next beat
    List<NoteType> nextNoteTypes = this.mod.getNoteType(beatIndex + 1);

    //if the pitchNumbers in the next beat contain the given pitch
    if (nextNotePitches.contains(pitch)) {
      //the index of the pitch at the next beat
      int nextNoteIndex = nextNotePitches.indexOf(pitch);
      //if the noteType at the nextNoteIndex is SUSTAIN, return false
      return (nextNoteTypes.get(nextNoteIndex) != NoteType.SUSTAIN);
    } else {
      return true;
    }
  }

  //adds the proper values to pitchStarts and pitchEnds
  private void initStartEndLists() {
    if (this.mod == null) {
      throw new IllegalStateException();
    }

    for (int k = 0; k < mod.numBeats(); k++) {
      List<Integer> pitches = mod.getBeatPitchNums(k);
      List<NoteType> types = mod.getNoteType(k);
      List<Integer> instruments = mod.getInstruments(k);
      List<Integer> velocities = mod.getVolumes(k);

      List<Note> pitchStarts = new ArrayList<>();
      List<Note> pitchEnds = new ArrayList<>();
      for (int t = 0; t < types.size(); t++) {

        if (types.get(t) == NoteType.HEAD) {
          pitchStarts.add(new Note(k, pitches.get(t), NoteType.HEAD,
                  instruments.get(t), velocities.get(t)));
        }

        if (this.isLastNote(k, t, pitches.get(t))) {
          pitchEnds.add(new Note(k, pitches.get(t), NoteType.SUSTAIN,
                  instruments.get(t), velocities.get(t)));
        }
      }
      this.pitchNumsStart.add(pitchStarts);
      this.pitchNumsEnd.add(pitchEnds);
    }
  }

  @Override
  public void setModel(ImmutableMusicEditorModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Invalid model");
    }
    this.mod = model;
    this.bpm = 60000000 / mod.getTempo();

    try {
      this.sequence = new Sequence(Sequence.PPQ, this.mod.getTempo());
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }

    this.initStartEndLists();
  }

  /**
   * Restarts playback from the beginning of this sequence.
   */
  public void jumpToBegin() {
    this.sequencer.setMicrosecondPosition(0);
    try {
      this.sequencer.setSequence(this.sequence);
      this.sequencer.open();
      this.sequencer.start();
      this.sequencer.setTempoInBPM(this.bpm);
    } catch (Exception e) {
      e.printStackTrace();
    }


  }

  /**
   * Fast forwards playback to the end of this sequence.
   */
  public void jumpToEnd() {
    this.sequencer.setTickPosition(this.sequencer.getMicrosecondLength());
  }
}