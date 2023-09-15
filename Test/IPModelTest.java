import org.junit.Before;
import org.junit.Test;
import model.IPModel;
import model.PPMPixel;
import model.file.PPM;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;


import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * This represents the tests for all of the models's methods.
 */
public class IPModelTest {
  File file;
  String output;
  FileOutputStream fos;
  PPMPixel[][] imageGrid;

  PPMPixel[][] imageGridFlipHorizontal;
  PPMPixel[][] imageGridFlipVertical;

  PPMPixel[][] colorBoardRed;
  PPMPixel[][] colorBoardGreen;
  PPMPixel[][] colorBoardBlue;


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
  PPMPixel pixel1Red;
  PPMPixel pixel2Red;
  PPMPixel pixel3Red;
  PPMPixel pixel4Red;
  PPMPixel pixel5Red;
  PPMPixel pixel6Red;
  PPMPixel pixel7Red;
  PPMPixel pixel8Red;
  PPMPixel pixel9Red;
  PPMPixel pixel10Red;
  PPMPixel pixel11Red;
  PPMPixel pixel12Red;
  PPMPixel pixel1Green;
  PPMPixel pixel2Green;
  PPMPixel pixel3Green;
  PPMPixel pixel4Green;
  PPMPixel pixel5Green;
  PPMPixel pixel6Green;
  PPMPixel pixel7Green;
  PPMPixel pixel8Green;
  PPMPixel pixel9Green;
  PPMPixel pixel10Green;
  PPMPixel pixel11Green;
  PPMPixel pixel12Green;
  PPMPixel pixel1Blue;
  PPMPixel pixel2Blue;
  PPMPixel pixel3Blue;
  PPMPixel pixel4Blue;
  PPMPixel pixel5Blue;
  PPMPixel pixel6Blue;
  PPMPixel pixel7Blue;
  PPMPixel pixel8Blue;
  PPMPixel pixel9Blue;
  PPMPixel pixel10Blue;
  PPMPixel pixel11Blue;
  PPMPixel pixel12Blue;

  IPModel image;


  @Before
  public void setUp() {
    file = new File("test.ppm");
    output = "P3\n3 3\n255\n137 210 220 143 45 86 239 121 138"
            + "\n16 29 66 247 169 168 244 249 233"
            + "\n144 112 140 12 164 165 232 141 103"
            + "\n180 185 171 75 65 43 145 99 102\n";
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


    imageGridFlipVertical = new PPMPixel[][] {{pixel10, pixel11, pixel12},
      {pixel7, pixel8, pixel9},
      {pixel4, pixel5, pixel6},
      {pixel1, pixel2, pixel3}};


    imageGridFlipHorizontal = new PPMPixel[][] {{pixel3, pixel2, pixel1},
      {pixel6, pixel5, pixel4},
      {pixel9, pixel8, pixel7},
      {pixel12, pixel11, pixel10}};

    image = new IPModel((new PPM()).readFile("test.ppm"), "test");

    pixel1Red = new PPMPixel(137,0,0);
    pixel2Red = new PPMPixel(143,0,0);
    pixel3Red = new PPMPixel(239,0,0);
    pixel4Red = new PPMPixel(16,0,0);
    pixel5Red  = new PPMPixel(247,0,0);
    pixel6Red  = new PPMPixel(244,0,0);
    pixel7Red  = new PPMPixel(144,0,0);
    pixel8Red  = new PPMPixel(12,0,0);
    pixel9Red  = new PPMPixel(232,0,0);
    pixel10Red = new PPMPixel(180,0,0);
    pixel11Red = new PPMPixel(75,0, 0);
    pixel12Red = new PPMPixel(145,0,0);


    colorBoardRed = new PPMPixel[][] {{pixel1Red, pixel2Red, pixel3Red},
      {pixel4Red, pixel5Red, pixel6Red},
      {pixel7Red, pixel8Red, pixel9Red},
      {pixel10Red, pixel11Red, pixel12Red}};

    pixel1Green = new PPMPixel(0,210,0);
    pixel2Green = new PPMPixel(0,45,0);
    pixel3Green = new PPMPixel(0,121,0);
    pixel4Green = new PPMPixel(0,29,0);
    pixel5Green = new PPMPixel(0,169,0);
    pixel6Green = new PPMPixel(0,249,0);
    pixel7Green = new PPMPixel(0,112,0);
    pixel8Green = new PPMPixel(0,164,0);
    pixel9Green = new PPMPixel(0,141,0);
    pixel10Green = new PPMPixel(0,185,0);
    pixel11Green = new PPMPixel(0,65, 0);
    pixel12Green = new PPMPixel(0,99,0);


    colorBoardGreen = new PPMPixel[][] {{pixel1Green, pixel2Green, pixel3Green},
      {pixel4Green, pixel5Green, pixel6Green},
      {pixel7Green, pixel8Green, pixel9Green},
      {pixel10Green, pixel11Green, pixel12Green}};

    pixel1Blue = new PPMPixel(0,0,220);
    pixel2Blue = new PPMPixel(0,0,86);
    pixel3Blue = new PPMPixel(0,0,138);
    pixel4Blue = new PPMPixel(0,0,66);
    pixel5Blue = new PPMPixel(0,0,168);
    pixel6Blue = new PPMPixel(0,0,233);
    pixel7Blue = new PPMPixel(0,0,140);
    pixel8Blue = new PPMPixel(0,0,165);
    pixel9Blue = new PPMPixel(0,0,103);
    pixel10Blue = new PPMPixel(0,0,171);
    pixel11Blue = new PPMPixel(0,0, 43);
    pixel12Blue = new PPMPixel(0,0,102);

    colorBoardBlue = new PPMPixel[][] {{pixel1Blue, pixel2Blue, pixel3Blue},
      {pixel4Blue, pixel5Blue, pixel6Blue},
      {pixel7Blue, pixel8Blue, pixel9Blue},
      {pixel10Blue, pixel11Blue, pixel12Blue}};


    try {
      fos = new FileOutputStream(file, false);
      fos.write(new String(output).getBytes());
      fos.close();
    } catch (IOException e) {
      System.out.print("Can't produce file");
    }
  }

