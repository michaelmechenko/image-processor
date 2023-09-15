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
 * this class represents the display (GUI) of the Image Processing application. It uses user input
 * taken in through the controller to make changes to various pictures and can change different
 * layers/versions of a picture.
 */
public class ActionPerformedListener extends JFrame implements ActionListener, IView {

  int[] listRed;
  int[] listGreen;
  int[] listBlue;
  int[] listGray;
  String command;
  String lumafactor;
  String imageLoadedFilePath;
  String imageLoadedName;
  String imageSavedName;
  String imageSavedFilePath;
  String imageToBeEdited;
  String addCommand;
  JLabel imageLabel;
  private JLabel fileOpenDisplay;
  private JLabel fileSaveDisplay;
  private JLabel optionsNameDisplay;
  JTextField brightText;
  private JTextField textLoadButton;
  private JTextField textOptionButton;
  private JTextField textSaveButton;
  JPanel imagePanel;
  JPanel imagePanelHistRed;
  JPanel imagePanelHistGreen;
  JPanel imagePanelHistBlue;
  JPanel imagePanelHistGray;
  String image;
  private ArrayList<String> currVersionOptions;
  private JLabel currVersionsDisplay;
  private IPGUIController controller;

  /**
   * sets up the panels for the view.
   * @param controller game controller to translate the commands
   */
  public ActionPerformedListener(IPGUIController controller) {
    super();
    command = "";
    setTitle("Swing features");
    setSize(400, 400);

    this.controller = controller;


    JPanel mainPanel = new JPanel();
    JScrollPane mainScrollPane;
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);


    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Load Image"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.X_AXIS));
    mainPanel.add(dialogBoxesPanel);


    //dialog boxes
    JPanel dialogBoxesPanel2 = new JPanel();
    dialogBoxesPanel2.setBorder(BorderFactory.createTitledBorder("Edit an Image"));
    dialogBoxesPanel2.setLayout(new BoxLayout(dialogBoxesPanel2, BoxLayout.X_AXIS));
    mainPanel.add(dialogBoxesPanel2);

    //dialog boxes
    JPanel dialogBoxesPanelSave = new JPanel();
    dialogBoxesPanelSave.setBorder(BorderFactory.createTitledBorder("Save an Image"));
    dialogBoxesPanelSave.setLayout(new BoxLayout(dialogBoxesPanelSave, BoxLayout.X_AXIS));
    mainPanel.add(dialogBoxesPanelSave);


    //file open
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

    //file save
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

    //JOptionsPane options dialog
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


    //JOptionsPane options dialog
    JPanel goButtonPanel = new JPanel();
    goButtonPanel.setLayout(new FlowLayout());


    JButton goButton = new JButton("Go");
    goButton.setActionCommand("Go");
    goButton.addActionListener(this);
    goButtonPanel.add(goButton);
    dialogBoxesPanel2.add(goButtonPanel);

    //combo boxes
    JPanel imageVersionsPanel = new JPanel();
    dialogBoxesPanel2.add(imageVersionsPanel);

    JPanel currImagePanel = new JPanel();
    currImagePanel.setLayout(new FlowLayout());
    imageVersionsPanel.add(currImagePanel);


    JPanel currVersionsPanel = new JPanel();
    currVersionsPanel.setLayout(new FlowLayout());
    imageVersionsPanel.add(currVersionsPanel);

    JButton currVersionsButton = new JButton("Current Versions");
    currVersionsButton.setActionCommand("Current Versions");
    currVersionsButton.addActionListener(this);
    currImagePanel.add(currVersionsButton);

    currVersionsDisplay = new JLabel("Default");
    currVersionsPanel.add(currVersionsButton);

    currVersionOptions = new ArrayList<>();
    currVersionOptions.add("None (Load an Image)");


    //show an image with a scrollbar
    imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Current Image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    //g.setMaximumSize(null);
    mainPanel.add(imagePanel);

    image = "koala-horizontal.png";
    JScrollPane imageScrollPane;

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
   * depending on the user inputted command, translates GUI input into controller commands.
   *
   * @param e the event to be processed
   */
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Go": {
        System.out.println("Command: " + addCommand);
        if (addCommand.equalsIgnoreCase("brighten") ||
                addCommand.equalsIgnoreCase("darken")) {
          command = addCommand + " " + lumafactor + " " + imageLoadedName + " " + imageToBeEdited;
        } else {
          command = addCommand + " " + imageLoadedName + " " + imageToBeEdited;
        }

