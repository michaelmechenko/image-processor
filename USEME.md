------------- IMAGE PROCESSING APPLICATION README -----------

This program represents an image processing application. A user can load an image, choose from a
defined set of modification commands to change their image, and save that image. The user
also has the option of running operating on multiple loaded images concurrently.

The commands include:
"load" : translates the PPM file into an object that can have operations performed on it
(by transforming it into a 2D array of pixels.
Example command: "load <filename> <reference_name>"
"brighten": changes the brightness of an image by changing all RGB values by an inputted factor.
a positive factor brightens the image, a negative factor darkens the image.
Args:
- luma factor: integer representing the degree to which the image is brightened or darkened
  Example command: "brighten <luma_factor> <image_to_be_operated_on> <new_reference_name>"

  "horizontal-flip": flips the image left to right.
  Example command: "horizontal-flip <image_to_be_operated_on> <new_reference_name>"
  "vertical-flip": flips the image right to left.
  Example command: "vertical-flip <image_to_be_operated_on> <new_reference_name>"
  "value-component": creates a grayscale image.
  Example command: "value-component <image_to_be_operated_on> <new_reference_name>"
  "value-component-color": creates an image of any of the RGB values (i.e only red, only blue,
  or only green)
  Args:
  - color: the color to be visualized (red, green, or blue)
  Example command: "value-component-color <color> <image_to_be_operated_on>
  <new_reference_name>"

  "save": saves the image to root directory, regardless of whether any modifications have been
  made.
  Example command: "save <filename_to_write_to> <image_to_write>"

  Commands can be used in any order, consecutively.

In order to run:
When the program runs, first load in an image using the example command written above under
load. Then feel free to type any of the example commands as a command line argument as long
as you perform operations on images only after they have been loaded in. If you would like to
see the changes, save the file using the example command under save.

Example:
User input: "load test.ppm test brighten 10 test test-brighten horizontal-flip
test-brighten test-brighten-horizontal load test-2.ppm test2 value-component-color green test
test-green save test-green.ppm test-green value-component test2 test2-grayscale"

    What this does: This loads the test image, brightens and horizontally flips one version.
    Another operation is performed on the original test image, visualizing the green components
    and only saves/output the green version. This loads the second test image as well and
    makes it int grayscale.

    Output at the end of processing: this input there are four versions of test in the prev images
    map in test's model (test, test-brighten, test-brighten-horizontal, and test-green). There are
    also two versions of test2 in its prev images map (test2, test2-grayscale). Any of these images
    in both models can have operations performed on them and can be saved/outputted.



Graphics Base User Interface
In order to start the program, enter the jar or just include no arguments in the command line.


The top panel loads the image, select the file, name it (press enter here), and then press load. If
an argument is not included, it won't load, so fill in the arguments. The second panel edits the 
image. The command button allows you to select the method of editing it, and the new name of the
image. Hit go to perform the command. The current versions button allows you to select which version
to edit if it is already loaded. The bottom panel saves the image in the same fashion as described 
for load.






