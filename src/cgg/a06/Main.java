package cgg.a06;
import static cgtools.Vector.*;
import static cgtools.Matrix.*;

import java.util.ArrayList;
import java.util.List;

import cgg.*;
import cgtools.*;

public class Main {

    public static void main(String[] args) {
      final int width = 400;
      final int height = 700;
      double angle = Math.PI / 2;

      Image image = new Image(width, height);      
      PinHoleCamera camera = new PinHoleCamera(width,
                                               height,
                                               angle,
                                               multiply(
                                                rotation(xAxis,-30),
                                                translation(0,0,20)
                                                ));

      var orange = hsvToRgb(color(0.09, 1, 1));
      var green = hsvToRgb(color(0.21, 1, 1));
      var red = hsvToRgb(color(0.001,1,0.9));
      var experiment = hsvToRgb(color(0.3, 0.5, 0.20));
      var violet = hsvToRgb(color(0.75,0.75,0.75));      
      var darkgrey = hsvToRgb(color(0.156,0.148,0.150));
      var test01 = new Emission(hsvToRgb(color(0.2,0.4,0.2)));
      var test02 = new Emission(hsvToRgb(color(1,1,0)));

      double objectHeight = 13;
      double radius = 2.0;

      var scene01 = new Group(
        new Background(test02),
        new Plane(point(0,-5,0),yAxis,200,new Diffuse(experiment)),
        new Plane(point(0,0,0), yAxis, 10, new Diffuse(darkgrey)),       
        new Cylinder(objectHeight,radius,new Diffuse(violet)),
        new Sphere(point(0, 16, 0),3.0,new Diffuse(orange))
                
        
      );

      int numSpheres = 20;
      

       for(int i = 0; i < numSpheres ; i++){
        double y = i * (objectHeight / numSpheres); // Höhenposition der Sphären
        double a = y * (2 * Math.PI / numSpheres); // Winkel der aktuellen Spähre
        double x = radius * Math.cos(a);              // x - Koordinate der Sphäre 
        double z = radius * Math.sin(a);              // z - Koordinate der Spähre
        

        scene01.addShape(new Sphere(point (x,y,z),0.5, new Diffuse(red)));

      }
      for(int i = 0; i < numSpheres ; i++){
        double y = i * (objectHeight / numSpheres); // Höhenposition der Sphären
        double a = y * (2 * Math.PI / numSpheres) + Math.PI; // Winkel der aktuellen Spähre
        double x = radius * Math.cos(a);              // x - Koordinate der Sphäre 
        double z = radius * Math.sin(a);              // z - Koordinate der Spähre
        

        scene01.addShape(new Sphere(point (x,y,z),0.5, new Diffuse(red)));

      }

      int numPlanes = 8;
      for(int i = 0; i < numPlanes ; i++){
        double x = 10 - i;
        var color = new Diffuse(hsvToRgb(color(Random.random(), Random.random(), Random.random())));
        scene01.addShape(new Plane(point(0,0,0),yAxis,x,color));
      }
     
 
      
      Sampler sampler = new StratifiedSampler(new RayTracer(camera, scene01), 16);
      image.sample(sampler); 
        
  
      // Write the image to disk.
      final String filename = "doc/a06-camera.png";    
      image.write(filename);    
      System.out.println("Wrote image: " + filename);
     
      
    }
}








