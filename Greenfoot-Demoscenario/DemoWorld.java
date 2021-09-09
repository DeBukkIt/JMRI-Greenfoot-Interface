import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Random;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DemoWorld extends World
{    
    Train train;
    
    public DemoWorld()
    {    
        // Create a new world with 32x22 cells with a cell size of 32x32 pixels.
        super(16, 12, 64);

        paintBackgroundGrid();
        prepare();
        initTrackEnvironments();
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
    
     /**
     * Verarbeitet Mauseingaben, steuert Switches in Abhängigkeit von der angeklickten Stelle.
     */
    public void act() {        
        // Switch clicks
        if(Greenfoot.getMouseInfo() != null) {
            Actor focusedActor = Greenfoot.getMouseInfo().getActor();
            if(Greenfoot.mousePressed(focusedActor)) {
                if(focusedActor != null && focusedActor instanceof Switch) {
                    Switch clickedSwitch = (Switch) focusedActor;
                    clickedSwitch.toggleSwitched();
                }
            }
        }
    }

    /**
     * Bereite die Welt für den Programmstart vor.
     * Das heißt: Erzeuge die Anfangs-Objekte und füge sie der Welt hinzu.
     */
    private void prepare()
    {   
        // Place objets
        TrackHorizontal trackHorizontal = new TrackHorizontal();
        addObject(trackHorizontal,4,4);
        TrackHorizontal trackHorizontal2 = new TrackHorizontal();
        addObject(trackHorizontal2,5,4);
        TrackHorizontal trackHorizontal3 = new TrackHorizontal();
        addObject(trackHorizontal3,6,4);
        TrackHorizontal trackHorizontal4 = new TrackHorizontal();
        addObject(trackHorizontal4,7,4);
        TrackHorizontal trackHorizontal5 = new TrackHorizontal();
        addObject(trackHorizontal5,8,4);
        TrackHorizontal trackHorizontal6 = new TrackHorizontal();
        addObject(trackHorizontal6,9,4);
        TrackHorizontal trackHorizontal7 = new TrackHorizontal();
        addObject(trackHorizontal7,10,4);
        TrackCurveLeftBottom trackCurveLeftBottom = new TrackCurveLeftBottom();
        addObject(trackCurveLeftBottom,11,4);
        TrackVertical trackVertical = new TrackVertical();
        addObject(trackVertical,11,5);
        TrackHorizontal trackHorizontal8 = new TrackHorizontal();
        addObject(trackHorizontal8,12,4);
        TrackHorizontal trackHorizontal9 = new TrackHorizontal();
        addObject(trackHorizontal9,13,4);
        TrackCurveTopLeft trackCurveTopLeft = new TrackCurveTopLeft();
        addObject(trackCurveTopLeft,11,6);
        TrackHorizontal trackHorizontal10 = new TrackHorizontal();
        addObject(trackHorizontal10,10,6);
        TrackHorizontal trackHorizontal11 = new TrackHorizontal();
        addObject(trackHorizontal11,9,6);
        TrackHorizontal trackHorizontal12 = new TrackHorizontal();
        addObject(trackHorizontal12,8,6);
        TrackHorizontal trackHorizontal13 = new TrackHorizontal();
        addObject(trackHorizontal13,7,6);
        TrackHorizontal trackHorizontal14 = new TrackHorizontal();
        addObject(trackHorizontal14,6,6);
        TrackHorizontal trackHorizontal15 = new TrackHorizontal();
        addObject(trackHorizontal15,5,6);
        TrackHorizontal trackHorizontal16 = new TrackHorizontal();
        addObject(trackHorizontal16,4,6);
        TrackHorizontal trackHorizontal17 = new TrackHorizontal();
        addObject(trackHorizontal17,3,6);
        TrackHorizontal trackHorizontal18 = new TrackHorizontal();
        addObject(trackHorizontal18,1,6);
        TrackCurveTopRight trackCurveTopRight = new TrackCurveTopRight();
        addObject(trackCurveTopRight,2,6);
        TrackVertical trackVertical2 = new TrackVertical();
        addObject(trackVertical2,2,5);
        TrackVertical trackVertical3 = new TrackVertical();
        addObject(trackVertical3,2,4);
        TrackVertical trackVertical4 = new TrackVertical();
        addObject(trackVertical4,2,3);
        TrackCurveRightBottom trackCurveRightBottom = new TrackCurveRightBottom();
        addObject(trackCurveRightBottom,2,2);
        TrackHorizontal trackHorizontal19 = new TrackHorizontal();
        addObject(trackHorizontal19,3,2);
        TrackHorizontal trackHorizontal20 = new TrackHorizontal();
        addObject(trackHorizontal20,4,2);
        TrackHorizontal trackHorizontal21 = new TrackHorizontal();
        addObject(trackHorizontal21,5,2);
        TrackHorizontal trackHorizontal22 = new TrackHorizontal();
        addObject(trackHorizontal22,6,2);
        TrackVertical trackVertical5 = new TrackVertical();
        addObject(trackVertical5,11,7);
        TrackVertical trackVertical6 = new TrackVertical();
        addObject(trackVertical6,2,1);
        TrackCurveTopLeft trackCurveTopLeft2 = new TrackCurveTopLeft();
        addObject(trackCurveTopLeft2,7,2);
        TrackCurveRightBottom trackCurveRightBottom2 = new TrackCurveRightBottom();
        addObject(trackCurveRightBottom2,7,1);
        TrackCurveLeftBottom trackCurveLeftBottom2 = new TrackCurveLeftBottom();
        addObject(trackCurveLeftBottom2,8,1);
        TrackCurveTopRight trackCurveTopRight2 = new TrackCurveTopRight();
        addObject(trackCurveTopRight2,8,2);
        TrackHorizontal trackHorizontal23 = new TrackHorizontal();
        addObject(trackHorizontal23,10,2);
        TrackHorizontal trackHorizontal24 = new TrackHorizontal();
        addObject(trackHorizontal24,9,2);
        TrackCurveTopLeft trackCurveTopLeft3 = new TrackCurveTopLeft();
        addObject(trackCurveTopLeft3,11,2);
        TrackVertical trackVertical7 = new TrackVertical();
        addObject(trackVertical7,11,1);
        TrackCurveLeftBottom trackCurveLeftBottom3 = new TrackCurveLeftBottom();
        addObject(trackCurveLeftBottom3,11,0);
        TrackHorizontal trackHorizontal25 = new TrackHorizontal();
        addObject(trackHorizontal25,10,0);
        TrackHorizontal trackHorizontal26 = new TrackHorizontal();
        addObject(trackHorizontal26,9,0);
        TrackHorizontal trackHorizontal27 = new TrackHorizontal();
        addObject(trackHorizontal27,8,0);
        TrackHorizontal trackHorizontal28 = new TrackHorizontal();
        addObject(trackHorizontal28,7,0);
        TrackHorizontal trackHorizontal29 = new TrackHorizontal();
        addObject(trackHorizontal29,6,0);
        TrackHorizontal trackHorizontal30 = new TrackHorizontal();
        addObject(trackHorizontal30,5,0);
        TrackHorizontal trackHorizontal31 = new TrackHorizontal();
        addObject(trackHorizontal31,4,0);
        TrackHorizontal trackHorizontal32 = new TrackHorizontal();
        addObject(trackHorizontal32,3,0);
        TrackHorizontal trackHorizontal33 = new TrackHorizontal();
        addObject(trackHorizontal33,2,0);
        TrackHorizontal trackHorizontal34 = new TrackHorizontal();
        addObject(trackHorizontal34,1,0);
        TrackCurveRightBottom trackCurveRightBottom3 = new TrackCurveRightBottom();
        addObject(trackCurveRightBottom3,0,0);
        TrackVertical trackVertical8 = new TrackVertical();
        addObject(trackVertical8,0,1);
        TrackVertical trackVertical9 = new TrackVertical();
        addObject(trackVertical9,0,2);
        TrackVertical trackVertical10 = new TrackVertical();
        addObject(trackVertical10,0,3);
        TrackVertical trackVertical11 = new TrackVertical();
        addObject(trackVertical11,0,4);
        TrackVertical trackVertical12 = new TrackVertical();
        addObject(trackVertical12,0,5);
        TrackVertical trackVertical13 = new TrackVertical();
        addObject(trackVertical13,0,6);
        TrackVertical trackVertical14 = new TrackVertical();
        addObject(trackVertical14,0,7);
        TrackVertical trackVertical15 = new TrackVertical();
        addObject(trackVertical15,0,8);
        TrackVertical trackVertical16 = new TrackVertical();
        addObject(trackVertical16,0,9);
        TrackCurveTopRight trackCurveTopRight3 = new TrackCurveTopRight();
        addObject(trackCurveTopRight3,0,10);
        TrackHorizontal trackHorizontal35 = new TrackHorizontal();
        addObject(trackHorizontal35,1,10);
        TrackHorizontal trackHorizontal36 = new TrackHorizontal();
        addObject(trackHorizontal36,2,10);
        TrackCurveTopLeft trackCurveTopLeft4 = new TrackCurveTopLeft();
        addObject(trackCurveTopLeft4,3,10);
        TrackCurveLeftBottom trackCurveLeftBottom4 = new TrackCurveLeftBottom();
        addObject(trackCurveLeftBottom4,3,9);
        TrackCurveTopRight trackCurveTopRight4 = new TrackCurveTopRight();
        addObject(trackCurveTopRight4,2,9);
        TrackCurveRightBottom trackCurveRightBottom4 = new TrackCurveRightBottom();
        addObject(trackCurveRightBottom4,2,8);
        TrackHorizontal trackHorizontal37 = new TrackHorizontal();
        addObject(trackHorizontal37,3,8);
        TrackHorizontal trackHorizontal38 = new TrackHorizontal();
        addObject(trackHorizontal38,4,8);
        TrackHorizontal trackHorizontal39 = new TrackHorizontal();
        addObject(trackHorizontal39,5,8);
        TrackHorizontal trackHorizontal41 = new TrackHorizontal();
        addObject(trackHorizontal41,7,8);
        TrackHorizontal trackHorizontal42 = new TrackHorizontal();
        addObject(trackHorizontal42,8,8);
        TrackHorizontal trackHorizontal43 = new TrackHorizontal();
        addObject(trackHorizontal43,9,8);
        TrackHorizontal trackHorizontal44 = new TrackHorizontal();
        addObject(trackHorizontal44,10,8);
        TrackHorizontal trackHorizontal45 = new TrackHorizontal();
        addObject(trackHorizontal45,11,8);
        TrackHorizontal trackHorizontal46 = new TrackHorizontal();
        addObject(trackHorizontal46,12,8);
        TrackHorizontal trackHorizontal47 = new TrackHorizontal();
        addObject(trackHorizontal47,13,8);
        TrackHorizontal trackHorizontal48 = new TrackHorizontal();
        addObject(trackHorizontal48,14,8);
        TrackCurveTopLeft trackCurveTopLeft5 = new TrackCurveTopLeft();
        addObject(trackCurveTopLeft5,15,8);
        TrackVertical trackVertical17 = new TrackVertical();
        addObject(trackVertical17,15,7);
        TrackVertical trackVertical18 = new TrackVertical();
        addObject(trackVertical18,15,6);
        TrackVertical trackVertical19 = new TrackVertical();
        addObject(trackVertical19,15,5);
        TrackVertical trackVertical20 = new TrackVertical();
        addObject(trackVertical20,15,4);
        TrackCurveLeftBottom trackCurveLeftBottom5 = new TrackCurveLeftBottom();
        addObject(trackCurveLeftBottom5,15,3);
        TrackHorizontal trackHorizontal49 = new TrackHorizontal();
        addObject(trackHorizontal49,14,3);
        TrackHorizontal trackHorizontal50 = new TrackHorizontal();
        addObject(trackHorizontal50,13,3);
        TrackHorizontal trackHorizontal51 = new TrackHorizontal();
        addObject(trackHorizontal51,12,3);
        TrackHorizontal trackHorizontal52 = new TrackHorizontal();
        addObject(trackHorizontal52,11,3);
        TrackHorizontal trackHorizontal53 = new TrackHorizontal();
        addObject(trackHorizontal53,10,3);
        TrackHorizontal trackHorizontal54 = new TrackHorizontal();
        addObject(trackHorizontal54,9,3);
        TrackHorizontal trackHorizontal55 = new TrackHorizontal();
        addObject(trackHorizontal55,8,3);
        TrackHorizontal trackHorizontal56 = new TrackHorizontal();
        addObject(trackHorizontal56,7,3);
        TrackHorizontal trackHorizontal57 = new TrackHorizontal();
        addObject(trackHorizontal57,6,3);
        TrackHorizontal trackHorizontal58 = new TrackHorizontal();
        addObject(trackHorizontal58,5,3);
        TrackHorizontal trackHorizontal59 = new TrackHorizontal();
        addObject(trackHorizontal59,4,3);
        TrackCurveRightBottom trackCurveRightBottom5 = new TrackCurveRightBottom();
        addObject(trackCurveRightBottom5,3,3);
        TrackCurveTopRight trackCurveTopRight5 = new TrackCurveTopRight();
        addObject(trackCurveTopRight5,3,4);
        TrackCurveTopRight trackCurveTopRight6 = new TrackCurveTopRight();
        addObject(trackCurveTopRight6,6,11);
        TrackHorizontal trackHorizontal60 = new TrackHorizontal();
        addObject(trackHorizontal60,8,11);
        TrackHorizontal trackHorizontal61 = new TrackHorizontal();
        addObject(trackHorizontal61,9,11);
        TrackHorizontal trackHorizontal62 = new TrackHorizontal();
        addObject(trackHorizontal62,10,11);
        TrackHorizontal trackHorizontal63 = new TrackHorizontal();
        addObject(trackHorizontal63,11,11);
        TrackHorizontal trackHorizontal64 = new TrackHorizontal();
        addObject(trackHorizontal64,12,11);

        TrackSwitchLeftBottom trackSwitchRightBottom = new TrackSwitchLeftBottom("ITabc123");
        addObject(trackSwitchRightBottom,6,8);
        TrackVertical trackVertical21 = new TrackVertical();
        addObject(trackVertical21,6,9);
        TrackVertical trackVertical22 = new TrackVertical();
        addObject(trackVertical22,6,10);
        TrackHorizontal trackHorizontal40 = new TrackHorizontal();
        addObject(trackHorizontal40,7,11);

        removeObject(trackHorizontal18);
        removeObject(trackVertical6);
        removeObject(trackVertical5);
        removeObject(trackHorizontal8);
        removeObject(trackHorizontal9);
    }
    
    private void initTrackEnvironments() {
        // Initialize tracks' environment
        List<Track> allTracks = getObjects(Track.class);
        for(Track currentTrack : allTracks) {
            currentTrack.findNeighbouringTracks();
        }
    }
}
