package model;

/**
 * this class represents a Pixel for an image.
 */
public class AbstractPixel implements PixelInterface {

  private int red;
  private int green;
  private int blue;

  private int alpha;

  /**
   * constructs a ConvPixel with given RGB values and an alpha value.
   * @param red the red value
   * @param green the green value
   * @param blue the blue value
   * @param alpha the transparency value
   * @throws IllegalArgumentException if any of the given color values are invalid (negative or
   *       greater than 255) or if the alpha value is negative or greater than 1
   */
  public AbstractPixel(int red, int green, int blue, int alpha) throws IllegalArgumentException {

    if (invalidColorValue(red) || invalidColorValue(green) || invalidColorValue(blue)
            || invalidColorValue(alpha)) {
      throw new IllegalArgumentException("One of the RGB values is invalid (either negative or "
              + "above 255) or the alpha value is negative/greater than 0");
    }
    this.red = red;
    this.blue = blue;
    this.green = green;
    this.alpha = alpha;
  }

  /**
   * checks if another object is a pixel instance and is equal to this pixel instance.
   * @param other the other object that we are comparing this pixel instance to
   * @returns true if the other pixel is equal
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
   * returns a hash code based on the RGB values of the pixel.
   * @returns an int of the hash code
   */
  public int hashCode() {
    return (int)(this.red * this.green * this.blue * this.alpha) * 62791;
  }

  /**
   * gets the color value of the pixel.
   * @param color the color value to be returned
   * @returns the red field of this pixel
   * @throws IllegalArgumentException if the color is not a field in this pixel
   */
  public int getColorValue(String color) throws IllegalArgumentException {
    if (color.equalsIgnoreCase("red")) {
      return this.red;
    }
    else if (color.equalsIgnoreCase("green")) {
      return this.green;
    }
    else if (color.equalsIgnoreCase("blue")) {
      return this.blue;
    }
    else {
      throw new IllegalArgumentException("Invalid color");
    }
  }

  /**
   * returns the alpha value of the pixel. (detemines transperency on a scale of 0.0 (transparent)
   * to 1.0 (opaque).
   */
  public int getAlpha() {
    return this.alpha;
  }


  /**
   * visualizes the color of just one of the RGB valuesP: red, green, or blue.
   * @param color the color that is being visualizes in the image
   * @return the pixel of that visualizes the color
   * @throws IllegalArgumentException if the color is not valid
   */
  public AbstractPixel makeColorPixel(String color) throws IllegalArgumentException {
    int temp_red = 0;
    int temp_blue = 0;
    int temp_green = 0;

    if (color.equalsIgnoreCase("red")) {
      temp_red = this.red;
    }
    else if (color.equalsIgnoreCase("green")) {
      temp_green = this.green;
    }
    else if (color.equalsIgnoreCase("blue")) {
      temp_blue = this.blue;
    }
    else {
      throw new IllegalArgumentException("Invalid color");
    }
    AbstractPixel temp_pixel = new AbstractPixel(temp_red, temp_green, temp_blue, this.alpha);
    return temp_pixel;
  }


  /**
   * returns if the RGB value is not negative or greater than 255. used to check the constructor
   * input RGB values to see if they are valid, throws an exception if not.
   * @param value the integer value of either the R, G, or B value in RGB
   * @return return if it's a valid RGB value
   */
  private boolean invalidColorValue(int value) {
    return value < 0 || value > 255;
  }





  public AbstractPixel clampPixel() {
    return new AbstractPixel(clampColorValue(this.red), clampColorValue(this.green),
            clampColorValue(this.blue), this.alpha);
  }

  /**
   * brightens or darkens an RGB value, accounts for if it is above 255 (max value) or negative
   * and defaults to 255 and 0, respectively.
   * @param color the color value that is being edited
   * @param lumaFactor the degree to which the color value is being brightened
   * @returns the edited color value
   */
  private int changeBrightnessColor(int color, int lumaFactor) {
    int temp_color = color + lumaFactor;
    return clampColorValue(temp_color);
  }

  /**
   * changes the brightness of the pixel by the luma factor.
   * @param lumaFactor the degree to which the pixel is brightened or darkened
   * @return pixel after being brightened by the luma factor
   */
  public AbstractPixel changeBrightnessPixel(int lumaFactor) {
    AbstractPixel temp_pixel = new AbstractPixel(changeBrightnessColor(red, lumaFactor),
            changeBrightnessColor(green, lumaFactor), changeBrightnessColor(blue, lumaFactor),
            this.alpha);
    return temp_pixel;
  }


  private int clampColorValue(int color) {
    if (color > 255) {
      color = 255;
    }
    else if (color < 0) {
      color = 0;
    }
    return color;
  }

  /**
   * overlays a given filter on an image for kernel-based image processing operations.
   * @param filter the kernel filter to apply to the image (i.e grayscale filter, sepia tone filter,
   *               etc).
   */
  public AbstractPixel createColorTonePixel(double[][] filter) {

    int[][] pixelValues = new int[][] {{this.red, this.green, this.blue},
        {this.red, this.green, this.blue},
        {this.red, this.green, this.blue}};
    int[] channel = new int[3];
    for (int i = 0; i < filter.length; i++) {
      double colorChannel = 0;
      for (int j = 0; j < filter[0].length; j++) {
        colorChannel += filter[i][j] * pixelValues[i][j];
      }
      channel[i] = this.clampColorValue((int)colorChannel);
    }

    return new AbstractPixel(channel[0], channel[1], channel[2], this.alpha);
  }

}

