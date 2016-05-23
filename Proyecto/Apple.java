import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class represents an apple, used in the Apple Catch game.
 */
public class Apple extends Actor
{
    public Apple()
    {
        setRotation(90);
    }
    
    /**
     * Act - The apple is constantly moving until touched by the basket or the
     * barrier.
     */
    public void act() 
    {
        move(2);
    }    
}
