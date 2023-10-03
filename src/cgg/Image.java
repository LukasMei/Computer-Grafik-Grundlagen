/** @author henrik.tramberend@beuth-hochschule.de */
package cgg;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



import cgtools.*;

public class Image {

  private int width = 0;
  private int height = 0;
  private double[] tuWas;
  private int cores = Runtime.getRuntime().availableProcessors();

  private int testCors = 5;

  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    int res = (width*height) * 3;
    tuWas = new double[res];    
    
  }

  public void sample(Sampler content, int n){     
    
   // Creates an image and iterates over all pixel positions inside the image.
    

    ExecutorService pool = Executors.newFixedThreadPool(cores);
    List<Future<Color>> results = new ArrayList<>();

    long startTime = System.currentTimeMillis();

    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        results.add(pool.submit(new Task(content,n,x,y)));
        
      
      }
    }

    
    int index = 0;
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++){    
        try {
          Future<Color> future = results.get(index++);
          Color result = future.get();          
          setPixel(x,y,result);                   
               
    } catch (InterruptedException | ExecutionException e) {      
      System.out.println(e.getMessage());
    } 
      
    }
      
    
  }
  pool.shutdown();
  long endTime = System.currentTimeMillis();
  long waitTime = endTime - startTime;  
  System.out.format("Miliseconds waited %d\n", waitTime);
}
  
  

  public void setPixel(int x, int y, Color color) {
    double r = Math.pow(color.r(), 1 / 2.2);    
    double g = Math.pow(color.g(), 1 / 2.2);
    double b = Math.pow(color.b(), 1 / 2.2);

    int pos = 3 * (y * width + x);
    tuWas[pos] = r;
    tuWas[pos+1] = g;
    tuWas[pos+2] = b;
    
  }

  public void write(String filename) {
  ImageWriter.write(filename,tuWas,width,height);    
  }

  
}

