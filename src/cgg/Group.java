package cgg;

import static cgtools.Matrix.*;
import java.util.ArrayList;
import java.util.List;

import cgtools.BoundingBox;
import cgtools.Matrix;

public class Group implements Shape {

    private ArrayList<Shape> shapes;    
    private final Transformation transformation;
    private BoundingBox bbox;

    public Group(Matrix matrix, BoundingBox bbox) {
        transformation = new Transformation(matrix);
        shapes = new ArrayList<Shape>();
        this.bbox = bbox;
    }

    public Group(Shape...shapes){                
        this(identity, shapes);
        
    }

     public Group(List<Shape> list){
        this(list.toArray(new Shape[list.size()]));
    } 
       

    public Group(Matrix matrix, Shape... shapes){
        /* this.shapes = new ArrayList<>();
        this.transformation = new Transformation(matrix); */
        this(matrix,BoundingBox.empty);
        for(Shape s:shapes){
            addShape(s);
        }
    }
     public Group(){
        this(identity,BoundingBox.empty);
    } 
    public Group(Matrix matrix) {
        this(matrix, BoundingBox.empty);
    }

  
    @Override
    public Hit intersect(Ray ray) {
        if(ray == null){
            return null;
        }
        Hit closest = null;

        if(bound().intersect(ray)){
            Ray rayTrans = transformation.transformRay(ray);
            for (var s : shapes){
                closest = Hit.closest(closest,s.intersect(rayTrans));
            }
        }    
        if(closest == null)
            return null;
        else return transformation.transformHit(closest);
        
        
        
    }

    public void addShape(Shape s){   
        if(shapes == null){
            shapes = new ArrayList<>();
        }
        shapes.add(s);

        // Quelle chat.openai.com        
        BoundingBox box = s.bound().transform(transformation.matrix);
        if(box.equals(BoundingBox.empty)){
            bbox = box;
        } else {
            bbox = bbox.extend(box);
        }         

    
}

    @Override
    public BoundingBox bound() {
        return bbox;
    }
}


