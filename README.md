## IMAGE PROCESSING APPLICATION

This program represents an image processing application. A user can load an image, choose from a
defined set of modification commands to change their image, and save that image. The user
also has the option of running operating on multiple loaded images concurrently.

## File Package

- FileInterface

  - Interface that represents a file

- ConventionalFormats Class

  - Reads and writes BMP, PNG, and JPG files by converting
    the image into a buffered image and then translating that into a 2D array of pixels

- PPM Class
  - Reads and writes to a PPM file by converting the string into a 2D array of pixels

## Model Package

- AbstractPixel Class

  - Represents a PPM pixel with fields for the red, green, and blue values for that
    image.

- PPMPixel Class

  - Represents a PPM pixel with fields for the red, green, and blue values
    (defaults alpha to 255) for that image.

- ConvPixel Class

  - Represents a conventional pixel with a red, green, and blue values

- IPModel Class
  - Represents a full image using a 2D array of pixels and their equivalent red, green, and blue values

## Controller Package

- IPControllerInterface Interface

  - Interface that houses program functionality and processing

- IPController Class

  - Represents the program with fields representing the loaded
    images that the user can perform operations on. Responsible for iterating through user input
    and transforming them into method calls

- IPGUIController Class
  - Manages the program in conjunction with the view. Responds to view commands and processes them for the model

## Command Package

- IPCommandInterface
  - Represents a command or operation that can be performed on an image. This
    interface enables the formatting of the method calls to be sorted into a HashMap so it can more easily translate user input into operations

## View Package

- ActionPerformedListener Class

  - Implements the ActionListener interface and is responsible for translating user input into
    method calls

- IView Interface

  - Manages the view for the program

- Histogram Class

  - Creates a histogram based on the image's pixel values

- JFrameView Class
  - Creates a JFrame for the view that displays the image, histogram, and sends commands to
    the controller
