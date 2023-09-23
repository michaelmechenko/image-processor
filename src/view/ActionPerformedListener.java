package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.IPGUIController;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JFileChooser;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import java.awt.Color;

/**
 * this class represents the display (GUI) of the Image Processing application.
 * It uses user input
 * taken in through the controller to make changes to various pictures and can
 * change different
 * layers/versions of a picture.
 */
public class ActionPerformedListener extends JFrame implements ActionListener, IView {

  int[] listRed; // Array to store red values
  int[] listGreen; // Array to store green values
  int[] listBlue; // Array to store blue values
  int[] listGray; // Array to store gray values
  String command; // Current command
  String lumafactor; // Luma factor
  String imageLoadedFilePath; // File path of the loaded image
  String imageLoadedName; // Name of the loaded image
  String imageSavedName; // Name of the saved image
  String imageSavedFilePath; // File path of the saved image
  String imageToBeEdited; // Image to be edited
  String addCommand; // Additional command
  JLabel imageLabel; // Label to display the image
  private JLabel fileOpenDisplay; // Label to display the file open status
  private JLabel fileSaveDisplay; // Label to display the file save status
  private JLabel optionsNameDisplay; // Label to display the options name
  JTextField brightText; // Text field for brightness
  private JTextField textLoadButton; // Text field for load button
  private JTextField textOptionButton; // Text field for option button
  private JTextField textSaveButton; // Text field for save button
  JPanel imagePanel; // Panel to display the image
  JPanel imagePanelHistRed; // Panel to display the red histogram
  JPanel imagePanelHistGreen; // Panel to display the green histogram
  JPanel imagePanelHistBlue; // Panel to display the blue histogram
  JPanel imagePanelHistGray; // Panel to display the gray histogram
  String image; // Current image
  private ArrayList<String> currVersionOptions; // List of current version options
  private JLabel currVersionsDisplay; // Label to display the current versions
  private IPGUIController controller; // Controller for the GUI

  /**
   * Constructor for ActionPerformedListener
   * 
   * @param controller Controller for the GUI
   **/
  public ActionPerformedListener(IPGUIController controller) {
    super();
    command = "";
    setTitle("Swing features");
    setSize(400, 400);

    this.controller = controller;

    // Create the main panel
    JPanel mainPanel = new JPanel();
    JScrollPane mainScrollPane;
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    // Create the dialog boxes panel for loading an image
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Load Image"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.X_AXIS));
    mainPanel.add(dialogBoxesPanel);

    // Create the dialog boxes panel for editing an image
    JPanel dialogBoxesPanel2 = new JPanel();
    dialogBoxesPanel2.setBorder(BorderFactory.createTitledBorder("Edit an Image"));
    dialogBoxesPanel2.setLayout(new BoxLayout(dialogBoxesPanel2, BoxLayout.X_AXIS));
    mainPanel.add(dialogBoxesPanel2);

    // Create the dialog boxes panel for saving an image
    JPanel dialogBoxesPanelSave = new JPanel();
    dialogBoxesPanelSave.setBorder(BorderFactory.createTitledBorder("Save an Image"));
    dialogBoxesPanelSave.setLayout(new BoxLayout(dialogBoxesPanelSave, BoxLayout.X_AXIS));
    mainPanel.add(dialogBoxesPanelSave);

    // Create the file open panel
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileopenPanel);
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    fileopenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("File path will appear here");
    fileopenPanel.add(fileOpenDisplay);

    JPanel loadDialogPanel = new JPanel();
    loadDialogPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(loadDialogPanel);
    JLabel loadNameDisplay;
    loadNameDisplay = new JLabel("Name of loaded image");
    loadDialogPanel.add(loadNameDisplay);
    textLoadButton = new JTextField();
    textLoadButton.setPreferredSize(new Dimension(250, 40));
    textLoadButton.setActionCommand("Load text");
    textLoadButton.addActionListener(this);
    loadDialogPanel.add(textLoadButton);

    dialogBoxesPanel.add(loadDialogPanel);

    JPanel loadButtonPanel = new JPanel();
    loadButtonPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(loadButtonPanel);

    JButton loadButton = new JButton("Load");
    loadButton.setActionCommand("Load Button");
    loadButton.addActionListener(this);
    loadButtonPanel.add(loadButton);

