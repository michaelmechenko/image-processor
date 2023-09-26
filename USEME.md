## Instructions

To run this program, run the jar from a command line input in the following format:
Running a script: java -jar image-processor.jar -file <path-of-script>
Using the interactive text terminal: java -jar image-processor.jar -text
Using the GUI: java -jar image-processor.jar

### Commands

- "load" : loads a given image of acceptable format into the program

  - Example command: "load <filename> <reference_name>"

- "save": saves the image to root directory, regardless of whether any modifications have been made

  - Example command: "save <filename_to_write_to> <image_to_write>"

- "brighten": changes the brightness of an image by changing all RGB values by an inputted factor. A positive factor brightens the image, a negative factor darkens the image.

  - Example command: "brighten <luma_factor> <image_to_be_operated_on> <new_reference_name>"

- "horizontal-flip": flips the image left to right

  - Example command: "horizontal-flip <image_to_be_operated_on> <new_reference_name>"

- "vertical-flip": flips the image top to bottom

  - Example command: "vertical-flip <image_to_be_operated_on> <new_reference_name>"

- "value-component": creates a grayscale image

  - Example command: "value-component <image_to_be_operated_on> <new_reference_name>"

- "value-component-color": creates an image solely based on a given color
  - color: the color to be visualized (red, green, or blue)
  - Example command: "value-component-color <color> <image_to_be_operated_on> <new_reference_name>"
