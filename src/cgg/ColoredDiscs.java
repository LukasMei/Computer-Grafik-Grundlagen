/** @author henrik.tramberend@beuth-hochschule.de */
package cgg;

import java.util.ArrayList;
import java.util.List;

import javax.swing.GrayFilter;

import cgtools.*;

// Represents the contents of an image. Provides the same color for all pixels.
public class ColoredDiscs implements Sampler {
  List<Disc> discList;  
  
  public ColoredDiscs(int amount, int width, int height){ 
    

    discList = new ArrayList<>();

    for (int i = 0; i < amount; i++){
      Color color = new Color(Random.random(), Random.random(), Random.random());
      discList.add(new Disc(Random.random()*width, Random.random()*height,Random.random()*amount, color));

    }
    discList.sort(((o1, o2) -> Double.compare(o1.radius(), o2.radius())));

  } 
 


     

  @Override
  public Color getColor(double x, double y) {
     for (Disc disc : discList) {
      if(disc.isPointInDisc(x, y)){
        return disc.color();
      }
      
     }
     return Vector.black;      
    
  }

  
   
  

  


  

 
  

  
}   

