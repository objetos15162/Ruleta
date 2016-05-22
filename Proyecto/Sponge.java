import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Sponge here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sponge extends Actor
{
    private SimpleTimer touchTime;
    private boolean touch;
    private GreenfootSound recycle;
    /**
     * Act - do whatever the Sponge wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Sponge()
    {
        recycle = new GreenfootSound("windowsrecycle.wav");
        touch = false;
        touchTime = new SimpleTimer();
    }
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
    
    public void setTouchValue()
    {
        if(touch == true && touchTime.millisElapsed() >= 10)
        {
            touch = false;
        }
    }
    
    public boolean touched()
    {
        return(touch);
    }
}
