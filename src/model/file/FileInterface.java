package model.file;

import model.AbstractPixel;

/**
 * Interface for reading and writing files
 */
public interface FileInterface {

  /**
   * Reads an image file and stores the pixel values in a 2D array.
   * 
   * @param filename The file path of the image to be read.
   * @return A 2D array of pixels.
   * @throws IllegalStateException If the file path is invalid and the file cannot
   *                               be found.
   */
  public AbstractPixel[][] readFile(String filename) throws IllegalStateException;

  /**
   * Writes the 2D array of Pixels into a file, allowing the user to save the file
   * under a different file type than it was originally created under.
   *
   * @param board      The 2D array of Pixels for the image.
   * @param formatName The file type of the image (.ppm, .png, .bmp, or .jpg).
   * @param filename   The file path where the image should be stored.
   * @throws IllegalStateException If there is an error in writing the file.
   */
  public void writeFile(AbstractPixel[][] board, String formatName, String filename)
      throws IllegalStateException;
}
