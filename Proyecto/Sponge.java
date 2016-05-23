import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class represents a sponge, used in the Clean Screen game.
 * They're controlled by the player's left and right hands.
 */
public class Sponge extends Actor
{
    private SimpleTimer touchTime;
    private boolean touch;
    private GreenfootSound recycle;
    
    public Sponge()
    {
        recycle = new GreenfootSound("windowsrecycle.wav");
        touch = false;
        touchTime = new SimpleTimer();
    }
    
    /**
     * Checks if the sponge is touching a Trash object and if so, removes it.
     */
    public void act() 
    {
        if(isTouching(Trash.class))
        {
            removeTouching(Trash.class);
            touch = true;
            touchTime.mark();
            recycle.play();
        }
        setTouchValue();
    }    
    
    /**
     * Updates the boolean indicator for touching each 10 milliseconds so the
     * player doesn't recieve double points for a single trash.
     */
    public void setTouchValue()
    {
        if(touch == true && touchTime.millisElapsed() >= 10)
        {
            touch = false;
        }
    }
    
    /**
     * Access method which returns the boolean indicator as true if the sponge has
     * touched a Trash object recently.
     */
    public boolean touched()
    {
        return(touch);
    }
}
