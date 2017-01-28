package cs3500.music.view;


import java.awt.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JPanel;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicNote;
import cs3500.music.model.Pitch;
import cs3500.music.model.Repeat;


/**
 * A view that draws the music piece.
 */
public class ConcreteGuiViewPanel extends JPanel {
  private IMusicModel model;
  private static final int xPush = 60;
  private static final int yPush = 30;
  private static final int constant = 20;
  int width;
  int height;
  int xLine;
  boolean lineInit = true;

  /**
   * Constructs a {@Code ConcreteGuiViewPanel} object.
   *
   * @param width  width of the panel
   * @param height height of the panel
   */
  public ConcreteGuiViewPanel(int width, int height) {
    this.width = width;
    this.height = height;
  }

  /**
   * to set the model field to the given model.
   *
   * @param m the given model the field will be set to
   * @throws IllegalArgumentException if the given model is null
   */
  public void setModel(IMusicModel m) {
    if (m != null) {
      this.model = m;
      repaint();
      revalidate();
    } else {
      throw new IllegalArgumentException("Null Model.");
    }
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
   * Draws the pitch/octaves, lines, and notes (represented as rectangles), and measures.
   *
   * @param g the given graphics
   */
  @Override
  public void paintComponent(Graphics g) {
    if (!model.getNotelist().isEmpty()) {
      ArrayList<String> a = listOfPO(model);
      Collections.reverse(a);
      ArrayList<String> aNoSpace = new ArrayList<String>();
      g.setColor(new Color(11, 192, 203));
      setBackground(new Color(54, 111, 119));
      int bpm = model.getBeatsPerMeasure();
      super.paintComponent(g);
      repaint();
      revalidate();
      // draws the pitch octave combos
      int yString = yPush + 15;
      for (String s : a) {
        String sNew = s.replace(" ", "");
        aNoSpace.add(sNew);
        g.drawString(sNew, 5, yString);
        yString = yString + 20;
      }
      // draw vertical lines
      for (int i = 0; i < model.getLastBeat(); i++) {
        if (i % Math.pow(bpm, 2) == 0) {
          g.drawString(Integer.toString(i), (xPush - constant / 2) + constant * i, yPush
                  - constant / 2);
        }
        if (i % bpm == 0) {
          g.drawLine(xPush + constant * i, yPush, xPush + constant * i, yPush + aNoSpace.size()
                  * constant);
        }
      }
      g.drawLine(xPush + constant * (((model.getLastBeat() / bpm) + 1) * bpm), yPush, xPush
              + constant * (((model.getLastBeat() / bpm) + 1) * bpm), yPush + aNoSpace.size()
              * constant);
      g.drawString(Integer.toString(((model.getLastBeat() / bpm) + 1) * bpm), (xPush - constant / 2)
              + constant * (((model.getLastBeat() / bpm) + 1) * bpm), yPush - constant / 2);
      // draw horizontal lines
      for (int i = 0; i <= aNoSpace.size(); i++) {
        g.drawLine(xPush, yPush + constant * i, xPush + (((model.getLastBeat() / bpm) + 1) * bpm)
                * constant, yPush + constant * i);
      }
      // draw music notes
      HashMap<Integer, ArrayList<MusicNote>> hm = model.getNotelist();
      for (Integer k : hm.keySet()) {
        for (MusicNote n : hm.get(k)) {
          if (n.isStartOfNote(k)) {
            g.setColor(new Color(217, 116, 176));
          } else {
            g.setColor(new Color(216, 178, 74));
          }
          g.fillRect(xPush + k * constant, yPush + aNoSpace.indexOf(n.combinePitchOctave())
                  * constant, constant, constant);
        }
      }
      // draw repeats
      if (!model.getRepeats().isEmpty()) {
        ArrayList<Repeat> rList = model.getRepeats();
        for (Repeat r : rList) {
          g.setColor(new Color(11, 192, 203));
          g.fillRect(xPush + constant * r.getStart(), yPush, 8, aNoSpace.size() * constant);
          g.fillRect(xPush + constant * r.getEnd(), yPush, 8, aNoSpace.size() * constant);
        }
      }
      // draw animated line
      g.setColor(Color.WHITE);
      if (lineInit) {
        g.drawLine(xPush, yPush, xPush, yPush + aNoSpace.size() * constant);
      } else {
        g.drawLine(((xLine + bpm / 2) * constant), yPush, ((xLine + bpm / 2) * constant),
                yPush + aNoSpace.size() * constant);
      }
      width = model.getLastBeat() * constant + xPush + 40;
      height = yPush + aNoSpace.size() * 2 * constant;
      setPreferredSize(new Dimension(width, height));
    }
  }

  /**
   * To get the start beat position of the note at the given int.
   *
   * @param i int
   * @return int
   */
  public int returnNoteStartBeat(int i) {
    int bpm = model.getBeatsPerMeasure();
    return i / 20 - bpm / 2;
  }

  /**
   * Sets the xLine at the given int.
   *
   * @param i int
   * @return int
   */
  public int setxLine(int i) {
    xLine = i / (constant / 2);
    return xLine;
  }

  /**
   * True if the line is at its initial point. False if its moved.
   *
   * @param b boolean
   */
  public void setLineInit(boolean b) {
    lineInit = b;
  }

  /**
   * To get the pitch octave combo (as a string) at the given position i.
   *
   * @param i int
   * @return String
   */
  public String getHashPitchOctave(IMusicModel mm, int i) {
    ArrayList<String> a = listOfPO(mm);
    Collections.reverse(a);
    HashMap<Integer, String> h = new HashMap<Integer, String>();
    ArrayList<String> aNoSpace = new ArrayList<String>();
    for (String s : a) {
      String sNew = s.replace(" ", "");
      aNoSpace.add(sNew);
      int y = 45;
      for (String sNoSpace : aNoSpace) {
        h.put(y, sNoSpace);
        y = y + 20;
      }
    }
    if (Integer.toString(i).charAt(Integer.toString(i).length() - 1) == '9') {
      i = i + 6;
    } else if (Integer.toString(i).charAt(Integer.toString(i).length() - 1) == '8') {
      i = i + 7;
    } else if (Integer.toString(i).charAt(Integer.toString(i).length() - 1) == '7') {
      i = i + 8;
    } else if (Integer.toString(i).charAt(Integer.toString(i).length() - 1) == '6') {
      i = i + 9;
    } else if (Integer.toString(i).charAt(Integer.toString(i).length() - 1) == '0') {
      i = i + 5;
    } else if (Integer.toString(i).charAt(Integer.toString(i).length() - 1) == '4') {
      i = i + 1;
    } else if (Integer.toString(i).charAt(Integer.toString(i).length() - 1) == '3') {
      i = i + 2;
    } else if (Integer.toString(i).charAt(Integer.toString(i).length() - 1) == '2') {
      i = i + 3;
    } else if (Integer.toString(i).charAt(Integer.toString(i).length() - 1) == '1') {
      i = i + 4;
    }
    return h.get(i);
  }

}