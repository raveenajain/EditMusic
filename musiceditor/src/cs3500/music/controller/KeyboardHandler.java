package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * To represent the class that deals with KeyBoard events.
 */
public class KeyboardHandler implements KeyListener {

  private Map<Integer, Runnable> typed;
  private Map<Integer, Runnable> pressed;
  private Map<Integer, Runnable> released;

  /**
   * constructor for the KeyboardHandler.
   */
  public KeyboardHandler() {
    this.typed = new HashMap<Integer, Runnable>();
    this.pressed = new HashMap<Integer, Runnable>();
    this.released = new HashMap<Integer, Runnable>();
  }

  /**
   * to set the typed field.
   *
   * @param t the map the typed field is set to
   */
  public void setTyped(Map<Integer, Runnable> t) {
    typed = t;
  }

  /**
   * to set the pressed field.
   *
   * @param p the map the pressed field is set to
   */
  public void setPressed(Map<Integer, Runnable> p) {
    pressed = p;
  }

  /**
   * to set the released field.
   *
   * @param r the map the released field is set to
   */
  public void setReleased(Map<Integer, Runnable> r) {
    released = r;
  }

  /**
   * to set typed based on the given key event.
   *
   * @param e the given key event
   */
  @Override
  public void keyTyped(KeyEvent e) {
    if (typed.containsKey(e.getKeyCode())) {
      typed.get(e.getKeyCode()).run();
    }
  }

  /**
   * to set pressed based on the given key event.
   *
   * @param e the given key event
   */
  @Override
  public void keyPressed(KeyEvent e) {
    if (pressed.containsKey(e.getKeyCode())) {
      pressed.get(e.getKeyCode()).run();
    }
  }

  /**
   * to set released based on the given key event.
   *
   * @param e the given key event
   */
  @Override
  public void keyReleased(KeyEvent e) {
    if (released.containsKey(e.getKeyCode())) {
      released.get(e.getKeyCode()).run();
    }
  }

  /**
   * to get the pressed field.
   *
   * @return pressed field
   */
  public Map<Integer, Runnable> getPressed() {
    return pressed;
  }

  /**
   * to get the typed field.
   *
   * @return typed field
   */
  public Map<Integer, Runnable> getTyped() {
    return typed;
  }

  /**
   * to get the released field.
   *
   * @return released field
   */
  public Map<Integer, Runnable> getReleased() {
    return released;
  }
}
