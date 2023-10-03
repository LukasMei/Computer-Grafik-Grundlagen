package cgg;

import cgtools.*;
import static cgtools.Vector.*;





public record RayTracer(PinHoleCamera camera, World world) implements Sampler{

    @Override
    public Color getColor(double x, double y) {
        Ray ray = camera.erzeugeStrahl(x, y);        
        return calculateRadiance(world, ray, 10);        
        
    }
    public Color shade(Direction normal, Color color){
        Direction lightDir = normalize(direction(1,1,0.5));
        Color ambient = multiply(0.1,color);
        Color diffuse = multiply(0.9 * Math.max(0,dotProduct(lightDir,normal)),color);
        return add(ambient,diffuse);
    }

    public Color calculateRadiance(World world, Ray ray, int depth){
        if( depth == 0) {
            return black;
        }

        Hit hit = world.scene.intersect(ray);

        if(hit == null){
            return white;
        }
        
        Material material = hit.m();

        Color emittedRadiance = material.emission(ray,hit);        
        Ray scatteredRay = material.scattered(ray, hit);
        

        Color reflectedRadiance = multiply(material.albedo(ray, hit),
        calculateRadiance(world, scatteredRay, depth-1));

        if (hit.m() instanceof Diffuse){
            for (Light l : world.lights){
                Color luminance = l.incomingIntensity(hit.trefferX(),hit.n(), world.scene);
                if (luminance != null){
                    reflectedRadiance = add(reflectedRadiance, luminance);
                }
            }
        }

        return add(emittedRadiance,reflectedRadiance);        
        
       
    }

}



