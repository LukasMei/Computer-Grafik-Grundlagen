package cgg;

import  static cgtools.Vector.*;
import cgtools.*;

public record Hit(double t, Point trefferX, Direction n,Point uv, Material m)  {

    public Hit(double t, Point trefferX, Direction n, Material m){
        this(t,trefferX,n,zero,m);
    }

    public static Hit closest(Hit h1, Hit h2) {        
        if (h1 == null && h2 == null) {
            return null;
        } else if (h1 == null) {
            return h2;
        } else if (h2 == null) {
            return h1;
        } else if (h1.t() < h2.t()) {
            return h1;
        } else {
            return h2;
        } 
    }

    

    
}
