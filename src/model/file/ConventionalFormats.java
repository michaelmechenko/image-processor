package model.file;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.AbstractPixel;
import model.ConvPixel;

/**
 * This class allows for reading from and writing out PPM, PNG, BMP, and JPG
 * files.
 */
public class ConventionalFormats implements FileInterface {

  /**
   * Reads an image file and returns a 2D array of AbstractPixel objects
   * representing the image.
   * 
   * @param filename the name of the file to read
   * @return a 2D array of AbstractPixel objects representing the image
   * @throws IllegalStateException if the file cannot be read
   */
  public AbstractPixel[][] readFile(String filename) throws IllegalStateException {

    // reads the image file
    try {
      FileInputStream fs = new FileInputStream(filename);
      BufferedImage image = ImageIO.read(fs);
      int rows = image.getHeight();
      int cols = image.getWidth();
      AbstractPixel[][] readArray = new AbstractPixel[rows][cols];

      // iterate over each pixel in the image
      for (int row = 0; row < readArray.length; row++) {
        for (int col = 0; col < readArray[0].length; col++) {
          // get the RGB values of the pixel
          Color rgbValues = new Color(image.getRGB(col, row));
          // create a new ConvPixel object with the RGB values
          readArray[row][col] = new ConvPixel(rgbValues.getRed(), rgbValues.getGreen(),
              rgbValues.getBlue(), rgbValues.getAlpha());
        }
      }
      return readArray;

    } catch (IllegalArgumentException | IOException e) {
      throw new IllegalStateException("Can't read in provided file name");
    }
  }

  /**
   * Translates the 2D array of Pixels into a string that is outputted into a
   * file, in order to save the file. Allows user to save a file under a different
   * file type than it was originally created under.
   *
   * @param board      the 2D array of Pixels for the image
   * @param formatName the file type of the image (.ppm, .png, .bmp, or .jpg)
   * @param filename   the file path that the image should be stored to
   * @throws IllegalStateException if the file cannot be written
   */
  public void writeFile(AbstractPixel[][] board, String formatName, String filename)
      throws IllegalStateException {
    int height = board.length;
    int width = board[0].length;

    // Create a new BufferedImage with the specified width and height
    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    // Iterate over each pixel in the 2D array and set the corresponding RGB values
    // in the newImage
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int colorRed = board[i][j].getColorValue("red");
        int colorGreen = board[i][j].getColorValue("green");
        int colorBlue = board[i][j].getColorValue("blue");
        int alpha = board[i][j].getAlpha();
        int p = (alpha << 24) | (colorRed << 16) | (colorGreen << 8) | colorBlue;
        newImage.setRGB(j, i, p);
      }
    }

    try {
      // Retrieve the output file
      File outputfile = new File(filename);

      // Write the newImage to the output file using the specified formatName
      ImageIO.write(newImage, formatName, outputfile);
    } catch (IOException e) {
      throw new IllegalStateException("Can't write out provided file name");
    }
  }

}