    // file save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);
    filesavePanel.add(fileSaveButton);
    fileSaveDisplay = new JLabel("File path will appear here");
    filesavePanel.add(fileSaveDisplay);
    dialogBoxesPanelSave.add(filesavePanel);

    JPanel saveDialogPanel = new JPanel();
    saveDialogPanel.setLayout(new FlowLayout());
    dialogBoxesPanelSave.add(saveDialogPanel);
    JLabel saveNameDisplay;
    saveNameDisplay = new JLabel("Name of saved image");
    saveDialogPanel.add(saveNameDisplay);
    textSaveButton = new JTextField();
    textSaveButton.setPreferredSize(new Dimension(250, 40));
    textSaveButton.setActionCommand("Save text");
    textSaveButton.addActionListener(this);
    saveDialogPanel.add(textSaveButton);

    JPanel saveButtonPanel = new JPanel();
    saveButtonPanel.setLayout(new FlowLayout());
    saveDialogPanel.add(saveButtonPanel);

    JButton saveButton = new JButton("Save");
    saveButton.setActionCommand("Save Button");
    saveButton.addActionListener(this);
    dialogBoxesPanelSave.add(saveButton);

    // JOptionsPane options dialog
    JPanel optionsDialogPanel = new JPanel();
    optionsDialogPanel.setLayout(new FlowLayout());
    dialogBoxesPanel2.add(optionsDialogPanel);

    JButton optionButton = new JButton("Commands");
    optionButton.setActionCommand("Commands");
    optionButton.addActionListener(this);
    optionsDialogPanel.add(optionButton);

    JPanel optionTextDialogPanel = new JPanel();
    optionTextDialogPanel.setLayout(new FlowLayout());
    optionsDialogPanel.add(optionTextDialogPanel);
    optionsNameDisplay = new JLabel("Name of edited image");
    optionTextDialogPanel.add(optionsNameDisplay);
    textOptionButton = new JTextField();
    textOptionButton.setPreferredSize(new Dimension(250, 40));
    textOptionButton.setActionCommand("Option Text");
    textOptionButton.addActionListener(this);
    optionTextDialogPanel.add(textOptionButton);
    dialogBoxesPanel2.add(optionTextDialogPanel);

    JPanel brightnessTextDialogPanel = new JPanel();
    brightnessTextDialogPanel.setLayout(new FlowLayout());
    optionsDialogPanel.add(brightnessTextDialogPanel);
    JLabel brightnessDisplay;
    brightnessDisplay = new JLabel("Amount to Brighten/Darkness");
    brightText = new JTextField();
    brightText.setPreferredSize(new Dimension(100, 40));
    brightText.setActionCommand("Brightness");
    brightText.addActionListener(this);
    brightnessTextDialogPanel.add(brightnessDisplay);
    brightnessTextDialogPanel.add(brightText);
    dialogBoxesPanel2.add(brightnessTextDialogPanel);

    // JOptionsPane options dialog
    JPanel goButtonPanel = new JPanel();
    goButtonPanel.setLayout(new FlowLayout());

    JButton goButton = new JButton("Go");
    goButton.setActionCommand("Go");
    goButton.addActionListener(this);
    goButtonPanel.add(goButton);
    dialogBoxesPanel2.add(goButtonPanel);

    // combo boxes
    JPanel imageVersionsPanel = new JPanel();
    dialogBoxesPanel2.add(imageVersionsPanel);

    JPanel currImagePanel = new JPanel();
    currImagePanel.setLayout(new FlowLayout());
    imageVersionsPanel.add(currImagePanel);

    JPanel currVersionsPanel = new JPanel();
    currVersionsPanel.setLayout(new FlowLayout());
    imageVersionsPanel.add(currVersionsPanel);

    // Create a button for displaying current versions
    JButton currVersionsButton = new JButton("Current Versions");
    currVersionsButton.setActionCommand("Current Versions");
    currVersionsButton.addActionListener(this);
    currImagePanel.add(currVersionsButton);

    // Display the label for current versions
    currVersionsDisplay = new JLabel("Default");
    currVersionsPanel.add(currVersionsButton);

    // Create a list to store current version options
    currVersionOptions = new ArrayList<>();
    currVersionOptions.add("None (Load an Image)");

    // Show an image with a scrollbar
    imagePanel = new JPanel();
    // Create a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Current Image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    mainPanel.add(imagePanel);

