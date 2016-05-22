import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Basket here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Basket extends Actor
{
    private Counter points;
    
    public Basket(Counter pointCount)
    {
        points = pointCount;
    }
    
    public void act() 
    {
        if(isTouching(Apple.class))
        {
            removeTouching(Apple.class);
            points.add(25);
        }
    }    
}
