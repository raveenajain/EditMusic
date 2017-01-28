package cs3500.music.view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import cs3500.music.controller.IMusicController;
import cs3500.music.model.IMusicModel;
import cs3500.music.model.ViewModel;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewImpl extends javax.swing.JFrame implements GuiView {

  private final ConcreteGuiViewPanel displayPanel;
  private JScrollPane scroll;
  private JDialog dialog;
  private StringBuilder output;

  /**
   * Creates new GuiView.
   */
  public GuiViewImpl() {
    setSize(1000, 1000);
    this.displayPanel = new ConcreteGuiViewPanel(1000, 1000);
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    scroll = new JScrollPane(displayPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.getContentPane().add(scroll);
    displayPanel.setPreferredSize(new Dimension(1000, 1000));
    scroll.revalidate();
    scroll.repaint();
    scroll.setFocusable(true);
    this.pack();
  }

  /**
   * .
   *
   * @param c IMusicController that creates the KeyListener
   */
  public void addKeyListener(IMusicController c) {
    scroll.addKeyListener(c.createKeyboardHandler());
  }

  /**
   * .
   *
   * @param c IMusicController that creates the MouseListener
   */
  public void addMouseListener(IMusicController c) {
    scroll.addMouseListener(c.createMouseHandler());
  }

  /**
   * .
   */
  //@Override
  public void initialize() {
    this.setVisible(true);
  }

  /**
   * to set the frame to the given dimensions.
   *
   * @return the dimension
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(500, 500);
  }

  /**
   * .
   *
   * @param model the given model to be drawn
   */
  @Override
  public void drawModel(ViewModel model) {
    initialize();
    displayPanel.setModel(model);
  }

  /**
   * .
   */
  @Override
  public void jumpHome() {
    scroll.getViewport().setViewPosition(new Point(0, 0));
  }

  /**
   * .
   */
  @Override
  public void jumpEnd() {
    scroll.getViewport().setViewPosition(new Point(displayPanel.getWidth() - this.getWidth() + 20,
            0));
  }

  /**
   * .
   *
   * @return ConcreteGuiViewPanel
   */
  public ConcreteGuiViewPanel getDisplayPanel() {
    return displayPanel;
  }

  /**
   * .
   */
  public void addNotePopUp() {
    output = new StringBuilder();
    JLabel label1 = new JLabel("<html>" + "<p>" + "Please Enter New Note Information (separated by"
            + " spaces):" + "</p>" + "</html>");
    JLabel label2 = new JLabel("<html>" + "<p>" + "Pitch Octave Duration Startbeat Volume " +
            "Instrument ->" + "</p>" + "</html>");
    JLabel label3 = new JLabel("<html>" + "<p>" + "For Example Enter: C# 6 5 2 100 1" + "</p>"
            + "</html>");
    JLabel label4 = new JLabel("<html>" + "<p>" + "Please Press Enter On Your Keyboard After " +
            "Pressing OK To Add Your Note" + "</p>" + "</html>");
    JTextField text = new JTextField();
    JButton ok = new JButton("OK");
    dialog = new JDialog(this);
    dialog.setLayout(new FlowLayout());
    dialog.setLocation(50, 50);
    dialog.setSize(550, 170);
    dialog.setTitle("Add Notes To The Music!");
    dialog.add(label1);
    dialog.add(label2);
    dialog.add(label3);
    dialog.add(label4);
    text.setPreferredSize(new Dimension(350, 50));
    dialog.add(text);
    dialog.add(ok);
    dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    dialog.setVisible(true);
    ok.addActionListener((ActionEvent e) -> {
      output.append(text.getText());
      dialog.dispose();
    });
  }

  /**
   * .
   */
  public void addRepeatPopUp() {
    output = new StringBuilder();
    JLabel label1 = new JLabel("<html>" + "<p>" + "Please Enter Repeat Information (two numbers "
            + "separated by a space)." + "</p>" + "</html>");
    JLabel label2 = new JLabel("<html>" + "<p>" + "Numbers Must be the Start or End of a Measure "
            + "and Greater Than 0" + "</p>" + "</html>");
    JLabel label3 = new JLabel("<html>" + "<p>" + "For Example Enter: 4 12" + "</p>" + "</html>");
    JLabel label4 = new JLabel("<html>" + "<p>" + "Please Press Enter On Your Keyboard After " +
            "Pressing OK To Add Your Repeat" + "</p>" + "</html>");
    JTextField text = new JTextField();
    JButton ok = new JButton("OK");
    dialog = new JDialog(this);
    dialog.setLayout(new FlowLayout());
    dialog.setLocation(50, 50);
    dialog.setSize(550, 170);
    dialog.setTitle("Add Repeats To The Music!");
    dialog.add(label1);
    dialog.add(label2);
    dialog.add(label3);
    dialog.add(label4);
    text.setPreferredSize(new Dimension(350, 50));
    dialog.add(text);
    dialog.add(ok);
    dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    dialog.setVisible(true);
    ok.addActionListener((ActionEvent e) -> {
      output.append(text.getText());
      dialog.dispose();
    });
  }

  /**
   * .
   *
   * @return String
   */
  public String getOutput() {
    return output.toString();
  }

  /**
   * .
   */
  public void addPopUpError() {
    JLabel label = new JLabel("<html>" + "<p>" + "Invalid Input. Please Try Entering "
            + "Information Again..." + "</p>" + "</html>");
    JButton ok = new JButton("OK");
    dialog = new JDialog(this);
    dialog.setLayout(new FlowLayout());
    dialog.setLocation(50, 50);
    dialog.setSize(400, 75);
    dialog.setTitle("ERROR");
    dialog.add(label);
    dialog.add(ok);
    dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    dialog.setVisible(true);
    ok.addActionListener((ActionEvent e) -> {
      dialog.dispose();
    });
  }

  /**
   * .
   *
   * @param m MidiViewImpl
   */
  public void pauseMusic(MidiViewImpl m) {
    m.pauseMusic();
  }

  /**
   * .
   *
   * @return JScrollPane
   */
  public JScrollPane getScroll() {
    return scroll;
  }

  /**
   * .
   *
   * @return int
   */
  public int getFrameWidth() {
    return this.getWidth();
  }

  /**
   * .
   *
   * @return int
   */
  public int getFrameSize() {
    return (int) this.getBounds().getSize().getWidth();
  }
}
