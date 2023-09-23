package controller;

import java.io.StringReader;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import javax.swing.JFrame;
import javax.swing.UIManager;

import javax.swing.UnsupportedLookAndFeelException;
import command.ChangeBrightness;
import command.ChangeColor;
import command.GaussianBlur;
import command.HorizontalFlip;
import command.IPCommandInterface;
import command.MakeGrayscale;
import command.SepiaTone;
import command.Sharpen;
import command.VerticalFlip;
import model.IPModel;
import model.file.ConventionalFormats;
import model.file.FileInterface;
import model.file.PPM;
import view.ActionPerformedListener;

/**
 * This class represents the controller for the GUI. It calculates what user
 * commands were inputted based on what buttons were pressed and delegates to
 * the view accordingly.
 */
public class IPGUIController implements IPControllerInterface {

  // Stores all versions of all loaded images based on their name
  private Map<String, IPModel> images;

  // Listener for handling actionPerformed events from the view
  ActionPerformedListener view;

  // Readable input source for the controller
  Readable input;

  /**
   * Constructs a new IPGUIController with the given map of images.
   *
   * @param imagesCopy the map of images to be processed by the controller
   * @throws IllegalArgumentException if the input map is null
   */
  public IPGUIController(Map<String, IPModel> imagesCopy) throws IllegalArgumentException {
    if (imagesCopy == null) {
      throw new IllegalArgumentException("One or more args are null.");
    }

    // Copy the images to a new map
    images = new HashMap<>();
    for (String elt : imagesCopy.keySet()) {
      images.put(elt, imagesCopy.get(elt));
    }

    input = null;

    // Initialize the GUI view
    ActionPerformedListener.setDefaultLookAndFeelDecorated(false);
    view = new ActionPerformedListener(this);
    view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    view.setVisible(true);

    try {
      // Set cross-platform Java L&F (also called "Metal")
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

    } catch (UnsupportedLookAndFeelException e) {
      // handle exception
    } catch (ClassNotFoundException e) {
      // handle exception
    } catch (InstantiationException e) {
      // handle exception
    } catch (IllegalAccessException e) {
      // handle exception
    } catch (Exception e) {
      // handle exception
    }
  }

