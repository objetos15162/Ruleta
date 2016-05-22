import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class represents a platform, they're used in the FallBall game.
 */
public class Platform extends Actor
{
    private int speed;
    
    public Platform(int scrollSpeed)
    {
        setRotation(-90);
        speed = scrollSpeed;
    }
    
    /**
     * The platform keeps ascending.
     * Its speed depends on the current world's scrollSpeed.
     */
    public void act() 
    {
        move(speed);
    }    
}
