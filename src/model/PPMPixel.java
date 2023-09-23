package model;

/**
 * This class represents a single Pixel in an image. An image is composed of a
 * 2D array of Pixels,
 * so image modification involves modification of each Pixel.
 */
public class PPMPixel extends AbstractPixel {

  /**
   * Constructs a Pixel with given RGB values.
   * 
   * @param red   The red value.
   * @param green The green value.
   * @param blue  The blue value.
   * @throws IllegalArgumentException If any of the given color values are invalid
   *                                  (negative or
   *                                  greater than 255).
   */
  public PPMPixel(int red, int green, int blue) throws IllegalArgumentException {
    super(red, green, blue, 255);
  }
}