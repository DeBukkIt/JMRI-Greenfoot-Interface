/**
 * Enum Direction, das die Richtungen aufzählt, in die RailVehicle sich
 * bewegen können oder in denen benachbarte Tracks liegen.
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public enum Direction  
{
    TOP(0), RIGHT(1), BOTTOM(2), LEFT(3);
    
    /**
     * Die Identifikationsnummer der Direction.<br>
     * 0: top, 1: right, 2: bottom, 3: left
     */
    private int id;
    
    /**
     * Erzeugt eine Direction mit gegebener Identifikationsnummer.
     * @param id    Die Identifikationsnummer der Direction
     */
    Direction(int id) {
        this.id = id;
    }
    
    /**
     * Gibt die Identifikationsnummer der Direction zurück.
     * @return Die Identifikationsnummer der Direction
     */
    public int getId() {
        return id;
    }
}
