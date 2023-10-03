package cgg.a09;
import static cgtools.Vector.*;
import static cgtools.Matrix.*;
import static cgtools.Matrix.multiply;


import cgg.*;
import cgtools.*;

public class Main {

    public static void main(String[] args) {
      final int width = 1500;
      final int height = 1000;
      double angle = Math.PI / 1.5;
      double testAngle = Math.toRadians(80);

      Image image = new Image(width, height);      
      PinHoleCamera camera = new PinHoleCamera(width,
                                               height,
                                               testAngle,
                                               multiply(
                                                translation(0, 2, 0),
                                                rotation(yAxis,0),                                          
                                                rotation(xAxis,35),
                                                scaling(1,1,-1),                                                
                                                translation(0,0,50)
                                                
                                                ));

      var orange = hsvToRgb(color(0.09, 1, 1));
      var green = hsvToRgb(color(0.33, 1, 1));
      var red = hsvToRgb(color(0.001,1,0.9));
      var sky = new Emission(new ImageTexture("C:/Users/lukas/Desktop/nightsky.png"));
      var gray = new Diffuse(color(0.8));
      var venus = new Bright(new ImageTexture("C:/Users/lukas/Desktop/venus.png"));
      var moon = new Bright(new ImageTexture("C:/Users/lukas/Desktop/moon.png"));
      var nebel = new Emission(new ImageTexture("C:/Users/lukas/Desktop/nebel.png"));
      

      
      var galaxy = new Group(
        new Background(sky),               
        new Sphere(point(-15,1,0),10, venus),
        new Sphere(point(20,5,0),8, moon)
        
         
   
      );


      


      Sampler sampler = new RayTracer(camera, galaxy);
      image.sample(sampler, 16); 
        
  
      // Write the image to disk.
      final String filename = "doc/a09.png";    
      image.write(filename);    
      System.out.println("Wrote image: " + filename);
     
      
    }
  }

    








