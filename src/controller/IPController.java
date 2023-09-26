package controller;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import command.GaussianBlur;
import command.SepiaTone;
import command.Sharpen;
import model.IPModel;
import command.ChangeBrightness;
import command.ChangeColor;
import command.HorizontalFlip;
import command.MakeGrayscale;
import command.VerticalFlip;
import command.IPCommandInterface;
import model.file.ConventionalFormats;
import model.file.FileInterface;
import model.file.PPM;

/**
 * This class represents the controller for the image processing program. It
 * handles user input and
 * delegates to respective commands using the command design pattern. It saves
 * all versions of the image
 * to a Map.
 */
public final class IPController implements IPControllerInterface {

  // model gets created after you find the path name for the file
  private Readable input;

  // stores all versions of all loaded images based on their name
  private Map<String, IPModel> images;

  /**
   * Constructor that takes in user input to be processed by the controller and
   * sets the default file path.
   *
   * @param input      commands given by the user
   * @param imagesCopy a map of the loaded images
   * @throws IllegalArgumentException if the input or imagesCopy is null
   */
  public IPController(Readable input, Map<String, IPModel> imagesCopy)
      throws IllegalArgumentException {
    if (input == null || imagesCopy == null) {
      throw new IllegalArgumentException("One or more args are null.");
    }
    images = new HashMap<>();
    for (String elt : imagesCopy.keySet()) {
      images.put(elt, imagesCopy.get(elt));
    }
    this.input = input;
  }

  /**
   * This method parses through the commands inputted by the user and delegates
   * them to the
   * appropriate classes using the command design.
   *
   * @throws IllegalStateException if the command or image name is not recognized
   */
  public void imageProcessing() throws IllegalStateException {
    try {
      Scanner scan = new Scanner(this.input);

      // Command Design: adds all commands to a hashmap
      Map<String, Function<Scanner, IPCommandInterface>> knownCommands = new HashMap<>();
      knownCommands.put("brighten", s -> new ChangeBrightness(s.nextInt()));
      knownCommands.put("horizontal-flip", s -> new HorizontalFlip());
      knownCommands.put("vertical-flip", s -> new VerticalFlip());
      knownCommands.put("value-component", s -> new MakeGrayscale());
      knownCommands.put("value-component-color", s -> new ChangeColor(s.next()));
      knownCommands.put("gaussian-blur", s -> new GaussianBlur());
      knownCommands.put("sharpen", s -> new Sharpen());
      knownCommands.put("sepia", s -> new SepiaTone());

      // while there are still commands to process, iterate through the input
      while (scan.hasNext()) {
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
          Function<Scanner, IPCommandInterface> cmd = knownCommands.getOrDefault(in, null);
          // if the command entered is invalid
          if (cmd == null) {
            throw new IllegalArgumentException("Invalid Command");
          } else {
            // applies command operation and saves it to that image's HashMap of previous
            // versions
            IPCommandInterface c = cmd.apply(scan);

            String nameOfCurrentImage = scan.next();
            String newNameOfCurrentImage = scan.next();

            IPModel model = getCurrentImage(nameOfCurrentImage);
            c.goCommand(model);
            model.writePrevImages(newNameOfCurrentImage);
          }
        }
      }
    } catch (InputMismatchException | IllegalArgumentException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * Iterates through all modified versions of the original image and returns the
   * image that
   * is currently being operated.
   * 
   * @param nameOfCurrentImage name of desired version of image
   * @return the version of the image that needs to be edited
   * @throws IllegalArgumentException if there is no corresponding model to the
   *                                  user's inputted
   *                                  image name
   */
  private IPModel getCurrentImage(String nameOfCurrentImage) throws IllegalArgumentException {
    IPModel model = null;

    // iterate through Map of all images
    for (String elt : images.keySet()) {
      if (images.get(elt).getPrevImages(nameOfCurrentImage)) {
        model = images.get(elt);
        break;
      }
    }

    // if model never gets re-instantiated, then it was never loaded
    if (model == null) {
      throw new IllegalArgumentException("Image has not been loaded");
    }
    return model;
  }

  /**
   * Returns the format type of the file based on the user inputted file name.
   * 
   * @param filename the name of the file
   * @return the format type of filename (file path)
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
    // depending on the file type of image, we need to call different readFile
    // methods
    // we generalize FileInterface and specify if it's a PPM file or
    // ConventionalFormat
    FileInterface filetype;
    IPModel temp_model = getCurrentImage(nameOfCurrentImage);
    if (this.fileFormatName(nameOfCurrentPathFile).equalsIgnoreCase("ppm")) {
      filetype = new PPM();
    } else {
      filetype = new ConventionalFormats();
    }

    try {
      if (in.equalsIgnoreCase("load")) {
        images.put(nameOfCurrentImage, new IPModel(
            filetype.readFile(nameOfCurrentPathFile),
            nameOfCurrentImage));
      } else {
        filetype.writeFile(temp_model.getPixelBoard(),
            this.fileFormatName(nameOfCurrentPathFile), nameOfCurrentPathFile);
      }
    } catch (IllegalStateException s) {
      s.printStackTrace();
    }
  }

}