  @Test
  public void testIPModelConstructor() {

    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        assertTrue(imageGrid[row][col].equals(image.getPixel(row, col)));
      }
    }
  }

  @Test
  public void testIPModelConstructorException() {
    assertThrows(IllegalArgumentException.class,
        () -> new IPModel(null, "test"));
    assertThrows(IllegalArgumentException.class,
        () -> new IPModel((new PPM()).readFile("test.ppm"), null));
    // file not found -- createImage
    assertThrows(IllegalArgumentException.class,
        () -> new IPModel((new PPM()).readFile("test"), "test"));
  }

  @Test
  public void testMakeColorBoard() {
    assertThrows(IllegalArgumentException.class,
        () -> image.makeColorBoard("hi"));
  }

  @Test
  public void testFlipVertically() {
    IPModel testImage = new IPModel((new PPM()).readFile("test-2.ppm"),
            "test");
    testImage.flipVertically();

    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        assertTrue(imageGridFlipVertical[row][col].equals(testImage.getPixel(row, col)));
      }
      System.out.print("\n");
    }
  }

  @Test
  public void testFlipHorizontally() {
    IPModel testImage = new IPModel((new PPM()).readFile("test-2.ppm"),
            "test");
    testImage.flipHorizontally();

    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        assertTrue(imageGridFlipHorizontal[row][col].equals(testImage.getPixel(row, col)));
      }
    }
  }


  @Test
  public void testColorBoardRed() {
    IPModel testImage = new IPModel((new PPM()).readFile("test-2.ppm"), "test");
    testImage.makeColorBoard("Red");
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        assertTrue(colorBoardRed[row][col].equals(testImage.getPixel(row, col)));
      }
    }
  }

  @Test
  public void testColorBoardBlue() {
    IPModel testImage = new IPModel((new PPM()).readFile("test-2.ppm"),
            "test");
    testImage.makeColorBoard("Blue");
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        assertTrue(colorBoardBlue[row][col].equals(testImage.getPixel(row, col)));
      }
    }
  }

  @Test
  public void testColorBoardGreen() {
    IPModel testImage = new IPModel((new PPM()).readFile("test-2.ppm"),
            "test");
    testImage.makeColorBoard("Green");
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        assertTrue(colorBoardGreen[row][col].equals(testImage.getPixel(row, col)));
      }
    }
  }

}