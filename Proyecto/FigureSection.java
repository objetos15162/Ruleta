import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class represents a figure section, used in the Figure Painting game.
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
    
    /**
     * Act - If the section hasn't yet been painted, checks if it's being touched
     * by the paintbrush and it so, updates the object image to the colores section
     * GreenfootImage.
     */
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
    
    /**
     * Access method which returns the boolean indicator as true if the
     * section has already been painted.
     */
    public boolean isPainted()
    {
        return(painted);
    }
}
