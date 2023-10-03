package cgg;

import cgtools.*;
import static cgtools.Vector.*;

public record StratifiedSampler(Sampler content, int n) implements Sampler {

    @Override
    public Color getColor(double x, double y) {
        Color color = black;
        for(int xi=0; xi<n; xi++) {
            for(int yi=0; yi<n; yi++) {
              double rx = Math.random();
              double ry = Math.random();
              double xs = x + (xi+rx)/n;
              double ys = y + (yi+ry)/n;
              color = add(color,content.getColor(xs, ys));
            }
        }
        return divide(color, n * n);
    }

    
}