        controller.imageProcessing();
        imageLabel.setIcon(new ImageIcon("hello.png"));
        command = "save" + " " + "currentfile.png" + " " + imageToBeEdited;
        controller.imageProcessing();
        this.addVersion(imageToBeEdited);
        imageLoadedName = imageToBeEdited;
        image = "currentfile.png";
        try {
          imageLabel.setIcon(new ImageIcon(ImageIO.read(new File("currentfile.png"))));
        } catch (IOException ie) {
          throw new IllegalArgumentException();
        }
        makeHistogramListener(imageToBeEdited);
      }
      break;
      case "Open file": {
        imageLoadedFilePath = openFileListener();
      }
      break;
      case "Save file": {
        imageSavedFilePath = saveFileListener();
      }
      break;
      case "Load text": {
        imageLoadedName = loadTextListener();
      }
      break;
      case "Save text": {
        imageSavedName = saveTextListener();
      }
      break;
      case "Brightness": {
        lumafactor = brightenTextListener();
      }
      break;
      case "Commands": {
        addCommand = this.addFilterListener();
      }
      break;
      case "Option Text": {
        imageToBeEdited = optionTextListener();
      }
      break;
      case "Current Versions": {
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
   * gets the filepath of the image that the user wants to open for modification/saving.
   *
   * @return the filepath of the file
   * @throws IllegalArgumentException if file cannot be opened
   */
  public String openFileListener() throws IllegalArgumentException {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & GIF Images", "jpg", "gif");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(ActionPerformedListener.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      fileOpenDisplay.setText(f.getAbsolutePath());
      return f.getAbsolutePath();
    } else {
      throw new IllegalArgumentException("File not opening.");
    }
  }

  /**
   * gets the filepath of the image that the user wants to save.
   *
   * @return the filepath of the file
   * @throws IllegalArgumentException if file cannot be saved
   */
  public String saveFileListener() throws IllegalArgumentException {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(ActionPerformedListener.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      fileSaveDisplay.setText(f.getAbsolutePath());
      return f.getAbsolutePath();
    } else {
      throw new IllegalArgumentException("File not saving.");
    }
  }

  /**
   * returns and resets the name of the image to be what the user inputted.
   *
   * @return message saying that the image has been loaded as the name the user wanted it to be
   */
  public String loadTextListener() {
    String temp = textLoadButton.getText();
    textLoadButton.setText("Loaded Name Has Been Entered");
    return temp;

  }

  /**
   * returns and resets the name of the image that the user wants to save their image as.
   *
   * @return message saying that the image has been saved as the name the user wanted it to be
   */
  public String saveTextListener() {
    String temp = textSaveButton.getText();
    textSaveButton.setText("Saved Name Has Been Entered");
    return temp;
  }

  /**
   * returns and resets the name of the command that the user wants to execute on their image.
   *
   * @return message saying that the command has been entered
   */
  public String optionTextListener() {
    String temp = textOptionButton.getText();
    textOptionButton.setText("Option Name Has Been Entered");
    return temp;
  }

  /**
   * returns a message after the brightness (luma factor) has been entered.
   *
   * @return message saying that the luma factor has been entered
   */
  public String brightenTextListener() {
    String temp = brightText.getText();
    brightText.setText("Amount of Brightness");
    return temp;
  }

  /**
   * This allowed the user to select a filter to put on a image.
   *
   * @return hte name of the filter
   */
  public String addFilterListener() {
    String temp = "";
    String[] options = {"Blur", "Sharpen", "Sepia", "Grayscale", "Visualize Red",
        "Visualize Blue", "Visualize Green", "Horizontal-flip", "Vertical-flip", "Brighten",
        "Darken"};
    int retvalue = JOptionPane.showOptionDialog(ActionPerformedListener.this,
            "Please choose number", "Options", JOptionPane.YES_OPTION,
            JOptionPane.INFORMATION_MESSAGE, null, options, options[4]);
    optionsNameDisplay.setText(options[retvalue]);
    temp = options[retvalue];
    return temp;
  }

  /**
   * This allows users to select the user to select an already loaded version of an image.
   *
   * @return the name of the current version to edit
   */
  public String currentVersionsListener() {
    String temp = "";
    int currVersionsOptionsLength = currVersionOptions.size();
    String[] tempOptions = new String[currVersionsOptionsLength];
    for (int i = 0; i < currVersionsOptionsLength; i++) {
      tempOptions[i] = currVersionOptions.get(i);
    }
    int retvalue = JOptionPane.showOptionDialog(ActionPerformedListener.this,
            "Please choose number", "Options", JOptionPane.YES_OPTION,
            JOptionPane.INFORMATION_MESSAGE, null, tempOptions,
            tempOptions[currVersionsOptionsLength / 2]);
    currVersionsDisplay.setText(tempOptions[retvalue]);
    temp = currVersionOptions.get(retvalue);
    return temp;
  }


  /**
   * adds an edited version to the array.
   *
   * @param version the name of the version to be added to the button
   */
  public void addVersion(String version) {
    if (currVersionOptions.get(0).equalsIgnoreCase("None (Load an Image)")) {
      currVersionOptions.remove(0);
    }
    currVersionOptions.add(version);
  }


  /**
   * all the commands that have been entered so far based on user actions in the GUI.
   *
   * @return all the commands as a string.
   */
  public String getCommand() {
    String tempCommand = command;
    command = "";
    return tempCommand;
  }

  /**
   * delegates on controller and view to make R, G, B, and Intensity histograms for an image.
   */
  public void makeHistogramListener(String image) {
    listRed = controller.getFrequencyBoard(image, "Red");
    listGreen = controller.getFrequencyBoard(image, "Green");
    listBlue = controller.getFrequencyBoard(image, "Blue");
    listGray = controller.getBrightnessBoard(image);

    JFrameView frameRed = new JFrameView(listRed, Color.RED);
    frameRed.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frameRed.setVisible(true);

    JFrameView frameGreen = new JFrameView(listGreen, Color.GREEN);
    frameGreen.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frameGreen.setVisible(true);

    JFrameView frameBlue = new JFrameView(listBlue, Color.CYAN);
    frameBlue.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frameBlue.setVisible(true);

    JFrameView frameIntensity = new JFrameView(listGray, Color.BLACK);
    frameIntensity.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frameIntensity.setVisible(true);
  }

}
