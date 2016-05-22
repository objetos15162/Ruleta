import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * This class is the world for the CleanScreen game.
 */
public class TrashWorld extends KinectWorld
{
    private static final int THUMBNAIL_WIDTH = 80;
    private static final int THUMBNAIL_HEIGHT = 60;
    
    private SimpleTimer time;
    private SimpleTimer clock;
    private Counter points;
    private Counter timeLeft;
    private int secLeft;
    private GreenfootImage bg;
    private GreenfootImage activeBG;
    private ArrayList<GreenfootImage> trashIMGs;
    private int totalPoints;
    private int gamesLeft;

    public TrashWorld(int games, int pointCount)
    {    
        super(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, 1.0, false);
        
        gamesLeft = games-1;
        totalPoints = pointCount;
        bg = new GreenfootImage("park.png");
        clock = new SimpleTimer();
        time = new SimpleTimer();
        points = new Counter("Points: ");
        timeLeft = new Counter("Time left: ");
        
        initializeTrashIMGs();
        
        final int width = getWidth();
        final int height = getHeight();
        secLeft = 30;
        timeLeft.setValue(30);
        
        setBackground(bg);
        addObject(points, 60, 20);
        addObject(timeLeft, 72, 55);
        addObject(new TrashPlayer(width, height, points), width/2, height/2);
        addObject(new Thumbnail(), 40, height - THUMBNAIL_HEIGHT/2);
    }
    
    public void act()
    {
        super.act();
        if (isConnected() == false)
        {
            return;
        }
        drawPlayer();
        addTrash();
        updateTimeElapsed();
    }
    
    public void drawPlayer()
    {
        UserData[] us = getTrackedUsers();
        
        activeBG = new GreenfootImage(bg);
        setBackground(activeBG);
        
        for (UserData u: us)
        {
            u.drawStickFigure(getBackground(), 60);
        }
    }
    
    public void addTrash()
    {
        int i, x, y, image;
        GreenfootImage trashIMG;
        if(clock.millisElapsed() >= 3000)
        {
            for(i=0; i<3; i++)
            {
                x = Greenfoot.getRandomNumber(560);
                y = Greenfoot.getRandomNumber(560);
                image = Greenfoot.getRandomNumber(5);
                trashIMG = trashIMGs.get(image);
                addObject(new Trash(trashIMG), x, y);
                clock.mark();
            }
        }
    }
    
    public void updateTimeElapsed()
    {    
        if(time.millisElapsed() >= 1000)
        {
            secLeft--;
            timeLeft.setValue(secLeft);
            time.mark();
        }
        if(secLeft == 0)
        {
            Greenfoot.delay(10);
            totalPoints += points.getValue();
            Greenfoot.setWorld(new RouletteWorld(gamesLeft, totalPoints));
        }
    }
    
    public void initializeTrashIMGs()
    {
        GreenfootImage image;
        trashIMGs = new ArrayList();
        image = new GreenfootImage("tire.png");
        trashIMGs.add(image);
        image = new GreenfootImage("banana.png");
        trashIMGs.add(image);
        image = new GreenfootImage("spider.png");
        trashIMGs.add(image);
        image = new GreenfootImage("fly.png");
        trashIMGs.add(image);
        image = new GreenfootImage("bittenapple.png");
        trashIMGs.add(image);
    }
}
