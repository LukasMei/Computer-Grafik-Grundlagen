package cgg;

import cgtools.*;
import static cgtools.Vector.*;

public record DullMetal(Color albedo, double roughness) implements Material {

    private final static Random random = new Random();

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
    Direction reflection = reflect(r.d(),h.n());
    Direction scatteredDirection = add(reflection, multiply(randomInUnitSphere(), roughness));    
        return new Ray(
            h.trefferX(),
            scatteredDirection,
            Util.EPSILON,
            Double.POSITIVE_INFINITY
        );
    }

    private Direction randomInUnitSphere() {
        Direction p;
        do {
            p = subtract(multiply(direction(random.nextDouble(), random.nextDouble(), random.nextDouble()), 2), direction(1, 1, 1));
        } while (squaredLength(p) >= 1);
        return p;
    }



    public static Direction reflect(Direction v, Direction n){
        return subtract(v,multiply(2 * dotProduct(v, n),n));
    }
    
}
