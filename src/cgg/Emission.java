package cgg;


import cgtools.Color;
import cgtools.Sampler;

import static cgtools.Vector.*;

public record Emission(Sampler emission)  implements Material{

    public Emission(Color emission){
        this((double x, double y) -> emission);
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        return black;
    }

    @Override
    public Color emission(Ray r, Hit h) {
        return emission.getColor(h.uv().x(),h.uv().y()); 
    }

    @Override
    public Ray scattered(Ray r, Hit h) {
        return null; 
    }
    
}
