import controller.IPController;
import model.IPModel;
import model.PPMPixel;
import model.file.PPM;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * This represents all of the controller tests.
 */
public class IPControllerTest {


  // all pixels for test image (the 3x3 square image)
  PPMPixel pixel1t1;
  PPMPixel pixel2t1;
  PPMPixel pixel3t1;
  PPMPixel pixel4t1;
  PPMPixel pixel5t1;
  PPMPixel pixel6t1;
  PPMPixel pixel7t1;
  PPMPixel pixel8t1;
  PPMPixel pixel9t1;

  // all pixels for test image brightened
  PPMPixel pixel1t1b;
  PPMPixel pixel2t1b;
  PPMPixel pixel3t1b;
  PPMPixel pixel4t1b;
  PPMPixel pixel5t1b;
  PPMPixel pixel6t1b;
  PPMPixel pixel7t1b;
  PPMPixel pixel8t1b;
  PPMPixel pixel9t1b;

  // all pixels for the test-2 image (the 3x4 rectangular image)
  PPMPixel pixel1test2;
  PPMPixel pixel2test2;
  PPMPixel pixel3test2;
  PPMPixel pixel4test2;
  PPMPixel pixel5test2;
  PPMPixel pixel6test2;
  PPMPixel pixel7test2;
  PPMPixel pixel8test2;
  PPMPixel pixel9test2;
  PPMPixel pixel10test2;
  PPMPixel pixel11test2;
  PPMPixel pixel12test2;

  PPMPixel[][] test1original;

  PPMPixel[][] test1bright;

  PPMPixel[][] test2original;

  PPMPixel[][] test2vertical;

  Map<String, PPMPixel[][]> prevImagesTest1;

  Map<String, PPMPixel[][]> prevImagesTest2;

  Map<String, IPModel> images;
  IPModel testModel;
  IPModel test2model;


  // all of this is related to the testMultipleImagesMultipleVersions()
  // we already have all the models for the original test and test2 images

  // all pixels turned green for test-2 image
  PPMPixel pixel1t2green;
  PPMPixel pixel2t2green;
  PPMPixel pixel3t2green;
  PPMPixel pixel4t2green;
  PPMPixel pixel5t2green;
  PPMPixel pixel6t2green;
  PPMPixel pixel7t2green;
  PPMPixel pixel8t2green;
  PPMPixel pixel9t2green;
  PPMPixel pixel10t2green;
  PPMPixel pixel11t2green;
  PPMPixel pixel12t2green;

  //board for test2-green
  PPMPixel[][] test2green;

  // all pixels for test-2 image turned gray
  PPMPixel pixel1t2gray;
  PPMPixel pixel2t2gray;
  PPMPixel pixel3t2gray;
  PPMPixel pixel4t2gray;
  PPMPixel pixel5t2gray;
  PPMPixel pixel6t2gray;
  PPMPixel pixel7t2gray;
  PPMPixel pixel8t2gray;
  PPMPixel pixel9t2gray;
  PPMPixel pixel10t2gray;
  PPMPixel pixel11t2gray;
  PPMPixel pixel12t2gray;

  // board for test-2 turned gray

  PPMPixel[][] test2gray;

  //all pixels for test image turned blue
  PPMPixel pixel1t1blue;
  PPMPixel pixel2t1blue;
  PPMPixel pixel3t1blue;
  PPMPixel pixel4t1blue;
  PPMPixel pixel5t1blue;
  PPMPixel pixel6t1blue;
  PPMPixel pixel7t1blue;
  PPMPixel pixel8t1blue;
  PPMPixel pixel9t1blue;

  //board for test-blue
  PPMPixel[][] testBlue;

  //all pixels for test-blue darkened (luma factor -10)
  PPMPixel pixel1t1blueDark;
  PPMPixel pixel2t1blueDark;
  PPMPixel pixel3t1blueDark;
  PPMPixel pixel4t1blueDark;
  PPMPixel pixel5t1blueDark;
  PPMPixel pixel6t1blueDark;
  PPMPixel pixel7t1blueDark;
  PPMPixel pixel8t1blueDark;
  PPMPixel pixel9t1blueDark;

