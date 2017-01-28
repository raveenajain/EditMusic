package cs3500.music.controller;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicNote;
import cs3500.music.model.Pitch;
import cs3500.music.model.Repeat;
import cs3500.music.model.ViewModel;
import cs3500.music.view.CompositeView;
import cs3500.music.view.GuiView;
import cs3500.music.view.GuiViewImpl;
import cs3500.music.view.IMusicView;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.ConsoleViewImpl;

/**
 * To represent the controller of the musicEditor.
 */
public class MusicController implements IMusicController {

  protected IMusicModel model;
  protected IMusicView view;
  private static final int xPush = 60;

  /**
   * To represent a mock music controller.
   */
  public MusicController() {
    // empty constructor
  }

  /**
   * .
   *
   * @return the controller's model field
   */
  public IMusicModel getModel() {
    return model;
  }

  /**
   * .
   *
   * @param m the given model to set the field to
   */
  public void setModel(IMusicModel m) {
    if (m != null) {
      model = m;
    } else {
      throw new IllegalArgumentException("Null Model.");
    }
  }

  /**
   * .
   *
   * @return a new viewModel
   */
  public ViewModel createViewModel() {
    if (!model.getNotelist().isEmpty()) {
      return new ViewModel(model);
    } else {
      throw new IllegalArgumentException("Null Model.");
    }
  }

  /**
   * visual = GUI, midi = midi, console = console, comp = composite.
   *
   * @param s input specifying which view to create
   * @return a new IMusicView
   */
  public IMusicView createView(String s) {
    if (s.equals("midi")) {
      view = new MidiViewImpl();
    } else if (s.equals("visual")) {
      view = new GuiViewImpl();
      GuiView v = (GuiView) view;
      v.addKeyListener(this);
      v.addMouseListener(this);
    } else if (s.equals("console")) {
      view = new ConsoleViewImpl();
    } else if (s.equals("comp")) {
      GuiView vGui = new GuiViewImpl();
      MidiViewImpl vMidi = new MidiViewImpl();
      view = new CompositeView(vGui, vMidi);
      CompositeView v = (CompositeView) view;
      v.addKeyListener(this);
      v.addMouseListener(this);
    } else {
      throw new IllegalArgumentException("Invalid Input.");
    }
    return view;
  }

