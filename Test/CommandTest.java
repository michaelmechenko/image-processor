import org.junit.Before;
import org.junit.Test;


import command.ChangeBrightness;
import command.GaussianBlur;
import command.HorizontalFlip;
import command.SepiaTone;
import command.Sharpen;
import command.VerticalFlip;
import controller.IPController;
import model.AbstractPixel;
import model.ConvPixel;
import model.IPModel;
import model.PPMPixel;
import model.file.ConventionalFormats;
import model.file.PPM;

import static org.junit.Assert.assertTrue;

/**
 * specifically tests the command design of the controller.
 */
public class CommandTest {

  IPController controller;
  IPModel model;

  //all the pixels in the test-2 original image
  PPMPixel pixel1;
  PPMPixel pixel2;
  PPMPixel pixel3;
  PPMPixel pixel4;
  PPMPixel pixel5;
  PPMPixel pixel6;
  PPMPixel pixel7;
  PPMPixel pixel8;
  PPMPixel pixel9;
  PPMPixel pixel10;
  PPMPixel pixel11;
  PPMPixel pixel12;

  PPMPixel pixel1Bright;
  PPMPixel pixel2Bright;
  PPMPixel pixel3Bright;
  PPMPixel pixel4Bright;
  PPMPixel pixel5Bright;
  PPMPixel pixel6Bright;
  PPMPixel pixel7Bright;
  PPMPixel pixel8Bright;
  PPMPixel pixel9Bright;
  PPMPixel pixel10Bright;
  PPMPixel pixel11Bright;
  PPMPixel pixel12Bright;

  PPMPixel pixel1Darken;
  PPMPixel pixel2Darken;
  PPMPixel pixel3Darken;
  PPMPixel pixel4Darken;
  PPMPixel pixel5Darken;
  PPMPixel pixel6Darken;
  PPMPixel pixel7Darken;
  PPMPixel pixel8Darken;
  PPMPixel pixel9Darken;
  PPMPixel pixel10Darken;
  PPMPixel pixel11Darken;
  PPMPixel pixel12Darken;

  PPMPixel[][] imageGrid;
  PPMPixel[][] imageGridFlipHorizontal;
  PPMPixel[][] imageGridFlipVertical;

  PPMPixel[][] imageGridBright;
  PPMPixel[][] imageGridDarken;

  //pixels for a 3x4 pixel png image
  ConvPixel convPixel1;
  ConvPixel convPixel2;
  ConvPixel convPixel3;
  ConvPixel convPixel4;
  ConvPixel convPixel5;
  ConvPixel convPixel6;
  ConvPixel convPixel7;
  ConvPixel convPixel8;
  ConvPixel convPixel9;
  ConvPixel convPixel10;
  ConvPixel convPixel11;
  ConvPixel convPixel12;

  //png pixels blurred (all pixels will have the same RGB values)
  ConvPixel convPixelBlur;

  //png pixels sharpened
  ConvPixel convPixelSharpen;

  //pixel sharpen board
  ConvPixel[][] boardSharpen;

  //png pixels Sepia
  ConvPixel convPixel1Sepia;
  ConvPixel convPixel2Sepia;
  ConvPixel convPixel3Sepia;
  ConvPixel convPixel4Sepia;
  ConvPixel convPixel5Sepia;
  ConvPixel convPixel6Sepia;
  ConvPixel convPixel7Sepia;
  ConvPixel convPixel8Sepia;
  ConvPixel convPixel9Sepia;
  ConvPixel convPixel10Sepia;
  ConvPixel convPixel11Sepia;
  ConvPixel convPixel12Sepia;

  //board for sepia toned png pixels
  ConvPixel[][] sepiaBoard;

  //board for blurred png pixels
  ConvPixel[][] blurBoard;


