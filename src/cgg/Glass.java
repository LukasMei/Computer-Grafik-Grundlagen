package cgg;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Sampler;
import cgtools.Util;

import static cgtools.Vector.*;

public record Glass(Sampler albedo, double refracOut, double refracIn)implements Material {

    public Glass(Sampler texture){
        this(texture,1.0,1.5);
    }

    public Glass(Color color){
        this(new Constant(color), 1.0,1.5);
    }

    public Glass(){
        this(new Constant(white), 1.0,1.5);
    }
    @Override
    public Color albedo(Ray r, Hit h) {       
        return albedo.getColor(h.uv().x(),h.uv().y());
    }

    @Override
    public Color emission(Ray r, Hit h) {
       return new Color(0,0,0);
    }

    @Override
    public Ray scattered(Ray r, Hit h) {
        double n1;
        double n2;
        Direction n = normalize(h.n());
        Direction d = normalize(r.d());

        if(dotProduct(n,d) > 0){
            n = negate(n);
            n1 = refracIn;
            n2 = refracOut;
        } else {
            n1 = refracOut;
            n2 = refracIn;
        }

        Direction dt = refract(d, n, n1, n2);
        if (dt != null && (Random.random() > schlick(d, n, n1, n2))) {
            return new Ray(h.trefferX(),dt,Util.EPSILON,Double.POSITIVE_INFINITY);
        } else {
            return new Ray(h.trefferX(),reflect(d, n),Util.EPSILON, Double.POSITIVE_INFINITY);
        }
    }


    public static Direction refract(Direction d, Direction n, double n1, double n2){
        double r = n1/n2;
        double c = - dotProduct(n,d);
        double disc = 1- (r*r)*(1-(c*c));
        if(disc < 0 ){
            return null;
        } else {
            return add(multiply(r,d),multiply(r*c - Math.sqrt(disc),n));
        }
    }
    
    public static double schlick(Direction d, Direction n, double n1, double n2){
        double r = Math.pow((n1-n2)/(n1+n2),2);
        return r + (1 - r) * Math.pow(1 + dotProduct(n,d),5);
    }

    public static Direction reflect(Direction v, Direction n){
        return subtract(v,multiply(2 * dotProduct(v, n),n));
    }

}
