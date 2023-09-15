package model.file;

import model.AbstractPixel;

/**
 * this class allows for reading from and writing out conventional file formats
 * and PPM files.
 */
public interface FileInterface {

  /**
   * reads in image and stores values of each pixel in a 2D array of Pixels.
   * 
   * @param filename the file path that the image to be read in is currently
   *                 stored in
   * @return 2D array of Pixels
   * @throws IllegalStateException if file path is invalid (file to be loaded
   *                               can't be found)
   */
  public AbstractPixel[][] readFile(String filename) throws IllegalStateException;

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
      throws IllegalStateException;
}