  /**
   * home key moves view to the start.
   * end key moves view to the end.
   * A key allows user to enter a note to be added.
   * R key allows user to enter a repeat to be added.
   * enter key adds the above note or repeat.
   * P key pauses view.
   *
   * @return a keyboardhandler that allows for different key events
   */
  public KeyboardHandler createKeyboardHandler() {
    KeyboardHandler key = new KeyboardHandler();
    Map<Integer, Runnable> typed = new HashMap<Integer, Runnable>();
    Map<Integer, Runnable> pressed = new HashMap<Integer, Runnable>();
    Map<Integer, Runnable> released = new HashMap<Integer, Runnable>();
    GuiView frame = (GuiView) view;
    // to jump to the beginning of the composition
    pressed.put(KeyEvent.VK_H, new Runnable() {
      @Override
      public void run() {
        frame.jumpHome();
      }
    });
    // to jump to the end of the composition
    pressed.put(KeyEvent.VK_E, new Runnable() {
      @Override
      public void run() {
        frame.jumpEnd();
      }
    });
    // to add a pop up window to add a note
    pressed.put(KeyEvent.VK_A, new Runnable() {
      @Override
      public void run() {
        frame.addNotePopUp();
      }
    });
    // to pause the composition
    pressed.put(KeyEvent.VK_P, new Runnable() {
      @Override
      public void run() {
        frame.pauseMusic(new MidiViewImpl());
      }
    });
    pressed.put(KeyEvent.VK_R, new Runnable() {
      @Override
      public void run() {
        frame.addRepeatPopUp();
      }
    });
    // to add the note to the composition
    pressed.put(KeyEvent.VK_ENTER, new Runnable() {
      @Override
      public void run() {
        int bpm = model.getBeatsPerMeasure();
        int lb = model.getLastBeat();
        Scanner s = new Scanner(frame.getOutput());
        String[] stringList = new String[0];
        if (s.hasNext()) {
          stringList = s.nextLine().split(" ");
        }
        try {
          if (stringList.length == 2) {
            int start = Integer.parseInt(stringList[0]);
            int end = Integer.parseInt(stringList[1]);
            Repeat r = new Repeat(start, end);
            if (r.isValidRepeat(bpm, lb)) { // checks to make sure repeat is valid
              ArrayList<Repeat> a = model.getRepeats();
              {
                if (!model.checkNestedRepeats(r)) { // to make sure a nested repeat isn't added
                  a.add(r);
                } else {
                  frame.addPopUpError();
                }
              }
              model.setRepeats(a);
            } else {
              frame.addPopUpError();
            }
          } else {
            throw new NumberFormatException();
          }
        } catch (NumberFormatException n) {
          System.out.println("here");
          Pitch p;
          int oct;
          int dur;
          int sb;
          int volume;
          int instrument;
          if (stringList.length == 6) {
            String sp;
            if (stringList[0].length() == 2) {
              sp = stringList[0].replace('#', 'S');
              p = Pitch.valueOf(sp);
            } else {
              p = Pitch.valueOf(stringList[0]);
            }
            oct = Integer.parseInt(stringList[1]);
            dur = Integer.parseInt(stringList[2]);
            sb = Integer.parseInt(stringList[3]);
            volume = Integer.parseInt(stringList[4]);
            instrument = Integer.parseInt(stringList[5]);
            try {
              model.addNote(new MusicNote(p, oct, dur, sb, volume, instrument));
            } catch (IllegalArgumentException e) {
              frame.addPopUpError();
              // do nothing with exception
            }
          } else {
            frame.addPopUpError();
          }
        }
        ViewModel v = createViewModel();
        try {
          view.drawModel(v);
        } catch (InvalidMidiDataException e) {
          // do nothing with exception
        }
        int x = ((GuiView) view).getScroll().getViewport().getX();
        int y = ((GuiView) view).getScroll().getViewport().getX();
        ((GuiView) view).getScroll().getViewport().setViewPosition(new Point(x, y));
      }
    });
    key.setTyped(typed);
    key.setPressed(pressed);
    key.setReleased(released);
    return key;
  }

  /**
   * clicking the mouse removes the note at the current coordinate.
   *
   * @return a mousehandler that allows for mouse events
   */
  public MouseHandler createMouseHandler() {
    MouseHandler mouse = new MouseHandler();
    Map<Integer, Runnable> clicked = new HashMap<Integer, Runnable>();
    GuiView frame = (GuiView) view;
    // to remove a note from the composition
    clicked.put(MouseEvent.MOUSE_CLICKED, new Runnable() {
      @Override
      public void run() {
        if (mouse.getX() > xPush) {
          HashMap<Integer, ArrayList<MusicNote>> nl = model.getNotelist();
          if (!nl.isEmpty()) {
            ArrayList<MusicNote> a = nl.get(frame.getDisplayPanel().returnNoteStartBeat(Math.abs(
                    frame.getDisplayPanel().getX()) + 1 + mouse.getX()));
            int r1 = Math.abs(frame.getDisplayPanel().getY()) + mouse.getY();
            int r2 = Math.abs(frame.getDisplayPanel().getY()) - 10 + mouse.getY();
            for (MusicNote n : a) {
              if (n.combinePitchOctave().equals(frame.getDisplayPanel().getHashPitchOctave(model,
                      r1)) || n.combinePitchOctave().equals(
                      frame.getDisplayPanel().getHashPitchOctave(model, r2))) {
                try {
                  model.removeNote(n);
                  ViewModel v = createViewModel();
                  view.drawModel(v);
                  int x = ((GuiView) view).getScroll().getViewport().getX();
                  int y = ((GuiView) view).getScroll().getViewport().getX();
                  ((GuiView) view).getScroll().getViewport().setViewPosition(new Point(x, y));
                  break;
                } catch (IllegalArgumentException e) {
                  // do nothing with exceptions
                } catch (NullPointerException np) {
                  // do nothing with exceptions
                } catch (InvalidMidiDataException e) {
                  // do nothing with exceptions
                }
              }
            }
          }
        }
      }
    });
    mouse.setClicked(clicked);
    return mouse;
  }
}
