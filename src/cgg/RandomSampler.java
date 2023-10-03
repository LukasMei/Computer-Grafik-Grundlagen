package cgg;

import cgtools.*;
import static cgtools.Vector.*;

public record RandomSampler(Sampler content,int n) implements Sampler {

    @Override
    public Color getColor(double x, double y) {
        Color color = black;
        for(int i = 0; i<n; i++){
          double rx = Math.random();
          double ry = Math.random();
          double xs = x + rx;
          double ys = y + ry;
          color = add(color,content.getColor(xs,ys));
        }
       
        return divide(color,n);
      
    
    }
    
}
