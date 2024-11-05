import java.awt.*;


/****************************************************************
NAME: BOUNCE BALL

DESCRIPTION: This subclass of the Basic ball is for implementing 
            the bouncing  ball for the game. It contains its specialized 
            reset function. And allows the ball bounce on the walls 3 times 
            before disappearing.
 ****************************************************************/
public class BounceBall extends BasicBall
{
    
    int wall = 3; //the ammount of times it can bounce before it disappears

    public BounceBall(double r, Color c) {
        super(r, c); 
    }

    @Override 
    public int reset() {
        rx = 0.0;
        ry = 0.0;
        vx = StdRandom.uniform(-0.01, 0.01);
        vy = StdRandom.uniform(-0.01, 0.01);
        this.wall = 3;
        isOut = false; 
        return 1;
    }


    @Override
    public void move() //The class specialized move function
    {
        if(isOut)
        {
            return;
        }
        rx = rx + vx;
        ry = ry + vy;
        if (Math.abs(rx) + radius >= 1.0)
         {
            if (wall > 0) 
            {
                vx = -vx; 
                if (vx > 0) 
                { 
                    rx = rx + radius;
                } 
                else 
                {
                    rx = rx - radius;
                }
                wall--;
            } 
            else 
            {
                isOut = true;
            }
        }
        if (Math.abs(ry) + radius >= 1.0) 
        {
            if (wall > 0) 
            {
                vy = -vy; 
                if (vy > 0) 
                {                    
                    ry = ry + radius;
                } 
                else 
                {
                    ry = ry - radius;
                }
                wall--;
            } 
            else 
            {
                isOut = true;
            }
        }
    }


    @Override
    public void draw() { 
    	if ((Math.abs(rx) <= 1.0) && (Math.abs(ry) <= 1.0)) {
    		StdDraw.setPenColor(color);
    		StdDraw.filledCircle(rx, ry, radius);
    	} else
    		isOut = true;
    	
    }
    
}
