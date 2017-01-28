package cs3500.music.tests;

import org.junit.Test;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import cs3500.music.controller.KeyboardHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * To test the keyboard handler class.
 */
public class KeyboardHandlerTest {
  StringBuilder str = new StringBuilder();
  KeyboardHandler key = new KeyboardHandler();
  Map<Integer, Runnable> typed = new HashMap<Integer, Runnable>();
  Map<Integer, Runnable> pressed = new HashMap<Integer, Runnable>();
  Map<Integer, Runnable> released = new HashMap<Integer, Runnable>();
  Runnable homeRun;
  Runnable endRun;
  Runnable pauseRun;
  Runnable addRun;
  Runnable enterRun;

  {
    homeRun = new Runnable() {
      @Override
      public void run() {
        str.append("home ");
        key.setTyped(typed);
        key.setReleased(released);
      }
    };
  }

  {
    pauseRun = new Runnable() {
      @Override
      public void run() {
        str.append("pause ");
        key.setTyped(typed);
        key.setReleased(released);
      }
    };
  }

  {
    endRun = new Runnable() {
      @Override
      public void run() {
        str.append("end ");
        key.setTyped(typed);
        key.setReleased(released);
      }
    };
  }

  {
    addRun = new Runnable() {
      @Override
      public void run() {
        str.append("add ");
        key.setTyped(typed);
        key.setReleased(released);
      }
    };
  }

  {
    enterRun = new Runnable() {
      @Override
      public void run() {
        str.append("enter ");
        key.setTyped(typed);
        key.setReleased(released);
      }
    };
  }

  @Test
  public void testKeyTyped() {
    // also tests setTyped and getTyped
    typed.put(KeyEvent.VK_P, pauseRun);
    typed.put(KeyEvent.VK_A, addRun);
    key.setTyped(typed);
    KeyEvent e1 = new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_P, 'p');
    KeyEvent e2 = new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_A, 'a');
    key.keyTyped(e1);
    key.keyTyped(e2);
    assertEquals(str.toString(), "pause add ");
    assertEquals(typed.keySet().toString(), "[80, 65]");
    assertEquals(key.getTyped().keySet().toString(), "[80, 65]");
  }

  @Test
  public void testKeyPressed() {
    // also tests setPressed and getPressed
    pressed.put(KeyEvent.VK_H, homeRun);
    pressed.put(KeyEvent.VK_E, endRun);
    pressed.put(KeyEvent.VK_P, pauseRun);
    pressed.put(KeyEvent.VK_A, addRun);
    pressed.put(KeyEvent.VK_ENTER, enterRun);
    key.setPressed(pressed);
    KeyEvent e1 = new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_H, 'h');
    KeyEvent e2 = new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_E, 'e');
    KeyEvent e3 = new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_P, 'p');
    KeyEvent e4 = new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_A, 'a');
    KeyEvent e5 = new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_ENTER, 'e');
    key.keyPressed(e1);
    key.keyPressed(e2);
    key.keyPressed(e3);
    key.keyPressed(e4);
    key.keyPressed(e5);
    assertEquals(str.toString(), "home end pause add enter ");
    assertEquals(pressed.keySet().toString(), "[80, 65, 69, 72, 10]");
    assertEquals(key.getPressed().keySet().toString(), "[80, 65, 69, 72, 10]");
  }

  @Test
  public void testKeyReleased() {
    // also tests setReleased and getReleased
    released.put(KeyEvent.VK_H, homeRun);
    released.put(KeyEvent.VK_E, endRun);
    released.put(KeyEvent.VK_P, pauseRun);
    released.put(KeyEvent.VK_A, addRun);
    released.put(KeyEvent.VK_ENTER, enterRun);
    key.setReleased(released);
    KeyEvent e1 = new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_H, 'h');
    KeyEvent e2 = new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_E, 'e');
    KeyEvent e3 = new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_P, 'p');
    KeyEvent e4 = new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_A, 'a');
    KeyEvent e5 = new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_ENTER, 'e');
    key.keyReleased(e1);
    key.keyReleased(e2);
    key.keyReleased(e3);
    key.keyReleased(e4);
    key.keyReleased(e5);
    assertEquals(str.toString(), "home end pause add enter ");
    assertEquals(released.keySet().toString(), "[80, 65, 69, 72, 10]");
    assertEquals(key.getReleased().keySet().toString(), "[80, 65, 69, 72, 10]");
  }

  @Test
  public void testEmpty() {
    key.setPressed(pressed);
    key.setTyped(typed);
    key.setReleased(released);
    assertEquals(str.toString(), "");
    assertEquals(pressed.keySet().toString(), "[]");
    assertEquals(typed.keySet().toString(), "[]");
    assertEquals(released.keySet().toString(), "[]");
  }

  @Test
  public void testKeyPressedError() {
    pressed.put(KeyEvent.VK_H, homeRun);
    key.setPressed(pressed);
    KeyEvent e = new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_G, 'h');
    key.keyPressed(e);
    assertFalse(pressed.keySet().toString().equals("[80]"));
  }

  @Test
  public void testKeyTypedError() {
    typed.put(KeyEvent.VK_P, enterRun);
    key.setTyped(typed);
    KeyEvent e = new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_6, 'p');
    key.keyTyped(e);
    assertFalse(typed.keySet().toString().equals("[35]"));
  }

  @Test
  public void testKeyReleasedError() {
    pressed.put(KeyEvent.VK_E, endRun);
    key.setReleased(released);
    KeyEvent e = new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_Z, 'e');
    key.keyReleased(e);
    assertFalse(released.keySet().toString().equals("[65]"));
  }
}