import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Thumbnail  extends Actor
{
    public Thumbnail()
    {
    }
    
    public void act() 
    {
        GreenfootImage img = ((KinectWorld)getWorld()).getThumbnailUnscaled();
        setImage(img);
    }
}
