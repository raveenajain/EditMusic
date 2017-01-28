package cs3500.music;

import cs3500.music.controller.ControllerAdapter;
import cs3500.music.controller.IMusicController;
import cs3500.music.controller.MusicController;
import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.model.ViewModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.IMusicView;

import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

/**
 * To represent a MusicEditor.
 */
public class MusicEditor {
  /**
   * To run the code.
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    MusicReader reader = new MusicReader();
    CompositionBuilder<MusicModel> cb = new MusicModel.Builder();
    try {
      Readable readable1 = new FileReader(args[0]);
      IMusicModel model = reader.parseFile(readable1, cb);
      String sNext1 = args[1];
      IMusicController controller = new MusicController();
      ControllerAdapter cont2 = new ControllerAdapter();
      controller.setModel(model);
      if (sNext1.equals("orig")) {
        ViewModel viewModel = controller.createViewModel();
        IMusicView view = controller.createView(args[2]);
        view.drawModel(viewModel);
      } else if (sNext1.equals("prov")) {
        ViewModel viewModel = controller.createViewModel();
        IMusicView view = cont2.createView(args[2]);
        view.drawModel(viewModel);
      }
    } catch (IllegalArgumentException a) {
      System.out.println(a);
    }
  }
}
