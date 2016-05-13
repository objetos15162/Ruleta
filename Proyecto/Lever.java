import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lever here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lever extends Actor
{
    /**
     * Act - do whatever the Lever wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean touched;
    
    public Lever()
    {
        touched = false;
    }
    
    public void act() 
    {
        if(!touched)
        {
            checkForTouch();
        }
    }    
    
    @Override
    public void move(int pos)
    {
        if(isTouching(Cursor.class))
        {
            setLocation(507, pos);
        }
    }
    
    public void checkForTouch()
    {
        if(isTouching(Cursor.class))
        {
            touched = true;
        }
    }
    
    public boolean hasBeenTouched()
    {
        return touched;
    }
}
