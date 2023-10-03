/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a02;

import static cgtools.Vector.*;

import java.util.ArrayList;
import java.util.List;

import cgg.*;
import cgtools.*;

public class Main {

  public static void main(String[] args) {
    final int width = 480;
    final int height = 270;   

   

    // This class instance defines the contents of the image.
    //ConstantColor content = new ConstantColor(blue);
    ColoredDiscs content = new ColoredDiscs(120, width, height);
    Image image = new Image(width, height);
    image.sample(new StratifiedSampler(content,16)); 
      

    // Write the image to disk.
    final String filename = "doc/a02-discs-supersampling.png";    
    image.write(filename);    
    System.out.println("Wrote image: " + filename);
   
    
  }
}
