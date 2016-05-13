import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class RouletteWorld extends KinectWorld
{
    private static final int THUMBNAIL_WIDTH = 80;
    private static final int THUMBNAIL_HEIGHT = 60;
    
    private int totalPoints;
    private int gamesLeft;
    private Counter gameCount;
    private Counter points;
    private Roulette roulette;
    private Label instructions;
    private Lever lever;
    private Cursor cursor;
    private Joint rightHand;
    private boolean leverDown;
    private boolean isSpinning;
    private SimpleTimer spinTime;
    private int randTime;
    private int turnSpeed;
    
    public RouletteWorld(int games, int pointCount)
    {
        super(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, 1.0, false);
        
        gamesLeft = games;
        totalPoints = pointCount;
        
        setBackground("rouletteworldbg.png");
        
        turnSpeed = 20;
        isSpinning = false;
        leverDown = false;
        cursor = new Cursor();
        lever = new Lever();
        gameCount = new Counter("Games Left: ");
        gameCount.setValue(gamesLeft);
        points = new Counter("Points Accumulated: ");
        points.setValue(totalPoints);
        instructions = new Label("");
        instructions.setText("Use the lever to spin the roulette!", 30);
        roulette = new Roulette();
        
        final int width = getWidth();
        final int height = getHeight();
        
        addObject(lever, 507, 150);
        addObject(instructions, 445, getHeight()-20);
        addObject(points, 120, 25);
        addObject(gameCount, 550, 25);
        addObject(roulette, 320, 240);
        addObject(cursor, 20, 20);
        addObject(new Thumbnail(), 40, height - THUMBNAIL_HEIGHT/2);
    }
    
    public void act()
    {
        super.act();
        if(!isConnected())
        {
            return;
        }
        drawCursor();
        if(leverDown)
        {
            if(isSpinning)
            {
                if(spinTime.millisElapsed() <= randTime)
                {
                    roulette.turn(turnSpeed);
                    if(spinTime.millisElapsed() <= (randTime/20)*(turnSpeed-1))
                    {
                        if(turnSpeed > 1)
                        {
                            turnSpeed --;
                        }
                    }
                }
                else
                {
                    Greenfoot.delay(150);
                    checkGame();
                }
            }
            else
            {
                randTime = (Greenfoot.getRandomNumber(6)+2)*1000;
                spinTime = new SimpleTimer();
                isSpinning = true;
            }
            
        }
        else
        {
            if(lever.hasBeenTouched())
            {
                moveLever();
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
    
    public void moveLever()
    {
        lever.move(rightHand.getY());
        if(lever.getY() < 150)
        {
            lever.move(150);
        }
        if(lever.getY() > 332)
        {
            lever.move(332);
            leverDown = true;
        }
    }
    
    public void checkGame()
    {
        if(roulette.getRotation() >= 0 && roulette.getRotation() < 90)
        {
            
        }
        if(roulette.getRotation() >= 90 && roulette.getRotation() < 180)
        {
            
        }
        if(roulette.getRotation() >= 180 && roulette.getRotation() < 270)
        {
            Greenfoot.setWorld(new BallWorld(gamesLeft, totalPoints));
        }
        if(roulette.getRotation() >= 270 && roulette.getRotation() < 360)
        {
            Greenfoot.setWorld(new TrashWorld(gamesLeft, totalPoints));
        }
    }
}
