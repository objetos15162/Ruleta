import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the class for the game records screen.
 * It lists the highest scores.
 */
public class RecordWorld extends KinectWorld
{
    private static final int THUMBNAIL_WIDTH = 80;
    private static final int THUMBNAIL_HEIGHT = 60;
    
    private RedButton titleScreen;
    private Label title;
    private Cursor cursor;
    private Joint rightHand;
    private GreenfootSound selection;
    
    public RecordWorld()
    {
        super(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, 1.0, false);
        
        setBackground("startscreenbg.png");
        selection = new GreenfootSound("selection.wav");
        
        Label records = new Label("Records", 50);
        title = new Label("Return to Title Screen", 30);
        titleScreen = new RedButton();
        cursor = new Cursor();
        
        addObject(records, 320, 50);
        addObject(titleScreen, 500, 360);
        addObject(title, 500, 420);
    }
    
    public void act()
    {
        super.act();
        if(!isConnected())
        {
            return;
        }
        drawCursor();
        checkForPressedButton();
    }
    
    public void drawCursor()
    {
        UserData[] us = getTrackedUsers();
        for (UserData user: us)
        {
            rightHand = user.getJoint(Joint.RIGHT_HAND);
            cursor.setLocation(rightHand.getX(), rightHand.getY());
        }
    }
    
    public void checkForPressedButton()
    {
        if(titleScreen.isPressed())
        {
            selection.play();
            Greenfoot.setWorld(new StartScreen());
        }
    }
}
