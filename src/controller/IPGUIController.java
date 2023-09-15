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
 * this class represents the controller for the GUI. Calculates what user commands were inputted
 * based on what buttons were pressed and delegates to view accordingly.
 */
public class IPGUIController implements IPControllerInterface {

  //stores all versions of all loaded images based on their name
  private Map<String, IPModel> images;

  ActionPerformedListener view;

  Readable input;


  /**
   * constructor that takes in user input to be processed by the controller, sets default file path.
   *
   * @throws IllegalArgumentException if the input is null.
   */
  public IPGUIController(Map<String, IPModel> imagesCopy)
          throws IllegalArgumentException {
    if (imagesCopy == null) {
      throw new IllegalArgumentException("One or more args are null.");
    }
    images = new HashMap<>();
    for (String elt : imagesCopy.keySet()) {
      images.put(elt, imagesCopy.get(elt));
    }
    input = null;

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
      //handle exception
    }
  }

  /**
   * this parses through the commands inputted by the user and delegates it to the
   * appropriate classes using command design.
   *
   * @throws IllegalStateException if the command or image name is not recognized
   */
  public void imageProcessing() throws IllegalStateException {
    String tempString = view.getCommand();
    if (tempString != null) {
      input = new StringReader(tempString);
      Scanner scan = new Scanner(input);
      //Command Design: adds all commands to a hashmap
      Map<String, Function<Scanner, IPCommandInterface>> knownCommands = new HashMap<>();
      knownCommands.put("Brighten", s -> new ChangeBrightness(s.nextInt()));
      knownCommands.put("Horizontal-flip", s -> new HorizontalFlip());
      knownCommands.put("Vertical-flip", s -> new VerticalFlip());
      knownCommands.put("Grayscale", s -> new MakeGrayscale());
      knownCommands.put("Visualize", s -> new ChangeColor(s.next()));
      knownCommands.put("Blur", s -> new GaussianBlur());
      knownCommands.put("Sharpen", s -> new Sharpen());
      knownCommands.put("Sepia", s -> new SepiaTone());

      //while there is still commands to process, iterates through input
      while (scan.hasNext()) {
        try {
          String in = scan.next();

          // quits the program if the user wants to
          if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
            System.out.println("Quit the program.");
            return;
          }

          // load and save are not part of command design (see README.md for explanation)
          if (in.equalsIgnoreCase("load") || in.equalsIgnoreCase("save")) {
            this.processImages(in, scan.next(), scan.next());
          } else {
            System.out.println(in);
            Function<Scanner, IPCommandInterface> cmd =
                    knownCommands.getOrDefault(in, null);
            // if command entered is invalid
            if (cmd == null) {
              throw new IllegalArgumentException("Invalid Command");
            } else {
              //applies command operation and saves to that image's HashMap of previous versions
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
   * iterates through all modified versions of the original image and returns the image that
   * is currently being operated.
   *
   * @param nameOfCurrentImage name of desired version of image
   * @throws IllegalArgumentException if there is no corresponding model to the user's inputted
   *                                  image name
   * @returns the version of the image that needs to be edited
   */
  private IPModel getCurrentImage(String nameOfCurrentImage) throws IllegalArgumentException {
    IPModel model = null;

    //iterate through Map of all images
    for (String elt : images.keySet()) {
      if (images.get(elt).getPrevImages(nameOfCurrentImage)) {
        model = images.get(elt);
        break;
      }
    }

    //if model never gets re-instantiated, then it was never loaded
    if (model == null) {
      throw new IllegalArgumentException("Image you want has not been loaded");
    }
    return model;
  }


  /**
   * returns the format type of the file based on the user inputted file name.
   *
   * @param filename the name of the file
   * @returns the format type of filename (file path)
   */
  private String fileFormatName(String filename) {
    String fileExtension = "";

    int index = filename.lastIndexOf('.');
    if (index >= 0) {
      fileExtension = filename.substring(index + 1);
    }
    return fileExtension;
  }

  private void processImages(String in, String nameOfCurrentPathFile, String nameOfCurrentImage)
          throws IllegalStateException {
    //depending on the file type of image, we need to call different readFile methods
    //we generalize FileInterface and specify if it's a PPM file or ConventionalFormat
    FileInterface filetype;
    if (this.fileFormatName(nameOfCurrentPathFile).equalsIgnoreCase("ppm")) {
      filetype = new PPM();
    } else {
      filetype = new ConventionalFormats();
    }

    try {
      if (in.equalsIgnoreCase("load")) {
        IPModel temp_model = new IPModel(
                filetype.readFile(nameOfCurrentPathFile), nameOfCurrentImage);
        images.put(nameOfCurrentImage, temp_model);

      } else {
        IPModel temp_model = getCurrentImage(nameOfCurrentImage);
        filetype.writeFile(temp_model.getPixelBoard(),
                this.fileFormatName(nameOfCurrentPathFile), nameOfCurrentPathFile);
      }
    } catch (IllegalStateException s) {
      s.printStackTrace();
    }
  }

  public int[] getFrequencyBoard(String image, String color) {
    IPModel model = getCurrentImage(image);
    return model.makeHistogram(color);
  }

  public int[] getBrightnessBoard(String image) {
    IPModel model = getCurrentImage(image);
    return model.makeBrightnessHistogram();
  }


}