    // Set the image file path
    image = "koala-horizontal.png";
    JScrollPane imageScrollPane;

    // Create a label to display the image
    imageLabel = new JLabel();
    imageScrollPane = new JScrollPane(imageLabel);
    imageLabel.setIcon(new ImageIcon(image));
    imageScrollPane.setPreferredSize(new Dimension(100, 400));
    imagePanel.add(imageScrollPane);

    JPanel imagePanelHist = new JPanel();
    imagePanelHist.setBorder(BorderFactory.createTitledBorder("Histograms"));
    imagePanelHist.setLayout(new BoxLayout(imagePanelHist, BoxLayout.Y_AXIS));
    imagePanel.add(imagePanelHist);

    imagePanelHistRed = new JPanel();
    imagePanelHist.add(imagePanelHistRed);

    imagePanelHistGreen = new JPanel();
    JScrollPane imageScrollPaneGreen;
    imageScrollPaneGreen = new JScrollPane(imagePanelHistGreen);
    imageScrollPaneGreen.setPreferredSize(new Dimension(100, 40));
    imagePanelHist.add(imagePanelHistGreen);

    imagePanelHistBlue = new JPanel();
    JScrollPane imageScrollPaneBlue;
    imageScrollPaneBlue = new JScrollPane(imagePanelHistBlue);
    imageScrollPaneBlue.setPreferredSize(new Dimension(100, 40));
    imagePanelHist.add(imagePanelHistBlue);

