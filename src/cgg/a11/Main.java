package cgg.a11;
import static cgtools.Vector.*;
import static cgtools.Vector.multiply;
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
                                                rotation(xAxis,-35),
                                                translation(0,0,30)
                                                
                                                ));

      var orange = hsvToRgb(color(0.09, 1, 1));
      var green = hsvToRgb(color(0.33, 1, 1));
      var red = hsvToRgb(color(0.001,1,0.9));
      var gray = new Emission(color(0.8));
      var metal = new PolishedMetal(white);
      var dull = new DullMetal(white, 0.5);
      var test = new Emission(hsvToRgb(color(0.2,0.4,0.2)));
      var brightOrange = new Color(1, 0.6, 0.3);
      var wüstenboden = new Diffuse(new ImageTexture("/home/lukas/Bilder/Fotos/wüstenboden.png"));
      var himmel = new Emission(new ImageTexture("/home/lukas/Bilder/Fotos/himmel.jpg"));
      var planeColor = new Diffuse(hsvToRgb(color(0.156,0.148,0.150)));
      
      Light l = new DirectionalLight(multiply(l, direction(0, 0, 1)), Color.white);


      Group b = new Group(new Background(himmel));      
     
      

      
      var scene = new Group(
        new Background(gray),
        new Plane(point(0,-1,0),yAxis,50,test),
        new Sphere(point(0,2,0),4,new Diffuse(red))   
   
      ); 


      World w = new World(b,11,12);


      Sampler sampler = new RayTracer(camera, scene);
      image.sample(sampler, 16); 
        
  
      // Write the image to disk.
      final String filename = "doc/a11.png";    
      image.write(filename);    
      System.out.println("Wrote image: " + filename);
     
      
    }
  }

    








