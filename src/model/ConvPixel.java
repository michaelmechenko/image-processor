package model;

/**
 * represents a Pixel for an image of a conventional file type (i.e .jpg, .bmp, .png).
 */
public class ConvPixel extends AbstractPixel {

  /**
   * constructs a ConvPixel with given RGB values and an alpha (transparency) value.
   * @param red the red value
   * @param green the green value
   * @param blue the blue value
   * @param alpha the transparency value
   * @throws IllegalArgumentException if any of the given color values are invalid (negative or
   *       greater than 255) or if the alpha value is negative or greater than 1
   */
  public ConvPixel(int red, int green, int blue, int alpha) throws IllegalArgumentException {
    super(red, green, blue, alpha);
  }



}

