import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import de.wwu.jmrigreenfootinterface.items.*;

/**
 * Eine Welt, in der das Schema einer Modelleisenbahn (Panel) nachgebildet werden kann.
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public class PanelWorld extends World
{
    
    int tickCounter = -1;
    
    /**
     * Konstruktor für Objekte der Klasse PanelWorld
     * 
     */
    public PanelWorld()
    {    
        // Create a new world with 16x12 cells with a cell size of 64x64 pixels.
        super(16, 12, 64);

        paintBackgroundGrid();
        prepare();
    }
    
    @Override
    public void started() {
        getObjects(Turnout.class).forEach(t -> {
            TurnoutState s = t.getState();
            t.updateImage();
        });
        repaint();
    }
    
    public void act() {        
        // Clicks on turnouts
        if(Greenfoot.getMouseInfo() != null) {
            Actor focusedActor = Greenfoot.getMouseInfo().getActor();
            if(Greenfoot.mousePressed(focusedActor)) {
                if(focusedActor != null) {
                    if(focusedActor instanceof Turnout) {
                        Turnout clickedSwitch = (Turnout) focusedActor;
                        clickedSwitch.toggleState();
                        clickedSwitch.updateImage();
                    } else if(focusedActor instanceof Button) {
                        Button clickedButton = (Button) focusedActor;
                        clickedButton.onClick();
                    }
                }
            }
        }
        
        // Update layout block activation (every n ticks)
        if((++tickCounter) % 16 == 0) {
            getObjects(Curve.class).forEach(t -> t.updateImage());
            getObjects(TrackStraight.class).forEach(t -> t.updateImage());
            getObjects(Turnout.class).forEach(t -> t.updateImage());
        }
    }
    
    private void paintBackgroundGrid() {
        GreenfootImage bg = getBackground();

        bg.setColor(Color.GRAY);
        for(int x = 1; x < getWidth(); x++) {
            bg.drawLine(x * getCellSize(), 0, x * getCellSize(), getHeight() * getCellSize());
        }
        for(int y = 1; y < getWidth(); y++) {
            bg.drawLine(0, y * getCellSize(), getWidth() * getCellSize(), y * getCellSize());
        }
    }
    
    private void prepare() {
        TrackStraight trackStraight0 = new TrackStraight(StraightType.HORIZONTAL);
        addObject(trackStraight0,4,1); 
        TrackStraight trackStraight1 = new TrackStraight(StraightType.HORIZONTAL);
        addObject(trackStraight1,5,1);        
        TrackStraight trackStraight2 = new TrackStraight(StraightType.HORIZONTAL);
        addObject(trackStraight2,6,1);
        TrackStraight trackStraight3 = new TrackStraight(StraightType.HORIZONTAL);
        addObject(trackStraight3,7,1);
        TrackStraight trackStraight4 = new TrackStraight(StraightType.HORIZONTAL);
        addObject(trackStraight4,8,1);
        TrackStraight trackStraight5 = new TrackStraight(StraightType.HORIZONTAL);
        addObject(trackStraight5,9,1);
        TrackStraight trackStraight6 = new TrackStraight(StraightType.HORIZONTAL);
        addObject(trackStraight6,10,1);
        TrackStraight trackStraight7 = new TrackStraight(StraightType.HORIZONTAL);
        addObject(trackStraight7,11,1);

        TrackStraight trackStraight20 = new TrackStraight(StraightType.HORIZONTAL);
        addObject(trackStraight20,4,6); 
        //TrackStraight trackStraight21 = new TrackStraight(StraightType.HORIZONTAL);
        //addObject(trackStraight21,5,6);        
        TrackStraight trackStraight22 = new TrackStraight(StraightType.HORIZONTAL);
        addObject(trackStraight22,6,5);
        TrackStraight trackStraight23 = new TrackStraight(StraightType.HORIZONTAL);
        addObject(trackStraight23,7,5);
        TrackStraight trackStraight24 = new TrackStraight(StraightType.HORIZONTAL);
        addObject(trackStraight24,8,5);
        TrackStraight trackStraight25 = new TrackStraight(StraightType.HORIZONTAL);
        addObject(trackStraight25,9,5);
        //TrackStraight trackStraight26 = new TrackStraight(StraightType.HORIZONTAL);
        //addObject(trackStraight26,10,6);
        TrackStraight trackStraight27 = new TrackStraight(StraightType.HORIZONTAL);
        addObject(trackStraight27,11,6);

        TrackStraight trackStraight30 = new TrackStraight(StraightType.VERTICAL);
        addObject(trackStraight30,3,2);
        TrackStraight trackStraight31 = new TrackStraight(StraightType.VERTICAL);
        addObject(trackStraight31,3,3);
        TrackStraight trackStraight32 = new TrackStraight(StraightType.VERTICAL);
        addObject(trackStraight32,3,4);
        TrackStraight trackStraight33 = new TrackStraight(StraightType.VERTICAL);
        addObject(trackStraight33,3,5);

        TrackStraight trackStraight40 = new TrackStraight(StraightType.VERTICAL);
        addObject(trackStraight40,12,2);
        TrackStraight trackStraight41 = new TrackStraight(StraightType.VERTICAL);
        addObject(trackStraight41,12,3);
        TrackStraight trackStraight42 = new TrackStraight(StraightType.VERTICAL);
        addObject(trackStraight42,12,4);
        TrackStraight trackStraight43 = new TrackStraight(StraightType.VERTICAL);
        addObject(trackStraight43,12,5);

        TrackStraight trackStraight52 = new TrackStraight(StraightType.HORIZONTAL);
        addObject(trackStraight52,6,7);
        TrackStraight trackStraight53 = new TrackStraight(StraightType.HORIZONTAL);
        addObject(trackStraight53,7,7);
        TrackStraight trackStraight54 = new TrackStraight(StraightType.HORIZONTAL);
        addObject(trackStraight54,8,7);
        TrackStraight trackStraight55 = new TrackStraight(StraightType.HORIZONTAL);
        addObject(trackStraight55,9,7);

        Curve curve = new Curve(CurveType.BOTTOM_RIGHT);
        addObject(curve,3,1);
        Curve curve2 = new Curve(CurveType.BOTTOM_LEFT);
        addObject(curve2,12,1);
        Curve curve3 = new Curve(CurveType.TOP_RIGHT);
        addObject(curve3,3,6);
        Curve curve4 = new Curve(CurveType.TOP_LEFT);
        addObject(curve4,12,6);
        Turnout turnout = new Turnout(TurnoutType.LEFT, "ITabc123");
        addObject(turnout,5,6);
        Turnout turnout2 = new Turnout(TurnoutType.RIGHT, "ITabc321");
        addObject(turnout2,10,6);
        Curve curve5 = new Curve(CurveType.BOTTOM_RIGHT);
        addObject(curve5,5,5);
        Curve curve6 = new Curve(CurveType.BOTTOM_LEFT);
        addObject(curve6,10,5);
        Curve curve7 = new Curve(CurveType.TOP_RIGHT);
        addObject(curve7,5,7);
        Curve curve8 = new Curve(CurveType.TOP_LEFT);
        addObject(curve8,10,7);
        
        ButtonGo buttonGo = new ButtonGo("S3");
        addObject(buttonGo,1,9);
        ButtonStop buttonStop = new ButtonStop("S3");
        addObject(buttonStop,3,9);
        ButtonChangeDirections buttonChangeDirections = new ButtonChangeDirections("S3");
        addObject(buttonChangeDirections,2,9);
    }
       
}
