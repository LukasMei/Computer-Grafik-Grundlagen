package cgg;

import cgtools.BoundingBox;

public interface Shape {

    public Hit intersect(Ray ray);
    public BoundingBox bound(); 
    
}

