/**
 * Enum TrackType, das die Typen von Tracks aufzählt. Ein TrackType bestimmt die Directions
 * eines Track, in denen damit verbundene Tracks liegen können, d.h. die Anknüpfungpunkte des Tracks eines bestimmten TrackType<br>
 * Beispiel: Der TrackType HORIZONTAL gibt LEFT und RIGHT als seine Anknüpfungpunkte an. In diesen beiden Richtungen
 * können Tracks mit diesem Track verbunden werden. Darüber und darunter (TOP, BOTTOM) liegende Tracks können keine
 * Verbindung ausbilden.
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public enum TrackType  
{
    HORIZONTAL(false, true, false, true),
    VERTICAL(true, false, true, false),
    
    CURVE_LEFTBOTTOM(false, false, true, true),
    CURVE_TOPLEFT(true, false, false, true),
    CURVE_TOPRIGHT(true, true, false, false),
    CURVE_RIGHTBOTTOM(false, true, true, false),
    
    SWITCH_BOTTOMLEFT(true, false, true, true),
    SWITCH_BOTTOMRIGHT(true, true, true, false),
    SWITCH_LEFTBOTTOM(false, true, true, true),
    SWITCH_LEFTTOP(true, true, false, true),
    
    SWITCH_RIGHTBOTTOM(false, true, true, true),
    SWITCH_RIGHTTOP(true, true, false, true),
    SWITCH_TOPLEFT(true, false, true, true),
    SWITCH_TOPRIGHT(true, true, true, false);
    
    /**
     * Gibt an, ob ein Track mit diesem TrackType eine Verbindung zu einem darüber (TOP) liegenden Track ausbilden kann
     */
    private boolean connectsTop;
    /**
     * Gibt an, ob ein Track mit diesem TrackType eine Verbindung zu einem rechts (RIGHT) davon liegenden Track ausbilden kann
     */
    private boolean connectsRight;
    /**
     * Gibt an, ob ein Track mit diesem TrackType eine Verbindung zu einem darunter (BOTTOM) liegenden Track ausbilden kann
     */
    private boolean connectsBottom;
    /**
     * Gibt an, ob ein Track mit diesem TrackType eine Verbindung zu einem links (LEFT) davon liegenden Track ausbilden kann
     */
    private boolean connectsLeft;
    
    /**
     * Erzeugt einen TrackType unter Angabe der Richtungen, in denen damit verbundene Tracks liegen können, d.h. die Anknüpfungpunkte des Tracks dieses TrackType.
     * @param connectsTop       Gibt an, ob Tracks dieses TrackType mit darüber (TOP) liegenden Tracks verbunden sein sollen
     * @param connectsRight     Gibt an, ob Tracks dieses TrackType mit rechts (RIGHT) davon liegenden Tracks verbunden sein sollen
     * @param connectsBottom    Gibt an, ob Tracks dieses TrackType mit darüber (BOTTOM) liegenden Tracks verbunden sein sollen
     * @param connectsLeft      Gibt an, ob Tracks dieses TrackType mit links (LEFT) davon liegenden Tracks verbunden sein sollen
     */
    TrackType(boolean connectsTop, boolean connectsRight,
                boolean connectsBottom, boolean connectsLeft) {
      this.connectsTop = connectsTop;
      this.connectsRight = connectsRight;
      this.connectsBottom = connectsBottom;
      this.connectsLeft = connectsLeft;
    }
    
    /**
     * Gibt an, ob ein Track mit diesem TrackType eine Verbindung zu einem darüber (TOP) liegenden Track ausbilden kann.
     * @return true, falls ein Track mit diesem TrackType eine Verbindung zu einem darüber (TOP) liegenden Track ausbilden kann
     */
    public boolean getConnectsTop() {
        return connectsTop;
    }
    
    /**
     * Gibt an, ob ein Track mit diesem TrackType eine Verbindung zu einem rechts (RIGHT) davon liegenden Track ausbilden kann.
     * @return true, falls ein Track mit diesem TrackType eine Verbindung zu einem rechts (RIGHT) davon liegenden Track ausbilden kann
     */
    public boolean getConnectsRight() {
        return connectsRight;
    }
    
    /**
     * Gibt an, ob ein Track mit diesem TrackType eine Verbindung zu einem darunter (BOTTOM) liegenden Track ausbilden kann.
     * @return true, falls ein Track mit diesem TrackType eine Verbindung zu einem darunter (BOTTOM) liegenden Track ausbilden kann
     */
    public boolean getConnectsBottom() {
        return connectsBottom;
    }
    
    /**
     * Gibt an, ob ein Track mit diesem TrackType eine Verbindung zu einem links (LEFT) davon liegenden Track ausbilden kann.
     * @return true, falls ein Track mit diesem TrackType eine Verbindung zu einem links (LEFT) davon liegenden Track ausbilden kann
     */
    public boolean getConnectsLeft() {
        return connectsLeft;
    }
    
}