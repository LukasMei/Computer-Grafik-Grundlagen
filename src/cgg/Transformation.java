package cgg;

import cgtools.Matrix;
import static cgtools.Matrix.*;
import static cgtools.Vector.*;

public class Transformation {

    public final Matrix matrix;
    private final Matrix inverse;
    private final Matrix inverseTranspose;

    public Transformation(Matrix m){
        this.matrix = m;
        this.inverse = invert(matrix);
        this.inverseTranspose = transpose(inverse);
    }
    public Ray transformRay(Ray ray){
        return new Ray(multiply(inverse,ray.x0()),
        multiply(inverse,ray.d()),ray.t_min(),ray.t_max());
    }

    public Hit transformHit(Hit hit){
        if(hit != null){
            return new Hit(hit.t(), multiply(
                matrix,hit.trefferX()), 
                normalize(multiply(inverseTranspose,hit.n())),hit.uv(),hit.m());
        } else return null;
    }
}
