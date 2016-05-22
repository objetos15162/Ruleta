import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the class for the title screen / main menu.
 * From here you can start the game, view the highest scores or leave.
 */
public class StartScreen extends KinectWorld
{
    private static final int THUMBNAIL_WIDTH = 80;
    private static final int THUMBNAIL_HEIGHT = 60;
    
    private Joint rightHand;
    private Cursor cursor;
    
    private GreenfootImage bg;
    private GreenfootImage activeBG;
    
    private boolean isEnvironmentDrawn;
    private RedButton playGame;
    private BlueButton viewRecords;
    private YellowButton quitGame;
    private Label play;
    private Label records;
    private Label quit;
    private Label welcome;
    private Label instructions;
    private Label howToStart;
    private GreenfootSound selection;
    
    public StartScreen()
    {
        super(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, 1.0, false);
        
        isEnvironmentDrawn = false;
        welcome = new Label("Welcome to Roulette!", 40);
        instructions = new Label("Raise both hands to begin.", 20);
        play = new Label("Start Game", 40);
        records = new Label("View Records", 40);
        quit = new Label("Quit Game", 40);
        howToStart = new Label("Hold the cursor over a button to press it.", 20);
        playGame = new RedButton();
        viewRecords = new BlueButton();
        quitGame = new YellowButton();
        cursor = new Cursor();
        selection = new GreenfootSound("selection.wav");
        
        setBackground("startscreenbg.png");
        
        final int width = getWidth();
        final int height = getHeight();
        
        addObject(welcome, 320, 50);
        addObject(instructions, 325, 85);
        addObject(new Thumbnail(), 40, height - THUMBNAIL_HEIGHT/2);
    }
    
    public void act()
    {
        super.act();
        if(!isConnected())
        {
            return;
        }
        if(isEnvironmentDrawn)
        {
            drawCursor();
            checkForPressedButtons();
        }
        else
        {
            if(areBothHandsRaised())
            {
                drawEnvironment();
                isEnvironmentDrawn = true;
            }
        }
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
    
    public boolean areBothHandsRaised()
    {
        boolean handsRaised = false;
        UserData[] users = getTrackedUsers();
        for (UserData user : users)
        {
            Joint leftHand = user.getJoint(Joint.LEFT_HAND);
            rightHand = user.getJoint(Joint.RIGHT_HAND);
            Joint head = user.getJoint(Joint.HEAD);
            if(leftHand.getY() > head.getY() && rightHand.getY() > head.getY())
            {
                handsRaised = true;
            }
        }
        return handsRaised;
    }
    
    public void drawEnvironment()
    {
        removeObject(instructions);
        addObject(howToStart, 325, 85);
        addObject(playGame, 205, 230);
        addObject(play, 205, 275);
        addObject(viewRecords, 435, 230);
        addObject(records, 430, 275);
        addObject(quitGame, 500, 380);
        addObject(quit, 500, 440);
        addObject(cursor, rightHand.getX(), rightHand.getY());
    }
    
    public void checkForPressedButtons()
    {
        if(playGame.isPressed())
        {
            selection.play();
            Greenfoot.setWorld(new RouletteWorld(4, 0));
        }
        if(viewRecords.isPressed())
        {
            selection.play();
            Greenfoot.setWorld(new RecordWorld());
        }
        if(quitGame.isPressed())
        {
            selection.play();
            Greenfoot.stop();
        }
    }
}
