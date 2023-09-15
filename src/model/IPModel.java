package model;

import java.util.HashMap;
import java.util.Map;

/**
 * This represents the model of an PPM image as a 2D array of pixels. Performs operations
 * implemented in controller (see README.md for more detail on commands).
 */
public class IPModel implements IPModelInterface {
  private AbstractPixel[][] imageGrid;
  private Map<String, AbstractPixel[][]> prevImages;

  /**
   * takes in parameters to load in an image.
   *
   * @param board           the 2D representation of the array in terms of Pixels
   * @param nameOfCurrImage the image's name as referenced in the map
   * @throws IllegalArgumentException if the inputted (pathName or nameOfCurrImage) args are null
   */
  public IPModel(AbstractPixel[][] board, String nameOfCurrImage)
          throws IllegalArgumentException {
    if (board == null || nameOfCurrImage == null) {
      throw new IllegalArgumentException("One or more arguments is null");
    }
    this.imageGrid = board;
    this.prevImages = new HashMap<>();
    prevImages.put(nameOfCurrImage, imageGrid);
  }

  /**
   * get width (number of cols) of the board.
   *
   * @return width of board in pixels.
   */
  public int getWidth() {
    return this.imageGrid[0].length;
  }

  /**
   * get height (number of rows) of the board.
   *
   * @return height of board in pixels.
   */
  public int getHeight() {
    return this.imageGrid.length;
  }

