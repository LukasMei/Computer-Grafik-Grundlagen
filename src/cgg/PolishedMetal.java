package cgg;

import cgtools.*;
import static cgtools.Vector.*;

public record PolishedMetal(Color albedo) implements Material {

    @Override
    public Color albedo(Ray r, Hit h) {
        return albedo;
    }

    @Override
    public Color emission(Ray r, Hit h) {
        return black;
    }

    @Override
    public Ray scattered(Ray r, Hit h) {
        return new Ray(
            h.trefferX(),
            subtract(r.d(),multiply(2* dotProduct(r.d(),h.n()),h.n())),
            Util.EPSILON,
            Double.POSITIVE_INFINITY
        );
    }
    
}
