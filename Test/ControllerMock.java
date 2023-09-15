import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import command.GaussianBlur;
import command.SepiaTone;
import command.Sharpen;
import controller.IPControllerInterface;
import model.AbstractPixel;
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
 * this class represents the controller for the image processing program. It handles user input and
 * delegates to respective commands using command design pattern. It saves all versions of the image
 * to a Map.
 */
public class ControllerMock implements IPControllerInterface {
  // model gets created after you find the path name for the file
  private final Readable input;

  //stores all versions of all loaded images based on their name
  private Map<String, IPModel> images;

  StringBuilder log;


  /**
   * constructor that takes in user input to be processed by the controller, sets default file path.
   *
   * @param input commands given by user
   * @throws IllegalArgumentException if the input is null.
   */
  public ControllerMock(Readable input, Map<String, IPModel> imagesCopy, StringBuilder log)
          throws IllegalArgumentException {
    if (input == null || imagesCopy == null) {
      throw new IllegalArgumentException("One or more args are null.");
    }
    images = new HashMap<>();
    for (String elt : imagesCopy.keySet()) {
      images.put(elt, imagesCopy.get(elt));
    }
    this.input = input;
    this.log = log;
  }

  /**
   * processes the inputs and records the inputs.
   * @throws IllegalStateException if the image or command is not found
   */
  public void imageProcessing() throws IllegalStateException {
    try {
      Scanner scan = new Scanner(this.input);

      //Command Design: adds all commands to a hashmap
      Map<String, Function<Scanner, IPCommandInterface>> knownCommands = new HashMap<>();
      knownCommands.put("brighten", s -> new ChangeBrightness(s.nextInt()));
      knownCommands.put("horizontal-flip", s -> new HorizontalFlip());
      knownCommands.put("vertical-flip", s -> new VerticalFlip());
      knownCommands.put("value-component", s -> new MakeGrayscale());
      knownCommands.put("value-component-color", s -> new ChangeColor(s.next()));
      knownCommands.put("gaussian-blur", s -> new GaussianBlur());
      knownCommands.put("sharpen", s -> new Sharpen());
      knownCommands.put("sepia", s -> new SepiaTone());

      //while there is still commands to process, iterates through input
      while (scan.hasNext()) {
        String in = scan.next();

        log.append(in);

        // quits the program if the user wants to
        if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
          System.out.println("Quit the program.");
          return;
        }

        // load and save are not part of command design (see README.md for explanation)

        //this if statement loads
        if (in.equalsIgnoreCase("load")) {
          String nameOfCurrentPathFile = scan.next();
          log.append(nameOfCurrentPathFile);
          String nameOfCurrentImage = scan.next();
          log.append(nameOfCurrentImage);

          //depending on the file type of image, we need to call different readFile methods
          //we generalize FileInterface and specify if it's a PPM file or ConventionalFormat
          FileInterface read;
          if (this.fileFormatName(nameOfCurrentPathFile).equalsIgnoreCase("ppm")) {
            read = new PPM();
          } else {
            read = new ConventionalFormats();
          }

          // puts loaded image in HashMap
          try {
            images.put(nameOfCurrentImage, new IPModel(
                    read.readFile(nameOfCurrentPathFile),
                    nameOfCurrentImage));
          } catch (IllegalStateException s) {
            s.printStackTrace();
          }

        }

        //this if statement saves the file
        else if (in.equalsIgnoreCase("save")) {
          String nameOfCurrentPathFile = scan.next();
          log.append(nameOfCurrentPathFile);
          String nameOfCurrentImage = scan.next();
          log.append(nameOfCurrentImage);

          IPModel temp_model = getCurrentImage(nameOfCurrentImage);
          // outputs the file into PPM format

          //depending on the file type of image, we need to call different writeFile methods
          //we generalize FileInterface and specify if it's a PPM file or ConventionalFormat
          FileInterface write;
          if (this.fileFormatName(nameOfCurrentPathFile).equalsIgnoreCase("ppm")) {
            write = new PPM();
          } else {
            write = new ConventionalFormats();
          }
          try {
            write.writeFile(getPixelBoard(temp_model),
                    this.fileFormatName(nameOfCurrentPathFile), nameOfCurrentPathFile);
          } catch (IllegalStateException s) {
            s.printStackTrace();
          }
        } else {
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
      }
    } catch (InputMismatchException | IllegalArgumentException e) {
      throw new IllegalStateException(e.getMessage());
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

    //if model never gets reinstantiated, then it was never loaded
    if (model == null) {
      throw new IllegalArgumentException("Image you want has not been loaded");
    }
    return model;
  }

  /**
   * gets the 2D array of pixels for a given model (used when saving the image).
   * @param model the model for which we want to get the 2D array of pixels for
   * @return the 2D array of pixels
   */
  private AbstractPixel[][] getPixelBoard(IPModel model) {
    AbstractPixel[][] board = new AbstractPixel[model.getHeight()][model.getWidth()];
    for (int row = 0; row < model.getHeight(); row++) {
      for (int col = 0; col < model.getWidth(); col++) {
        board[row][col] = model.getPixel(row, col);
      }
    }
    return board;
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

}
