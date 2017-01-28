package cs3500.music.tests;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicNote;
import cs3500.music.model.Pitch;
import cs3500.music.model.ViewModel;
import cs3500.music.view.IMusicView;
import cs3500.music.view.ConsoleViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * To test the methods inside of the ConsoleViewImpl class.
 */
public class ConsoleViewImplTest {

  IMusicView mv1 = new ConsoleViewImpl();
  IMusicModel m1 = new MusicModel();
  IMusicModel mEmpty = new MusicModel();
  MusicNote n1 = new MusicNote(Pitch.G, 0, 2, 2, 1, 8);
  MusicNote n2 = new MusicNote(Pitch.FS, 0, 3, 9, 4, 0);

  {
    m1.addNote(n1);
    m1.addNote(n2);
  }

  ViewModel v1 = new ViewModel(m1);
  ViewModel vEmpty = new ViewModel(mEmpty);

  @Test
  public void testDrawModel1() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    try {
      mv1.drawModel(v1);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    assertEquals(out.toString(), "   F#0   G0 \n" +
            " 0          \n" +
            " 1          \n" +
            " 2       X  \n" +
            " 3       |  \n" +
            " 4          \n" +
            " 5          \n" +
            " 6          \n" +
            " 7          \n" +
            " 8          \n" +
            " 9  X       \n" +
            "10  |       \n" +
            "11  |       \n" +
            "\n");
  }

  //  Change path to your mary had a little lamb and add imports
  //  @Test
  //  public void testDrawModelMary() {
  //    // also tests parseFile
  //    MusicReader reader = new MusicReader();
  //    CompositionBuilder<MusicModel> cb = new MusicModel.Builder();
  //    Readable readable = null;
  //    try {
  //      readable = new FileReader("/Users/McMacBook/Desktop/"
  // + "EVERYTHING/school/neu/ood/musiceditor/"
  //              + "src/cs3500/music/util/mary-little-lamb.txt");
  //    } catch (FileNotFoundException e) {
  //      e.printStackTrace();
  //    }
  //    IMusicModel model = reader.parseFile(readable, cb);
  //    IMusicController controller = new MusicController();
  //    controller.setModel(model);
  //    ViewModel viewModel = controller.createViewModel();
  //    IMusicView view = controller.createView("console");
  //    ByteArrayOutputStream out = new ByteArrayOutputStream();
  //    System.setOut(new PrintStream(out));
  //    try {
  //      view.drawModel(viewModel);
  //    } catch (InvalidMidiDataException e) {
  //      e.printStackTrace();
  //    }
  //    assertEquals(out.toString(), "    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4"
  // + "   "
  //            + "E4   F4  F#4   G4 \n" + " 0                 X                            "
  // + "          "
  //            + "      X                 \n"
  //            + " 1                 |                                            |           "
  //          + "      \n"
  //            + " 2                 |                                  X                     "
  //            + "      \n"
  //            + " 3                 |                                  |                   "
  //            + "        \n"
  //            + " 4                 |                        X                                 "
  //            + "    \n"
  //            + " 5                 |                        |                             "
  //            + "        \n"
  //            + " 6                 |                                  X                      "
  //            + "     \n"
  //            + " 7                                                    |                 "
  //            + "          \n"
  //            + " 8                 X                                            X           "
  //            + "      \n"
  //            + " 9                 |                                            |          "
  //            + "       \n"
  //            + "10                 |                                            X          "
  //            + "       \n"
  //            + "11                 |                                            |         "
  //            + "        \n"
  //            + "12                 |                                            X          "
  //            + "       \n"
  //            + "13                 |                                            |           "
  //            + "      \n"
  //            + "14                 |                                            |            "
  //            + "     \n"
  //            + "15                                                                            "
  //            + "    \n"
  //            + "16                 X                                  X            "
  //            + "               \n"
  //            + "17                 |                                  |              "
  //            + "             \n"
  //            + "18                 |                                  X                "
  //            + "           \n"
  //            + "19                 |                                  |                  "
  //            + "         \n"
  //            + "20                 |                                  X                    "
  //            + "       \n"
  //            + "21                 |                                  |                      "
  //            + "     \n"
  //            + "22                 |                                  |                      "
  //            + "     \n"
  //            + "23                 |                                  |                "
  //            + "           \n"
  //            + "24                 X                                            X       "
  //            + "          \n"
  //            + "25                 |                                            |        "
  //            + "         \n"
  //            + "26                                                                   "
  //            + "          X  \n"
  //            + "27                                                              "
  //            + "               |  \n"
  //            + "28                                                                "
  //            + "             X  \n"
  //            + "29                                                                  "
  //            + "           |  \n"
  //            + "30                                                                "
  //            + "             |  \n"
  //            + "31                                                               "
  //            + "              |  \n"
  //            + "32                 X                                            X  "
  //            + "               \n"
  //            + "33                 |                                            |   "
  //            + "              \n"
  //            + "34                 |                                  X             "
  //            + "              \n"
  //            + "35                 |                                  |             "
  //            + "              \n"
  //            + "36                 |                        X                        "
  //            + "             \n"
  //            + "37                 |                        |                         "
  //            + "            \n"
  //            + "38                 |                                  X                "
  //            + "           \n"
  //            + "39                 |                                  |                 "
  //            + "          \n"
  //            + "40                 X                                            X        "
  //            + "         \n"
  //            + "41                 |                                            |         "
  //            + "        \n"
  //            + "42                 |                                            X          "
  //            + "       \n"
  //            + "43                 |                                            |           "
  //            + "      \n"
  //            + "44                 |                                            X            "
  //            + "     \n"
  //            + "45                 |                                            |             "
  //            + "    \n"
  //            + "46                 |                                            X "
  //            + "                \n"
  //            + "47                 |                                            |  "
  //            + "               \n"
  //            + "48                 X                                  X            "
  //            + "               \n"
  //            + "49                 |                                  |            "
  //            + "               \n"
  //            + "50                 |                                  X            "
  //            + "               \n"
  //            + "51                 |                                  |            "
  //            + "               \n"
  //            + "52                 |                                            X  "
  //            + "               \n"
  //            + "53                 |                                            |  "
  //            + "               \n"
  //            + "54                 |                                  X            "
  //            + "               \n"
  //            + "55                 |                                  |            "
  //            + "               \n"
  //            + "56  X                                       X                     "
  //            + "                \n"
  //            + "57  |                                       |                     "
  //            + "                \n"
  //            + "58  |                                       |                    "
  //            + "                 \n"
  //            + "59  |                                       |                 "
  //            + "                    \n"
  //            + "60  |                                       |                "
  //            + "                     \n"
  //            + "61  |                                       |               "
  //            + "                      \n"
  //            + "62  |                                       |                "
  //            + "                     \n"
  //            + "63  |                                       |                 "
  //            + "                    \n"
  //            + "\n");
  //  }

  @Test
  public void testDrawModelEmpty() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    try {
      mv1.drawModel(vEmpty);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    assertTrue(out.toString().equals("No beat(s) to drop\n"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testJumpHome() {
    mv1.jumpHome();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testJumpEnd() {
    mv1.jumpEnd();
  }
}