import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class represents a barrier used in the FallBall game.
 */
public class Barrier extends Actor
{
    private Counter failsLeft;
    private GreenfootSound fail;
    
    public Barrier(Counter fails)
    {
        failsLeft = fails;
        fail = new GreenfootSound("wrong.wav");
    }
    
    /**
     * Act - Checks if an apple has been caught by the barrier and if so, removes
     * it and updates the remaining fails.
     */
    public void act() 
    {
        if(isTouching(Apple.class))
        {
            removeTouching(Apple.class);
            fail.play();
            failsLeft.add(-1);
        }
    }    
}
