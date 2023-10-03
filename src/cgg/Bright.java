package cgg;

import cgtools.*;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Util;
import static cgtools.Random.*;
import static cgtools.Vector.*;

public record Bright(Sampler albedo,Sampler emission)  implements Material{

    
       
    public Bright(Sampler sampler){
        this(sampler, new Constant(black));
    } 

    public Bright(Color color, Color emission){
        this(new Constant(color), new Constant(emission));
    }
    public Bright(Color color){
        this(new Constant(color));
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        return albedo.getColor(h.uv().x(),h.uv().y());
    }

    @Override
    public Color emission(Ray r, Hit h) {
        return emission.getColor(h.uv().x(),h.uv().y()); 
    }

    @Override
    public Ray scattered(Ray r, Hit h) {
        return new Ray(
        h.trefferX(),
        normalize(add(h.n(),randomDir())),
        Util.EPSILON,
        Double.POSITIVE_INFINITY); 
    }
    public static Direction randomDir(){
        var d = direction(
            random()*2-1,
            random()*2-1,
            random()*2-1);
        return squaredLength(d) <= 1.0 ? d : randomDir();    
        

    }
    
}
