package cgg.a01;

import cgtools.*;

public record Pattern(int width, int height) {

    public Color getColor(double x, double y){
        

        int ix = (int) x / (width/10);
        int iy = (int) y / (height/10);  
                   
        
         
        if(ix % 3 == 0){
            return new Color(0.2, y / height, 0.4);            
        } else if(iy % 3 == 1){
            return new Color(0.5, x / width, 0.8);
        } else return new Color(0.4,x / width, 0.3);        
         

    }
    
}
