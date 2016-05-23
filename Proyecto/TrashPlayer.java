import greenfoot.*;

/**
 * This class represents the player in the CleanScreen game.
 */
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

    /**
     * Act - Tracks the user's joints and calls the corresponding methods to
     * move the sponges and check if the score needs to be updated.
     */
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
    
    /**
     * If the sponges haven't been added yet, this method adds them to the world.
     */
    public void addSponges(TrashWorld world)
    {
        if(areSpongesActive == false)
            {
                world.addObject(leftSponge, leftHand.getX(), leftHand.getY());
                world.addObject(rightSponge, rightHand.getX(), rightHand.getY());
                areSpongesActive = true;
            }
    }
    
    /**
     * Sets the left and right sponges to the player's left and right hands
     * respectively.
     */
    public void setSpongeLocations()
    {
        leftSponge.setLocation(leftHand.getX(), leftHand.getY());
        rightSponge.setLocation(rightHand.getX(), rightHand.getY());
    }
    
    /**
     * Also checks if a trash object has been touched recently and if so,
     * increases the score.
     */
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
