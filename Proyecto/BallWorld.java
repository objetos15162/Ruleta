import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is the world for the FallBall game.
 */
public class BallWorld extends KinectWorld
{
    private static final int THUMBNAIL_WIDTH = 80;
    private static final int THUMBNAIL_HEIGHT = 60;
    
    private static final GreenfootImage bgImage = new GreenfootImage("brickbg.png");
    private GreenfootImage scrollingImage;
    private int scrollSpeed;
    private int scrollPosition = 0;
    private FallingBall ball;
    private Joint head;
    private SimpleTimer timeElapsed;
    private Counter points;
    private Platform a;
    private Platform b;
    private int totalPoints;
    private int gamesLeft;
    private GreenfootSound spUp;
    private GreenfootSound fail;
    
    public BallWorld(int games, int pointCount)
    {
        super(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, 1.0, false);
        
        gamesLeft = games-1;
        totalPoints = pointCount;
        scrollSpeed = 1;
        points = new Counter("Points: ");
        points.setValue(0);
        ball = new FallingBall();
        timeElapsed = new SimpleTimer();
        GreenfootImage activeBG = new GreenfootImage(640, 480);
        scrollingImage = getScrollingImage(640, 480);
        activeBG.drawImage(scrollingImage, 0, 0);
        setBackground(activeBG);
        spUp = new GreenfootSound("speedup.wav");
        fail = new GreenfootSound("wrong.wav");
        
        final int width = getWidth();
        final int height = getHeight();
        
        addObject(points, 60, 20);
        addObject(ball, 320, 240);
        addObject(new Thumbnail(), 40, height - THUMBNAIL_HEIGHT/2);
    }
    
    /**
     * Act - Pauses the game if the Kinect isn't connected.
     * Calls the corresponging methods to scroll the screen, control the ball,
     * create the ascending platforms and speed up the scrolling.
     * Ends the game if the ball reaches the top of the screen.
     */
    public void act()
    {
        super.act();
        if (isConnected() == false)
        {
            return;
        }
        doScrolling();
        moveBall();
        createPlatforms();
        speedUp();
        if(ball.getY() == 0)
        {
            fail.play();
            Greenfoot.delay(10);
            totalPoints += points.getValue();
            Greenfoot.setWorld(new RouletteWorld(gamesLeft, totalPoints));
        }
    }
    
    /**
     * This method creates a pair of platforms, calculating a space between them
     * for the ball to fall in between. If the pair of platforms reach the top,
     * it removes them and creates a new pair.
     */
    public void createPlatforms()
    {
        if(a==null)
        {
            int randNum = Greenfoot.getRandomNumber(5)+1;
            a = new Platform(scrollSpeed);
            b = new Platform(scrollSpeed);
            a.getImage().scale(a.getImage().getWidth(), 50*randNum);
            addObject(a, (a.getImage().getHeight())/2, getHeight());
            if((a.getImage().getHeight()/2)+150+(b.getImage().getHeight()/2) > 100)
            {
                b.getImage().scale(b.getImage().getWidth(), (getWidth()-150-a.getImage().getHeight()));
                addObject(b, a.getX()+(a.getImage().getHeight()/2)+150+(b.getImage().getHeight()/2), getHeight());
            }
        }
        else
        {
            if(a.getY()==0)
            {
                removeObject(a);
                a=null;
                if(b.getWorld() != null)
                {
                    removeObject(b);
                }
                b=null;
                points.add(10+(10*scrollSpeed));
            }
        }
    }
    
    /**
     * Speeds up the scrolling each 10 seconds.
     */
    public void speedUp()
    {
        if(timeElapsed.millisElapsed() >= 10000)
        {
            scrollSpeed++;
            timeElapsed.mark();
            spUp.play();
        }
    }
    
    /**
     * Sets the x position of the ball to the player's x-axis.
     * Moves the ball by the scrollSpeed.
     */
    public void moveBall()
    {
        UserData[] users = getTrackedUsers();
        for (UserData user : users)
        {
            head = user.getJoint(Joint.HEAD);
            ball.setLocation(head.getX(), ball.getY());
        }
        ball.move(scrollSpeed);
    }
    
    /**
     * Updates the background image to the position recieved.
     */
    private void paint(int position)
    {
        GreenfootImage background = getBackground();
        background.drawImage(scrollingImage, 0, position);
        background.drawImage(scrollingImage, 0, position - scrollingImage.getHeight());
    }
    
    /**
     * Returns the image to be scrolled.
     */
    private GreenfootImage getScrollingImage(int width, int height)
    {
        GreenfootImage image = new GreenfootImage(width, height);
        for(int x = 0; x < width; x += bgImage.getWidth()) {
            for(int y = 0; y < height; y += bgImage.getHeight()) {
                image.drawImage(bgImage, x, y);
            }
        }
        return image;
    } 
    
    /**
     * Updates the scroll position of the background image and calls the paint method
     * to paint it.
     */
    private void doScrolling()
    {
        if(scrollSpeed > 0 && scrollPosition <= 0) {
            scrollPosition = getHeight();
        }
        if(scrollSpeed < 0 && scrollPosition >= getHeight()) {
            scrollPosition = 0;
        }
        scrollPosition -= scrollSpeed;
        paint(scrollPosition);
    }
}
