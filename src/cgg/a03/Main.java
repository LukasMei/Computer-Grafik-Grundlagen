package cgg.a03;
import static cgtools.Vector.*;

import java.util.ArrayList;
import java.util.List;

import cgg.*;
import cgtools.*;

public class Main {

    public static void main(String[] args) {
      final int width = 600;
      final int height = 600;   
  
     
      Color backColor = hsvToRgb(color(0.3, 0.5, 0.20));
      double winkel = Math.PI/4; 
      Point eye = point(0, 0, 18);        
      PinHoleCamera camera = new PinHoleCamera(width, height, winkel, eye);
      Image image = new Image(width, height);
      

      RayTracerOld rayTracer = new RayTracerOld(camera,backColor );
            
      
      rayTracer.addSphere(new Sphere(point(0, 1.5, -2), 1,hsvToRgb(color(0.2, 0.25, 0.3))));
      rayTracer.addSphere(new Sphere(point(0, -3, 4), 1,hsvToRgb(color(0.2, 0.25, 0.3))));
      rayTracer.addSphere(new Sphere(point(0, -1.5, -5), 1,hsvToRgb(color(0.5, 0.55, 0.6))));
      rayTracer.addSphere(new Sphere(point(3, -1.5, -10), 1,hsvToRgb(color(0.5, 0.55, 0.6))));
      rayTracer.addSphere(new Sphere(point(-3, -1.5, -10), 1,hsvToRgb(color(0.5, 0.55, 0.6))));
      
      Sampler sampler = new StratifiedSampler(rayTracer, 16);
      image.sample(sampler); 
        
  
      // Write the image to disk.
      final String filename = "doc/a03-spheresTest.png";    
      image.write(filename);    
      System.out.println("Wrote image: " + filename);
     
      
    }
}








