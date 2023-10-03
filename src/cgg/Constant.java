package cgg;

import cgtools.Color;
import cgtools.Sampler;

public record Constant(Color color) implements Sampler {

    @Override
    public Color getColor(double x, double y) {
       return color;
    }

    
    
}
