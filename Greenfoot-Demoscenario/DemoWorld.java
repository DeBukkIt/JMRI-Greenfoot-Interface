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
                if(focusedActor != null && focusedActor instanceof Turnout) {
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
        
    }
    
    private void initTrackEnvironments() {
        // Initialize tracks' environment
        List<Track> allTracks = getObjects(Track.class);
        for(Track currentTrack : allTracks) {
            currentTrack.findNeighbouringTracks();
        }
    }
}
