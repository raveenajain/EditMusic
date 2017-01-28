package cs3500.music.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * To represent the model adapter for the Music Editor that allows the providers code to be used.
 */
public class ModelAdapter implements cs3500.music.model.ImmutableMusicEditorModel, IMusicModel {

  private final IMusicModel adaptee;

  /**
   * constructor for the ModelAdapter.
   *
   * @param mAdapt the IMusicModel being used to make the providers code run
   */
  public ModelAdapter(IMusicModel mAdapt) {
    this.adaptee = mAdapt;
    if (adaptee == null) {
      throw new IllegalArgumentException("Null Model");
    }
  }

  /**
   * .
   *
   * @param beat the current beat of the song
   * @return List of NoteTypes
   */
  @Override
  public List<NoteType> getNoteType(int beat) {
    List<NoteType> typeList = new ArrayList<NoteType>();
    if (adaptee.getNotelist().containsKey(beat)) {
      List<MusicNote> nList = adaptee.getNotelist().get(beat);
      for (MusicNote n : nList) {
        if (n.isStartOfNote(beat)) {
          typeList.add(NoteType.HEAD);
        } else {
          typeList.add(NoteType.SUSTAIN);
        }
      }
    }
    return typeList;
  }

  /**
   * .
   *
   * @param beat the current beat of the song
   * @return Integer list of pitch numbers.
   */
  @Override
  public List<Integer> getBeatPitchNums(int beat) {
    List<Integer> intList = new ArrayList<Integer>();
    if (adaptee.getNotelist().containsKey(beat)) {
      List<MusicNote> nList = adaptee.getNotelist().get(beat);
      int i;
      for (MusicNote n : nList) {
        i = 12 * n.getOctave() + n.getPitch().ordinal();
        intList.add(i);
      }
    }
    return intList;
  }

  /**
   * .
   *
   * @return Integer list of pitch numbers.
   */
  @Override
  public List<Integer> getPitchNums() {
    List<MusicNote> nList = adaptee.allNotes();
    List<Integer> intList = new ArrayList<Integer>();
    int i;
    for (MusicNote n : nList) {
      for (int j = 0; j < n.getDuration(); j++) {
        i = 12 * n.getOctave() + n.getPitch().ordinal();
        intList.add(i);
      }
    }
    return intList;
  }

  /**
   * .
   *
   * @param beat the current beat of the song
   * @return List of Integer
   */
  @Override
  public List<Integer> getInstruments(int beat) {
    List<Integer> intList = new ArrayList<Integer>();
    if (adaptee.getNotelist().containsKey(beat)) {
      List<MusicNote> nList = adaptee.getNotelist().get(beat);
      for (MusicNote n : nList) {
        intList.add(n.getInstrument());
      }
    }
    return intList;
  }

  /**
   * .
   *
   * @param beat the current beat of the song
   * @return List of Integer
   */
  @Override
  public List<Integer> getVolumes(int beat) {
    List<Integer> intList = new ArrayList<Integer>();
    if (adaptee.getNotelist().containsKey(beat)) {
      List<MusicNote> nList = adaptee.getNotelist().get(beat);
      for (MusicNote n : nList) {
        intList.add(n.getVolume());
      }
    }
    return intList;
  }

  /**
   * we cannot implement this code as we were told not to have a method that creates the intended
   * console output in our model; it should only be in our console view.
   *
   * @throws IllegalArgumentException for the console output
   */
  @Override
  public String printState() {
    throw new IllegalArgumentException("Not for Provider Code.");
  }

  /**
   * .
   *
   * @return the number of beats in this song
   */
  @Override
  public int numBeats() {
    return adaptee.getLastBeat();
  }

  // -----------

  /**
   * .
   *
   * @param n the MusicNote to be added
   */
  @Override
  public void addNote(MusicNote n) {
    adaptee.addNote(n);
  }

  /**
   * .
   *
   * @param an the list of MusicNotes to be added
   */
  @Override
  public void addNotes(ArrayList<MusicNote> an) {
    adaptee.addNotes(an);
  }

  /**
   * .
   *
   * @return a list of all the notes that make up the model
   */
  @Override
  public ArrayList<MusicNote> allNotes() {
    return adaptee.allNotes();
  }

  /**
   * .
   *
   * @param n the note to be removed
   */
  @Override
  public void removeNote(MusicNote n) {
    adaptee.removeNote(n);
  }

  /**
   * .
   *
   * @param an the list of MusicNotes to be removed
   */
  @Override
  public void removeNotes(ArrayList<MusicNote> an) {
    adaptee.removeNotes(an);
  }

  /**
   * .
   *
   * @param n   the note in this model to be changed
   * @param p   the new pitch
   * @param oct the new octave
   * @param d   the new duration
   * @param sb  the new startBeat
   * @param v   the new volume
   * @param i   the new instrument
   */
  @Override
  public void editNote(MusicNote n, Pitch p, int oct, int d, int sb, int v, int i) {
    adaptee.editNote(n, p, oct, d, sb, v, i);
  }

  /**
   * .
   *
   * @return the last beat value
   */
  @Override
  public int getLastBeat() {
    return adaptee.getLastBeat();
  }

  /**
   * .
   *
   * @param m the model to be merged with this model
   */
  @Override
  public void mergeModels(MusicModel m) {
    adaptee.mergeModels(m);
  }

  /**
   * .
   *
   * @param m the model to be played after this model
   */
  @Override
  public void addModelAtEnd(MusicModel m) {
    adaptee.addModelAtEnd(m);
  }

  /**
   * .
   *
   * @return a two element list of the lowest and highest notes
   */
  @Override
  public ArrayList<MusicNote> getLowestThenHighest() {
    return adaptee.getLowestThenHighest();
  }

  /**
   * .
   *
   * @return the beatsPerMeasure value
   */
  @Override
  public int getBeatsPerMeasure() {
    return adaptee.getBeatsPerMeasure();
  }

  /**
   * .
   *
   * @return the notelist map
   */
  @Override
  public HashMap<Integer, ArrayList<MusicNote>> getNotelist() {
    return adaptee.getNotelist();
  }

  /**
   * .
   *
   * @return the tempo value
   */
  @Override
  public int getTempo() {
    return adaptee.getTempo();
  }

  /**
   * .
   *
   * @return the repeats value
   */
  @Override
  public ArrayList<Repeat> getRepeats() {
    throw new IllegalArgumentException("Not For Provider.");
  }

  /**
   * .
   *
   * @param notes this models new notelist
   */
  @Override
  public void setNoteList(ArrayList<MusicNote> notes) {
    adaptee.setNoteList(notes);
  }

  /**
   * .
   *
   * @param t this models new tempo
   */
  @Override
  public void setTempo(int t) {
    adaptee.setTempo(t);
  }

  /**
   * .
   *
   * @param r this models new repeats
   */
  @Override
  public void setRepeats(ArrayList<Repeat> r) {
    throw new IllegalArgumentException("Not For Provider.");
  }

  /**
   * .
   *
   * @param r Repeat
   * @return boolean
   */
  @Override
  public boolean checkNestedRepeats(Repeat r) {
    throw new IllegalArgumentException("Not For Provider.");
  }

  /**
   * .
   *
   * @param i The given pitchNumber
   * @return A string representing i
   */
  @Override
  public String pitchNumtoString(int i) {
    Pitch p = Pitch.values()[i % 12];
    int oct = i / 12;
    return p.toString() + oct;
  }
}