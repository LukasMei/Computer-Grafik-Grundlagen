package cgg.a01;

import cgtools.Color;

public record ChessPattern(Color color, Color color2, int width, int height) {
    

    public Color getColor(double x, double y) {        
      
        int xi = (int) (x/(width/8));
        int yi = (int) (y/(height/8));

        if ((xi + yi) % 2 == 0){
            return color; // return new Color(0, y / height, 0)
        } else 
            return color2; // return new Color(0, x / width, 0)   

        
      }
      

    
    
}
