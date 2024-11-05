import java.awt.Color;



/****************************************************************
NAME: SPLIT BALL

DESCRIPTION: This subclass of the Basic ball is for implementing 
            the split  ball for the game. It contains its specialized 
            reset function.
 ****************************************************************/
public class SplitBall  extends BasicBall{

    public SplitBall(double r, Color c) {
        super(r, c);
    }

    @Override
    public int reset() {
        rx = 0.0;
        ry = 0.0;  	
        vx = StdRandom.uniform(-0.01, 0.01); 
        vy = StdRandom.uniform(-0.01, 0.01);
        
        return 1;
    }

    
}
