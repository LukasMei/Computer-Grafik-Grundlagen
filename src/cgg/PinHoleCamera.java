package cgg;

import cgtools.Direction;
import static cgtools.Matrix.*;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Vector;
import static cgtools.Vector.*;

public class PinHoleCamera {

    private double width;
    private double height;
    private double winkel;
    private Point x0 = Vector.zero;
    private Matrix v;

    public PinHoleCamera(double width, double height, double winkel, Point x0){
        this.width = width;
        this.height = height;
        this.winkel = winkel;
        this.x0 = x0;
    }
    public PinHoleCamera(double width, double height, double winkel){
        this.width = width;
        this.height = height;
        this.winkel = winkel;        
    }
    public PinHoleCamera(double width, double height, double winkel, Matrix v){
        this.width = width;
        this.height = height;
        this.winkel = winkel;
        this.v = v;    
            
    }

    public Ray erzeugeStrahl(double x, double y){
        Direction d = direction(x - width / 2.0,height / 2.0 - y, -distance());
        return new Ray(
            multiply(v,zero),
            multiply(v,normalize(d)),
            0,
            Double.POSITIVE_INFINITY);
    }

    public double distance(){
        return (width / 2.0) / Math.tan(winkel / 2.0);
    }

    

   
}
