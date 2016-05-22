import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FallingBall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FallingBall extends Actor
{
    private int speed;
    
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
