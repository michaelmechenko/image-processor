import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Scanner;

import controller.IPController;
import controller.IPGUIController;

public class Main {

  /**
   * The entry point of the program.
   *
   * This function takes input commands to perform operations on images.
   *
   * @param args the commands to edit, load, and/or save an image.
   */
  public static void main(String[] args) {
    try {
      // Check if command-line arguments are provided
      if (args.length > 0) {
        // If "-file" argument is provided
        if (args[0].equalsIgnoreCase("-file")) {
          // Process the arguments and create an IPController object
          IPController imageController = Main.processArgs(args);

          // Perform image processing
          imageController.imageProcessing();
        }
        // If "-text" argument is provided
        else if (args[0].equalsIgnoreCase("-text")) {
          // Create an IPController object with input from System.in
          IPController imageController = new IPController(new InputStreamReader(System.in),
                  new HashMap<>());

          // Perform image processing
          imageController.imageProcessing();
        }
        // Invalid argument
        else {
          System.out.println("Invalid argument");
        }
      } else {
        // If no command-line arguments are provided, use GUI
        IPGUIController imageController;

        // Create an IPGUIController object
        imageController = new IPGUIController(new HashMap<>());

        // Perform image processing
        imageController.imageProcessing();
      }
    } catch (IllegalStateException e) {
      // Handle any exceptions
      System.out.println("Error: " + e.getMessage());
    }
  }

  /**
   * convert the inputted user commands from the GUI into a string of commands.
   * 
   * @param args the commands inputted by the user
   * @return the controller
   */
  public static IPController processArgs(String[] args) {
    IPController imageController;
    String commands = "";

    // Loop through the user commands
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-file")) {
        i++;
        String filename = args[i];
        Scanner sc;
        try {
          // Read commands from file
          sc = new Scanner(new FileInputStream(filename));
        } catch (IOException e) {
          throw new IllegalArgumentException("File " + filename + " not found!");
        }

        // Append commands from file to the string
        while (sc.hasNext()) {
          commands += sc.next() + " ";
        }
      } else {
        // Append command to the string
        commands += args[i] + " ";
      }
    }

    // Create IPController with the string of commands
    imageController = new IPController(new StringReader(commands), new HashMap<>());

    return imageController;
  }

}