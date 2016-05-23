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
    private ScoreBoard score;

    public RecordWorld()
    {
        super(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, 1.0, false);

        score = new ScoreBoard(600, 400);
        setBackground("startscreenbg.png");
        selection = new GreenfootSound("selection.wav");

        Label records = new Label("Records", 50);
        title = new Label("Return to Title Screen", 30);
        titleScreen = new RedButton();
        cursor = new Cursor();

        addObject(records, 320, 35);
        addObject(score, 325,220);
        addObject(titleScreen, 343, 450);
        addObject(title, 502, 450);
    }

    /**
     * Act - Pauses the game if the Kinect isn't connected.
     * Calls the corresponding methods to control the cursor and check
     * if the button has been pressed.
     */
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

    /**
     * Updates the position of the cursor to the player's hand position.
     */
    public void drawCursor()
    {
        UserData[] us = getTrackedUsers();
        for (UserData user: us)
        {
            rightHand = user.getJoint(Joint.RIGHT_HAND);
            cursor.setLocation(rightHand.getX(), rightHand.getY());
        }
    }

    /**
     * Returns to the title screen if the button has been pressed.
     */
    public void checkForPressedButton()
    {
        if(titleScreen.isPressed())
        {
            selection.play();
            Greenfoot.setWorld(new StartScreen());
        }
    }
}
