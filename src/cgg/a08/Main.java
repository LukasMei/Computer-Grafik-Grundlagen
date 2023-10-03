package cgg.a08;
import static cgtools.Vector.*;
import static cgtools.Matrix.*;
import static cgtools.Matrix.multiply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.GrayFilter;

import cgg.*;
import cgtools.*;

public class Main {

    public static void main(String[] args) {
      final int width = 600;
      final int height = 600;
      double angle = Math.PI / 2;

      Image image = new Image(width, height);      
      PinHoleCamera camera = new PinHoleCamera(width,
                                               height,
                                               angle,
                                               multiply(                                                
                                                rotation(xAxis,-35),                                                
                                                translation(0,0,70)
                                                
                                                ));

      var orange = hsvToRgb(color(0.09, 1, 1));
      var green = hsvToRgb(color(0.33, 1, 1));
      var red = hsvToRgb(color(0.001,1,0.9));
      var experiment = hsvToRgb(color(0.3, 0.5, 0.20));
      var violet = hsvToRgb(color(0.75,0.75,0.75));      
      var darkgrey = hsvToRgb(color(0.156,0.148,0.150));
      var pink = hsvToRgb(color(0.917,1,1));
      var lightViolett = hsvToRgb(color(0.8,0.8,1));
      var lightblue = hsvToRgb(color(0.583,1,1));
      var darkblue = hsvToRgb(color(0.667,1,0.8));
      var sky = new Emission(hsvToRgb(color(0,0,0.06)));
      var orangeBright = new Bright(orange);
      var violetBright = new Bright(violet);
      var redBright = new Bright(red);
      var greenBright = new Bright(green);
      var pinkBright = new Bright(pink);
      var lightViolettBright = new Bright(lightViolett);
      var lightblueBright = new Bright(lightblue);
      var darkblueBright = new Bright(darkblue);

      var ringColors = new ArrayList<Material>();

      
      ringColors.add(darkblueBright);
      ringColors.add(lightblueBright);
      ringColors.add(pinkBright);
      ringColors.add(lightViolettBright);
      ringColors.add(redBright);
      ringColors.add(greenBright);

      var cylinder = new Cylinder(25, 8, greenBright);
      var spiral = makeSpiral(30, 9, 25 ,darkblueBright);

      

      var myObject = createObject(orangeBright, new Diffuse(red), orangeBright, ringColors);

      var figure = createFigure(lightViolettBright,redBright,lightblueBright);

      var ring = makeRing(8,3, 1,1.5,ringColors);

      List<Shape> figuresA = new ArrayList<Shape>();
      List<Shape> figuresB = new ArrayList<Shape>();
      List<Shape> figuresC = new ArrayList<Shape>();
      
      int amount = 100;

      for(int i = 0; i != amount; i++){       
        figuresA.add(new Group(translation(-15, 1, 12 - (i * 15) ),figure));      
        
      }
      for(int i = 0; i != amount; i++){       
        figuresB.add(new Group(translation(0, 1, 8 - (i * 15) ),figure));      
        
      }
      for(int i = 0; i != amount; i++){       
        figuresC.add(new Group(translation(15, 1, 4 - (i * 15) ),figure));      
        
      }

      

      
      var benchmark = new Group(
        new Background(sky),
        new Plane(point(0,-5,0),yAxis,100000,new Diffuse(darkgrey)),       
        new Group(figuresA),
        new Group(figuresB),
        new Group(figuresC) 


      );

     


      Sampler sampler = new RayTracer(camera, benchmark);
      image.sample(sampler, 16); 
        
  
      // Write the image to disk.
      final String filename = "doc/a08-benchmark-scene.png";    
      image.write(filename);    
      System.out.println("Wrote image: " + filename);
     
      
    }

    public static Shape makeSpiral(int numSpheres, double radius, double objectHeight, Material m){
      var scene = new Group();

      for(int i = 0; i < numSpheres ; i++){
        double y = i * (objectHeight / numSpheres); // Höhenposition der Sphären
        double a = y * (2 * Math.PI / numSpheres); // Winkel der aktuellen Spähre
        double x = radius * Math.cos(a);              // x - Koordinate der Sphäre 
        double z = radius * Math.sin(a);              // z - Koordinate der Spähre
        


         scene.addShape(new Sphere(point (x,y,z),1.0, m));

      }
      return scene;
    }

    public static Shape createFigure(Material body, Material ears, Material eyeMaterial){
      var scene = new Group();

      scene.addShape(new Sphere(point(0,4,0), 3,body));
      scene.addShape(new Sphere(point(0,0,0), 4,body));
      scene.addShape(new Sphere(point(2.3,6.5,0), 1,ears));
      scene.addShape(new Sphere(point(-2.3,6.5,0), 1,ears));
      scene.addShape(new Sphere(point(1,5.7,2.1), 0.35,eyeMaterial));
      scene.addShape(new Sphere(point(-1,5.7,2.1), 0.35,eyeMaterial)); 

      return scene;
    }

    public static Shape createObject(Material materialCylinder, Material materialPlane, Material materialSphere, ArrayList<Material> materials ){
      var scene = new Group();

      var ring = makeRing(8, 5,9.5 ,0.5,materials);
      
      scene.addShape(new Cylinder(8, 3, materialCylinder));
      scene.addShape(new Plane(point(0,8.5,0),yAxis,6, materialPlane ));
      scene.addShape(new Sphere(point(0,10,0),3.5,materialSphere));
      scene.addShape(ring);
      
      return scene;
    }

    public static Shape makeRing(int numSpheres, double radiusKreis,double y ,double radiusSphere, ArrayList<Material> materials ){
      var scene = new Group();

      for(int i = 0; i < numSpheres ; i++){
        double angle = 2 * Math.PI * i / numSpheres;
        double x = Math.cos(angle) * radiusKreis;
        double z = Math.sin(angle) * radiusKreis;

        Material material = materials.get(i % materials.size());          
        scene.addShape(new Sphere(point(x, y , z),radiusSphere,material));
        
      }
      return scene;
    }
  }

    








