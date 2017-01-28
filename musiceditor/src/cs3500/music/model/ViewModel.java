package cs3500.music.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A read only version of the model that does not allow for mutation of the model.
 * Any methods that mutated the model in MusicModel now throw exceptions.
 */
public class ViewModel implements IMusicModel {

  private IMusicModel model;

  public ViewModel() {
    // empty constructor
  }

  /**
   * To construct a view model object.
   *
   * @param model IMusicModel containing the data this view needs
   */
  public ViewModel(IMusicModel model) {
    this.model = model;
  }

  /**
   * .
   *
   * @param n the MusicNote to be added
   * @throws IllegalArgumentException because the model cannot be changed
   */
  @Override
  public void addNote(MusicNote n) {
    throw new IllegalArgumentException("Not Allowed.");
  }

  /**
   * .
   *
   * @param an the list of MusicNotes to be added
   * @throws IllegalArgumentException because the model cannot be changed
   */
  @Override
  public void addNotes(ArrayList<MusicNote> an) {
    throw new IllegalArgumentException("Not Allowed.");
  }

  /**
   * .
   *
   * @return a list of all the notes that make up the model
   */
  @Override
  public ArrayList<MusicNote> allNotes() {
    return model.allNotes();
  }

  /**
   * .
   *
   * @param n the note to be removed
   * @throws IllegalArgumentException because the model cannot be changed
   */
  @Override
  public void removeNote(MusicNote n) {
    throw new IllegalArgumentException("Not Allowed.");
  }

  /**
   * .
   *
   * @param an the list of MusicNotes to be removed
   * @throws IllegalArgumentException because the model cannot be changed
   */
  @Override
  public void removeNotes(ArrayList<MusicNote> an) {
    throw new IllegalArgumentException("Not Allowed.");
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
   * @throws IllegalArgumentException because the model cannot be changed
   */
  @Override
  public void editNote(MusicNote n, Pitch p, int oct, int d, int sb, int v, int i) {
    throw new IllegalArgumentException("Not Allowed.");
  }

  /**
   * .
   *
   * @return the last beat value
   */
  @Override
  public int getLastBeat() {
    return model.getLastBeat();
  }

  /**
   * .
   *
   * @param m the model to be merged with this model
   * @throws IllegalArgumentException because the model cannot be changed
   */
  @Override
  public void mergeModels(MusicModel m) {
    throw new IllegalArgumentException("Not Allowed.");
  }

  /**
   * .
   *
   * @param m the model to be played after this model
   * @throws IllegalArgumentException because the model cannot be changed
   */
  @Override
  public void addModelAtEnd(MusicModel m) {
    throw new IllegalArgumentException("Not Allowed.");
  }

  @Override
  public ArrayList<MusicNote> getLowestThenHighest() {
    return model.getLowestThenHighest();
  }

  /**
   * .
   *
   * @return the beatsPerMeasure value
   */
  @Override
  public int getBeatsPerMeasure() {
    return model.getBeatsPerMeasure();
  }

  /**
   * .
   *
   * @return the notelist map
   */
  @Override
  public HashMap<Integer, ArrayList<MusicNote>> getNotelist() {
    return model.getNotelist();
  }

  /**
   * .
   *
   * @return the tempo value
   */
  @Override
  public int getTempo() {
    return model.getTempo();
  }

  @Override
  public ArrayList<Repeat> getRepeats() {
    return model.getRepeats();
  }

  /**
   * .
   *
   * @param notes this models new notelist
   * @throws IllegalArgumentException because the model cannot be changed
   */
  @Override
  public void setNoteList(ArrayList<MusicNote> notes) {
    throw new IllegalArgumentException("Not Allowed.");
  }

  /**
   * .
   *
   * @param t this models new tempo
   * @throws IllegalArgumentException because the model cannot be changed
   */
  @Override
  public void setTempo(int t) {
    throw new IllegalArgumentException("Not Allowed.");
  }

  @Override
  public void setRepeats(ArrayList<Repeat> r) {
    model.setRepeats(r);
  }

  @Override
  public boolean checkNestedRepeats(Repeat r) {
    return model.checkNestedRepeats(r);
  }
}
