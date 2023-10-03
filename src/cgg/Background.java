package cgg;
import cgtools.*;
import static cgtools.Vector.*;

public record Background(Material m) implements Shape {
    static final double INF = Double.POSITIVE_INFINITY; 

    @Override
    public Hit intersect(Ray ray) {
        if(ray == null){
            return null;
        }
        var inclination = Math.acos(ray.d().y());
        var azimuth = Math.PI + Math.atan2(ray.d().x(), ray.d().z());
        var v = inclination / Math.PI;
        var u = azimuth / (2 * Math.PI);

        if(ray.isValid(Double.POSITIVE_INFINITY)){
            return new Hit(INF,point(INF,INF,INF),
                multiply(-1, ray.d()),
                point(u,v,0), m);
        } else 
            return null;
        
    }

    @Override
    public BoundingBox bound() {
        return BoundingBox.everything;
    }
    
}
