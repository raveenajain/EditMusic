package cs3500.music.controller;

import cs3500.music.provider.view.MidiGuiView;
import cs3500.music.view.GuiViewAdapter;
import cs3500.music.view.IMusicView;

/**
 * To represent the controller of the musicEditor creating our providers views.
 */
public class ControllerAdapter extends MusicController {

  @Override
  public IMusicView createView(String s) {
    if (s.equals("comp")) {
      cs3500.music.provider.view.GuiViewFrame vGui =
              new cs3500.music.provider.view.GuiViewFrame();
      cs3500.music.provider.view.MidiViewImpl vMidi =
              new cs3500.music.provider.view.MidiViewImpl();
      view = new GuiViewAdapter(new MidiGuiView(vMidi, vGui));
      GuiViewAdapter v = (GuiViewAdapter) view;
      v.addMouseListener(this);
      v.addKeyListener(this);
    } else if (s.equals("visual")) {
      view = new GuiViewAdapter(new cs3500.music.provider.view.GuiViewFrame());
      GuiViewAdapter v = (GuiViewAdapter) view;
      v.addMouseListener(this);
      v.addKeyListener(this);
    } else {
      throw new IllegalArgumentException("Not For Provider.");
    }
    return view;
  }
}
