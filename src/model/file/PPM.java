package model.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import model.AbstractPixel;
import model.PPMPixel;

/**
 * this class allows for reading from and writing out PPM files.
 */
public class PPM implements FileInterface {

  /**
   * reads in image and stores values of each pixel in a 2D array of Pixels.
   * 
   * @param filename the file path that the image to be read in is currently
   *                 stored in
   * @return 2D array of Pixels
   * @throws IllegalStateException if file path is invalid (file to be loaded
   *                               can't be found)
   */
  public PPMPixel[][] readFile(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (IOException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    // read the file line by line, and populate a string. This will throw away any
    // comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    // now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();

    PPMPixel[][] board = new PPMPixel[height][width];
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        PPMPixel temp = new PPMPixel(r, g, b);
        board[row][col] = temp;
      }
    }
    sc.close();

    return board;
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

    String output = "";
    int height = board.length;
    int width = board[0].length;

    output += "P3\n" + width + " " + height + "\n255\n";
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int red = board[row][col].getColorValue("red");
        int blue = board[row][col].getColorValue("blue");
        int green = board[row][col].getColorValue("green");
        String tempString = red + " " + green + " " + blue + " ";
        output += tempString;
      }
      output += "\n";
    }

    try {
      File file = new File(filename);
      FileOutputStream fos = new FileOutputStream(file, false);
      fos.write(new String(output).getBytes());
      fos.close();
    } catch (IOException e) {
      throw new IllegalStateException("");
    }

  }
}
