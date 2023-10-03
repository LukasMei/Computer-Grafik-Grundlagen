package cgg;
import cgtools.*;

import static cgtools.Vector.*;


public class Plane implements Shape {
   private Point p;
   private Direction n;
   private double r;   
   private Material m;

   public Plane(Point p, Direction n, double r, Material m){
    this.p = p;
    this.n = normalize(n);
    this.r = r;
    this.m = m;
   }
   
    

// Ebeneformel : (x - p) * n = 0
// Begrenzung  : |x - p| < r

    @Override
    public Hit intersect(Ray ray) {
        if(ray == null){
            return null;
        }
        double nd = dotProduct(ray.d(), n);
        if(nd == 0.0)
            return null;
            
        double t = dotProduct(subtract(p,ray.x0()),n) / nd;
        if(!ray.isValid(t))
            return null;    
        
        Point x = ray.pointAt(t);
        if (length(subtract(x,p)) > r){
            return null;
        }    

        Point uv = point(x.x() / (2*r) + 0.5, x.z() / (2*r) + 0.5, 0);
            
        if (nd >= 0){
            return new Hit(t, x, multiply(-1,n), m);
        } else
            return new Hit(t, x, n,uv,m);    
        
    }



    @Override
    public BoundingBox bound() {
        return BoundingBox.everything;
    }
    
}
