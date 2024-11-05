 /**************************
  * 
  * Osaze Ogieriakhi
  * 
  **************************/

/****************************************************************
NAME: PLAYER

DESCRIPTION: This class is designed to managed the player profile 
            while playing the game. It manages parts inclusing the
            score, num of lives.
 ****************************************************************/
class Player {
    private int score;
    private int lives;
    private int hits, shrinks, bounces, basics, splits;


    // Constructor to initialize player stats
    public Player(int initialLives) {
        score = 0;
        lives = initialLives;
    }

    // Methods to update player stats
    public void increaseScore(int points) {
        score += points;
    }

    public void decreaseLives() {
        lives--;
    }

    // Getters to access player stats
    public int getScore() {
        return score;
    }

    public int getHits() {
        return hits;
    }

    public int getLives() {
        return lives;
    }

    public void CalcScore(int num)
    {
        hits++;
        switch(num)
        {
            case 1: //they hit a basic ball
            basics++;
            increaseScore(25);
            break;

            case 2: //they hit a shrink ball
            shrinks++;
            increaseScore(20);
            break;
            
            case 3: //they hit a bounce ball
            bounces++;
            increaseScore(15);
            break;

            case 4: //they hit a split ball
            splits++;
            increaseScore(10);
            break;

        }
    
    }

    public int GetHitCount(int num) //based on the number passed in, it returns the desired hits
    {
        int answer =0;
        switch(num)
        {
            case 1: //they hit a basic ball
            answer = basics;
            break;

            case 2: //they hit a shrink ball
            answer = shrinks;
            break;

            case 3: //they hit a bounce ball
            answer = bounces;
            break;

            case 4: //they hit a split ball
            answer = splits;
            break;
        }

        return answer;
    }
}