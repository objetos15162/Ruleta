import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class represents a basket used by the player to catch falling apples in
 * Apple Catch. It's controlled by the player's x-axis.
 */
public class Basket extends Actor
{
    private Counter points;
    
    public Basket(Counter pointCount)
    {
        points = pointCount;
    }
    
    /**
     * Act - Checks if an apple has been caught by the basket and if so, removes it
     * and updates the score.
     */
    public void act() 
    {
        if(isTouching(Apple.class))
        {
            removeTouching(Apple.class);
            points.add(25);
        }
    }    
}
