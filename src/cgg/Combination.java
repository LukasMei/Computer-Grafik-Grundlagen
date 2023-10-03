package cgg;

import cgtools.Color;
import cgtools.Sampler;

public record Combination(Sampler s, Material m1, Material m2) implements Material{

    @Override
    public Color albedo(Ray r, Hit h) {
        Color c = s.getColor(h.uv().x(),h.uv().y());
        if(c.r() < 0.1 && c.g() < 0.1 & c.b() <0.1){
            return m1.albedo(r, h);
        } else {
            return m2.albedo(r, h);
        }
    }

    @Override
    public Color emission(Ray r, Hit h) {
    Color c = s.getColor(h.uv().x(),h.uv().y());
        if(c.r() < 0.1 && c.g() < 0.1 & c.b() <0.1){
            return m1.emission(r, h);
        } else {
            return m2.emission(r, h);
        }
    }    

    @Override   
    public Ray scattered(Ray r, Hit h) {
    Color c = s.getColor(h.uv().x(),h.uv().y());
        if(c.r() < 0.1 && c.g() < 0.1 & c.b() <0.1){
            return m1.scattered(r, h);
        } else {
            return m2.scattered(r, h);
        }
    }    
    
}
