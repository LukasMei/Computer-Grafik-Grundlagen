package cgg.a05;
import static cgtools.Vector.*;

import java.util.ArrayList;
import java.util.List;

import cgg.*;
import cgtools.*;

public class Main {

    public static void main(String[] args) {
      final int width = 800;
      final int height = 600;
      double angle = Math.PI / 2;

      Image image = new Image(width, height);      
      PinHoleCamera camera = new PinHoleCamera(width, height, angle);

      var orange = hsvToRgb(color(0.09, 1, 1));
      var green = hsvToRgb(color(0.21, 1, 1));
      var red = hsvToRgb(color(0.001,1,0.9));
      var experiment = hsvToRgb(color(0.3, 0.5, 0.20));
      var violet = hsvToRgb(color(0.75,0.75,0.75));      
      var darkgrey = new Emission(hsvToRgb(color(0.156,0.148,0.150)));
      var test = new Emission(hsvToRgb(color(0.2,0.4,0.2)));

      var scene01 = new Group(
        new Background(darkgrey),
        new Plane(point(0,-1,-5),yAxis,200,new Diffuse(green)),
        new Sphere(point(0,0,-5), 1, new Diffuse(red)           
        )
      );

      var scene02 = new Group(
        new Background(test),
        new Plane(point(0.0, -0.5, 0.0), direction(0, 1, 0),2.0, new Diffuse(orange)),
        new Sphere(point(-1.0, -0.25, -2.5), 0.7, new Diffuse(orange)),
        new Sphere(point(0.0, -0.25, -2.5), 0.5, new Diffuse(orange)),
        new Sphere(point(1.0, -0.25, -2.5), 0.7, new Diffuse(orange))
      );

      

      
      

      var scene03 = new Group(
        new Background(test),
        new Plane(point(0,-1,-5),yAxis,50,new Diffuse(hsvToRgb(color(0.156,0.148,0.150)))),
        new Sphere(point(0,0,-5), 1, new Diffuse(orange)),
        new Sphere(point(2.5,0.2,-5), 1,new Diffuse(red)),       
        new Sphere(point(-2.5,0.2,-5), 1,new Diffuse(red)),
        new Sphere(point(4.3,-0.3,-5), 0.5,new Diffuse(green)),
        new Sphere(point(-4.3,-0.3,-5), 0.5,new Diffuse(green))
        
                   
        
      );
      
      
 
      
      Sampler sampler = new StratifiedSampler(new RayTracer(camera, scene03), 16);
      image.sample(sampler); 
        
  
      // Write the image to disk.
      final String filename = "doc/a05-diffuse-spheres.png";    
      image.write(filename);    
      System.out.println("Wrote image: " + filename);
     
      
    }
}








