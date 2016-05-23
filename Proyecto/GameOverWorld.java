import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the class for the game over screen.
 * You get sent here when you complete 4 random games.
 */
public class GameOverWorld extends KinectWorld
{
    private static final int THUMBNAIL_WIDTH = 80;
    private static final int THUMBNAIL_HEIGHT = 60;
    
    private int score;
    
    public GameOverWorld(int finalScore)
    {
        super(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, 1.0, false);
        
        score = finalScore;
        setBackground("startscreenbg.png");
        
        Label gameOver = new Label("Game Over!", 50);
        Label thanks = new Label("Thank you for playing!", 30);
        Counter yourScore = new Counter("Your Score: ");
        yourScore.setValue(score);
        
        addObject(gameOver, 320, 50);
        addObject(thanks, 320, 100);
        addObject(yourScore, 320, 350);
        
        saveRecord();
        Greenfoot.delay(20);
        
    }
    
    /**
     * Saves the player's final score to the records list.
     */
    public void saveRecord()
    {
        if (UserInfo.isStorageAvailable()) 
        {
            UserInfo myInfo = UserInfo.getMyInfo();
            if (score > myInfo.getScore() || myInfo.getScore()==0) 
            {
                myInfo.setScore(score);
                myInfo.store();
            }
        }
    }
}
