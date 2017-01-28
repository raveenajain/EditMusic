package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.controller.ControllerAdapter;
import cs3500.music.controller.IMusicController;
import cs3500.music.view.GuiViewAdapter;

import static org.junit.Assert.assertTrue;

/**
 * Created by McMacBook on 12/5/16.
 */
public class ControllerAdapterTest {

  IMusicController mc = new ControllerAdapter();

  @Test
  public void testCreateView() {
    assertTrue(mc.createView("comp") instanceof GuiViewAdapter);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateViewInvalidString() {
    mc.createView("nope");
  }
}