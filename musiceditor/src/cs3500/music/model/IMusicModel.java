package cs3500.music.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * To represent the Model component of the MusicEditor.
 */
public interface IMusicModel {

  /**
   * to add the given MusicNote to this model.
   *
   * @param n the MusicNote to be added
   * @throws IllegalArgumentException if the note is invalid
   */
  void addNote(MusicNote n);

  /**
   * to add each note in the given list to this model.
   *
   * @param an the list of MusicNotes to be added
   * @throws IllegalArgumentException if a note in the list is invalid
   */
  void addNotes(ArrayList<MusicNote> an);

  /**
   * To return all of the notes in this model as an ArrayList.
   *
   * @return a list of all the notes that make up the model
   */
  ArrayList<MusicNote> allNotes();

  /**
   * to remove the given MusicNote from this model.
   * removes the key value if it is now empty.
   *
   * @param n the note to be removed
   * @throws IllegalArgumentException if the note is invalid or if it does not exist in the
   *                                  noteList
   */
  void removeNote(MusicNote n);

  /**
   * to remove each note in the given list from this model.
   *
   * @param an the list of MusicNotes to be removed
   * @throws IllegalArgumentException if a note is invalid or if it does not exist in the noteList
   */
  void removeNotes(ArrayList<MusicNote> an);

  /**
   * to remove the given note in this model based on the given
   * parameters and add back a note with the given parameters.
   *
   * @param n   the note in this model to be changed
   * @param p   the new pitch
   * @param oct the new octave
   * @param d   the new duration
   * @param sb  the new startBeat
   * @param v   the new volume
   * @param i   the new instrument
   * @throws IllegalArgumentException if the note is invalid or if it does not exist in the
   *                                  noteList
   */
  void editNote(MusicNote n, Pitch p, int oct, int d, int sb, int v, int i);

  /**
   * to get the last/largest beat that exists in this model.
   *
   * @return the last beat value
   */
  int getLastBeat();

  /**
   * to merge the given model with this model so that the two will play at the same time.
   * the new MusicModel keeps this model's beatsPerMeasure.
   *
   * @param m the model to be merged with this model
   */
  void mergeModels(MusicModel m);

  /**
   * to add the given model to the end of this model (to start after this model's last beat).
   * the new MusicModel keeps this model's beatsPerMeasure.
   *
   * @param m the model to be played after this model
   */
  void addModelAtEnd(MusicModel m);

  /**
   * to get the lowest and highest pitches and octaves.
   * the first element in the list is the lowest note (has the lowest pitch and octave).
   * the second element in the list is the highest note (has the highest pitch and octave).
   *
   * @return a two element list of the lowest and highest notes
   */
  ArrayList<MusicNote> getLowestThenHighest();

  /**
   * to get this models beatsPerMeasure.
   *
   * @return the beatsPerMeasure value
   */
  int getBeatsPerMeasure();

  /**
   * to get this models notelist.
   *
   * @return the notelist map
   */
  HashMap<Integer, ArrayList<MusicNote>> getNotelist();

  /**
   * to get this models tempo.
   *
   * @return the tempo value
   */
  int getTempo();

  /**
   * to get the model's repeat value.
   *
   * @return the repeats value
   */
  ArrayList<Repeat> getRepeats();

  /**
   * to set this models noteList to the given list.
   *
   * @param notes this models new notelist
   */
  void setNoteList(ArrayList<MusicNote> notes);

  /**
   * to set this models tempo to the given int.
   *
   * @param t this models new tempo
   */
  void setTempo(int t);

  /**
   * to set the model's repeat field to the given.
   *
   * @param r this models new repeats
   */
  void setRepeats(ArrayList<Repeat> r);

  /**
   * returns true if not allowed to add repeat.
   *
   * @param r Repeat
   * @return boolean
   */
  boolean checkNestedRepeats(Repeat r);
}