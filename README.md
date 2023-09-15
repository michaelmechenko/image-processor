------------- IMAGE PROCESSING APPLICATION README -----------

This program represents an image processing application. A user can load an image, choose from a
defined set of modification commands to change their image, and save that image. The user
also has the option of running operating on multiple loaded images concurrently.

Files Structure:
MODEL PACKAGE

Files Interface/Package
ConventionalFormats class -- reads and writes in BMP, PNG, and JPG files by converting
the image into a buffered image and then translating that into a 2D array of pixels
and going back the opposite way.

PPM class -- reads and writes in a PPM file by converting the string into a 2D array of pixels

Abstract class -- represents a PPM pixel with fields for the red, green, and blue values for that
image. We made these fields non-final, but private, since these values may be altered for
certain image modifications  (such a changing brightness). Responsible for executing the above
commands for each individual pixel by altering these fields. Overrode equals and hashcode in
order to more efficiently compare the RGB values of two pixels in testing.

PPMPixel class -- represents a PPM pixel with fields for the red, green, and blue values
(defaults alpha to 255) for that image. We made these fields non-final, but private, since these
values may be altered for certain image modifications  (such a changing brightness).

ConvPixel class -- represents a PPM pixel with fields for the red, green, blue, and alpha
values for that image. We made these fields non-final, but private, since these
values may be altered for certain image modifications  (such a changing brightness).

IPModel class -- represents a full PPM image with fields representing the image itself
(in the form of a 2D array of Pixels), the previous version of the image (a hashmap stores
the 2D pixel array and its corresponding user inputted name), and the original file path
location. Responsible for executing above commands on the image as a whole, storing the
images, and retrieving the correct image that needs to be performed on. 

The sharpen and blur helpers that apply the kernel onto each pixel are in the IPModel to avoid the
pixel from accessing the IPModel because we believe that it would be bad design as the pixel would 
be accessing the board of pixels and its scope as a class would be expanded.

CONTROLLER PACKAGE

IPController class -- represents the program as a whole with fields representing the loaded
images that the user can perform operations on. Responsible for iterating through user input
and transforming them into method calls (this is done through the command design pattern).

IPGUIController class -- manages the program as a whole in conjunction with the view, in particular
with the view. Responds to view commands and processes them into something digestable for the model.

COMMAND PACKAGE (Command Design Pattern)
IPCommandInterface -- represents a command or operation that can be performed on an image. This
interface enables the formatting of the method calls to be sorted into a HashMap
called KnownCommands in IPController so it can more easily translate user input into operations.

** contains every command above except for load and save which have completely different formats
for user input. decided against abstracting these arguments further these are two fundamental
commands that every type of file would need for this type of program and this is the most
readable format.

-main homework 3 edits
VIEW PACKAGE
Action Listener -- this acts as the view, translates the user inputs into commands digestable 
for the controller to process.

Histogram -- makes the histograms based on the pixel valuess

MAIN METHOD
ImageUtil: stores the main method, representing the core of where the program runs. Command
line args are processed here and inputted into the controller


TO GET A BETTER UNDERSTANDING OF THE FULL SCOPE OF THIS PROGRAM GO TO IPCONTROLLER TEST
(testMultipleVersions) WHICH PERFORMS MULTIPLE OPERATIONS AND GIVES A STEP BY STEP BREAKDOWN.


test1, test2, and white rose image were created by us (random number generator or taken by us).


we decide to only account for .jpg not .jpeg.
alpha value for PPMPixel should just be set to 1 (fully opaque, make sure its correct)