  /**
   * This method parses through the commands inputted by the user and delegates it
   * to the
   * appropriate classes using command design.
   *
   * @throws IllegalStateException if the command or image name is not recognized
   */
  public void imageProcessing() throws IllegalStateException {
    String tempString = view.getCommand();
    if (tempString != null) {
      input = new StringReader(tempString);
      Scanner scan = new Scanner(input);

      // Command Design: adds all commands to a hashmap
      Map<String, Function<Scanner, IPCommandInterface>> knownCommands = new HashMap<>();
      // Brighten command
      knownCommands.put("Brighten", s -> new ChangeBrightness(s.nextInt()));
      // Horizontal-flip command
      knownCommands.put("Horizontal-flip", s -> new HorizontalFlip());
      // Vertical-flip command
      knownCommands.put("Vertical-flip", s -> new VerticalFlip());
      // Grayscale command
      knownCommands.put("Grayscale", s -> new MakeGrayscale());
      // Visualize command
      knownCommands.put("Visualize", s -> new ChangeColor(s.next()));
      // Blur command
      knownCommands.put("Blur", s -> new GaussianBlur());
      // Sharpen command
      knownCommands.put("Sharpen", s -> new Sharpen());
      // Sepia command
      knownCommands.put("Sepia", s -> new SepiaTone());

      // while there are still commands to process, iterate through the input
      while (scan.hasNext()) {
        try {
          String in = scan.next();

          // quits the program if the user wants to
          if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
            System.out.println("Quit the program.");
            return;
          }

          // load and save are not part of command design
          if (in.equalsIgnoreCase("load") || in.equalsIgnoreCase("save")) {
            this.processImages(in, scan.next(), scan.next());
          } else {
            System.out.println(in);
            Function<Scanner, IPCommandInterface> cmd = knownCommands.getOrDefault(in, null);

            // if command entered is invalid
            if (cmd == null) {
              throw new IllegalArgumentException("Invalid Command");
            } else {
              // applies command operation and saves to that image's HashMap of previous
              // versions
              IPCommandInterface c = cmd.apply(scan);
              String nameOfCurrentImage = scan.next();
              String newNameOfCurrentImage = scan.next();
              IPModel model = getCurrentImage(nameOfCurrentImage);
              c.goCommand(model);
              model.writePrevImages(newNameOfCurrentImage);
            }
          }
        } catch (InputMismatchException | IllegalArgumentException e) {
          throw new IllegalStateException(e.getMessage());
        }
      }
    }
  }

  /**
   * Returns the image that is currently being operated.
   *
   * @param nameOfCurrentImage - name of the desired version of the image
   * @return the version of the image that needs to be edited
   * @throws IllegalArgumentException if there is no corresponding model to the
   *                                  user's inputted image name
   */
  private IPModel getCurrentImage(String nameOfCurrentImage) throws IllegalArgumentException {
    IPModel model = null;

    // iterate through the Map of all images
    for (String elt : images.keySet()) {
      if (images.get(elt).getPrevImages(nameOfCurrentImage)) {
        model = images.get(elt);
        break;
      }
    }

    // if model never gets re-instantiated, then it was never loaded
    if (model == null) {
      throw new IllegalArgumentException("The image you want has not been loaded");
    }
    return model;
  }

  /**
   * Returns the format type of the file based on the user inputted file name.
   *
   * @param filename the name of the file
   * @return the format type of the filename (file extension)
   */
  private String fileFormatName(String filename) {
    // Initialize the file extension variable
    String fileExtension = "";

    // Find the last index of the dot character in the filename
    int index = filename.lastIndexOf('.');
    if (index >= 0) {
      // Extract the substring after the dot character
      fileExtension = filename.substring(index + 1);
    }

    // Return the file extension
    return fileExtension;
  }

  /**
   * Process images based on the file type and the operation specified.
   *
   * @param in                    The operation to perform ('load' or any other
   *                              value)
   * @param nameOfCurrentPathFile The path of the current file
   * @param nameOfCurrentImage    The name of the current image
   * @throws IllegalStateException If there is an error in processing the images
   */
  private void processImages(String in, String nameOfCurrentPathFile, String nameOfCurrentImage)
      throws IllegalStateException {
    // Depending on the file type of the image, we need to call different readFile
    // methods. We generalize FileInterface and specify if it's a PPM file or
    // ConventionalFormat.
    FileInterface filetype;
    if (this.fileFormatName(nameOfCurrentPathFile).equalsIgnoreCase("ppm")) {
      filetype = new PPM();
    } else {
      filetype = new ConventionalFormats();
    }

    try {
      if (in.equalsIgnoreCase("load")) {
        // Load the image and add it to the images map
        IPModel temp_model = new IPModel(filetype.readFile(nameOfCurrentPathFile), nameOfCurrentImage);
        images.put(nameOfCurrentImage, temp_model);
      } else {
        // Get the current image and write it to the file
        IPModel temp_model = getCurrentImage(nameOfCurrentImage);
        filetype.writeFile(temp_model.getPixelBoard(),
            this.fileFormatName(nameOfCurrentPathFile), nameOfCurrentPathFile);
      }
    } catch (IllegalStateException s) {
      s.printStackTrace();
    }
  }

  /**
   * Returns the frequency board for the specified image and color.
   *
   * @param image the image to process
   * @param color the color to compute the frequency for
   * @return an array representing the frequency board
   */
  public int[] getFrequencyBoard(String image, String color) {
    // Get the current image model
    IPModel model = getCurrentImage(image);

    // Compute the histogram for the specified color
    return model.makeHistogram(color);
  }

  /**
   * Returns the brightness board for the specified image.
   *
   * @param image the image to process
   * @return an array representing the brightness board
   */
  public int[] getBrightnessBoard(String image) {
    // Get the current image model
    IPModel model = getCurrentImage(image);

    // Compute the brightness histogram
    return model.makeBrightnessHistogram();
  }

}
