import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class represents a lever, used in RouletteWorld to spin the Roulette.
 * It's controlled by the player's cursor.
 */
public class Lever extends Actor
{
    private boolean touched;
    
    public Lever()
    {
        touched = false;
    }
    
    /**
     * Constantly checks if the lever has been touched.
     */
    public void act() 
    {
        if(!touched)
        {
            checkForTouch();
        }
    }    
    
    /**
     * Sets the lever's y position to the cursor's y position.
     */
    @Override
    public void move(int pos)
    {
        if(isTouching(Cursor.class))
        {
            setLocation(507, pos);
        }
    }
    
    /**
     * Checks if the lever is being touched by the cursor and if so, updates the
     * boolean indicator for it.
     */
    public void checkForTouch()
    {
        if(isTouching(Cursor.class))
        {
            touched = true;
        }
    }
    
    /**
     * Access method which returns the boolean indicator as true if the lever
     * has been touched.
     */
    public boolean hasBeenTouched()
    {
        return touched;
    }
}
