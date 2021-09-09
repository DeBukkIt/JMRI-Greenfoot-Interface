import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import java.lang.Exception;
import java.lang.Comparable;

/**
 * Repräsentation eines Waggons, d.h. einem RailVehicle, das als Teil eines Trains auf Tracks von einer Locomotive geschoben oder gezogen werden kann.
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public class Wagon extends RailVehicle implements Comparable
{    
    /**
     * Gibt die Farbe des Wagons als Zeichenkette an, so wie sie im Dateinamen
     * des zugehörigen Images anzutreffen ist.
     */
    private String colorName = "";
    /**
     * Gibt den Kupplungs-Zustand des Wagons als Zeichenkette an, so wie er im
     * Dateinamen des zugehörigen Images anzutreffen ist.
     */
    private String coupleState = "";
    
    /**
     * Der Inhalt des Wagons, der in der Welt als Schriftzug auf dem Wagon sichtbar wird
     */
    private int content = 0;
    
    /**
     * Erzeugt einen Wagon. Die Bewegungsrichtung ist RIGHT, die Farbe ist BLUE.
     */
    public Wagon(int content) {
        this(content, WagonColor.BLUE, Direction.RIGHT);
    }
    
    /**
     * Erzeugt einen Wagon mit gegebener initialer Bewegungsrichtung.
     * @param initialDirection Die gegebene initiale Bewegungsrichtung
     */
    public Wagon(int content, WagonColor color, Direction initialDirection) {
        super();
        this.setColor(color);
        this.setDirection(initialDirection); 
        this.setContent(content);       
    }
    
    /**
     * Setzt den Inhalt des Wagons und aktualisiert die entsprechende Beschriftung
     * des Wagon-Bildes in der World.
     * @param content Inhalt des Wagons
     */
    public void setContent(int content) {
        this.content = content;
        this.updateImage();
    }
    
    /**
     * Gibt den Inhalt des Wagons zurück
     * @return Inhalt des Wagons
     */
    public int getContent() {
        return this.content;
    }
    
    /**
     * Bestimmt die Farbe des Wagons
     * @param Die Farbe des Wagons als Element des Enums <code>WagonColor</code>
     */
    private void setColor(WagonColor color) {
        this.colorName = color.name().toLowerCase();
        updateImage();
    }
    
    /**
     * Aktualisiert das angezeigte Image des Wagons in Abhängigkeit von der gewählten
     * Farbe und dem Kupplungs-Zustand (angekuppelt, abgekuppelt).
     */
    private void updateImage() {
        // Draw image
        this.setImage("wagon_" + colorName + coupleState + ".png");
        
        // Draw content as String
        this.getImage().setFont(new Font(true, false, 18));
        int x = (64 / 2) - (5 * String.valueOf(content).length());
        int y = 48;
        this.getImage().drawString(String.valueOf(content), x, y);
    }
    
    @Override
    public void onCouple() {
        this.coupleState = "";
        updateImage();
    }
    
    @Override
    public void onDecouple() {
        this.coupleState = "_decoupled";
        updateImage();
    }
    
    /**
     * Vergleicht den Inhalt dieses Wagons mit dem des übergebenen Wagons
     * @param otherObject Der andere Wagon
     * @return 0, falls beide Wagons denselben Inhalt haben; -1, falls der Inhalt
     * des übergeben Waggons größer ist; +1, falls der Inhalt dieses Wagons größer
     * ist als der Inhalt des übergebenen Wagons.
     * @throws RuntimeException, falls es ein Problem mit dem übergeben {@link java.lang.Object} gibt
     */
    @Override
    public int compareTo(Object otherObject) {
        try {
            Wagon otherWagon = (Wagon) otherObject;
            return Integer.valueOf(this.getContent()).compareTo(otherWagon.getContent());
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    
    /**
     * Diese Methode wird vom Greenfoot-Framework aufgerufen, um Actors die Möglichkeit
     * zu geben, eine Aktion auszuführen. Bei jedem Aktionsschritt in der Umgebung wird die
     * act-Methode jedes Objekts aufgerufen, in nicht spezifizierter Reihenfolge. Die
     * Standardimplementierung tut nichts. Diese Methode kann in Unterklassen
     * überschrieben werden, um die Aktion eines Akteurs zu implementieren.<br><br>
     * Die Implementierung dieser Methode in Wagon tut ebenfalls nichts, denn dder Wagon
     * soll nicht durch ihre Act-Methode gesteuert werden.
     */
    @Override
    public void act() 
    {
        // Wagons do not act on their own
    }
}