import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is the world for the FallBall game.
 */
public class AppleWorld extends KinectWorld
{
    private static final int THUMBNAIL_WIDTH = 80;
    private static final int THUMBNAIL_HEIGHT = 60;
    
    public Counter points;
    private Counter failsLeft;
    private Joint head;
    private SimpleTimer time;
    private Barrier barr;
    private Basket bask;
    private GreenfootSound falling;
    
    private int totalPoints;
    private int gamesLeft;
    
    /**
     * Constructor for objects of class AppleWorld.
     * 
     */
    public AppleWorld(int games, int pointCount)
    {
        super(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, 1.0, false);
        
        gamesLeft = games-1;
        totalPoints = pointCount;
        
        setBackground("appleworldbg.png");
        
        final int width = getWidth();
        final int height = getHeight();
        
        points = new Counter("Points: ");
        failsLeft = new Counter("Misses left: ");
        failsLeft.setValue(3);
        time = new SimpleTimer();
        barr = new Barrier(failsLeft);
        bask = new Basket(points);
        falling = new GreenfootSound("fallingapple.wav");
        
        addObject(points, 60, 20);
        addObject(failsLeft, 72, 55);
        addObject(new Thumbnail(), 40, height - THUMBNAIL_HEIGHT/2);
        addObject(barr, 320, 480);
        addObject(bask, 320, 410);
    }
    
    public void act()
    {
        super.act();
        if (isConnected() == false)
        {
            return;
        }
        addApple();
        drawBasket();
        if(failsLeft.getValue()==0)
        {
            Greenfoot.delay(10);
            totalPoints += points.getValue();
            Greenfoot.setWorld(new RouletteWorld(gamesLeft, totalPoints));
        }
    }
    
    public void addApple()
    {
        int x, y;
        if(time.millisElapsed() >= 5000)
        {
            x = Greenfoot.getRandomNumber(400)+70;
            y = Greenfoot.getRandomNumber(250)+200;
            addObject(new Apple(), x, y);
            time.mark();
            falling.play();
        }
    }
    
    public void drawBasket()
    {
        UserData[] users = getTrackedUsers();
        for (UserData user : users)
        {
            head = user.getJoint(Joint.HEAD);
            bask.setLocation(head.getX(), bask.getY());
        }
    }
}
