package model;

/**
 * Represents a pixel in a convolutional image.
 */
public class ConvPixel extends AbstractPixel {

  /**
   * Constructs a ConvPixel object with the specified color values.
   *
   * @param red   the red component of the pixel's color (0-255)
   * @param green the green component of the pixel's color (0-255)
   * @param blue  the blue component of the pixel's color (0-255)
   * @param alpha the alpha component of the pixel's color (0-255)
   * @throws IllegalArgumentException if any of the color values are outside the
   *                                  valid range
   */
  public ConvPixel(int red, int green, int blue, int alpha) throws IllegalArgumentException {
    super(red, green, blue, alpha);
  }
}