  @Before
  public void testSetUp() {

    //all the pixels for the png image
    convPixel1 = new ConvPixel(137,210,220, 255);
    convPixel2 = new ConvPixel(143,45,86, 255);
    convPixel3 = new ConvPixel(239,121,138, 255);
    convPixel4 = new ConvPixel(16,29,66, 255);
    convPixel5 = new ConvPixel(247,169,168, 255);
    convPixel6 = new ConvPixel(244,249,233, 255);
    convPixel7 = new ConvPixel(144,112,140, 255);
    convPixel8 = new ConvPixel(12,164,165, 255);
    convPixel9 = new ConvPixel(232,141,103, 255);
    convPixel10 = new ConvPixel(180,185,171, 255);
    convPixel11 = new ConvPixel(75,65, 43, 255);
    convPixel12 = new ConvPixel(145,99,102, 255);


    //all the png image pixels blurred
    convPixelBlur = new ConvPixel(69, 72, 84, 255);

    //png images sharpen pixel
    convPixelSharpen = new ConvPixel(129, 172, 202, 255);

    //all the png image pixels altered by sepia tone
    convPixel1Sepia = new ConvPixel(243, 228, 178, 255);
    convPixel2Sepia = new ConvPixel(92, 95, 74, 255);
    convPixel3Sepia = new ConvPixel(188, 189, 147, 255);
    convPixel4Sepia = new ConvPixel(39, 36, 28, 255);
    convPixel5Sepia = new ConvPixel(233, 230, 179, 255);
    convPixel6Sepia = new ConvPixel(255, 255, 229, 255);
    convPixel7Sepia = new ConvPixel(154, 150, 117, 255);
    convPixel8Sepia = new ConvPixel(160, 144, 112, 255);
    convPixel9Sepia = new ConvPixel(195, 194, 151, 255);
    convPixel10Sepia = new ConvPixel(227, 218, 170, 255);
    convPixel11Sepia = new ConvPixel(80, 77, 60, 255);
    convPixel12Sepia = new ConvPixel(137, 135, 105, 255);

    //board sharpen
    boardSharpen = new ConvPixel[][] {
            {convPixelSharpen, convPixelSharpen, convPixelSharpen},
            {convPixelSharpen, convPixelSharpen, convPixelSharpen},
            {convPixelSharpen, convPixelSharpen, convPixelSharpen},
            {convPixelSharpen, convPixelSharpen, convPixelSharpen},
    };

    //sepia board
    sepiaBoard = new ConvPixel[][] {
            {convPixel1Sepia, convPixel2Sepia, convPixel3Sepia},
            {convPixel4Sepia, convPixel5Sepia, convPixel6Sepia},
            {convPixel7Sepia, convPixel8Sepia, convPixel9Sepia},
            {convPixel10Sepia, convPixel11Sepia, convPixel12Sepia}
    };

    //blur board
    blurBoard = new ConvPixel[][] {
            {convPixelBlur, convPixelBlur, convPixelBlur},
            {convPixelBlur, convPixelBlur, convPixelBlur},
            {convPixelBlur, convPixelBlur, convPixelBlur},
            {convPixelBlur, convPixelBlur, convPixelBlur}
    };



    //the model we'll use for all the PPM image tests

    this.model = new IPModel((new PPM()).readFile("test-2.ppm"), "test2");

    //all pixels
    pixel1 = new PPMPixel(137,210,220);
    pixel2 = new PPMPixel(143,45,86);
    pixel3 = new PPMPixel(239,121,138);
    pixel4 = new PPMPixel(16,29,66);
    pixel5 = new PPMPixel(247,169,168);
    pixel6 = new PPMPixel(244,249,233);
    pixel7 = new PPMPixel(144,112,140);
    pixel8 = new PPMPixel(12,164,165);
    pixel9 = new PPMPixel(232,141,103);
    pixel10 = new PPMPixel(180,185,171);
    pixel11 = new PPMPixel(75,65, 43);
    pixel12 = new PPMPixel(145,99,102);


    imageGrid = new PPMPixel[][] {{pixel1, pixel2, pixel3},
        {pixel4, pixel5, pixel6},
        {pixel7, pixel8, pixel9},
        {pixel10, pixel11, pixel12}};

    //board when the image has been flipped horizontally
    imageGridFlipHorizontal = new PPMPixel[][] {{pixel3, pixel2, pixel1},
        {pixel6, pixel5, pixel4},
        {pixel9, pixel8, pixel7},
        {pixel12, pixel11, pixel10}};


    //board when the image has been flipped vertically
    imageGridFlipVertical = new PPMPixel[][] {{pixel10, pixel11, pixel12},
        {pixel7, pixel8, pixel9},
        {pixel4, pixel5, pixel6},
        {pixel1, pixel2, pixel3}};

    //for testing changing brightness

    //all pixels
    pixel1Bright = new PPMPixel(147,220,230);
    pixel2Bright = new PPMPixel(153,55,96);
    pixel3Bright = new PPMPixel(249,131,148);
    pixel4Bright = new PPMPixel(26,39,76);
    pixel5Bright = new PPMPixel(255,179,178);
    pixel6Bright = new PPMPixel(254,255,243);
    pixel7Bright = new PPMPixel(154,122,150);
    pixel8Bright = new PPMPixel(22,174,175);
    pixel9Bright = new PPMPixel(242,151,113);
    pixel10Bright = new PPMPixel(190,195,181);
    pixel11Bright = new PPMPixel(85,75, 53);
    pixel12Bright = new PPMPixel(155,109,112);


    //all pixels
    pixel1Darken = new PPMPixel(137,210,220);
    pixel2Darken = new PPMPixel(143,45,86);
    pixel3Darken = new PPMPixel(239,121,138);
    pixel4Darken = new PPMPixel(16,29,66);
    pixel5Darken = new PPMPixel(245,169,168);
    pixel6Darken = new PPMPixel(244,245,233);
    pixel7Darken = new PPMPixel(144,112,140);
    pixel8Darken = new PPMPixel(12,164,165);
    pixel9Darken = new PPMPixel(232,141,103);
    pixel10Darken = new PPMPixel(180,185,171);
    pixel11Darken = new PPMPixel(75,65, 43);
    pixel12Darken = new PPMPixel(145,99,102);

    imageGridBright = new PPMPixel[][] {{pixel1Bright, pixel2Bright, pixel3Bright},
        {pixel4Bright, pixel5Bright, pixel6Bright},
        {pixel7Bright, pixel8Bright, pixel9Bright},
        {pixel10Bright, pixel11Bright, pixel12Bright}};

    imageGridDarken = new PPMPixel[][] {{pixel1Darken, pixel2Darken, pixel3Darken},
        {pixel4Darken, pixel5Darken, pixel6Darken},
        {pixel7Darken, pixel8Darken, pixel9Darken},
        {pixel10Darken, pixel11Darken, pixel12Darken}};

  }

