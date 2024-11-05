/******************************************************************************
 *  Compilation:  javac BallGame.java
 *  Execution:    java BallGame n
 *  Dependencies: BasicBall.java StdDraw.java
 *
 *  Creates a BasicBall and animates it
 *
 *  Part of the animation code is adapted from Computer Science:   An Interdisciplinary Approach Book
 *  
 *******************************************************************************/

 /**************************
  * 
  * Osaze Ogieriakhi
  * 
  **************************/
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class BallGame { 

    public static void main(String[] args) {
  
    	// number of bouncing balls
    	int numBalls = Integer.parseInt(args[0]);

    	//ball types
    	String ballTypes[] = new String[numBalls];

    	//sizes of balls
    	double ballSizes[] = new double[numBalls];
    	
        //The list of balls in the game
        ArrayList<BasicBall> ballList = new ArrayList<BasicBall>();
        ArrayList<BasicBall> temp_ballList = new ArrayList<BasicBall>();

    	//retrieve ball types
    	int index =1;
    	for (int i=0; i<numBalls; i++) {
    		ballTypes[i] = args[index];
    		index = index+2;
    	}
    	//retrieve ball sizes
    	index = 2;
    	for (int i=0; i<numBalls; i++) {
    		ballSizes[i] = Double.parseDouble(args[index]);
    		index = index+2;
    	}
     
    	//TO DO: create a Player object and initialize the player game stats.  
    	Player player = new Player(3);
    	
    	//number of active balls
    	int numBallsinGame = 0;
        StdDraw.enableDoubleBuffering();

        StdDraw.setCanvasSize(800, 800);
        // set boundary to box with coordinates between -1 and +1
        StdDraw.setXscale(-1.0, +1.0);
        StdDraw.setYscale(-1.0, +1.0);

        // create colored balls 

        //TO DO: Create "numBalls" balls (of types given in "ballTypes" with sizes given in "ballSizes") and store them in an Arraylist
        
        

        for(int i =0; i < numBalls; ++i)
        {
            String ballType = ballTypes[i].toLowerCase();
            if (ballType.equals("basic"))
            {
                BasicBall ball = new BasicBall(ballSizes[i],Color.YELLOW);
                ballList.add(ball);
            }

            else if (ballType.equals("shrink"))
            {
                BasicBall ball = new ShrinkBall(ballSizes[i],Color.RED);
                ballList.add(ball);
            }

            else if (ballType.equals("split"))
            {
                BasicBall ball = new SplitBall(ballSizes[i],Color.GREEN);
                ballList.add(ball);
            }
            
            else if (ballType.equals("bounce"))
            {
                BasicBall ball = new BounceBall(ballSizes[i],Color.BLUE);
                ballList.add(ball);
            }
        }
       
   		//BasicBall ball = new BasicBall(ballSizes[0],Color.RED);
   		//TO DO: initialize the numBallsinGame
   		numBallsinGame = numBalls;


        
        // do the animation loop
        StdDraw.enableDoubleBuffering();

         while(player.getLives() > 0)
       {
        
        while (numBallsinGame > 0) {

        	//move all balls
            for(BasicBall ball : ballList)
            {
                ball.move();
                ball.draw();
            }

            //Check if the mouse is clicked
            if (StdDraw.isMousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();

                //check whether a ball is hit. Check each ball. 
                for(BasicBall ball : ballList)
                {

                    if (ball.isHit(x,y)) {
                    
                    	//TO DO: Update player statistics
                        if ("SplitBall".equals(ball.getClass().getSimpleName()))
                        {
                            BasicBall temp1 = new SplitBall(ball.getRadius(), Color.BLUE);
                            temp_ballList.add(temp1); //the ball split and creates a new split ball
                            player.CalcScore(4);
                        }
                        else if ("ShrinkBall".equals(ball.getClass().getSimpleName())) 
                        {
                            player.CalcScore(2);
                        } 
                        else if ("BounceBall".equals(ball.getClass().getSimpleName()))
                        {
                            player.CalcScore(3);
                        } 
                        else 
                        {
                            player.CalcScore(1);
                        }
                        ball.reset();
                }
                }
                
            }
                
            numBallsinGame = 0;
            // draw the n balls
            StdDraw.clear(StdDraw.GRAY);
            StdDraw.setPenColor(StdDraw.BLACK);
            
            //TO DO: check each ball and see if they are still visible. numBallsinGame should hold the number of visible balls in the game.  
            for (BasicBall ball : ballList) 
            {
                if (ball.isOut == false) 
                { 
                    ball.draw();
                    numBallsinGame++;
                }
            }


            ballList.addAll(temp_ballList);  // Add new balls from temporary list to main list
            temp_ballList.clear();

            //Print the game progress
            StdDraw.setPenColor(StdDraw.YELLOW);
            Font font = new Font("Arial", Font.BOLD, 20);
            StdDraw.setFont(font);
            StdDraw.text(-0.65, 0.90, "Number of balls in game: "+ String.valueOf(numBallsinGame));
            //TO DO: print the rest of the player statistics
            StdDraw.text(-0.65, 0.85, "Number of hits: "+ String.valueOf(player.getHits()));
            StdDraw.text(-0.65, 0.80, "Player Score: "+ String.valueOf(player.getScore()));

            StdDraw.show();
            StdDraw.pause(20);
        }

        player.decreaseLives(); //the player just lost a game and lives has reduced.

    }
    while (true) {
        StdDraw.setPenColor(StdDraw.BLUE);
        Font font = new Font("Arial", Font.BOLD, 30); // Decreased font size for better readability
        StdDraw.setFont(font);
        StdDraw.text(0, -0.2, "GAME OVER");
    
        double yOffset = 0.1; // Adjust this value to increase or decrease vertical spacing
        
        StdDraw.text(0, .5 + yOffset, "Score: " + String.valueOf(player.getScore()));
        StdDraw.text(0, .4 + yOffset, "Total Hits: " + String.valueOf(player.getHits()));
        StdDraw.text(0, .3 + yOffset, "Basic: " + String.valueOf(player.GetHitCount(1)));
        StdDraw.text(0, .2 + yOffset, "Shrink: " + String.valueOf(player.GetHitCount(2)));
        StdDraw.text(0, .1 + yOffset, "Bounce: " + String.valueOf(player.GetHitCount(3))); 
        StdDraw.text(0, 0 + yOffset, "Split: " + String.valueOf(player.GetHitCount(4))); 
    
        StdDraw.show();
        StdDraw.pause(10);           
    }
        	
        
    }
}
