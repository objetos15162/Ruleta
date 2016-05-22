import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FigureSection here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FigureSection extends Actor
{
    private GreenfootImage colored;
    private boolean painted;
    
    public FigureSection(GreenfootImage coloredSection)
    {
        colored = coloredSection;
        painted = false;
    }
    
    public void act() 
    {
        if(!painted)
        {
            if(isTouching(Paintbrush.class))
            {
                setImage(colored);
                painted = true;
            }
        }
    }    
    
    public boolean isPainted()
    {
        return(painted);
    }
}
