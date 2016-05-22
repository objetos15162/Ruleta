import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RedButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Button extends Actor
{
    private SimpleTimer time;
    private boolean isItPressed;
    
    public Button() 
    {
        time = new SimpleTimer();
        isItPressed = false;
    }    
    
    public void act()
    {
        if(isTouching(Cursor.class))
        {
            if(time.millisElapsed() >= 2000)
            {
                isItPressed = true;
            }
        }
        else 
        {
            time.mark();
        }
    }
    
    public boolean isPressed()
    {
        return isItPressed;
    }
    
    public boolean isTouchingCursor()
    {
        if(isTouching(Cursor.class))
            return true;
        else
            return false;
    }
}