  //board for test-blue
  PPMPixel[][] testBlueDarken;

  //board for test-brighten-horizontal
  PPMPixel[][] testBrightenHorizontal;

  //all pixels for test-2-vertical turned red

  PPMPixel pixel1t2red;
  PPMPixel pixel2t2red;
  PPMPixel pixel3t2red;
  PPMPixel pixel4t2red;
  PPMPixel pixel5t2red;
  PPMPixel pixel6t2red;
  PPMPixel pixel7t2red;
  PPMPixel pixel8t2red;
  PPMPixel pixel9t2red;
  PPMPixel pixel10t2red;
  PPMPixel pixel11t2red;
  PPMPixel pixel12t2red;

  //board for test-2-vertical-red
  PPMPixel[][] test2VerticalRed;


  @Before
  public void setUp() {

    //all pixels for the test image
    pixel1t1 = new PPMPixel(137, 210, 220);
    pixel2t1 = new PPMPixel(143, 45, 86);
    pixel3t1 = new PPMPixel(239, 121, 138);
    pixel4t1 = new PPMPixel(16, 29, 66);
    pixel5t1 = new PPMPixel(247, 169, 168);
    pixel6t1 = new PPMPixel(244, 249, 233);
    pixel7t1 = new PPMPixel(144, 112, 140);
    pixel8t1 = new PPMPixel(12, 164, 165);
    pixel9t1 = new PPMPixel(232, 141, 103);

    //all pixels for the test image, brightened with +10 luma factor
    pixel1t1b = new PPMPixel(147, 220, 230);
    pixel2t1b = new PPMPixel(153, 55, 96);
    pixel3t1b = new PPMPixel(249, 131, 148);
    pixel4t1b = new PPMPixel(26, 39, 76);
    pixel5t1b = new PPMPixel(255, 179, 178);
    pixel6t1b = new PPMPixel(254, 255, 243);
    pixel7t1b = new PPMPixel(154, 122, 150);
    pixel8t1b = new PPMPixel(22, 174, 175);
    pixel9t1b = new PPMPixel(242, 151, 113);

    //all pixels for the test-2 image
    pixel1test2 = new PPMPixel(137, 210, 220);
    pixel2test2 = new PPMPixel(143, 45, 86);
    pixel3test2 = new PPMPixel(239, 121, 138);
    pixel4test2 = new PPMPixel(16, 29, 66);
    pixel5test2 = new PPMPixel(247, 169, 168);
    pixel6test2 = new PPMPixel(244, 249, 233);
    pixel7test2 = new PPMPixel(144, 112, 140);
    pixel8test2 = new PPMPixel(12, 164, 165);
    pixel9test2 = new PPMPixel(232, 141, 103);
    pixel10test2 = new PPMPixel(180, 185, 171);
    pixel11test2 = new PPMPixel(75, 65, 43);
    pixel12test2 = new PPMPixel(145, 99, 102);

    //all the pixels for test-2 image turned green
    pixel1t2green = new PPMPixel(0, 210, 0);
    pixel2t2green = new PPMPixel(0, 45, 0);
    pixel3t2green = new PPMPixel(0, 121, 0);
    pixel4t2green = new PPMPixel(0, 29, 0);
    pixel5t2green = new PPMPixel(0, 169, 0);
    pixel6t2green = new PPMPixel(0, 249, 0);
    pixel7t2green = new PPMPixel(0, 112, 0);
    pixel8t2green = new PPMPixel(0, 164, 0);
    pixel9t2green = new PPMPixel(0, 141, 0);
    pixel10t2green = new PPMPixel(0, 185, 0);
    pixel11t2green = new PPMPixel(0, 65, 0);
    pixel12t2green = new PPMPixel(0, 99, 0);

    //all pixels for turning the test-2 image (technically vertically flipped) red
    pixel1t2red = new PPMPixel(137, 0, 0);
    pixel2t2red = new PPMPixel(143, 0, 0);
    pixel3t2red = new PPMPixel(239, 0, 0);
    pixel4t2red = new PPMPixel(16, 0, 0);
    pixel5t2red = new PPMPixel(247, 0, 0);
    pixel6t2red = new PPMPixel(244, 0, 0);
    pixel7t2red = new PPMPixel(144, 0, 0);
    pixel8t2red = new PPMPixel(12, 0, 0);
    pixel9t2red = new PPMPixel(232, 0, 0);
    pixel10t2red = new PPMPixel(180, 0, 0);
    pixel11t2red = new PPMPixel(75, 0, 0);
    pixel12t2red = new PPMPixel(145, 0, 0);

    //all pixels for turning the test-2 image gray
    pixel1t2gray = new PPMPixel(195, 195, 195);
    pixel2t2gray = new PPMPixel(68, 68, 68);
    pixel3t2gray = new PPMPixel(147, 147, 147);
    pixel4t2gray = new PPMPixel(28, 28, 28);
    pixel5t2gray = new PPMPixel(185, 185, 185);
    pixel6t2gray = new PPMPixel(246, 246, 246);
    pixel7t2gray = new PPMPixel(120, 120, 120);
    pixel8t2gray = new PPMPixel(131, 131, 131);
    pixel9t2gray = new PPMPixel(157, 157, 157);
    pixel10t2gray = new PPMPixel(182, 182, 182);
    pixel11t2gray = new PPMPixel(65, 65, 65);
    pixel12t2gray = new PPMPixel(108, 108, 108);

    //all pixels for test image turned blue
    pixel1t1blue = new PPMPixel(0, 0, 220);
    pixel2t1blue = new PPMPixel(0, 0, 86);
    pixel3t1blue = new PPMPixel(0, 0, 138);
    pixel4t1blue = new PPMPixel(0, 0, 66);
    pixel5t1blue = new PPMPixel(0, 0, 168);
    pixel6t1blue = new PPMPixel(0, 0, 233);
    pixel7t1blue = new PPMPixel(0, 0, 140);
    pixel8t1blue = new PPMPixel(0, 0, 165);
    pixel9t1blue = new PPMPixel(0, 0, 103);

    //all pixels for test-blue image darkened (luma factor -10)
    pixel1t1blueDark = new PPMPixel(0, 0, 210);
    pixel2t1blueDark = new PPMPixel(0, 0, 76);
    pixel3t1blueDark = new PPMPixel(0, 0, 128);
    pixel4t1blueDark = new PPMPixel(0, 0, 56);
    pixel5t1blueDark = new PPMPixel(0, 0, 158);
    pixel6t1blueDark = new PPMPixel(0, 0, 223);
    pixel7t1blueDark = new PPMPixel(0, 0, 130);
    pixel8t1blueDark = new PPMPixel(0, 0, 155);
    pixel9t1blueDark = new PPMPixel(0, 0, 93);


    // test board
    test1original = new PPMPixel[][]{{pixel1t1, pixel2t1, pixel3t1},
      {pixel4t1, pixel5t1, pixel6t1},
      {pixel7t1, pixel8t1, pixel9t1}};

    // test-brighten board
    test1bright = new PPMPixel[][]{{pixel1t1b, pixel2t1b, pixel3t1b},
      {pixel4t1b, pixel5t1b, pixel6t1b},
      {pixel7t1b, pixel8t1b, pixel9t1b}};

    // test-2 board
    test2original = new PPMPixel[][]{{pixel1test2, pixel2test2, pixel3test2},
      {pixel4test2, pixel5test2, pixel6test2},
      {pixel7test2, pixel8test2, pixel9test2},
      {pixel10test2, pixel11test2, pixel12test2}};

    //test-2-vertical board
    test2vertical = new PPMPixel[][]{{pixel10test2, pixel11test2, pixel12test2},
      {pixel7test2, pixel8test2, pixel9test2},
      {pixel4test2, pixel5test2, pixel6test2},
      {pixel1test2, pixel2test2, pixel3test2}};

    //test-2-vertical-red board
    test2VerticalRed = new PPMPixel[][]{{pixel10t2red, pixel11t2red, pixel12t2red},
      {pixel7t2red, pixel8t2red, pixel9t2red},
      {pixel4t2red, pixel5t2red, pixel6t2red},
      {pixel1t2red, pixel2t2red, pixel3t2red}};

    //test-2 gray board

    test2gray = new PPMPixel[][]{{pixel1t2gray, pixel2t2gray, pixel3t2gray},
      {pixel4t2gray, pixel5t2gray, pixel6t2gray},
      {pixel7t2gray, pixel8t2gray, pixel9t2gray},
      {pixel10t2gray, pixel11t2gray, pixel12t2gray}};

    //test-2-green board
    test2green = new PPMPixel[][]{{pixel1t2green, pixel2t2green, pixel3t2green},
      {pixel4t2green, pixel5t2green, pixel6t2green},
      {pixel7t2green, pixel8t2green, pixel9t2green},
      {pixel10t2green, pixel11t2green, pixel12t2green}};

    //test-blue board
    testBlue = new PPMPixel[][]{{pixel1t1blue, pixel2t1blue, pixel3t1blue},
      {pixel4t1blue, pixel5t1blue, pixel6t1blue},
      {pixel7t1blue, pixel8t1blue, pixel9t1blue}};

    //test-blue-darken board
    testBlueDarken = new PPMPixel[][]{{pixel1t1blueDark, pixel2t1blueDark, pixel3t1blueDark},
      {pixel4t1blueDark, pixel5t1blueDark, pixel6t1blueDark},
      {pixel7t1blueDark, pixel8t1blueDark, pixel9t1blueDark}};

    //test-brighten-horizontal board
    testBrightenHorizontal = new PPMPixel[][]{{pixel3t1b, pixel2t1b, pixel1t1b},
      {pixel6t1b, pixel5t1b, pixel4t1b},
      {pixel9t1b, pixel8t1b, pixel7t1b}};


    //----------- all IPModels ------------

    //model for the test image (original)
    testModel = new IPModel((new PPM()).readFile("test.ppm"), "test");

    //model for the test-2 image (original)
    test2model = new IPModel((new PPM()).readFile("test-2.ppm"), "test-2");


    images = new HashMap<>();
    images.put("test", testModel);
    images.put("test2", test2model);

    prevImagesTest1 = new HashMap<>();
    prevImagesTest1.put("test", test1original);
    prevImagesTest1.put("test-bright", test1bright);

    prevImagesTest2 = new HashMap<>();
    prevImagesTest1.put("test2", test2original);
    prevImagesTest1.put("test2-vertical", test2vertical);
  }

