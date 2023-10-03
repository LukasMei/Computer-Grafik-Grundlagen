/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a01;

import static cgtools.Vector.*;
import cgg.*;

public class Main {

  public static void main(String[] args) {
    final int width = 480;
    final int height = 270;
    final int radius = 5;
    final double pi = 3.142;

    // This class instance defines the contents of the image.
    //ConstantColor content = new ConstantColor(blue);
    ChessPattern content = new ChessPattern(green, black, width,height);
    Pattern pattern = new Pattern(width, height);


    // Creates an image and iterates over all pixel positions inside the image.
    Image image = new Image(width, height);
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        // Sets the color for one particular pixel.
        image.setPixel(x, y, content.getColor(x, y));
      }
    }

    Image imageP = new Image(width, height);
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        // Sets the color for one particular pixel.
        imageP.setPixel(x, y, pattern.getColor(x, y));
      }
    }

    // Write the image to disk.
    final String filename = "doc/a01-image.png";
    final String filePattern = "doc/a01-pattern.png";
    image.write(filename);
    imageP.write(filePattern);
    System.out.println("Wrote image: " + filename);
    System.out.println("Wrote image: " + filePattern);
    
  }
}
