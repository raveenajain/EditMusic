package cs3500.music.view;

import java.util.ArrayList;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicNote;
import cs3500.music.model.Pitch;
import cs3500.music.model.ViewModel;

/**
 * To represent the console view for the MusicEditor.
 */
public class ConsoleViewImpl implements IMusicView {

  Appendable ap;

  /**
   * To construct a music view object.
   */
  public ConsoleViewImpl() {
    // empty constructor
  }

  /**
   * to convert the string of lowest to highest notes to an ArrayList.
   * each set of five characters makes up one String element in the list.
   *
   * @return an ArrayList version of the string of lowest to highest notes
   */
  public ArrayList<String> listOfPO(IMusicModel mm) {
    String s = printLowestToHighest(mm).toString();
    ArrayList<String> a = new ArrayList<String>();
    for (int i = 0; i < s.length(); i = i + 5) {
      a.add(s.substring(i, i + 5));
    }
    return a;
  }

  /**
   * to print out all of the pitch/octave combinations starting from
   * the lowest note and going to the highest one in this model.
   *
   * @return a string of all of the pitch/octave combinations from lowest to highest
   */
  private String printLowestToHighest(IMusicModel mm) {
    StringBuilder acc = new StringBuilder();
    String s;
    int startPitch = mm.getLowestThenHighest().get(0).getPitch().ordinal();
    int startOctave = mm.getLowestThenHighest().get(0).getOctave();
    int endPitch = mm.getLowestThenHighest().get(1).getPitch().ordinal();
    int endOctave = mm.getLowestThenHighest().get(1).getOctave();
    for (int i = startOctave; i <= endOctave; i = i + 1) {
      for (int j = 0; j < 12; j = j + 1) {
        if (j == 0 && i == startOctave) {
          j = startPitch;
        }
        if (i == endOctave && j == endPitch) {
          s = Pitch.values()[j].toString() + i;
          if (s.length() == 2) {
            acc.append("  " + s + " ");
          } else if (s.length() == 3) {
            acc.append(" " + s + " ");
          } else if (s.length() == 4) {
            acc.append(" " + s);
          } else if (s.length() == 5) {
            acc.append(s);
          }
          break;
        }
        s = Pitch.values()[j].toString() + i;
        if (s.length() == 2) {
          acc.append("  " + s + " ");
        } else if (s.length() == 3) {
          acc.append(" " + s + " ");
        } else if (s.length() == 4) {
          acc.append(" " + s);
        } else if (s.length() == 5) {
          acc.append(s);
        }
      }
    }
    return acc.toString();
  }

  /**
   * does a note (based on the pitch/octave combination) exist at the given int.
   *
   * @param s the pitch/octave combination
   * @param i the beat the note could be hashed to
   * @return true if a note exists at the given string and false if the note does not
   */
  private boolean existingNote(IMusicModel mm, String s, int i) {
    ArrayList<MusicNote> a = new ArrayList<MusicNote>();
    ArrayList<MusicNote> all = mm.allNotes();
    for (MusicNote m : all) {
      if (s.contains(m.combinePitchOctave())) {
        a.add(m);
      }
    }
    for (MusicNote m : a) {
      if (i >= m.getStartBeat() && i < m.getStartBeat() + m.getDuration()) {
        return true;
      }
    }
    return false;
  }

  /**
   * does the note that exists at the given string start at the given int.
   *
   * @param s the pitch/octave combination
   * @param i the beat the note could start at
   * @return true if the note starts at the int and false if it does not
   */
  private boolean isStartBeat(IMusicModel mm, String s, int i) {
    for (MusicNote m : mm.allNotes()) {
      if (s.contains(m.combinePitchOctave())) {
        if (i == m.getStartBeat()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * contains the pitch/octave combination, the number of beats, the start
   * of a note represented as an "X", the rest of a note represented
   * with a "|", and empty spaces where a note does not exist.
   *
   * @param model the given model to be made into one of three views.
   */
  @Override
  public void drawModel(ViewModel model) {
    StringBuilder acc = new StringBuilder("");
    if (this == null || model.allNotes().isEmpty()) {
      acc.append("No beat(s) to drop");
    } else {
      for (int spaceNum = 0; spaceNum < Integer.toString(model.getLastBeat()).length();
           spaceNum = spaceNum + 1) {
        acc.append(" ");
      }
      acc.append(printLowestToHighest(model) + "\n");
      for (int i = 0; i <= model.getLastBeat(); i = i + 1) {
        acc.append(String.format("%" + Integer.toString(model.getLastBeat()).length() + "s",
                Integer.toString(i)));
        for (String s : listOfPO(model)) {
          if (existingNote(model, s, i)) {
            if (isStartBeat(model, s, i)) {
              acc.append("  X  ");
            } else {
              acc.append("  |  ");
            }
          } else {
            acc.append("     ");
          }
        }
        acc.append("\n");
      }
    }
    ap = acc;
    System.out.println(ap);
  }

  /**
   * .
   *
   * @throws IllegalArgumentException if this method is called for the console view
   */
  @Override
  public void jumpHome() {
    throw new IllegalArgumentException("Not For This Class.");
  }

  /**
   * .
   *
   * @throws IllegalArgumentException if this method is called for the console view
   */
  @Override
  public void jumpEnd() {
    throw new IllegalArgumentException("Not For This Class.");
  }
}
