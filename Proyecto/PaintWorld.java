import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * This class is the world for the figure painting game.
 */
public class PaintWorld extends KinectWorld
{
    private static final int THUMBNAIL_WIDTH = 80;
    private static final int THUMBNAIL_HEIGHT = 60;
    
    private boolean isFigureDrawn;
    private Counter points;
    private Counter timeLeft;
    private Joint rightHand;
    private SimpleTimer time;
    private Paintbrush brush;
    private GreenfootImage coloredSection;
    private ArrayList<FigureSection> sections; 
    private GreenfootSound splat;
    private GreenfootSound fail;
    
    private int totalPoints;
    private int gamesLeft;
    
    public PaintWorld(int games, int pointCount)
    {
        super(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, 1.0, false);
        
        gamesLeft = games-1;
        totalPoints = pointCount;
        
        setBackground("paintworldbg.png");
        coloredSection = new GreenfootImage("coloredsection.png");
        
        final int width = getWidth();
        final int height = getHeight();
        
        fail = new GreenfootSound("wrong.wav");
        splat = new GreenfootSound("paint.wav");
        isFigureDrawn = false;
        sections = new ArrayList<FigureSection>();
        points = new Counter("Points: ");
        timeLeft = new Counter("Time left: ");
        timeLeft.setValue(10);
        time = new SimpleTimer();
        brush = new Paintbrush();
        
        addObject(points, 60, 20);
        addObject(timeLeft, 72, 55);
        addObject(new Thumbnail(), 40, height - THUMBNAIL_HEIGHT/2);
        addObject(brush, 320, 240);
    }
    
    public void act()
    {
        super.act();
        if (isConnected() == false)
        {
            return;
        }
        drawBrush();
        updateTime();
        checkFigure();
        if(timeLeft.getValue()==0)
        {
            fail.play();
            Greenfoot.delay(10);
            totalPoints += points.getValue();
            Greenfoot.setWorld(new RouletteWorld(gamesLeft, totalPoints));
        }
    }
    
    public void drawBrush()
    {
        UserData[] users = getTrackedUsers();
        for (UserData user : users)
        {
            rightHand = user.getJoint(Joint.RIGHT_HAND);
            brush.setLocation(rightHand.getX(), rightHand.getY());
        }
    }
    
    public void updateTime()
    {
        if(time.millisElapsed()>=1000)
        {
            timeLeft.add(-1);
            time.mark();
        }
    }
    
    public boolean isFigureComplete()
    {
        int figureSize = sections.size();
        int partsPainted = 0;
        for(FigureSection part : sections)
        {
            if(part.isPainted())
            {
                partsPainted++;
            }
        }
        if(partsPainted == figureSize)
        {
            return (true);
        }
        else
        {
            return (false);
        }
    }
    
    public void checkFigure()
    {
        if(isFigureDrawn)
        {
            if(isFigureComplete())
            {
                points.add(100);
                time.mark();
                timeLeft.setValue(10);
                for(FigureSection part : sections)
                {
                    removeObject(part);
                }
                sections.clear();
                splat.play();
                isFigureDrawn = false;
            }   
        }
        else
        {
            addRandomFigure();
        }
    }
    
    public void addRandomFigure()
    {
        int randomNum = Greenfoot.getRandomNumber(3)+1;
        switch (randomNum)
        {
            case 1: drawRectangle();
                    break;
            case 2: drawCross();
                    break;
            case 3: drawTriangle();
                    break;
        }
        isFigureDrawn = true;
    }
    
    public void drawRectangle()
    {
        FigureSection part;
        int i;
        for(i=1; i<44; i++)
        {
            part = new FigureSection(coloredSection);
            sections.add(part);
            addObject(part, 100+(i*10), 100);
        }
        for(i=1; i<44; i++)
        {
            part = new FigureSection(coloredSection);
            sections.add(part);
            addObject(part, 100+(i*10), 300);
        }
        for(i=1; i<20; i++)
        {
            part = new FigureSection(coloredSection);
            sections.add(part);
            addObject(part, 110, 100+(i*10));
        }
        for(i=1; i<20; i++)
        {
            part = new FigureSection(coloredSection);
            sections.add(part);
            addObject(part, 530, 100+(i*10));
        }
    }
    
    public void drawCross()
    {
        FigureSection part;
        int i;
        for(i=1; i<44; i++)
        {
            part = new FigureSection(coloredSection);
            sections.add(part);
            addObject(part, 100+(i*10), 200);
        }
        for(i=1; i<20; i++)
        {
            part = new FigureSection(coloredSection);
            sections.add(part);
            addObject(part, 320, 100+(i*10));
        }
    }
    
    public void drawTriangle()
    {
        FigureSection part;
        int i;
        for(i=0; i<20; i++)
        {
            part = new FigureSection(coloredSection);
            sections.add(part);
            addObject(part, 320+(i*10), 100+(i*10));
        }
        for(i=0; i<20; i++)
        {
            part = new FigureSection(coloredSection);
            sections.add(part);
            addObject(part, 320-(i*10), 100+(i*10));
        }
        for(i=0; i<38; i++)
        {
            part = new FigureSection(coloredSection);
            sections.add(part);
            addObject(part, 130+(i*10), 290);
        }
    }
}
