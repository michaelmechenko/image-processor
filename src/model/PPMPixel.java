package model;

/**
 * this class represents a single Pixel in an image. An image is composed of a 2D array of Pixels,
 * so image modification involves modification of each Pixel.
 */
public class PPMPixel extends AbstractPixel {

  /**
   * constructs a Pixel with given RGB values.
   * @param red the red value
   * @param green the green value
   * @param blue the blue value
   * @throws IllegalArgumentException if any of the given color values are invalid (negative or
   *       greater than 255)
   */
  public PPMPixel(int red, int green, int blue) throws IllegalArgumentException {
    super(red, green, blue, 255);
  }
}
