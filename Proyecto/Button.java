import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class represents a button, used in the title screen and records screen.
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
    
    /**
     * Act - Checks if it's being touched by the player's cursor and if so,
     * starts counting two seconds. After those two seconds, the boolean indicator
     * is updated to let the world know the button has been selected.
     */
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
    
    /**
     * Access method which returns the boolean indicator as true if the button has
     * been selected.
     */
    public boolean isPressed()
    {
        return isItPressed;
    }
}
