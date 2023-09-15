package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Rectangle;

/**
 * this class represents a histogram of an Image. A histogram shows the frequency of R,G,B,Intensity
 * values in an image based on how many pixels in the image have that value.
 */
public class Histogram extends JPanel {

  public static final int AXIS_OFFSET = 25;
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
   * constructs a Histogram based on the image data.
   * @param list array of size 256 representing the frequency of pixel values in the image (from
   *             pixel value 0 to 255)
   * @param xl label for the x axis
   * @param yl label for the y axis
   * @param color the color that we want to find the frequency for
   */
  public Histogram(int[] list, String xl, String yl, Color color) {
    super();
    this.list = list;
    xLabel = xl;
    yLabel = yl;
    this.color = color;
  }

  /**
   * makes the histogram by drawing the bars and axes of the graph.
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
   * calculates the size of the histogram.
   */
  private void computeSize() {
    int width = this.getWidth();
    int height = this.getHeight();

    chartWidth = width - 2 * AXIS_OFFSET;
    chartHeight = height - 2 * AXIS_OFFSET - TOP_BUFFER;

    originX = AXIS_OFFSET;
    originY = height - AXIS_OFFSET;
  }

  /**
   * draws bars for the histogram.
   *
   * @param g2 the graphics to be displayed upon
   */
  public void drawChartBars(Graphics2D g2) {

    Color original = g2.getColor();

    double numberOfBars = list.length;
    double max = 0;

    for (Integer wrapper : list) {
      if (max < wrapper) {
        max = wrapper;
      }
    }

    int barWidth = (int) (chartWidth / numberOfBars);

    int value;
    int height;
    int xLeft;
    int yTopLeft;

    for (int i = 0; i < list.length; i++) {
      value = list[i];

      double height2 = (value / max) * chartHeight;
      height = (int) height2;

      xLeft = AXIS_OFFSET + i * barWidth;
      yTopLeft = originY - height;
      Rectangle rec = new Rectangle(xLeft, yTopLeft, barWidth, height);

      g2.setColor(color);
      g2.fill(rec);

    }
    g2.setColor(original);
  }

  /**
   * draws chart axes for the histogram.
   *
   * @param graph the graphics to be displayed upon
   */
  private void drawChartAxes(Graphics2D graph) {

    int rX = originX + chartWidth;
    int tY = originY - chartHeight;

    graph.drawLine(originX, originY, rX, originY);

    graph.drawLine(originX, originY, originX, tY);

    graph.drawString(xLabel, originX + chartWidth / 2, originY + AXIS_OFFSET / 2 + 3);

    Font original = graph.getFont();

    Font font = new Font(null, original.getStyle(), original.getSize());
    AffineTransform a = new AffineTransform();
    a.rotate(Math.toRadians(-90), 0, 0);
    Font rotatedFont = font.deriveFont(a);
    graph.setFont(rotatedFont);
    graph.drawString(yLabel, AXIS_OFFSET / 2 + 3, originY - chartHeight / 2);
    graph.setFont(original);
  }
}
