package model;

/**
 * This class represents an abstract pixel in an image file.
 */
public class AbstractPixel implements PixelInterface {

  private int red;
  private int green;
  private int blue;

  private int alpha;

  /**
   * Constructs an AbstractPixel with the given RGB values and an alpha value.
   * 
   * @param red   The red value (0-255)
   * @param green The green value (0-255)
   * @param blue  The blue value (0-255)
   * @param alpha The transparency value (0-1)
   * @throws IllegalArgumentException If any of the given color values are invalid
   *                                  (negative or greater than 255) or
   *                                  if the alpha value is negative or greater
   *                                  than 1
   */
  public AbstractPixel(int red, int green, int blue, int alpha) throws IllegalArgumentException {

    if (invalidColorValue(red) || invalidColorValue(green) || invalidColorValue(blue)
        || invalidColorValue(alpha)) {
      throw new IllegalArgumentException(
          "One of the RGB values is invalid (either negative or above 255) or the alpha value is negative/greater than 1");
    }
    this.red = red;
    this.blue = blue;
    this.green = green;
    this.alpha = alpha;
  }

  /**
   * Checks if another object is a pixel instance and is equal to this pixel
   * instance.
   *
   * @param other the other object that we are comparing this pixel instance to
   * @return true if the other pixel is equal
   */
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof AbstractPixel)) {
      return false;
    }
    AbstractPixel p = (AbstractPixel) other;
    return p.getColorValue("red") == this.red
        && p.getColorValue("green") == green
        && p.getColorValue("blue") == this.blue
        && p.getAlpha() == this.alpha;
  }

  /**
   * This method returns a hash code based on the RGB values of the pixel.
   * 
   * @return An integer representing the hash code.
   */
  public int hashCode() {
    // Multiply the RGB values of the pixel by the alpha and a constant factor to
    // get the hash code
    return (int) (this.red * this.green * this.blue * this.alpha) * 62791;
  }

  /**
   * This method gets the color value of the pixel.
   * 
   * @param color the color value to be returned
   * @return the color value of the specified color
   * @throws IllegalArgumentException if the color is not a valid color
   */
  public int getColorValue(String color) throws IllegalArgumentException {
    if (color.equalsIgnoreCase("red")) {
      return this.red; // return the red field of this pixel
    } else if (color.equalsIgnoreCase("green")) {
      return this.green; // return the green field of this pixel
    } else if (color.equalsIgnoreCase("blue")) {
      return this.blue; // return the blue field of this pixel
    } else {
      throw new IllegalArgumentException("Invalid color"); // throw exception if the color is not valid
    }
  }

  /**
   * This method returns the alpha value of the pixel, which determines the
   * transparency on a scale of 0.0 (transparent)
   * to 1.0 (opaque).
   *
   * @return The alpha value of the pixel.
   */
  public int getAlpha() {
    return this.alpha;
  }

  /**
   * Visualizes the color of just one of the RGB values: red, green, or blue.
   * 
   * @param color the color that is being visualized in the image (valid values:
   *              "red", "green", "blue")
   * @return the pixel that visualizes the color
   * @throws IllegalArgumentException if the color is not valid
   */
  public AbstractPixel makeColorPixel(String color) throws IllegalArgumentException {
    int temp_red = 0;
    int temp_blue = 0;
    int temp_green = 0;

    if (color.equalsIgnoreCase("red")) {
      temp_red = this.red;
    } else if (color.equalsIgnoreCase("green")) {
      temp_green = this.green;
    } else if (color.equalsIgnoreCase("blue")) {
      temp_blue = this.blue;
    } else {
      throw new IllegalArgumentException("Invalid color");
    }

    AbstractPixel temp_pixel = new AbstractPixel(temp_red, temp_green, temp_blue, this.alpha);
    return temp_pixel;
  }

  /**
   * Returns true if the RGB value is not negative or greater than 255.
   * Used to check the constructor input RGB values to see if they are valid.
   * Throws an exception if not.
   * 
   * @param value The integer value of either the R, G, or B value in RGB.
   * @return True if it's a valid RGB value, false otherwise.
   */
  private boolean invalidColorValue(int value) {
    return value < 0 || value > 255;
  }

  /**
   * Clamps the color values of the pixel and returns a new AbstractPixel object.
   * The color values are clamped to ensure they are within the valid range.
   * 
   * @return A new AbstractPixel object with clamped color values.
   */
  public AbstractPixel clampPixel() {
    // Clamp the red, green, and blue color values
    int clampedRed = clampColorValue(this.red);
    int clampedGreen = clampColorValue(this.green);
    int clampedBlue = clampColorValue(this.blue);

    // Create and return a new AbstractPixel object with clamped color values
    return new AbstractPixel(clampedRed, clampedGreen, clampedBlue, this.alpha);
  }

  /**
   * Brightens or darkens an RGB value, accounting for values above 255 (max
   * value) or negative values.
   * Defaults to 255 and 0, respectively.
   * 
   * @param color      the color value that is being edited
   * @param lumaFactor the degree to which the color value is being brightened
   * @return the edited color value
   */
  private int changeBrightnessColor(int color, int lumaFactor) {
    int temp_color = color + lumaFactor;
    return clampColorValue(temp_color);
  }

  /**
   * Changes the brightness of the pixel by the luma factor.
   * 
   * @param lumaFactor the degree to which the pixel is brightened or darkened
   * @return the pixel after being brightened by the luma factor
   */
  public AbstractPixel changeBrightnessPixel(int lumaFactor) {
    // Create a temporary pixel with the brightness changed for each color channel
    AbstractPixel temp_pixel = new AbstractPixel(
        changeBrightnessColor(red, lumaFactor),
        changeBrightnessColor(green, lumaFactor),
        changeBrightnessColor(blue, lumaFactor),
        this.alpha);

    // Return the temporary pixel
    return temp_pixel;
  }

  /**
   * Clamps the color value between 0 and 255.
   * 
   * @param color The color value to clamp.
   * @return The clamped color value.
   */
  private int clampColorValue(int color) {
    if (color > 255) {
      color = 255;
    } else if (color < 0) {
      color = 0;
    }
    return color;
  }

  /**
   * Applies a given filter to an image for kernel-based image processing
   * operations.
   * 
   * @param filter the kernel filter to apply to the image (i.e. grayscale filter,
   *               sepia tone filter, etc).
   * @return a new AbstractPixel object representing the color tone pixel after
   *         applying the filter.
   */
  public AbstractPixel createColorTonePixel(double[][] filter) {

    // Create a 2D array to store the current pixel values
    int[][] pixelValues = new int[][] { { this.red, this.green, this.blue },
        { this.red, this.green, this.blue },
        { this.red, this.green, this.blue } };

    // Create an array to store the final color channel values
    int[] channel = new int[3];

    // Apply the filter to each color channel of the pixel
    for (int i = 0; i < filter.length; i++) {
      double colorChannel = 0;
      for (int j = 0; j < filter[0].length; j++) {
        // Multiply the filter value with the corresponding pixel value and accumulate
        // the result
        colorChannel += filter[i][j] * pixelValues[i][j];
      }
      // Clamp the color channel value to ensure it is within the valid range
      channel[i] = this.clampColorValue((int) colorChannel);
    }

    // Create and return a new AbstractPixel object with the calculated color
    // channel values
    return new AbstractPixel(channel[0], channel[1], channel[2], this.alpha);
  }

}
