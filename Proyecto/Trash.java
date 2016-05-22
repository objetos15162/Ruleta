import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Trash extends Actor
{   
    private int image;
    
    public Trash(GreenfootImage image)
    {
        setImage(image);
    }
    
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