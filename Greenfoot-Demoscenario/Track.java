import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Gepräsentiert ein Stück Gleis, d.h. ein Objekt, auf dem sich RailVehicle bewegen können.<br>
 * Tracks übernehmen die Steuerung der Richtung eines Trains: In Abhängigkeit von Art und Zustand
 * eines Tracks wird der bewegte Train in eine bestimmte Richtung geleitet.
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public abstract class Track extends Actor
{   
    /**
     * Der Typ des Tracks, bspw. HORIZONTAL, VERTICAL oder eine Art von CURVE oder SWITCH
     */
    private TrackType trackType;

    // 0: top, 1: right, 2: bottom, 3: left
    /**
     * Ein Array, das die benachbarten Tracks speichert, um bei der Fahrt eines Trains die
     * einzelnen RailVehicle ggf. auf diese Tracks weiterleiten zu können.<br>
     * Index 0: top, 1: right, 2: bottom, 3: left
     */
    private Track[] neighbouringTracks;
    
    /**
     * Erzeugt einen Track mit gegebenem TrackType
     * @param trackType Der TrackType des Tracks
     */
    public Track(TrackType trackType) {
        this.trackType = trackType;
    }
    
    /**
     * Bestimmt, auf welchen anderen Track ein RailVehicle in Abhängigkeit von seiner momentanen Direction
     * weitergeleitet wird.
     * @param movingInDirection Die Bewegungsrichtung des fahrenden RailVehicle
     * @return Der benachbarte und mit diesem Track verbundene andere Track, auf den das RailVehicle in
     * Abhängigkeit von seiner momentanen Direction weitergeleitet wird.
     */
    public abstract Track determineNextTrack(Direction movingInDirection);
    
    /**
     * Gibt die Direction zurückgibt, die ein RailVehicle auf dem Track haben muss, um in die entgegengesetzte Richtung geleitet zu werden.<br>
     * <b>Achtung:</b> Die Richtung wird nicht einfach zur entgegengesetzten Richtung geändert, sondern so, dass
     * der Track das darauf befindliche RailVehicle rückwärts leitet. Beispiel: Die Richtung wird
     * auf einem Kurven-Track invertiert, der BOTTOM und RIGHT verbindet. In diesem Fall wird die Richtung LEFT
     * invertiert zu einer Bewegung TOP, sodass die Kurve statt einer Weiterleitung nach BOTTOM eine Weiterleitung
     * nach RIGHT gewährleistet.
     * @param movingDirection   Die Bewegungsrichtung des RailVehicle auf dem Track, die umgekehrt werden soll
     * @return Die Direction, die das RailVehicle auf dem Track haben muss, um in die entgegengesetzte Richtung geleitet zu werden.
     */
    public abstract Direction getReversedDirection(Direction movingDirection);
    
    /**
     * Gibt den TrackType des Tracks zurück
     * @return Der TrackType des Tracks
     */
    public TrackType getTrackType() {
        return this.trackType;
    }
    
    /**
     * Initialisiert und füllt das neighbouringTracks-Array mit den Tracks, die über, unter oder neben (nicht aber diagonal)
     * des Tracks liegen; falls dort solche sind.
     * 
     * Diese Methode muss aufgerufen werden, bevor Schienenfahrzeuge sich über den Track bewegen sollen, d.h. nach Platzieren
     * sämtlicher Tracks in der Welt, jedoch bevor sich irgendetwas über Tracks bewegen soll.
     */
    public void findNeighbouringTracks() {
        neighbouringTracks = new Track[4];
        
        Object trackTop = getOneObjectAtOffset(0, -1, Track.class);
        Object trackRight = getOneObjectAtOffset(1, 0, Track.class);
        Object trackBottom = getOneObjectAtOffset(0, 1, Track.class);
        Object trackLeft = getOneObjectAtOffset(-1, 0, Track.class);
        
        if(trackTop != null) {
            neighbouringTracks[0] = (Track) trackTop;
        }
        if(trackRight != null) {
            neighbouringTracks[1] = (Track) trackRight;
        }
        if(trackBottom != null) {
            neighbouringTracks[2] = (Track) trackBottom;
        }
        if(trackLeft != null) {
            neighbouringTracks[3] = (Track) trackLeft;
        }
    }
    
    /**
     * Gibt an, ob der Track einen benachbarten und verbundenen Track in der gegebenen Richtung hat.
     * @param dir   Die gegebene Richtung, in der ein benachbarter Track vermutet wird
     * @return true, falls sich in der gegebenen Richtung ein Track befindet, der mit diesem Track verbunden ist; false andernfalls
     */    
    public boolean hasNeighbourAt(Direction dir) {
        return neighbouringTracks[dir.getId()] != null && isConnectedWith(neighbouringTracks[dir.getId()]);
    }

    /**
     * Gibt den benachbarten und verbundenen Track in der gegebenen Richtung zurück.
     * @param dir   Die gegebene Richtung, in der der benachbarte Track liegen soll
     * @return Den benachbarten und verbundenen Track in der gegebenen Richtung, falls dort ein solcher liegt; null andernfalls
     */ 
    public Track getNeighbourAt(Direction dir) {
        if(hasNeighbourAt(dir)) {
            return neighbouringTracks[dir.getId()];
        }
        return null;
    }
    
    /**
     * Gibt die Richtung zurück, in der der gegebene benachbarte und verbundene Track liegt.
     * @param other Der benachbarte und verbundene Track, dessen Richtung bestimmt werden soll
     * @return Die Richtung, in der der gegebene benachbarte und verbundene Track liegt;
     * null, falls der gegebene Track nicht benachbart und verbunden ist
     */
    public Direction getDirectionOf(Track other) {
        for(Direction dir : Direction.values()) {
            if(this.hasNeighbourAt(dir) && this.getNeighbourAt(dir).equals(other)) {
                return dir;
            }
        }
        return null;
    }
    
    /**
     * Gibt an, ob der Track mit dem gegebenen benachbarten Track verbunden ist. Dabei kommt es auf die
     * Art des Tracks des an. Beispielsweise ist eine RIGHT-BOTTOM-Kurve mit den Tracks rechts davon
     * sowie darunter verbunden, jedoch nicht mit etwaigen Tracks, die direkt darüber oder direkt links daneben liegen.
     * @param   Der benachbarte Track, dessen Verbindung mit diesem Track geprüft werden soll
     * @return true, falls der gegebene Track mit diesem Track verbunden ist; false sonst.
     */
    public boolean isConnectedWith(Track other) {
        if(other == null) {
            return false;
        }
        
        if( (this.trackType.getConnectsLeft() && other.trackType.getConnectsRight() && this.getX() - 1 == other.getX() && this.getY() == other.getY())
        ||  (this.trackType.getConnectsRight() && other.trackType.getConnectsLeft() && this.getX() + 1 == other.getX() && this.getY() == other.getY())
        ||  (this.trackType.getConnectsTop() && other.trackType.getConnectsBottom() && this.getX() == other.getX() && this.getY() - 1 == other.getY())
        ||  (this.trackType.getConnectsBottom() && other.trackType.getConnectsTop() && this.getX() == other.getX() && this.getY() + 1 == other.getY())  ) {
            return true;
        }
        return false;
    }
    
    /**
     * Gibt an, ob sich im Moment des Aufrufs ein RailVehicle auf dem Track befindet.
     * @return true, falls sich im Moment des Aufrufs ein RailVehicle auf dem Track befindet; false andernfalls
     */
    public boolean isOccupiedByRailVehicle() {
        return !this.getIntersectingObjects(RailVehicle.class).isEmpty();
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