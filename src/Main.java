import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Scanner;

import controller.IPController;
import controller.IPGUIController;

/**
 * this class represents the main class for running the image processing application. Takes in
 * user input and delegates to controller.
 */
public class Main {

  /**
   * inputs commands to perform on images.
   *
   * @param args the commands to edit, load, and/or save an image.
   */
  public static void main(String[] args) {

    try {
      if (args.length > 0) {
        if (args[0].equalsIgnoreCase("-file")) {
          IPController imageController = Main.processArgs(args);

          imageController.imageProcessing();
        }
        else if (args[0].equalsIgnoreCase("-text")) {
          IPController imageController = new IPController(new InputStreamReader(System.in),
                  new HashMap<>());

          imageController.imageProcessing();
        }
        else {
          System.out.println("Invalid argument");
        }
      } else {
        IPGUIController imageController;

        imageController = new IPGUIController(new HashMap<>());

        imageController.imageProcessing();
      }
    } catch (IllegalStateException e) {
      System.out.println("Error: " + e.getMessage());
    }

  }

  /**
   * convert the inputted user commands from the GUI into a string of commands.
   * @param args the commands inputted by the user
   * @return the controller
   */
  public static IPController processArgs(String[] args) {
    IPController imageController;
    String commands = "";
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-file")) {
        i++;
        String filename = args[i];
        Scanner sc;
        try {
          sc = new Scanner(new FileInputStream(filename));
        } catch (IOException e) {
          throw new IllegalArgumentException("File " + filename + " not found!");
        }

        while (sc.hasNext()) {
          commands += sc.next() + " ";
        }
      } else {
        commands += args[i] + " ";
      }
    }
    imageController = new IPController(new StringReader(commands), new HashMap<>());
    return imageController;
  }

}

