import greenfoot.*;

public class TrashPlayer extends Actor
{
    private Sponge leftSponge;
    private Sponge rightSponge;
    private Counter pointCount;
    private int pointNum;
    private boolean areSpongesActive;
    private Joint leftHand;
    private Joint rightHand;
    
    public TrashPlayer(int width, int height, Counter points)
    {
        setImage(new GreenfootImage(width, height));
        pointCount = points;
        leftSponge = new Sponge();
        rightSponge = new Sponge();
        pointNum = 0;
        areSpongesActive = false;
    }

    public void act() 
    {
        TrashWorld world = (TrashWorld)getWorld();
        UserData[] users = world.getTrackedUsers();
        for (UserData user : users)
        {
            leftHand = user.getJoint(Joint.LEFT_HAND);
            rightHand = user.getJoint(Joint.RIGHT_HAND);
            addSponges(world);
            setSpongeLocations();
            checkForPoints();
        }
        pointCount.setValue(pointNum);
    }
    
    public void addSponges(TrashWorld world)
    {
        if(areSpongesActive == false)
            {
                world.addObject(leftSponge, leftHand.getX(), leftHand.getY());
                world.addObject(rightSponge, rightHand.getX(), rightHand.getY());
                areSpongesActive = true;
            }
    }
    
    public void setSpongeLocations()
    {
        leftSponge.setLocation(leftHand.getX(), leftHand.getY());
        rightSponge.setLocation(rightHand.getX(), rightHand.getY());
    }
    
    public void checkForPoints()
    {
        if(leftSponge.touched())
            {
                pointNum=pointNum+15;
            }    
        if(rightSponge.touched())
        {
            pointNum=pointNum+15;
        }
    }
}
