import java.awt.Color;


/****************************************************************
NAME: SHRINK BALL

DESCRIPTION: This subclass of the Basic ball is for implementing 
            the shrinking  ball for the game. It contains its specialized 
            reset function which reduces it radius by 2/3 everytime it is
            hit.
 ****************************************************************/
public class ShrinkBall extends BasicBall {
    
    double present_radius;

    public ShrinkBall(double r, Color c) {
        super(r, c);  
        present_radius = r;
    }

    @Override
    public int reset() {
        if (radius <= (present_radius * 0.25)) {
            radius = present_radius;  
            rx = 0.0;  
            ry = 0.0;
        } else {
            radius = (0.6667*radius);  
            rx = 0.0;  
            ry = 0.0;
        }
        vx = StdRandom.uniform(-0.01, 0.01);
        vy = StdRandom.uniform(-0.01, 0.01);
        return 1; 
    }
}
