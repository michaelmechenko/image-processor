import model.AbstractPixel;
import model.ConvPixel;
import model.IPModel;
import org.junit.Before;
import org.junit.Test;

import model.PPMPixel;
import model.file.PPM;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;

/**
 * This represents all the tests of pixel.
 */
public class PixelTest {
  AbstractPixel pixel1;
  AbstractPixel convPixel1;

  @Before
  public void setUp() {
    pixel1 = new PPMPixel(3, 4, 5);
    convPixel1 = new ConvPixel(1, 2, 3, 200);
  }

  @Test
  public void testInvalidConstructor() {
    assertThrows(IllegalArgumentException.class,
        () -> new PPMPixel(0, -1, 0));
    assertThrows(IllegalArgumentException.class,
        () -> new PPMPixel(256, 5, 0));
    assertThrows(IllegalArgumentException.class,
        () -> new ConvPixel(0, -1, 0, 255));
    assertThrows(IllegalArgumentException.class,
        () -> new ConvPixel(256, 5, 0, 255));
    assertThrows(IllegalArgumentException.class,
        () -> new ConvPixel(0, 5, 0, 256));
  }

  @Test
  public void testMakeColorPixelException() {
    assertThrows(IllegalArgumentException.class,
        () -> pixel1.makeColorPixel("hi"));
    assertThrows(IllegalArgumentException.class,
        () -> convPixel1.makeColorPixel("hi"));
  }

  @Test
  public void testGetColor() {
    assertEquals(3, pixel1.getColorValue("red"));
    assertEquals(4, pixel1.getColorValue("green"));
    assertEquals(5, pixel1.getColorValue("blue"));

    assertEquals(1, convPixel1.getColorValue("red"));
    assertEquals(2, convPixel1.getColorValue("green"));
    assertEquals(3, convPixel1.getColorValue("blue"));
  }

  @Test
  public void testGetAlpha() {
    assertEquals(200, convPixel1.getAlpha());
  }


  @Test
  public void testMakeColorPixel() {
    PPMPixel temp_pixel_red = new PPMPixel(3, 0, 0);
    PPMPixel temp_pixel_green = new PPMPixel(0, 4, 0);
    PPMPixel temp_pixel_blue = new PPMPixel(0, 0, 5);
    assertTrue(temp_pixel_red.equals(pixel1.makeColorPixel("red")));
    assertTrue(temp_pixel_green.equals(pixel1.makeColorPixel("green")));
    assertTrue(temp_pixel_blue.equals(pixel1.makeColorPixel("blue")));

    ConvPixel conv_temp_pixel_red = new ConvPixel(1, 0, 0, 200);
    ConvPixel conv_temp_pixel_green = new ConvPixel(0, 2, 0, 200);
    ConvPixel conv_temp_pixel_blue = new ConvPixel(0, 0, 3, 200);
    assertTrue(conv_temp_pixel_red.equals(convPixel1.makeColorPixel("red")));
    assertTrue(conv_temp_pixel_green.equals(convPixel1.makeColorPixel("green")));
    assertTrue(conv_temp_pixel_blue.equals(convPixel1.makeColorPixel("blue")));
  }


