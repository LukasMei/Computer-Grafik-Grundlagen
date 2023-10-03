package cgg;

import cgtools.*;
import static cgtools.Vector.*;

public class Cylinder implements Shape{
    
    private double height;
    private double radius;
    private Material m;

    public static final Point p = zero ;
    public static final Direction v = yAxis;

    public Cylinder(Material m){
        this(1, 1, m);
    }

    public Cylinder(double height, double radius, Material m){
        this.height = height;
        this.radius = radius;
        this.m = m;
    }

    @Override
    public Hit intersect(Ray ray) {

        //Quelle: https://www.matheboard.de/archive/1188/thread.html
        //Quelle: https://chat.openai.com/

        Direction e = subtract(ray.d(), multiply(v, dotProduct(ray.d(), v)/squaredLength(v)));
        Direction f = subtract(new Direction(ray.x0().x(), ray.x0().y(), ray.x0().z()),
         multiply(dotProduct(new Direction(ray.x0().x(), ray.x0().y(), ray.x0().z()), v) / squaredLength(v), v));

        double a = dotProduct(e, e);
        double b = 2 * dotProduct(e, f);
        double c = dotProduct(f, f) - radius*radius;
        double discriminant = b*b - 4*a*c;

        if (discriminant < 0) {
            return null;
        }    

        double t = (-b - Math.sqrt(discriminant))/(2*a);
        if (t > 0) {
            Point hitX = ray.pointAt(t);
            double d = dotProduct(hitX, v);
            if (d > 0 && d < height) {
                Point pointToCenter = subtract(hitX, multiply(v, d));
                Direction n = normalize(subtract(hitX, pointToCenter));
                return new Hit(t, hitX, n, m);
            }
        }

    // Überprüfung der Schnittpunkte mit den Zylinderdeckeln
    double tTop = (height - ray.x0().y()) / ray.d().y();
    double tBottom = -ray.x0().y() / ray.d().y();

    Point hitTop = ray.pointAt(tTop);
    Point hitBottom = ray.pointAt(tBottom);

    // Überprüfung, ob der Schnittpunkt mit dem oberen Deckel innerhalb des Radius liegt
    if (tTop > 0 && Math.pow(hitTop.x(), 2) + Math.pow(hitTop.z(), 2) <= Math.pow(radius, 2)) {
        return new Hit(tTop, hitTop, new Direction(0, 1, 0), m);
    }

    // Überprüfung, ob der Schnittpunkt mit dem unteren Deckel innerhalb des Radius liegt
    if (tBottom > 0 && Math.pow(hitBottom.x(), 2) + Math.pow(hitBottom.z(), 2) <= Math.pow(radius, 2)) {
        return new Hit(tBottom, hitBottom, new Direction(0, -1, 0), m);
    }
        return null;
    }

     @Override
    public BoundingBox bound() {
        // berechnung einer Begrenzungsbox welche den gesamten Zylinder umschließt
        Point min = new Point(-radius,0, -radius);
        Point max = new Point(radius,height,radius);
        return new BoundingBox(min, max);
    } 

   
        
    
    }
    

