package cgg;

import cgtools.Color;
import cgtools.Matrix;

import static cgtools.Matrix.*;
import static cgtools.Vector.*;
import cgtools.Sampler;

public record TexTransform(Matrix matrix, Sampler sampler) implements Sampler {

    
    @Override
    public Color getColor(double x, double y) {
        var uv = multiply(matrix, point(x,y ,0));
       return sampler.getColor(uv.x(),uv.y());
    }
    
}
