package cgg;
import cgtools.*;
import cgtools.Vector.*;
import static cgtools.Vector.*;

import java.util.ArrayList;
import java.util.List;

public class RayTracerOld implements Sampler {

    private PinHoleCamera camera;    
    private Color background;
    private List<Sphere> spheres;

    public RayTracerOld(PinHoleCamera camera,  Color background){
        this.camera = camera;        
        this.background = background;
        this.spheres = new ArrayList<Sphere>(); 
    } 
   

    @Override
    public Color getColor(double x, double y) {
        Ray ray = camera.erzeugeStrahl(x, y);
        double t = Double.POSITIVE_INFINITY;
        Direction n = null;
        Color color = background;
        
        for (Sphere s : spheres) {
            Hit hit = s.intersect(ray);
            if(hit != null && hit.t() < t){
                t = hit.t();
                n = hit.n();
                color = hit.c();
            }
        }

        if (t != Double.POSITIVE_INFINITY) {
            return shade(n, color);
        } else {
            return background;
        }
    
    }
     
    public void addSphere(Sphere sphere) {
        spheres.add(sphere);
    }
    
    public Color shade(Direction normal, Color color){
        Direction lightDir = normalize(direction(1,1,0.5));
        Color ambient = multiply(0.1,color);
        Color diffuse = multiply(0.9 * Math.max(0,dotProduct(lightDir,normal)),color);
        return add(ambient,diffuse);
    }
}
