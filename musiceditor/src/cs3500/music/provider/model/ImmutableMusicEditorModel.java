package cs3500.music.model;

import java.util.List;

/**
 * The interface for a MusicEditorModel.
 * Models a song as a collection of notes (containing pitch, octave, duration, instrument, volume)
 * which begin at specific beats (time steps). Provides methods for accessing notes only.
 */
public interface ImmutableMusicEditorModel {
  /**
   * Returns a list of notes playing at the given beat of the song.
   * @param beat the current beat of the song
   * @return List of NoteTypes
   */
  List<NoteType> getNoteType(int beat);

  /**
   * Returns a list of the pitchNumbers of the notes playing at the given beat.
   * @param beat the current beat of the song
   * @return Integer list of pitch numbers.
   */
  List<Integer> getBeatPitchNums(int beat);

  /**
   * Returns a list of the pitchNumbers of the notes in this piece.
   * @return Integer list of pitch numbers.
   */
  List<Integer> getPitchNums();

  /**
   * Returns a list of the instruments of the notes playing at the given beat.
   * @param beat the current beat of the song
   * @return List of Integer
   */
  List<Integer> getInstruments(int beat);

  /**
   * Returns a list of the instruments of the notes playing at the given beat.
   * @param beat the current beat of the song
   * @return List of Integer
   */
  List<Integer> getVolumes(int beat);

  /**
   * Prints out a string representation of the current state of the model.
   * @return String representation
   */
  String printState();

  /**
   * Returns the length of the song.
   * @return the number of beats in this song
   */
  int numBeats();

  /**
   * Gets this Model's tempo.
   * @return this model's tempo
   */
  int getTempo();

  /**
   * Represents a pitchNumber as a String.
   * @param i The given pitchNumber
   * @return A string representing i
   */
  String pitchNumtoString(int i);

}
