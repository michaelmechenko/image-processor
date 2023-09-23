package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Rectangle;

/**
 * This class represents a histogram of an Image. A histogram shows the
 * frequency of R, G, B, Intensity
 * values in an image based on how many pixels in the image have that value.
 */
public class Histogram extends JPanel {

  /**
   * The offset of the axis from the edges of the panel.
   */
  public static final int AXIS_OFFSET = 25;

  /**
   * The buffer at the top of the panel to leave space for labels.
   */
  public static final int TOP_BUFFER = 30;

  private int chartWidth;
  private int chartHeight;
  private int originX;
  private int originY;
  private int[] list;

  private String xLabel;
  private String yLabel;

  private Color color;

  /**
   * Constructor for the Histogram class.
   * 
   * @param list  the list of pixel values
   * @param xl    the x-axis label
   * @param yl    the y-axis label
   * @param color the color of the histogram
   **/
  public Histogram(int[] list, String xl, String yl, Color color) {
    super();
    this.list = list;
    xLabel = xl;
    yLabel = yl;
    this.color = color;
  }

  /**
   * This method makes the histogram by drawing the bars and axes of the graph.
   *
   * @param g the graphics to be displayed upon
   */
  public void paintComponent(Graphics g) {
    computeSize();
    Graphics2D g2 = (Graphics2D) g;
    drawChartBars(g2);
    drawChartAxes(g2);
  }

  /**
   * Calculates the size of the histogram.
   */
  private void computeSize() {
    int width = this.getWidth();
    int height = this.getHeight();

    // Calculate the chart width and height
    chartWidth = width - 2 * AXIS_OFFSET;
    chartHeight = height - 2 * AXIS_OFFSET - TOP_BUFFER;

    // Set the origin coordinates
    originX = AXIS_OFFSET;
    originY = height - AXIS_OFFSET;
  }

  /**
   * Draws bars for the histogram.
   *
   * @param g2 the graphics to be displayed upon
   */
  public void drawChartBars(Graphics2D g2) {

    // Store the original color
    Color original = g2.getColor();

    // Find the maximum value in the list
    double numberOfBars = list.length;
    double max = 0;

    for (Integer wrapper : list) {
      if (max < wrapper) {
        max = wrapper;
      }
    }

    // Calculate the width of each bar
    int barWidth = (int) (chartWidth / numberOfBars);

    int value;
    int height;
    int xLeft;
    int yTopLeft;

    // Iterate through the list and draw each bar
    for (int i = 0; i < list.length; i++) {
      value = list[i];

      // Calculate the height of the bar based on the value
      double height2 = (value / max) * chartHeight;
      height = (int) height2;

      // Calculate the position of the bar
      xLeft = AXIS_OFFSET + i * barWidth;
      yTopLeft = originY - height;

      // Create a rectangle for the bar
      Rectangle rec = new Rectangle(xLeft, yTopLeft, barWidth, height);

      // Set the color of the bar and fill it
      g2.setColor(color);
      g2.fill(rec);

    }

    // Restore the original color
    g2.setColor(original);
  }

  /**
   * Draws the chart axes for the histogram.
   *
   * @param graph the graphics to be displayed upon
   */
  private void drawChartAxes(Graphics2D graph) {

    // Calculate the coordinates of the right end of the x-axis
    int rX = originX + chartWidth;

    // Calculate the coordinates of the top end of the y-axis
    int tY = originY - chartHeight;

    // Draw the x-axis
    graph.drawLine(originX, originY, rX, originY);

    // Draw the y-axis
    graph.drawLine(originX, originY, originX, tY);

    // Draw the x-axis label
    graph.drawString(xLabel, originX + chartWidth / 2, originY + AXIS_OFFSET / 2 + 3);

    // Rotate the font for the y-axis label
    Font original = graph.getFont();
    Font font = new Font(null, original.getStyle(), original.getSize());
    AffineTransform a = new AffineTransform();
    a.rotate(Math.toRadians(-90), 0, 0);
    Font rotatedFont = font.deriveFont(a);
    graph.setFont(rotatedFont);

    // Draw the y-axis label
    graph.drawString(yLabel, AXIS_OFFSET / 2 + 3, originY - chartHeight / 2);

    // Reset the font back to the original
    graph.setFont(original);
  }
}