    imagePanelHistGray = new JPanel();
    JScrollPane imageScrollPaneGray;
    imageScrollPaneGray = new JScrollPane(imagePanelHistGray);
    imageScrollPaneGray.setPreferredSize(new Dimension(100, 40));
    imagePanelHist.add(imagePanelHistGray);
  }

  /**
   * Depending on the user inputted command, translates GUI input into controller
   * commands.
   *
   * @param e the event to be processed
   */
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Go": {
        // Print the command
        System.out.println("Command: " + addCommand);

        if (addCommand.equalsIgnoreCase("brighten") ||
            addCommand.equalsIgnoreCase("darken")) {
          // Construct the command with additional parameters
          command = addCommand + " " + lumafactor + " " + imageLoadedName + " " + imageToBeEdited;
        } else {
          command = addCommand + " " + imageLoadedName + " " + imageToBeEdited;
        }

        // Process the image using the controller
        controller.imageProcessing();

        // Update the image label
        imageLabel.setIcon(new ImageIcon("hello.png"));

        // Save the current file
        command = "save" + " " + "currentfile.png" + " " + imageToBeEdited;
        controller.imageProcessing();

        // Add the version of the image
        this.addVersion(imageToBeEdited);

        // Update the image loaded name
        imageLoadedName = imageToBeEdited;

        // Update the image path
        image = "currentfile.png";

        try {
          // Update the image label with the newly saved image
          imageLabel.setIcon(new ImageIcon(ImageIO.read(new File("currentfile.png"))));
        } catch (IOException ie) {
          throw new IllegalArgumentException();
        }

        // Make a histogram listener for the image
        makeHistogramListener(imageToBeEdited);
      }
        break;

      case "Open file": {
        // Open file listener
        imageLoadedFilePath = openFileListener();
      }
        break;

      case "Save file": {
        // Save file listener
        imageSavedFilePath = saveFileListener();
      }
        break;

      case "Load text": {
        // Load text listener
        imageLoadedName = loadTextListener();
      }
        break;

      case "Save text": {
        // Save text listener
        imageSavedName = saveTextListener();
      }
        break;

      case "Brightness": {
        // Brightness listener
        lumafactor = brightenTextListener();
      }
        break;

      case "Commands": {
        // Add filter listener
        addCommand = this.addFilterListener();
      }
        break;

      case "Option Text": {
        // Option text listener
        imageToBeEdited = optionTextListener();
      }
        break;

      case "Current Versions": {
        // Current versions listener
        imageLoadedName = this.currentVersionsListener();
        command = "save " + " " + "currentfile.png" + " " + imageLoadedName;
        controller.imageProcessing();
        image = "currentfile.png";
        imageLabel.setIcon(new ImageIcon("currentfile.png"));
      }
        break;

      case "Load Button": {
        if (imageLoadedFilePath == null || imageLoadedName == null) {
          throw new IllegalArgumentException("Load cannot be completed. One or more"
              + "required fields left incomplete.");
        } else {
          // Load button listener
          command = "load " + imageLoadedFilePath + " " + imageLoadedName;
          controller.imageProcessing();
          image = imageLoadedFilePath;
          imageLabel.setIcon(new ImageIcon(image));
          this.addVersion(imageLoadedName);
          textLoadButton.setText("Name of Loaded Image");
          fileOpenDisplay.setText("File Path will appear here");
          makeHistogramListener(imageLoadedName);
        }
      }
        break;

      case "Save Button": {
        if (imageSavedFilePath == null || imageLoadedName == null) {
          throw new IllegalArgumentException("Save cannot be completed. One or more"
              + "required fields left incomplete.");
        } else {
          command = "save " + imageSavedFilePath + " " + imageSavedName;
          controller.imageProcessing();
          textSaveButton.setText("Name of Saved Image");
          fileSaveDisplay.setText("File Path will appear here");
        }
      }
        break;

      default:
        throw new IllegalArgumentException();
    }
  }

  /**
   * Gets the filepath of the image that the user wants to open for
   * modification/saving.
   *
   * @return The filepath of the file
   * @throws IllegalArgumentException If file cannot be opened
   */
  public String openFileListener() throws IllegalArgumentException {
    // Create a file chooser dialog in the current directory
    final JFileChooser fchooser = new JFileChooser(".");

    // Set a filter to only show JPG and GIF image files
    FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
    fchooser.setFileFilter(filter);

    // Show the open file dialog and get the user's selection
    int retvalue = fchooser.showOpenDialog(ActionPerformedListener.this);

    // If the user selected a file
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      // Get the selected file
      File f = fchooser.getSelectedFile();

      // Update the display with the selected file's absolute path
      fileOpenDisplay.setText(f.getAbsolutePath());

      // Return the absolute path of the selected file
      return f.getAbsolutePath();
    } else {
      // If the user canceled the file selection, throw an exception
      throw new IllegalArgumentException("File not opening.");
    }
  }

  /**
   * Gets the filepath of the image that the user wants to save.
   *
   * @return The filepath of the file
   * @throws IllegalArgumentException If the file cannot be saved
   */
  public String saveFileListener() throws IllegalArgumentException {
    // Create a file chooser dialog with the current directory as the initial
    // directory
    final JFileChooser fchooser = new JFileChooser(".");

    // Show the save dialog and get the user's selection
    int retvalue = fchooser.showSaveDialog(ActionPerformedListener.this);

    // If the user selects a file and clicks "Save"
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      // Get the selected file
      File f = fchooser.getSelectedFile();

      // Set the selected file's absolute path as the text of a display component
      fileSaveDisplay.setText(f.getAbsolutePath());

      // Return the absolute path of the selected file
      return f.getAbsolutePath();
    } else {
      // If the user cancels the save dialog, throw an exception
      throw new IllegalArgumentException("File not saving.");
    }
  }

  /**
   * Returns and resets the name of the image to be what the user inputted.
   *
   * @return A message indicating that the image has been loaded with the name the
   *         user wanted it to be.
   */
  public String loadTextListener() {
    // Get the current text of the textLoadButton
    String temp = textLoadButton.getText();

    // Set the text of the textLoadButton to indicate that the loaded name has been
    // entered
    textLoadButton.setText("Loaded Name Has Been Entered");

    // Return the original text of the textLoadButton
    return temp;
  }

  /**
   * This method returns and resets the name of the image that the user wants to
   * save their
   * image as.
   *
   * @return A message indicating that the image has been saved with the desired
   *         name.
   */
  public String saveTextListener() {
    // Get the current text of the save button
    String temp = textSaveButton.getText();

    // Set the text of the save button to indicate that the name has been entered
    // and saved
    textSaveButton.setText("Saved Name Has Been Entered");

    // Return the previous text of the save button
    return temp;
  }

  /**
   * Returns and resets the name of the command that the user wants to execute on
   * their image.
   *
   * @return A message saying that the command has been entered.
   */
  public String optionTextListener() {
    // Get the current text of the option button
    String temp = textOptionButton.getText();
    // Set the text of the option button to indicate that the option name has been
    // entered
    textOptionButton.setText("Option Name Has Been Entered");
    // Return the original text of the option button
    return temp;
  }

  /**
   * Returns a message after the brightness (luma factor) has been entered.
   *
   * @return A message saying that the luma factor has been entered.
   */
  public String brightenTextListener() {
    // Get the current text of the brightText field
    String temp = brightText.getText();
    // Set the text of the brightText field to indicate the amount of brightness
    brightText.setText("Amount of Brightness");
    // Return the original text of the brightText field
    return temp;
  }

  /**
   * This method allows the user to select a filter to apply to an image.
   *
   * @return The name of the selected filter.
   */
  public String addFilterListener() {
    String temp = "";
    String[] options = { "Blur", "Sharpen", "Sepia", "Grayscale", "Visualize Red",
        "Visualize Blue", "Visualize Green", "Horizontal-flip", "Vertical-flip", "Brighten",
        "Darken" };

    // Display a dialog box with the filter options
    int retvalue = JOptionPane.showOptionDialog(ActionPerformedListener.this,
        "Please choose a filter", "Options", JOptionPane.YES_OPTION,
        JOptionPane.INFORMATION_MESSAGE, null, options, options[4]);

    // Set the selected filter name to be displayed
    optionsNameDisplay.setText(options[retvalue]);

    // Store the selected filter name in the temp variable
    temp = options[retvalue];

    // Return the selected filter name
    return temp;
  }

  /**
   * This method allows users to select an already loaded version of an image.
   * It displays a dialog box with a list of options and returns the selected
   * version.
   *
   * @return the name of the current version to edit
   */
  public String currentVersionsListener() {
    String temp = "";

    // Get the length of the current version options
    int currVersionsOptionsLength = currVersionOptions.size();

    // Create a temporary array to hold the options
    String[] tempOptions = new String[currVersionsOptionsLength];

    // Copy the current version options into the temporary array
    for (int i = 0; i < currVersionsOptionsLength; i++) {
      tempOptions[i] = currVersionOptions.get(i);
    }

    // Show a dialog box with the options and get the selected option
    int retvalue = JOptionPane.showOptionDialog(ActionPerformedListener.this,
        "Please choose number", "Options", JOptionPane.YES_OPTION,
        JOptionPane.INFORMATION_MESSAGE, null, tempOptions,
        tempOptions[currVersionsOptionsLength / 2]);

    // Set the selected option as the text of the current versions display
    currVersionsDisplay.setText(tempOptions[retvalue]);

    // Get the selected option and assign it to the temporary variable
    temp = currVersionOptions.get(retvalue);

    // Return the selected option
    return temp;
  }

  /**
   * Adds an edited version to the array.
   *
   * @param version the name of the version to be added to the button
   */
  public void addVersion(String version) {
    // Remove the "None (Load an Image)" option if it exists
    if (currVersionOptions.get(0).equalsIgnoreCase("None (Load an Image)")) {
      currVersionOptions.remove(0);
    }

    // Add the new version to the array
    currVersionOptions.add(version);
  }

  /**
   * Retrieves all the commands that have been entered so far based on user
   * actions in the GUI.
   *
   * @return all the commands as a string.
   */
  public String getCommand() {
    String tempCommand = command;
    command = "";
    return tempCommand;
  }

  /**
   * Delegates to the controller and view to make R, G, B, and Intensity
   * histograms
   * for an image.
   * 
   * @param image The path of the image file.
   */
  public void makeHistogramListener(String image) {
    // Get the frequency boards for Red, Green, Blue, and Gray channels
    listRed = controller.getFrequencyBoard(image, "Red");
    listGreen = controller.getFrequencyBoard(image, "Green");
    listBlue = controller.getFrequencyBoard(image, "Blue");
    listGray = controller.getBrightnessBoard(image);

    // Create a JFrameView for the Red histogram
    JFrameView frameRed = new JFrameView(listRed, Color.RED);
    frameRed.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frameRed.setVisible(true);

    // Create a JFrameView for the Green histogram
    JFrameView frameGreen = new JFrameView(listGreen, Color.GREEN);
    frameGreen.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frameGreen.setVisible(true);

    // Create a JFrameView for the Blue histogram
    JFrameView frameBlue = new JFrameView(listBlue, Color.CYAN);
    frameBlue.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frameBlue.setVisible(true);

    // Create a JFrameView for the Intensity histogram
    JFrameView frameIntensity = new JFrameView(listGray, Color.BLACK);
    frameIntensity.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frameIntensity.setVisible(true);
  }

}
