package cgg;

import cgtools.Color;
import cgg.*;

public interface Material {

   public Color albedo (Ray r, Hit h);

    public Color emission(Ray r, Hit h);

    public Ray scattered(Ray r, Hit h);

 

}