  @Test
  public void testLoad() {
    // loaded properly
    IPModel model = new IPModel((new PPM()).readFile("test-2.ppm"), "test2");
    for (int row = 0; row < model.getHeight(); row++) {
      for (int col = 0; col < model.getWidth(); col++) {
        assertTrue(imageGrid[row][col].equals(model.getPixel(row, col)));
      }
    }
  }

  @Test
  public void testSave() {
    // loaded properly
    IPModel model = new IPModel((new PPM()).readFile("test-2.ppm"), "test2");
    AbstractPixel[][] modelBoard = new AbstractPixel[model.getHeight()][model.getWidth()];
    for (int row = 0; row < model.getHeight(); row++) {
      for (int col = 0; col < model.getWidth(); col++) {
        assertTrue(imageGrid[row][col].equals(model.getPixel(row, col)));
        modelBoard[row][col] = model.getPixel(row, col);
      }
    }

    // saves and rereads to make sure the contents are the same
    (new PPM()).writeFile(modelBoard,"ppm", "test2-output.ppm");
    IPModel modelWrite = new IPModel((new PPM()).readFile("test2-output.ppm"),
            "test2output");

    for (int row = 0; row < model.getHeight(); row++) {
      for (int col = 0; col < model.getWidth(); col++) {
        assertTrue(imageGrid[row][col].equals(modelWrite.getPixel(row, col)));
      }
    }
  }


