import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Gepräsentiert ein Stück Gleis
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public abstract class Track extends Actor
{   
    protected String layoutBlockId;    
    
    public Track() {
        this.layoutBlockId = null;
    }
    
    public Track(String layoutBlockId) {
        this.layoutBlockId = layoutBlockId;
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