package cs3500.music.provider.view;

import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Rectangle;

import java.util.Objects;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;
import cs3500.music.model.ImmutableMusicEditorModel;

/**
 * A skeleton Frame (i.e., a window) in Swing.
 */
public class GuiViewFrame extends JFrame implements GuiView {

  private final ConcreteGuiViewPanel displayPanel;
  private final JScrollPane scrollPane;
  private int width;
  private JButton enter;
  private JTextField input;
  private JPanel buttonPanel;

  GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

  //CURRENT RESOLUTION
  private final int x = gd.getDisplayMode().getWidth();
  private final int y = gd.getDisplayMode().getHeight();

  /**
   * Creates new GuiView.
   */
  public GuiViewFrame() {
    this.setTitle("Music Editor");
    this.setLayout(new BorderLayout());
    this.displayPanel = new ConcreteGuiViewPanel();
    this.displayPanel.setPreferredSize(new Dimension(x, y));
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    scrollPane = new JScrollPane(displayPanel);
    scrollPane.setViewportView(displayPanel);
    this.getContentPane().add(scrollPane, BorderLayout.CENTER);
    this.setSize(x, y);

    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    this.width = 100000;

    //button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.NORTH);


    input = new JTextField(15);
    buttonPanel.add(input, BorderLayout.PAGE_END);

    enter = new JButton("Enter");
    enter.setActionCommand("Enter");
    buttonPanel.add(enter, BorderLayout.SOUTH);

    this.pack();
  }

  /**
   * Sets the mouse listeners of the view.
   * @param mouseListener the mouseListener to set
   */
  public void addMouseHandler(MouseHandler mouseListener) {
    this.addMouseListener(mouseListener);
    enter.addMouseListener(mouseListener);
    this.setFocusable(true);
  }

  @Override
  public String getInput() {
    String command = this.input.getText();
    this.input.setText("");
    return command;
  }

  @Override
  public void resetFocus() {
    this.displayPanel.setFocusable(true);
    this.displayPanel.requestFocus();
  }

  @Override
  public void addKeyHandler(KeyboardHandler keyboardHandler) {
    this.addKeyListener(keyboardHandler);
    this.setFocusable(true);
  }

  @Override
  public void initialize() {
    this.setVisible(true);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(x, y);
  }

  @Override
  public void setModel(ImmutableMusicEditorModel model) {
    Objects.requireNonNull(model);
    this.displayPanel.setModel(model);
    this.width = 150 + 50 * model.numBeats();
    this.displayPanel.setPreferredSize(new Dimension(width, y));
    this.scrollPane.setPreferredSize(new Dimension(width, y));
    revalidate();
  }

  @Override
  public void startScrollRight() {
    this.displayPanel.incrementFrame(1);
    this.updateViewport();
    this.displayPanel.repaint();
  }

  @Override
  public void startScrollLeft() {
    this.displayPanel.incrementFrame(-1);
    this.updateViewport();
    displayPanel.repaint();
  }

  @Override
  public void jumpToBegin() {
    this.displayPanel.jumpToBegin();
    this.updateViewport();
    this.displayPanel.repaint();
  }

  @Override
  public void jumpToEnd() {
    this.displayPanel.jumpToEnd();
    this.updateViewport();
    this.displayPanel.repaint();
  }

  /**
   * Sets the time of the view.
   * @param time float
   */
  public void setTime(float time) {
    displayPanel.setTime(time);
    this.updateViewport(time);

  }

  /**
   * Updates what interval of pixels the view should be displaying.
   * @param time the new time
   */
  public void updateViewport(float time) {
    if (time >= displayPanel.getEndX()) {
      displayPanel.scrollRectToVisible(new Rectangle(displayPanel.getBeginX(), 0, x, y));
      displayPanel.revalidate();
    }
  }

  /**
   * Updates what interval of pixels the view should be displaying.
   */
  public void updateViewport() {
    displayPanel.scrollRectToVisible(new Rectangle(displayPanel.getBeginX(), 0, x, y));
    displayPanel.revalidate();
  }
}
