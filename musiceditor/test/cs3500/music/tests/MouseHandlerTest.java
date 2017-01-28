package cs3500.music.tests;

import org.junit.Test;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import cs3500.music.controller.MouseHandler;

import static org.junit.Assert.assertEquals;

/**
 * To test the mouse handler class.
 */
public class MouseHandlerTest {
  StringBuilder str = new StringBuilder();
  MouseHandler mouse = new MouseHandler();
  private Map<Integer, Runnable> clicked = new HashMap<Integer, Runnable>();
  private int x;
  private int y;
  Runnable clickRun;

  {
    clickRun = new Runnable() {
      @Override
      public void run() {
        x = x + 1;
        y = y + 1;
        str.append("click ");
      }
    };
  }

  @Test
  public void testMouseClicked() {
    // also tests setClicked
    clicked.put(MouseEvent.MOUSE_CLICKED, clickRun);
    mouse.setClicked(clicked);
    MouseEvent e1 = new MouseEvent(new JPanel(), 0, 0, 0, 0, 0, 0, false);
    MouseEvent e2 = new MouseEvent(new JPanel(), 0, 0, 0, 0, 0, 0, false);
    MouseEvent e3 = new MouseEvent(new JPanel(), 0, 0, 0, 0, 0, 0, false);
    MouseEvent e4 = new MouseEvent(new JPanel(), 0, 0, 0, 0, 0, 0, false);
    mouse.mouseClicked(e1);
    mouse.mouseClicked(e2);
    mouse.mouseClicked(e3);
    mouse.mouseClicked(e4);
    assertEquals(x, 4);
    assertEquals(y, 4);
    assertEquals(mouse.getClicked().keySet().toString(), "[500]");
    assertEquals(str.toString(), "click click click click ");
  }

  @Test
  public void testNoMouseClicked() {
    clicked.put(MouseEvent.MOUSE_CLICKED, clickRun);
    mouse.setClicked(clicked);
    assertEquals(x, 0);
    assertEquals(y, 0);
    assertEquals(str.toString(), "");
  }

  @Test
  public void testGetX() {
    clicked.put(MouseEvent.MOUSE_CLICKED, clickRun);
    mouse.setClicked(clicked);
    MouseEvent e1 = new MouseEvent(new JPanel(), 0, 0, 0, 10, 0, 0, false);
    mouse.mouseClicked(e1);
    assertEquals(mouse.getX(), 10);
    MouseEvent e2 = new MouseEvent(new JPanel(), 0, 0, 0, 20, 0, 0, false);
    mouse.mouseClicked(e2);
    assertEquals(mouse.getX(), 20);
  }

  @Test
  public void testGetY() {
    clicked.put(MouseEvent.MOUSE_CLICKED, clickRun);
    mouse.setClicked(clicked);
    MouseEvent e1 = new MouseEvent(new JPanel(), 0, 0, 0, 0, 22, 0, false);
    mouse.mouseClicked(e1);
    assertEquals(mouse.getY(), 22);
    MouseEvent e2 = new MouseEvent(new JPanel(), 0, 0, 0, 0, 66, 0, false);
    mouse.mouseClicked(e2);
    assertEquals(mouse.getY(), 66);
  }
}