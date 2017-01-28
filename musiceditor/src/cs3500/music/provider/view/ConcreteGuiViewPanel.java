package cs3500.music.provider.view;

import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.BasicStroke;

import java.util.List;
import java.util.Collections;

import javax.swing.JPanel;
import cs3500.music.model.ImmutableMusicEditorModel;
import cs3500.music.model.NoteType;

/**
 * A dummy view that simply draws a string.
 */
public class ConcreteGuiViewPanel extends JPanel {
  private ImmutableMusicEditorModel model;

  // the height of a measure visually
  private int height;
  private float time;
  private int beginX;
  private int endX;
  private int beginBeat;
  private int endBeat;
  private int lowestPitch;
  private int highestPitch;
  private int noteRange;

  //beats per frame(visually)
  private int bpf;

  GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();


  //CURRENT RESOLUTION
  private final int x = gd.getDisplayMode().getWidth();
  private final int y = gd.getDisplayMode().getHeight();


  /**
   * A constructor for the ConcreteGuiViewPanel.
   * Sets model to null, and other values to standard starting values.
   */
  public ConcreteGuiViewPanel() {
    this.model = null;
    this.height = 25;

    this.bpf = this.x / this.height + 7;

    this.time = 0;

    this.beginX = 0;
    this.endX = x;

    this.beginBeat = 0;
    this.endBeat = bpf;

    this.lowestPitch = 0;
    this.highestPitch = 0;

    this.noteRange = 0;

  }

  /**
   * Sets the given ImmutableModel to be this view's ImmutableModel.
   * @param model The given ImmutableModel
   */
  public void setModel(ImmutableMusicEditorModel model) {
    this.model = model;
    this.getPitchRange();
    //find out what the height should be
    if (y / height < this.noteRange) {
      this.height = y / (this.noteRange + height / 3 * 2);
    }
    this.repaint();
  }

  /**
   * Determines what the lowest and highest pitches in this piece are.
   */
  private void getPitchRange() {
    List<Integer> pitches = model.getPitchNums();
    this.lowestPitch = Collections.min(pitches);
    this.highestPitch = Collections.max(pitches);
    this.noteRange = highestPitch - lowestPitch;
  }

  @Override
  public void paintComponent(Graphics g) {

    // Handle the default painting
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.BLACK);
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, height / 3 * 2));

    //draws the pitch range on the left of the screen
    if (this.beginX < 150) {
      for (int j = this.highestPitch; j >= this.lowestPitch; j--) {
        g2d.drawString(model.pitchNumtoString(j), 5, height * (Math.abs(j - highestPitch) + 2));
      }
    }

    //labels measures every 16 beats
    int i = 0;
    while (i < this.endBeat + 16) {
      g2d.drawString(Integer.toString(i), (i * 50) + 80, height);
      i = i + 16;
    }

    //draws notes
    for (int j = this.beginBeat; j < this.endBeat; j++) {
      for (int p = 0; p < model.getNoteType(j).size(); p++) {
        if (model.getNoteType(j).get(p) == NoteType.HEAD) {
          g2d.setColor(Color.BLACK);
        } else {
          g2d.setColor(Color.GREEN);
        }

        int pitch = model.getBeatPitchNums(j).get(p);
        g2d.fillRect(j * 50 + 80, height * (Math.abs(pitch - highestPitch) + 1), 50, height);

      }
    }


    g2d.setStroke(new BasicStroke(2));
    g2d.setColor(Color.BLACK);

    //draws vertical lines
    for (int j = 0; j <= Math.ceil((double) model.numBeats() / 4); j++) {
      if (j * 200 + 80 >= beginX && j * 200 + 80 <= endX) {
        g2d.drawLine(j * 200 + 80, height + 1, j * 200 + 80,
                height * (this.noteRange + 2) + 1);
      }
    }

    // draws horizontal lines
    for (int j = 0; j < this.noteRange + 2; j++) {
      if (this.beginX <= 150) {
        g2d.drawLine(80, height * (j + 1) + 1,
                this.endX, height * (j + 1) + 1);
      } else {
        g2d.drawLine(this.beginX, height * (j + 1) + 1,
                this.endX, height * (j + 1) + 1);
      }
    }

    //draws red line
    g2d.setColor(Color.RED);
    g2d.drawLine(80 + (int) time, height + 1, 80 + (int) time, height * (this.noteRange + 2) + 1);
  }

  /**
   * Sets the current time of the piece.
   * @param time int
   */
  public void setTime(float time) {
    this.time = time / 10000;
    this.updateViewport();
    this.repaint();
  }

  /**
   * Updates the intervals of view to be drawn.
   */
  private void updateViewport() {
    if (this.time >= endX) {
      this.beginBeat = Math.min(beginBeat + x / 50, this.model.numBeats() - bpf);
      this.endBeat = this.endBeat + bpf;
      this.beginX = Math.min(endX, this.model.numBeats() * 50 + 150 - x);
      this.endX = endX + x;
    }
  }

  /**
   * Returns the right bound of the pixels that should be drawn.
   * @return the right bound
   */
  public int getEndX() {
    return this.endX;
  }

  /**
   * Returns the left bound of the pixels that should be drawn.
   * @return the left bound
   */
  public int getBeginX() {
    return this.beginX;
  }

  /**
   * Resets the view to the beginning of the piece.
   */
  public void jumpToBegin() {
    this.beginX = 0;
    this.endX = x;
    this.time = 0;
    this.beginBeat = 0;
    this.endBeat = bpf;

  }

  /**
   * Resets the view to the end of the piece.
   */
  public void jumpToEnd() {
    this.beginX = this.model.numBeats() * 50 + 150 - x;
    this.endX = this.model.numBeats() * 50 + 150;
    this.time = (model.getTempo() / 10000) * model.numBeats();
    this.beginBeat = this.model.numBeats() - bpf;
    this.endBeat = this.model.numBeats();

  }

  /**
   * Adjusts the interval of the pixels to be drawn after scrolling left or right once.
   * @param numBeat int
   */
  public void incrementFrame(int numBeat) {
    if ((numBeat > 0 && this.endBeat + numBeat < model.numBeats())
            || (numBeat < 0 && this.beginBeat + numBeat >= 0)) {
      if (this.beginX <= 150) {
        this.beginX = beginX + (50 * numBeat);
        this.endX = endX + (50 * numBeat);
      } else {
        this.beginX = beginX + (50 * numBeat);
        this.endX = endX + (50 * numBeat);
        this.beginBeat = beginBeat + numBeat;
        this.endBeat = endBeat + numBeat;
      }
    }
  }
}
