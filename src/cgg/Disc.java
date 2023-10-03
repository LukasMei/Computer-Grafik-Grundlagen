package cgg;

import java.util.List;
import cgtools.*;

 

public record Disc(double xpos, double ypos, double radius, Color color) implements Comparable{

       

    /* --isPointInDisc()-- stellt fest ob ein Punkt in der Bildebene, mit beliebigen
    Koordinaten, innerhalb der Scheibe liegt oder nicht 
    */
    public boolean isPointInDisc(double x, double y){
        return Math.sqrt(Math.pow(x-xpos, 2)+ Math.pow(y-ypos, 2)) <= radius;        
    }

    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }    
   




}