package cgg;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Util;

import static cgtools.Vector.*;

public class DirectionalLight implements Light {

    public final Direction d;
    public final Color c;

    public DirectionalLight(Direction d, Color c){
        this.d = normalize(d);
        this.c = c;

    }

    @Override
    public Color incomingIntensity(Point x, Direction n, Shape scene) {
        
        double cos = dotProduct(negate(d),n);

        if ( cos > 0) {
            Hit hit = scene.intersect(new Ray(x, negate(d), Util.EPSILON, Double.MAX_VALUE));
            if (hit == null)
                return multiply(c,cos);
        }
        return null;
    }
    
}