  //tests that input is taken in correctly using a mock
  @Test
  public void testControllerInput() {
    // String input = "load test-2.ppm test2 vertical-flip test2 test2-vertical save "
    //         + "test-2-output.ppm test2-vertical";
    // StringReader reader = new StringReader(input);
    // Map<String, IPModel> images = new HashMap<>();
    StringBuilder output = new StringBuilder();
    String expectedOutput = "load test-2.ppm test2 vertical-flip test2 test2-vertical "
            + "save test-2-output.ppm test2-vertical ";
    assertEquals(expectedOutput, output.toString());

  }

  @Test
  public void testConstructorExceptions() {
    assertThrows(IllegalArgumentException.class,
        () -> new IPController(null, images));
    assertThrows(IllegalArgumentException.class,
        () -> new IPController(new StringReader(""), null));
  }

  @Test
  public void testImageProcessingException() {
    String inputException = " vertical-flip test2 test2-vertical"
            + " save test2-vertical.ppm test2-vertical";
    IPController program = new IPController(new StringReader(inputException), new HashMap<>());
    assertThrows(IllegalStateException.class,
        () -> program.imageProcessing());

    String inputExceptionNoInt = " load test2.ppm test2"
            + "brighten test2 test2-brighten"
            + " save test2-brighten.ppm test2-brighten";
    IPController programNoInt = new IPController(new StringReader(inputExceptionNoInt),
            new HashMap<>());
    assertThrows(IllegalStateException.class,
        () -> programNoInt.imageProcessing());
  }


