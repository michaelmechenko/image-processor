package model;

/**
 * This represents a pixel of an image file.
 */
public interface PixelInterface {

  /**
   * Checks if another object is a pixel instance and is equal to this pixel
   * instance.
   * 
   * @param other the other object that we are comparing this pixel instance to
   * @return true if the other pixel is equal
   */
  public boolean equals(Object other);

  /**
   * Returns a hash code based on the RGB values of the pixel.
   * 
   * @return an int of the hash code
   */
  public int hashCode();

  /**
   * Gets the color value of the pixel.
   * 
   * @param color the color value to be returned
   * @return the red field of this pixel
   * @throws IllegalArgumentException if the color is not a field in this pixel
   */
  public int getColorValue(String color) throws IllegalArgumentException;

  /**
   * Returns a pixel representative of its brightness based on luma formula.
   * 
   * @param filter the filter to be applied to the pixel
   * @return a grayscaled version of the pixel
   */
  public AbstractPixel createColorTonePixel(double[][] filter);

  /**
   * Returns a pixel representative of its brightness based on luma formula.
   * 
   * @param color the color value to be used for the pixel
   * @return a pixel with the specified color
   * @throws IllegalArgumentException if the color is not a valid color
   */
  public AbstractPixel makeColorPixel(String color) throws IllegalArgumentException;

  /**
   * Changes the brightness of the pixel by the luma factor.
   * 
   * @param lumaFactor the degree to which the pixel is brightened or darkened
   * @return pixel after being brightened by the luma factor
   */
  public AbstractPixel changeBrightnessPixel(int lumaFactor);
}