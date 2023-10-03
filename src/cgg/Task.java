package cgg;

import java.util.concurrent.Callable;

import cgtools.Color;
import cgtools.Random;
import cgtools.Sampler;
import static cgtools.Vector.*;

public class Task implements Callable<Color> {

    private Sampler sampler;
    private int n;
    private int x;
    private int y;

    public Task(Sampler sampler, int n, int x, int y){
        this.sampler = sampler;
        this.n = n;
        this.x = x;
        this.y = y;
    }


    @Override
    public Color call() {
       Color color = new Color(0,0,0);
       for(int i = 0; i != n; i++){
        for(int j = 0; j != n; j++){
            double randomX = Random.random();
            double randomY = Random.random();
            double xs = x + ( i + randomX)/n;
            double ys = y + ( j + randomY)/n;
            color = add(color, sampler.getColor(xs, ys));
        }
       }
       return divide(color, n * n);
    }
    
}