  @Test
  public void testHorizontalFlipClassCommand() {

    HorizontalFlip hflip = new HorizontalFlip();

    //this should modify the model by flipping it horizontally
    hflip.goCommand(model);

    for (int row = 0; row < model.getHeight(); row++) {
      for (int col = 0; col < model.getWidth(); col++) {
        assertTrue(imageGridFlipHorizontal[row][col].equals(model.getPixel(row, col)));
      }
    }
  }

  @Test
  public void testVerticalFlipClassCommand() {
    VerticalFlip vflip = new VerticalFlip();

    //this should modify the model by flipping it horizontally
    vflip.goCommand(model);

    for (int row = 0; row < model.getHeight(); row++) {
      for (int col = 0; col < model.getWidth(); col++) {
        assertTrue(imageGridFlipVertical[row][col].equals(model.getPixel(row, col)));
      }
    }
  }

  @Test
  public void testChangeBrightnessClassCommand() {
    IPModel modelBright = new IPModel((new PPM()).readFile("test-2.ppm"),
            "test2");
    ChangeBrightness brightness = new ChangeBrightness(10);

    //this should modify the model making it brighter
    brightness.goCommand(modelBright);

    for (int row = 0; row < modelBright.getHeight(); row++) {
      for (int col = 0; col < modelBright.getWidth(); col++) {
        assertTrue(imageGridBright[row][col].equals(modelBright.getPixel(row, col)));
      }
    }
    ChangeBrightness darkness = new ChangeBrightness(-10);

    //this should modify the model by making it darker
    darkness.goCommand(modelBright);

    for (int row = 0; row < model.getHeight(); row++) {
      for (int col = 0; col < model.getWidth(); col++) {
        assertTrue(imageGridDarken[row][col].equals(modelBright.getPixel(row, col)));
      }
    }
  }

  @Test
  public void testGaussianBlurClassCommand() {

    IPModel modelBlur = new IPModel((new ConventionalFormats()).readFile("test-2.png"),
            "test2");

    GaussianBlur blur = new GaussianBlur();

    //this should modify the model blurring
    blur.goCommand(modelBlur);

    for (int row = 0; row < modelBlur.getHeight(); row++) {
      for (int col = 0; col < modelBlur.getWidth(); col++) {
        assertTrue(blurBoard[row][col].equals(modelBlur.getPixel(row, col)));
      }
    }
  }

  @Test
  public void testSepiaToneClassCommand() {

    IPModel modelSepia = new IPModel((new ConventionalFormats()).readFile("test-2.png"),
            "test2");

    SepiaTone sepia = new SepiaTone();

    //this should modify the model with sepia
    sepia.goCommand(modelSepia);

    for (int row = 0; row < model.getHeight(); row++) {
      for (int col = 0; col < model.getWidth(); col++) {
        assertTrue(sepiaBoard[row][col].equals(modelSepia.getPixel(row, col)));
      }
    }
  }


  @Test
  public void testSharpenClassCommand() {

    IPModel modelSharpen = new IPModel((new ConventionalFormats()).readFile("test-2.png"),
            "test2");

    Sharpen sharpen = new Sharpen();

    //this should modify the model with sharpening
    sharpen.goCommand(modelSharpen);

    for (int row = 0; row < model.getHeight(); row++) {
      for (int col = 0; col < model.getWidth(); col++) {
        assertTrue(boardSharpen[row][col].equals(modelSharpen.getPixel(row, col)));
      }
    }
  }












} //end of Test class
