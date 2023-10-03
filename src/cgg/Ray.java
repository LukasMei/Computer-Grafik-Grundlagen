package cgg;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public record Ray(Point x0, Direction d, double t_min, double t_max) {

    public Point pointAt (double t){
        return Vector.add(x0, Vector.multiply(t, d)); 
    }

    public Boolean isValid(double t){
        return t >= t_min() && t <= t_max();
        
    }
    
    
   

}
