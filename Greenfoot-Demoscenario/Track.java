import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import de.wwu.jmrigreenfootinterface.*;
import java.net.ConnectException;

/**
 * Gepräsentiert ein Stück Gleis
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public abstract class Track extends Actor
{   
    protected String layoutBlockId = null;
    protected String imageBaseName = null;
    
    public Track(String imageBaseName) {
        this(imageBaseName, null);
    }
    
    public Track(String imageBaseName, String layoutBlockId) {
        this.imageBaseName = imageBaseName;
        this.layoutBlockId = layoutBlockId;
    }
    
    public void setLayoutBlock(String layoutBlockId) {
        this.layoutBlockId = layoutBlockId;
    }
    
    public String getLayoutBlock() {
        return layoutBlockId;
    }
    
    protected boolean isBlockActive() {
        if(layoutBlockId == null || layoutBlockId.isEmpty()) {
            return false;
        }
        
        try {
            int result = (int) JMRI.getInterface().getProperty("layoutBlocks", layoutBlockId, "state");
            return result == 2 ? true : false;
        } catch(Exception e) {
            return false;
        }
    }
    
    public abstract void updateImage();
    
    @Override
    protected void addedToWorld(World world) {
        // Uncomment the following line to display the state of the tracks even before starting the
        // scenario via the Play or Act button. Attention: A connection with JMRI will then be
        // attempted even before such a click. If this fails, error messages are displayed before
        // the scenario has been started.
        
        //updateImage();
    }
    
    /**
     * Diese Methode wird vom Greenfoot-Framework aufgerufen, um Actors die Möglichkeit
     * zu geben, eine Aktion auszuführen. Bei jedem Aktionsschritt in der Umgebung wird die
     * act-Methode jedes Objekts aufgerufen, in nicht spezifizierter Reihenfolge. Die
     * Standardimplementierung tut nichts. Diese Methode kann in Unterklassen
     * überschrieben werden, um die Aktion eines Akteurs zu implementieren.<br><br>
     * Die Implementierung dieser Methode in Track tut ebenfalls nichts, denn ein
     * Track soll nicht agieren.<br><br>
     * Ein Überschreiben dieser Methode kann in Erwägung gezogen werden, um beispielsweise
     * Animationen (blinkende Lichter etc.) zu realisieren.
     */
    @Override
    public void act() 
    {
        // tracks do not act
    }  
    
    /**
     * Ein Track bewegt sich nicht; deshalb geschieht beim Aufruf dieser Methode nichts.
     */
    @Override
    public void move(int distance) {
        // tracks do not move
    }
}