  @Test
  public void testControllerCommands() {
    String inputt2vertical = "load test-2.ppm test2"
            + " vertical-flip test2 test2-vertical"
            + " save test2-vertical.ppm test2-vertical";
    IPController program = new IPController(new StringReader(inputt2vertical), new HashMap<>());
    program.imageProcessing();

    IPModel test2verticaledited = new IPModel((new PPM()).readFile("test2-vertical.ppm"),
            "test2-vertical");
    for (int row = 0; row < test2model.getHeight(); row++) {
      for (int col = 0; col < test2model.getWidth(); col++) {
        assertTrue(test2vertical[row][col].equals(test2verticaledited.getPixel(row, col)));
      }
    }

  }

  //loads multiple images and performs operations on them concurrently
  //this test includes ALL the current commands supported by the image processing program.
  @Test
  public void testMultipleImagesMultipleVersions() {
    // tests creating operations on multiple version of multiple images
    // the pixel being passed in to the equals method is the actual image created by
    // our code (actual)
    // the pixel that equals is called on is the Pixel [][] that we typed out (expected)

    String[] args = new String[]{"-file", "testParseScript.txt"};

    Main.main(args);

    //model for the test-vertical being turned red
    IPModel test2verticalrededited = new IPModel((new PPM()).readFile("test2-vertical-red.ppm"),
            "test2-vertical-red");
    for (int row = 0; row < test2model.getHeight(); row++) {
      for (int col = 0; col < test2model.getWidth(); col++) {
        assertTrue(test2VerticalRed[row][col].equals(test2verticalrededited.getPixel(row, col)));
      }
    }

    //model for the test image being turned green

    IPModel test2Greenedited = new IPModel((new PPM()).readFile("test2-green.ppm"),
            "test2-green");

    for (int row = 0; row < test2model.getHeight(); row++) {
      for (int col = 0; col < test2model.getWidth(); col++) {
        assertTrue(test2green[row][col].equals(test2Greenedited.getPixel(row, col)));
      }
    }

    //model for the test image being turned grayscale

    IPModel test2GrayscaleEdited = new IPModel((new PPM()).readFile("test2-grayscale.ppm"),
            "test2-grayscale");

    for (int row = 0; row < test2GrayscaleEdited.getHeight(); row++) {
      for (int col = 0; col < test2GrayscaleEdited.getWidth(); col++) {
        assertTrue(test2gray[row][col].equals(test2GrayscaleEdited.getPixel(row, col)));
      }
    }

    //model for the test-brighten image being flipped horizontally

    IPModel testbrighthorizontaledited = new IPModel((new PPM()).readFile(
            "test-bright-horizontal.ppm"),"test-bright-horizontal");
    for (int row = 0; row < testbrighthorizontaledited.getHeight(); row++) {
      for (int col = 0; col < testbrighthorizontaledited.getWidth(); col++) {
        assertTrue(testBrightenHorizontal[row][col].equals(
                testbrighthorizontaledited.getPixel(row, col)));
      }
    }

    //model for the test-2-blue image being darkened with -10 luma factor

    IPModel testBlueDarkenEdited = new IPModel((new PPM()).readFile("test-blue-darken.ppm"),
            "test-blue-darken");
    for (int row = 0; row < testBlueDarkenEdited.getHeight(); row++) {
      for (int col = 0; col < testBlueDarkenEdited.getWidth(); col++) {
        assertTrue(testBlueDarken[row][col].equals(testBlueDarkenEdited.getPixel(row, col)));
      }
    }
  }


}