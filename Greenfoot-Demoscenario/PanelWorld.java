import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import de.wwu.jmrigreenfootinterface.JMRI;
import de.wwu.jmrigreenfootinterface.items.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import org.json.JSONObject;

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
            
            // Write names of trains onto layout blocks
            displayTrainIds();
            //System.out.println(JMRI.getInterface().getTrainOnLayoutBlock("IRreporter1"));
        }
        
    }
    
    private void displayTrainIds() {
        List<TrackStraight> tracksA = getObjects(TrackStraight.class);
        List<Curve>         tracksB = getObjects(Curve.class);
        List<Turnout>       tracksC = getObjects(Turnout.class);
        
        List<Track> allTracks = new ArrayList<>();
        allTracks.addAll(tracksA);
        allTracks.addAll(tracksB);
        allTracks.addAll(tracksC);
        
        // generate set of layout blocks associated with the tracks
        Set<String> layoutBlockNames = new HashSet<>();
        for(Track t : allTracks) {
            if(t != null && t.getLayoutBlock() != null) {
                layoutBlockNames.add(t.getLayoutBlock());
            }
        }
        
        // for every layout block...
        for(String layoutBlockName : layoutBlockNames) {
            // 1. query name of occupying train
            JSONObject queryResult = JMRI.getInterface().getTrainOnLayoutBlock(layoutBlockName);
            // no such train on this layout block?
            // --> continue with next layout block
            if(queryResult == null || queryResult.isEmpty() || !queryResult.has("data") || !queryResult.getJSONObject("data").has("name")) {
                continue;
            } // else (implicit)
            String trainName = queryResult.getJSONObject("data").getString("name");
            
            // 2. get all the tracks belonging to that layout block
            List<Track> tracksBelongingToBlock = new ArrayList<>();
            for(Track t : allTracks) {
                if(t.getLayoutBlock().equals(layoutBlockName)) {
                    tracksBelongingToBlock.add(t);
                }
            }
            
            // 3. calculate centroid of all the tracks belonging to the layout block
            Centroid centroid = calculateCentroid(tracksBelongingToBlock);
            
            // 4. display train name as string at centroid
            getBackground().drawString(trainName, (int) centroid.x, (int) centroid.y);
        }
    }
    
    private Centroid calculateCentroid(List<Track> tracks) {
        double centroidX = 0.0d;
        double centroidY = 0.0d;
        for(Track t : tracks) {
            centroidX += t.getX();
            centroidY += t.getY();
        }
        return new Centroid(centroidX / (double) tracks.size(), centroidY / (double) tracks.size());
    }
    
    private class Centroid {
        double x, y;
        
        protected Centroid(double x, double y) {
            this.x = x;
            this.y = y;
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
        TrackStraight trackStraight0 = new TrackStraight(StraightType.HORIZONTAL, "IBblock1");
        addObject(trackStraight0,4,1); 
        TrackStraight trackStraight1 = new TrackStraight(StraightType.HORIZONTAL, "IBblock1");
        addObject(trackStraight1,5,1);        
        TrackStraight trackStraight2 = new TrackStraight(StraightType.HORIZONTAL, "IBblock1");
        addObject(trackStraight2,6,1);
        TrackStraight trackStraight3 = new TrackStraight(StraightType.HORIZONTAL, "IBblock1");
        addObject(trackStraight3,7,1);
        TrackStraight trackStraight4 = new TrackStraight(StraightType.HORIZONTAL, "IBblock1");
        addObject(trackStraight4,8,1);
        TrackStraight trackStraight5 = new TrackStraight(StraightType.HORIZONTAL, "IBblock1");
        addObject(trackStraight5,9,1);
        TrackStraight trackStraight6 = new TrackStraight(StraightType.HORIZONTAL, "IBblock1");
        addObject(trackStraight6,10,1);
        TrackStraight trackStraight7 = new TrackStraight(StraightType.HORIZONTAL, "IBblock1");
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
        ButtonHorn buttonHorn = new ButtonHorn("S3");
        addObject(buttonHorn,5,9);
        ButtonScript buttonScript = new ButtonScript("S3");
        addObject(buttonScript,5,10);
    }
       
}