  /**
   * Returns the pixel at a certain row and certain column.
   * @return pixel at a certain row and column in the image
   * @throws IllegalArgumentException if the pixel is out of bounds of the board
   */
  public AbstractPixel getPixel(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row >= imageGrid.length || col < 0 || col >= imageGrid[0].length) {
      throw new IllegalArgumentException("Invalid index");
    }
    return this.imageGrid[row][col];
  }

  public AbstractPixel[][] getPixelBoard() {
    return this.imageGrid;
  }

  /**
   * visualizes an individual color component from the picture.
   *
   * @param color the RGB value that we are visualizing
   * @throws IllegalArgumentException if the user inputs an invalid color
   */
  public void makeColorBoard(String color) throws IllegalArgumentException {
    AbstractPixel[][] colorBoard = new AbstractPixel[imageGrid.length][imageGrid[0].length];
    for (int row = 0; row < imageGrid.length; row++) {
      for (int col = 0; col < imageGrid[0].length; col++) {
        colorBoard[row][col] = this.imageGrid[row][col].makeColorPixel(color);
      }
    }
    this.imageGrid = colorBoard;
  }

  /**
   * Checks if any of the image's edited versions correspond with user input image name.
   *
   * @param currImageName name of the image that the user wants to perform operations on
   * @return true if there is a version of the Pixel[][] that corresponds with user input
   */
  public boolean getPrevImages(String currImageName) {
    AbstractPixel[][] currImageGrid = null;
    for (String elt : this.prevImages.keySet()) {
      if (elt.equalsIgnoreCase(currImageName)) {
        currImageGrid = prevImages.get(elt);
        break;
      }
    }
    if (currImageGrid == null) {
      return false;
    } else {
      this.imageGrid = currImageGrid;
      return true;
    }
  }

  /**
   * stores a version of the image after being modified by a command from the controller.
   *
   * @param currImageName reference name for the edited image (pixel array)
   */
  public void writePrevImages(String currImageName) {
    prevImages.put(currImageName, this.imageGrid);
  }

  /**
   * iterates through the image grid and assigns each pixel to its vertical reflection.
   */
  public void flipVertically() {
    AbstractPixel[][] verticalBoard = new AbstractPixel[imageGrid.length][imageGrid[0].length];
    for (int row = 0; row < imageGrid.length; row++) {
      for (int col = 0; col < imageGrid[0].length; col++) {
        verticalBoard[imageGrid.length - row - 1][col] = this.imageGrid[row][col];
      }
      System.out.print("\n");
    }
    this.imageGrid = verticalBoard;
  }

  /**
   * iterates through the image grid and assigns each pixel to its horizontal reflection.
   */
  public void flipHorizontally() {
    AbstractPixel[][] horizontalBoard = new AbstractPixel[imageGrid.length][imageGrid[0].length];
    for (int row = 0; row < imageGrid.length; row++) {
      for (int col = 0; col < imageGrid[0].length; col++) {
        horizontalBoard[row][imageGrid[0].length - col - 1] = this.imageGrid[row][col];
      }
    }
    this.imageGrid = horizontalBoard;
  }

  /**
   * adds a sharpen filter to the image board by applying a specific sharpen kernel to each pixel
   * and its neighbors.
   */
  public void sharpenPicture() {
    double[][] sharpen = new double[][]{{-(1.0 / 8), -(1.0 / 8), -(1.0 / 8), -(1.0 / 8),
        -(1.0 / 8)}, {-(1.0 / 8), (1.0 / 4), (1.0 / 4), (1.0 / 4), -(1.0 / 8)},
        {-(1.0 / 8), (1.0 / 4), (1.0), (1.0 / 4), -(1.0 / 8)},
        {-(1.0 / 8), (1.0 / 4), (1.0 / 4), (1.0 / 4), -(1.0 / 8)},
        {-(1.0 / 8), -(1.0 / 8), -(1.0 / 8), -(1.0 / 8), -(1.0 / 8)}};
    AbstractPixel[][] sharpenBoard = new AbstractPixel[imageGrid.length][imageGrid[0].length];
    for (int row = 0; row < imageGrid.length; row++) {
      for (int col = 0; col < imageGrid[0].length; col++) {
        sharpenBoard[row][col] = contrastPixel(row, col, sharpen);
      }
    }
    this.imageGrid = sharpenBoard;
  }

  private AbstractPixel contrastPixel(int row, int col, double[][] kernel) {
    AbstractPixel[] neighbors = new AbstractPixel[kernel.length * kernel[0].length];

    double redChannel = 0;
    double greenChannel = 0;
    double blueChannel = 0;

    int count = 0;


    for (int i = -(kernel.length / 2); i <= (kernel.length / 2); i++) {
      for (int j = -(kernel.length / 2); j <= (kernel.length / 2); j++) {
        try {
          neighbors[count] = getPixel(row + i, col + j);
        } catch (IllegalArgumentException e) {
          neighbors[count] = new AbstractPixel(0, 0, 0,
                  this.getPixel(row, col).getAlpha());
        }
        count++;

      }
    }

    //reset the counter variable
    count = 0;

    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel[0].length; j++) {
        redChannel = redChannel + (neighbors[count].getColorValue("red") * kernel[i][j]);
        greenChannel = greenChannel + (neighbors[count].getColorValue("green") * kernel[i][j]);
        blueChannel = blueChannel + (neighbors[count].getColorValue("blue") * kernel[i][j]);

        count++;
      }
    }

    AbstractPixel pixel = this.clampPixelValues((int) redChannel, (int) greenChannel,
            (int) blueChannel,
            imageGrid[row][col].getAlpha());
    return pixel;
  }


  /**
   * clamps pixel values so that they are no larger than 255 and nonnegative.
   *
   * @param red   the red value of the pixel
   * @param green the green value of the pixel
   * @param blue  the blue value of the pixel
   * @param alpha the alpha (transparency) value of the pixel
   * @return a new pixel with the clamped color and alpha values.
   */
  private AbstractPixel clampPixelValues(int red, int green, int blue, int alpha) {
    return new AbstractPixel(clampColorValue(red), clampColorValue(green),
            clampColorValue(blue), alpha);
  }

  /**
   * clamps an individual color value.
   *
   * @param color the color value
   * @return the clamped color value.
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
   * iterates through the board and brightens or darkens each pixel by the luma factor.
   *
   * @param lumaFactor the degree to which the image is brightened or darkened (positive = bright,
   *                   negative = dark)
   */
  public void changeBrightness(int lumaFactor) {
    AbstractPixel[][] brightImageGrid = new AbstractPixel[imageGrid.length][imageGrid[0].length];
    for (int row = 0; row < this.imageGrid.length; row++) {
      for (int col = 0; col < this.imageGrid[0].length; col++) {
        brightImageGrid[row][col] = this.imageGrid[row][col].changeBrightnessPixel(lumaFactor);
      }
    }
    this.imageGrid = brightImageGrid;
  }

  /**
   * adds a gaussian blur filter to the image board by applying a specific blur kernel to each pixel
   * and its neighbors.
   */
  public void gaussianBlur() {
    double[][] kernel = new double[][]{{(1.0 / 16), (1.0 / 8), (1.0 / 16)},
        {(1.0 / 8), (1.0 / 4), (1.0 / 8)},
        {(1.0 / 16), (1.0 / 8), (1.0 / 16)}};

    AbstractPixel[][] gaussianBlurBoard = new AbstractPixel[imageGrid.length][imageGrid[0].length];
    for (int row = 0; row < imageGrid.length; row++) {
      for (int col = 0; col < imageGrid[0].length; col++) {
        gaussianBlurBoard[row][col] = contrastPixel(row, col, kernel);
      }
    }
    this.imageGrid = gaussianBlurBoard;
  }

  /**
   * adds a grayscale filter to the image board by applying a specific grayscale kernel to
   * each pixel and its neighbors.
   */
  public void makeGrayscaleBoard() {
    AbstractPixel[][] grayscaleBoard = new AbstractPixel[imageGrid.length][imageGrid[0].length];
    double[][] grayscale = new double[][]{{0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}};
    for (int row = 0; row < imageGrid.length; row++) {
      for (int col = 0; col < imageGrid[0].length; col++) {
        grayscaleBoard[row][col] = this.imageGrid[row][col].createColorTonePixel(grayscale);
      }
    }
    this.imageGrid = grayscaleBoard;
  }

  /**
   * adds a Sepia filter to the image board by applying a specific sepia kernel to each pixel
   * and its neighbors.
   */
  public void makeSepiaBoard() {
    AbstractPixel[][] sepiaBoard = new AbstractPixel[imageGrid.length][imageGrid[0].length];
    double[][] sepia = new double[][]{{0.292, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}};
    for (int row = 0; row < imageGrid.length; row++) {
      for (int col = 0; col < imageGrid[0].length; col++) {
        sepiaBoard[row][col] = this.imageGrid[row][col].createColorTonePixel(sepia);
      }
    }
    this.imageGrid = sepiaBoard;
  }

  /**
   * provides data for the R, G, or B frequency histogram.
   * @param color the color (R, G, B) that we want to get the frequency for
   * @return array representing frequency of color values in the image
   */
  public int[] makeHistogram(String color) {
    int[] freq = new int[256];

    for (int row = 0; row < imageGrid.length; row++) {
      for (int col = 0; col < imageGrid[0].length; col++) {
        freq[imageGrid[row][col].getColorValue(color)] += 1;
      }
    }
    return freq;
  }

  /**
   * provides data for the intensity histogram.
   * @return array representing frequency of different luma factor values in the image
   */
  public int[] makeBrightnessHistogram() {
    AbstractPixel[][] tempImageGrid = imageGrid;
    makeGrayscaleBoard();
    int[] freq = new int[256];
    for (int row = 0; row < imageGrid.length; row++) {
      for (int col = 0; col < imageGrid[0].length; col++) {
        freq[imageGrid[row][col].getColorValue("red")] += 1;
      }
    }

    imageGrid = tempImageGrid;
    return freq;
  }

} //end of class




