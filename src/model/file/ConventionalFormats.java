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
 * this class allows for reading from and writing out conventional file formats,
 * specifically .jpg,
 * .png, and .bmp.
 */
public class ConventionalFormats implements FileInterface {

  /**
   * reads in image and stores values of each pixel in a 2D array of Pixels.
   * 
   * @param filename the file path that the image to be read in is currently
   *                 stored in
   * @return 2D array of Pixels
   * @throws IllegalStateException if file path is invalid (file to be loaded
   *                               can't be found)
   */
  public AbstractPixel[][] readFile(String filename) throws IllegalStateException {

    // reads it in
    try {
      FileInputStream fs = new FileInputStream(filename);
      BufferedImage image = ImageIO.read(fs);
      int rows = image.getHeight();
      int cols = image.getWidth();
      AbstractPixel[][] readArray = new AbstractPixel[rows][cols];

      for (int row = 0; row < readArray.length; row++) {
        for (int col = 0; col < readArray[0].length; col++) {
          Color rgbValues = new Color(image.getRGB(col, row));
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
   * translates the 2D array of Pixels into a string that is outputted into a
   * file, in order to
   * save the file. allows user to save a file under a different file type than it
   * was originally
   * created under.
   *
   * @param board      the 2D array of Pixels for the image
   * @param formatName the file type of the image (.ppm, .png, .bmp, or .jpg)
   * @param filename   the file path that the image should be stored to
   */
  public void writeFile(AbstractPixel[][] board, String formatName, String filename)
      throws IllegalStateException {
    int height = board.length;
    int width = board[0].length;
    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
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
      // retrieve image
      File outputfile = new File(filename);
      ImageIO.write(newImage, formatName, outputfile);
    } catch (IOException e) {
      throw new IllegalStateException("Can't write out provided file name");
    }
  }

}
