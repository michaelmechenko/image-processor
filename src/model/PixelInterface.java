package model;

/**
 * This represents a pixel of an image file.
 */
public interface PixelInterface {

  /**
   * checks if another object is a pixel instance and is equal to this pixel instance.
   * @param other the other object that we are comparing this pixel instance to
   * @returns true if the other pixel is equal
   */
  public boolean equals(Object other);

  /**
   * returns a hash code based on the RGB values of the pixel.
   * @returns an int of the hash code
   */
  public int hashCode();

  /**
   * gets the color value of the pixel.
   * @param color the color value to be returned
   * @returns the red field of this pixel
   * @throws IllegalArgumentException if the color is not a field in this pixel
   */
  public int getColorValue(String color) throws IllegalArgumentException;

  /**
   * returns a pixel representative of its brightness based on luma formula.
   * @return a grayscaled version of the pixel
   */
  public AbstractPixel createColorTonePixel(double[][] filter);

  /**
   * returns a pixel representative of its brightness based on luma formula.
   * @return a grayscaled version of the pixel
   */
  public AbstractPixel makeColorPixel(String color) throws IllegalArgumentException;

  /**
   * changes the brightness of the pixel by the luma factor.
   * @param lumaFactor the degree to which the pixel is brightened or darkened
   * @return pixel after being brightened by the luma factor
   */
  public AbstractPixel changeBrightnessPixel(int lumaFactor);
}
