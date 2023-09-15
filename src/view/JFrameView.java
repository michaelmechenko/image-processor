package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JFrame;
import java.awt.BorderLayout;

/**
 * this class represents the view when an action is performed in the GUI (i.e a person chooses a
 * modification, saves a file, etc).
 */
public class JFrameView extends JFrame implements IView, ActionListener {

  int[] list;

  /**
   * constructs a JFrameView.
   * @param list the array representing the frequencies for pixel values 0-255
   * @param color the color value we want to get the frequencies for
   */
  public JFrameView(int[] list, Color color) {
    setSize(800, 500);
    this.list = list;
    Histogram chart = new Histogram(list, "Pixel Values", "Frequencies", color);
    this.setLayout(new BorderLayout(2, 2));
    this.add(chart, BorderLayout.CENTER);

  }

  /**
   * resets the list (representing the frequency of different pixel values in an image) when an
   * action is performed.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    // revert to the original file input method
    this.list = new int[256];
    Random generator = new Random();
    for (int i = 0; i < 50; i++) {
      int add = generator.nextInt(10) + 1;
      this.list[add] = add;
    }
    repaint();
  }

  @Override
  public String getCommand() {
    return null;
  }
}
