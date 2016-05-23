import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class represents trash, used in the CleanScreen game.
 */
public class Trash extends Actor
{   
    public Trash(GreenfootImage image)
    {
        setImage(image);
    }
    
    /**
     * Act - Makes the trash object move randomly around the screen.
     */
    public void act() 
    {
        int move;
        move(2);
        if(Greenfoot.getRandomNumber(2)==0)
        {
            turn(Greenfoot.getRandomNumber(90)-45);
        }
    }
}