import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class represents a ball, used in the FallBall game.
 * It's controlled by the player's x-axis.
 */
public class FallingBall extends Actor
{
    private int speed;
    
    /**
     * Act - If the ball is touching a platform, inverts its own rotation so it
     * moves up instead of down.
     */
    public void act() 
    {
        if(isTouching(Platform.class))
        {
            setRotation(-90);
        }
        else
        {
            setRotation(90);
        }
    }    
    
    /**
     * Recieves the world's current scroll speed and uses it to move the ball.
     */
    @Override
    public void move(int speed)
    {
        if(isTouching(Platform.class))
        {
            super.move(1+speed);
        }
        else
        {
            super.move(speed);
        }
    }
}
