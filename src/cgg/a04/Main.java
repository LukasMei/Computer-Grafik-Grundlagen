package cgg.a04;
import static cgtools.Vector.*;

import java.util.ArrayList;
import java.util.List;

import cgg.*;
import cgtools.*;

public class Main {

    public static void main(String[] args) {
      final int width = 800;
      final int height = 600;

      Image image = new Image(width, height);      
      PinHoleCamera camera = new PinHoleCamera(width, height, Math.PI / 1.8);

      var orange = hsvToRgb(color(0.09, 1, 1));
      var green = hsvToRgb(color(0.21, 1, 1));
      var red = hsvToRgb(color(0.001,1,0.9));
      var experiment = hsvToRgb(color(0.3, 0.5, 0.20));
      var violet = hsvToRgb(color(0.75,0.75,0.75));
      var darkgrey = hsvToRgb(color(0.156,0.148,0.150));
    

      var scene01 = new Group(
        new Background(new Emission(white)),
        new Sphere(point(0,0,-5), 1,new Diffuse(orange) )
      );

      var scene02 = new Group(
        new Background(new Diffuse(gray)),
        new Plane(point(0.0, -0.5, 0.0), direction(0, 1, 0),2.0, new Diffuse(gray)),
        new Sphere(point(-1.0, -0.25, -2.5), 0.7, new Diffuse(orange)),
        new Sphere(point(0.0, -0.25, -2.5), 0.5, new Diffuse(green)),
        new Sphere(point(1.0, -0.25, -2.5), 0.7, new Diffuse(orange))
      );
      
      var scene03 = new Group(
          new Background(new Diffuse(experiment)),          
          new Plane(point(0,-2,-6), yAxis, 3.6, new Diffuse(darkgrey)),
          new Plane(point(0,-2,-6), yAxis, 3.5, new Diffuse(hsvToRgb(color(0.136,0.138,0.140)))),
          new Plane(point(0,-2,-6), yAxis, 3.4, new Diffuse(hsvToRgb(color(0.126,0.138,0.130)))),
          new Plane(point(0,-2,-6), yAxis, 3.3, new Diffuse( hsvToRgb(color(0.116,0.128,0.120)))),
          new Plane(point(0,-2,-6), yAxis, 3.2, new Diffuse( hsvToRgb(color(0.106,0.118,0.110)))),
          new Sphere(point(0, -1, -6), 0.7, new Diffuse(orange)),
          new Sphere(point(-1.5, -1, -5), 0.7, new Diffuse(violet)),
          new Sphere(point(1.5, -1, -5), 0.7, new Diffuse(violet)),
          new Plane(point(-3,2,-5), xAxis, 1.5, new Diffuse(darkgrey)),
          new Plane(point(-3,2,-5), xAxis, 1.45, new Diffuse(hsvToRgb(color(0.1,0.1,0.1)))),
          new Plane(point(-3,2,-5), xAxis, 1.4, new Diffuse(hsvToRgb(color(0.05,0.05,0.05)))),
          new Plane(point(-3,2,-5), xAxis, 1.35, new Diffuse(hsvToRgb(color(0.02,0.02,0.02)))),
          new Sphere(point(-3, 2, -5), 0.5, new Diffuse(orange)),
          new Sphere(point(-1.5, 2.5, -5), 0.5, new Diffuse(orange)),
          new Sphere(point(0, 3, -5), 0.5, new Diffuse(orange)) 

          
      ); 

      
  
     
      
      
      Sampler sampler = new StratifiedSampler(new RayTracer(camera, scene01), 16);
      image.sample(sampler); 
        
  
      // Write the image to disk.
      final String filename = "doc/a04-scene.test.png";    
      image.write(filename);    
      System.out.println("Wrote image: " + filename);
     
      
    }
}








