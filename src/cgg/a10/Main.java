package cgg.a10;
import static cgtools.Vector.*;
import static cgtools.Matrix.*;
import static cgtools.Matrix.multiply;


import cgg.*;
import cgtools.*;

public class Main {

    public static void main(String[] args) {
      final int width = 700;
      final int height = 500;
      double angle = Math.PI / 1.5;
      double testAngle = Math.toRadians(80);

      Image image = new Image(width, height);      
      PinHoleCamera camera = new PinHoleCamera(width,
                                               height,
                                               testAngle,
                                               multiply(
                                                rotation(xAxis,-25),
                                                translation(0,0,25)
                                                
                                                ));

      var orange = hsvToRgb(color(0.09, 1, 1));
      var green = hsvToRgb(color(0.33, 1, 1));
      var red = hsvToRgb(color(0.001,1,0.9));
      var gray = new Emission(color(0.8));
      var metal = new PolishedMetal(white);
      var dull = new DullMetal(white, 0.5);
      var test = new Emission(hsvToRgb(color(0.2,0.4,0.2)));
      var brightOrange = new Color(1, 0.6, 0.3);
      var wüstenboden = new Diffuse(new ImageTexture("C:/Users/lukas/Desktop/BHT/wüstenboden.png"));
      var planeColor = new Diffuse(hsvToRgb(color(0.156,0.148,0.150))); 


          
     
      

      
      var scene = new Group(
        new Background(gray),
        new Plane(point(0,-1,-5),yAxis,200,planeColor),       
        new Plane(point(-9,0.01,0), yAxis,4,dull),
        new Sphere(point(-9,2.1,0),2,new Diffuse(red)),
        new Sphere(point(-16,3,-10),4,dull),
        new Plane(point(0,0.01,0), yAxis,4,metal),
        new Sphere(point(0,2.1,0),2,new Diffuse(orange)),
        new Sphere(point(-4,3,-10),4,metal),
        new Sphere(point(7,2.1,-6),1.5,new Diffuse(red)),
        new Sphere(point(10,2.1,-6),1.25,new Diffuse(green)),
        new Sphere(point(13,2.1,-6),1,new Diffuse(orange)),
        new Plane(point(8.2,3,0), zAxis,4.3,new Glass(brightOrange)),
        new Sphere(point(7,3,-14),4,new Glass(brightOrange))        
       




         
   
      ); 


      


      Sampler sampler = new RayTracer(camera, scene);
      image.sample(sampler, 16); 
        
  
      // Write the image to disk.
      final String filename = "doc/a10.png";    
      image.write(filename);    
      System.out.println("Wrote image: " + filename);
     
      
    }
  }

    








