package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

/**
 * To represent the class that deals with Mouse events.
 */
public class MouseHandler implements MouseListener {
  private Map<Integer, Runnable> clicked;
  private Map<Integer, Runnable> real;
  private int x;
  private int y;

  /**
   * constructor for the MouseHandler.
   */
  public MouseHandler() {
    real = new HashMap<Integer, Runnable>();
    clicked = new HashMap<Integer, Runnable>();
  }

  /**
   * updates the clicked, x, and y fields based on the mouse event.
   *
   * @param e the given mouse event
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    try {
      if (clicked.containsKey(e.MOUSE_CLICKED)) {
        x = e.getX();
        y = e.getY();
        clicked.get(e.MOUSE_CLICKED).run();
      }
    } catch (NullPointerException n) {
      // do nothing with exception
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // does nothing

  }

  @Override
  public void mouseReleased(MouseEvent e) {
    try {
      if (real.containsKey(e.MOUSE_CLICKED)) {
        real.get(e.MOUSE_CLICKED).run();
      }
    } catch (NullPointerException n) {
      // do nothing with exception
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // does nothing

  }

  @Override
  public void mouseExited(MouseEvent e) {
    // does nothing

  }

  /**
   * to set the clicked field to the given map.
   *
   * @param c the given map to set the field to
   */
  public void setClicked(Map<Integer, Runnable> c) {
    clicked = c;
  }

  public void setReal(Map<Integer, Runnable> c) {
    real = c;
  }

  /**
   * to get the X field.
   *
   * @return the x coordinate of the mousehandler
   */
  public int getX() {
    return x;
  }

  /**
   * to get the Y field.
   *
   * @return the y coordinate of the mousehandler
   */
  public int getY() {
    return y;
  }

  /**
   * to get the clicked field.
   *
   * @return clicked field
   */
  public Map<Integer, Runnable> getClicked() {
    return clicked;
  }
}
