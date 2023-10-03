package cgg;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;
import static cgtools.Vector.*;

import cgtools.BoundingBox;

public class Sphere implements Shape {
    
    private Point center;
    private double radius;
    private Material m;

    public Sphere(Point center, double radius, Material m){
        this.center = center;
        this.radius = radius;
        this.m = m;
    }    
    

    @Override
    public Hit intersect(Ray ray) {
        Point x0 = ray.x0();
        Direction temp = subtract(x0, center);
        double a = dotProduct(ray.d(), ray.d());
        double b = 2 * dotProduct(temp, ray.d());
        double c = dotProduct(temp, temp) - (radius * radius);

        double diskriminante = b * b - 4 * a * c;

        if(diskriminante < 0){
            return null;
        }

        double t = ((-b -Math.sqrt(diskriminante)) / (2 * a)); // Formel S.6

        if(t > 0 ){
            Point treffer = ray.pointAt(t);
            Direction xMinCenter =  Vector.subtract(treffer, center);
            Direction normal = Vector.normalize(Vector.divide(xMinCenter,radius));

            var inclination = Math.acos(normal.y());
            var azimuth = Math.PI + Math.atan2(normal.x(),normal.z());
            var u = azimuth / (2 * Math.PI); 
            var v = inclination / Math.PI;

            return new Hit(t, treffer, normal,point(u,v,0), m);
        }

        return null;
    }


    @Override
    public BoundingBox bound() {
        BoundingBox box = new BoundingBox(point(center.x()-radius, center.y()-radius, center.z()-radius), 
        point(center.x()+radius, center.y()+radius, center.z()+radius));

        return box;
    }

    
    
}
