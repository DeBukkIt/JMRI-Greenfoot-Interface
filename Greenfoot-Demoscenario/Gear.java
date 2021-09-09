/**
 * Enum Gear, das die Gänge aufzählt, die ein Train haben kann: Vorwärts und Rückwärts.
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public enum Gear  
{
    FORWARD(1), BACKWARD(-1);
 
    /**
     * Der Faktor, der die Ausprägung des Gear bestimmt.
     */
    private int multiplier;
    
    /**
     * Erzeugt einen Gear mit gegebenem Faktor, der seine Ausprägung bestimmt.
     * @param multiplier    Der Faktor, der die Ausprägung des Gear bestimmt
     */
    Gear(int multiplier) {
        this.multiplier = multiplier;
    }    
}
