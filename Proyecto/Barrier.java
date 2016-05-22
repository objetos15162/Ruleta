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
     * It's function is to update the counter each time an apple falls below the
     * basket, and 
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