  @Test
  public void testChangeBrightnessPixel() {

    //case where luma factor is positive
    PPMPixel temp_pixel = new PPMPixel(13, 14,15);
    assertTrue(temp_pixel.equals(pixel1.changeBrightnessPixel(10)));

    //case where luma factor is negative
    PPMPixel pixel2 = new PPMPixel(20, 148, 59);
    PPMPixel temp_pixel2 = new PPMPixel(5, 133, 44);
    assertTrue(temp_pixel2.equals(pixel2.changeBrightnessPixel(-15)));

    //case where darkening the original image would make one of the RGB values negative,
    //so we check that the method makes it 0

    PPMPixel negative_pixel = new PPMPixel(10, 5, 10);
    PPMPixel negative_darkened = new PPMPixel(0,0,0);
    assertTrue(negative_darkened.equals(negative_pixel.changeBrightnessPixel(-15)));

    //case where darkening the original image would make one of the RGB values above 255,
    //so we check that the method makes it 0
    PPMPixel greater_pixel = new PPMPixel(250, 249, 252);
    PPMPixel pixel_brightened = new PPMPixel(255,255,255);
    assertTrue(pixel_brightened.equals(greater_pixel.changeBrightnessPixel(15)));


    //case where luma factor is positive
    // ConvPixel conv_temp_pixel = new ConvPixel(13, 14,15, 200);
    assertTrue(temp_pixel.equals(pixel1.changeBrightnessPixel(10)));

    //case where luma factor is negative
    ConvPixel conv_temp_pixel2 = new ConvPixel(20, 148, 59, 200);
    ConvPixel conv_pixel2 = new ConvPixel(5, 133, 44, 200);
    assertTrue(conv_pixel2.equals(conv_temp_pixel2.changeBrightnessPixel(-15)));

    //case where darkening the original image would make one of the RGB values negative,
    //so we check that the method makes it 0

    ConvPixel conv_negative_pixel = new ConvPixel(10, 5, 10, 200);
    ConvPixel conv_negative_darkened = new ConvPixel(0,0,0, 200);
    assertTrue(conv_negative_darkened.equals(conv_negative_pixel
            .changeBrightnessPixel(-15)));

    //case where darkening the original image would make one of the RGB values above 255,
    //so we check that the method makes it 0
    // ConvPixel conv_greater_pixel = new ConvPixel(250, 249, 252, 200);
    // ConvPixel conv_pixel_brightened = new ConvPixel(255,255,255, 200);
    assertTrue(pixel_brightened.equals(greater_pixel.changeBrightnessPixel(15)));

  }

  @Test
  public void testEquals() {
    PPMPixel copyPixel1 = new PPMPixel(3, 4, 5);
    //returns true
    assertTrue(copyPixel1.equals(pixel1));

    PPMPixel badPixel1 = new PPMPixel(6, 4, 5);
    //returns false
    assertFalse(badPixel1.equals(pixel1));

    ConvPixel convCopyPixel1 = new ConvPixel(1, 2, 3, 200);
    //returns true
    assertTrue(convCopyPixel1.equals(convPixel1));

    ConvPixel convBadPixel1 = new ConvPixel(6, 4, 5, 200);
    //returns false
    assertFalse(convBadPixel1.equals(convPixel1));

    //returns false - Conv with pixel
    assertFalse(convPixel1.equals(pixel1));


    IPModel model = new IPModel((new PPM()).readFile("res/test-2.ppm"),
            "test");
    //returns false because its not an instance of
    assertFalse(pixel1.equals(model));

  }

  @Test
  public void testHashcode() {
    assertEquals(960702300, pixel1.hashCode());
    assertEquals(75349200, convPixel1.hashCode());
  }


  @Test
  public void testColorTonePixel() {
    double[][] grayscale = new double[][] {{0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}};
    double[][] sepia = new double[][] {{0.292, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}};
    PPMPixel temp_pixel_sepia = new PPMPixel(4, 4, 3);
    PPMPixel temp_pixel_grayscale = new PPMPixel(3, 3, 3);
    assertTrue(temp_pixel_sepia.equals(pixel1.createColorTonePixel(sepia)));
    assertTrue(temp_pixel_grayscale.equals(pixel1.createColorTonePixel(grayscale)));

    ConvPixel conv_temp_pixel_sepia = new ConvPixel(2, 2, 1, 200);
    ConvPixel conv_temp_pixel_grayscale = new ConvPixel(1, 1, 1, 200);
    assertTrue(conv_temp_pixel_sepia.equals(convPixel1.createColorTonePixel(sepia)));
    assertTrue(conv_temp_pixel_grayscale.equals(convPixel1.createColorTonePixel(grayscale)));
  }

}
