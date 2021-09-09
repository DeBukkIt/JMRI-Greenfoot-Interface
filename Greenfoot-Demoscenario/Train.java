import java.util.List;
import java.util.ArrayList;

/**
 * Repräsentiert einen Zug, d.h. eine Locomotive sowie eine Menge weiterer RailVehicle,
 * die über Tracks bewegt werden sollen. Train hält die Methoden bereit, die die Bewegung
 * der RailVehicle über die Tracks vornehmen und dabei Hinternisse usw. berücksichtigen.<br>
 * Locomotive, Wagon und weitere RailVehicle sind dabei nahezu ausschließlich Entitäten ohne
 * eigenes Verhalten. Das Verhalten wird durch Train bestimmt, indem die Attribute der RailVehicle
 * mit Rücksicht auf die Umgebung (World, Tracks, RailVehicle ohne Zugehörigkeit zum Train)
 * manipuliert werden.
 * 
 * @author Leonard Bienbeck
 * @version 1.0.0
 */
public class Train  
{
    /**
     * Die Locomotive, die den Train bewegt.
     */
    private Locomotive loc;
    /**
     * Eine geordnete Liste von RailVehicles, die an der Locomotive hängen und von ihr geschoben
     * oder gezogen werden. Das RailVehicle mit dem kleinsten Index ist am nächsten an der Locomotive.
     */
    private List<RailVehicle> vehicles;
    
    /**
     * Der Gear des Trains repräsentiert seinen Gang: Vorwärts (mit der Locomotive voran) oder rückwärts
     * (mit dem letzten RailVehicle voran, das nicht die Locomotive ist).
     */
    private Gear gear;

    /**
     * Gibt an, ob der Train verunfallt ist, beispielsweise durch Kollision mit einem nicht zum Train
     * gehörenden RailVehicle oder durch Abkommen vom Schienennetz.
     */
    private boolean isCrashed;
    
    /**
     * Erzeugt einen neuen, leeren Train. Der Vorwärtsgang ist eingelegt.
     */
    private Train() {
        this.vehicles = new ArrayList<RailVehicle>();
        
        this.gear = Gear.FORWARD;
    }
    
    /**
     * Erzeugt einen neuen Train mit der gegebenen Locomotive. Der Vorwärtsgang ist eingelegt.
     * @param loc   Die Locomotive, die den Train bewegen soll.
     */
    public Train(Locomotive loc) {
        this();        
        this.loc = loc;
    }
    
    /**
     * Erzeugt eine neuen Train mit gegebener Locomotive und beliebig vielen RailVehicles.
     * @param loc       Die Locomotive, die den Train bewegen soll.
     * @param vehicles  Ein Array von RailVehicles, die an der Locomotive hängen sollen in
     * geordneter Reihenfolge
     */
    public Train(Locomotive loc, RailVehicle... vehicles) {
        this(loc);        
        for(RailVehicle rv : vehicles) {
            if(rv != null) {
                this.vehicles.add(rv);
            }
        }
    }
    
    /**
     * Kehrt die Fahrtrichtung um, d.h. wechselt vom Vorwärts- in den Rückwärtsgang (Gear)
     * und umgekehrt.
     */
    public void reverseGear() {
        switch(gear) {
            case FORWARD: this.setGear(Gear.BACKWARD); break;
            case BACKWARD: this.setGear(Gear.FORWARD); break;
        }
    }
    
    /**
     * Setzt die Fahrtrichtung durch Festlegen des Gear; dies führt gleichzeitig zur
     * Invertierung der Bewegungsrichtung von Locomotive und sämtlicher angehängter RailVehicle,
     * jeweils in Abhängigkeit von den jeweils darunter befindlichen Tracks.
     * @param newGear   Der zu setzende Gear
     */
    public void setGear(Gear newGear) {
        if(this.gear != newGear) {
            this.gear = newGear;
            // Invert direction of all train members
            loc.invertDirection(newGear);
            for(RailVehicle wagon : this.vehicles) {
                wagon.invertDirection(newGear);
            }
        }
    }
    
    /**
     * Gibt den Gear an, der der Train derzeit inne hat.
     */
    public Gear getGear() {
        return this.gear;
    }
    
    /**
     * Entfernt die Locomotive aus dem Train; das entsprechende Attribut verweist anschließend auf null.
     */
    public void removeLoc() {
        this.setLoc(null);
    }
    
    /**
     * Setzt die Locomotive des Trains.
     * @param loc   Die Locomotive, die fortan den Zug anführen soll
     */
    public void setLoc(Locomotive loc) {
        this.loc = loc;
    }
    
    /**
     * Gibt die führende Locomotive des Trains zurück.
     * @return Die führende Locomotive des Trains
     */
    public Locomotive getLoc() {
        return this.loc;
    }
    
    /**
     * Gibt an, ob der Zug eine führende Locomotive besitzt, d.h. ob das entsprechende Attribut nicht null ist.
     * @return true, falls der Zug eine Locomotive besitzt; false andernfalls
     */
    public boolean hasLoc() {
        return this.getLoc() != null;
    }
    
    /**
     * Fügt ein RailVehicle am Ende des Trains hinzu. Dies ist beispielsweise beim Ankuppeln eines Wagons hilfreich.
     * @param vehicle   Das hinzuzufügende RailVehicle
     */
    private void addVehicle(RailVehicle vehicle) {
        if(vehicle != null) {
            this.vehicles.add(vehicle);
        }
    }

    /**
     * Entfernt das RailVehicle mit dem entsprechenden Index. i=0 ist das erste RailVehicle hinter der Locomotive.
     * <b>Achtung:<b> Hierbei wird keine Rücksicht darauf genommen, dass gegebenenfalls eine Lücke im Train entsteht.
     * Mit dieser Methode wird kein realistisches Abkuppeln durchgeführt, sodass anschließend der Index der RailVehicle
     * sich von der Darstellung des Train auf dem Bildschirm unterscheiden kann.
     * @param index Der Index des RailVehicle, das entfernt werden soll
     */
    private void removeVehicle(int index){
        if(index >= 0) {
            this.vehicles.remove(index);
        }
    }
    
    /**
     * Gibt das RailVehicle am gegeben Index zurück.
     * @param index Der Index
     * @return Das RailVehicle am gegebenen Index; oder null, falls der Index außerhalb der Begrenzungen der Liste von RailVehicles liegt
     */
    public RailVehicle getVehicle(int index) {
        if(index < 0 || index >= this.vehicles.size()) {
            return null;
        }
        return this.vehicles.get(index);
    }
    
    /**
     * Gibt an, ob der Train RailVehicles (zusätzlich zur führenden Locomotive) besitzt, d.h. ob die Anzahl
     * der an die Locomotive angehängten RailVehicles <code>&gt; 0</code> ist.
     * @return true, falls der Zug RailVehicles (zusätzlich zur führenden Locomotive) besitzt; false sonst
     */
    public boolean hasVehicles() {
        return this.getNumberOfVehicles() > 0;
    }
    
    /**
     * Gibt die Anzahl der angehängten RailVehicle im Train zurück.
     * @return Die Anzahl der angehängten RailVehicle im Train
     */
    public int getNumberOfVehicles() {
        return this.vehicles.size();
    }
    
    /**
     * Gibt das erste RailVehicle im Train zurück, also das direkt an die führende Locomotive angehängte RailVehicle.
     * @return Das erste RailVehicle im Train
     */
    public RailVehicle getFirstVehicle() {
        return this.vehicles.get(0);
    }
    /**
     * Gibt das letzte RailVehicle im Train zurück, also das am weitesten von der führenden Locomotive befindliche
     * RailVehicle, das zum Train gehört. Diesem RailVehicle kommt besondere Bedeutung zu, weil es bei einer Fahrt
     * rückswärts von den darunter befindlichen Gleisen geführt wird und damit die Richtung aller anderen Teile des
     * Zugs (einschließlich der führenden Locomotive) vorgibt.
     * @return Das letzte RailVehicle im Train
     */
    public RailVehicle getLastVehicle() {
        return this.vehicles.get(this.vehicles.size() - 1);
    }
    
    /**
     * Gibt das führende, in Fahrtrichtung vorderste RailVehicle (darunter auch die Locomotive) des Trains zurück.
     * In Abhängigkeit vom Gear, der die Fahrtrichtung (Vorwärts/Rückwärts) bestimmt, wird entweder die führende
     * Locomotive oder das letzte RailVehicle im Train (falls ein solches Existiert) zurückgegeben. Existiert kein
     * solches, wird die führende Locomotive auch bei Fahrt in rückwärtige Richtung zurückgegeben.
     * @return Das führende, in Fahrtrichtung vorderste RailVehicle (darunter auch die Locomotive) des Trains
     */
    public RailVehicle getLeadingVehicle() {
        switch(gear) {
            case FORWARD: return loc;
            case BACKWARD: return hasVehicles() ? getLastVehicle() : loc;
            default: return loc;
        }
    }
    
    /**
     * Wird bei einem Unfall des Trains aufgerufen und setzt das isCrashed-Attribut auf true.
     */
    public void onCrash() {
        this.setCrashed(true);
    }
    
    /**
     * Setzt das isCrashed-Attribut auf den gegebenen Wert.
     * @param Gibt an, ob der Train verunfallt ist: true, falls ja; false andernfalls
     */
    public void setCrashed(boolean isCrashed) {
        this.isCrashed = isCrashed;
    }
    
    /**
     * Gibt an, ob der Train verunfallt ist.
     * @return true, falls ja; false andernfalls
     */
    public boolean isCrashed() {
        return this.isCrashed;
    }
    
    /**
     * Kuppelt ein RailVehicle an den Train an. Dabei wird das RailVehicle angekuppelt, das sich auf dem Track direkt hinter dem
     * letzten, bereits verbundenen RailVehicle des Trains befindet. Bedingungen sind, dass das anzukuppelnde RailVehicle noch
     * nicht Teil des Trains ist (selbstverständlich, denn sonst kommt es gar nicht als Kandidat zum Kuppeln in Betracht), dass
     * es auf einem Track steht, dass es sich neben, oberhalb oder unterhalb (nicht diagonal) vom letzten, bereits verbundenen
     * RailVehicle des Trains befindet und dass der Track, auf dem es steht, mit dem Track verbunden ist, auf dem das Ende des
     * Trains steht.<br>
     * Nach dem Aufruf ist ein unter den o.g. Bedingungen das RailVehicle Teil des Trains und wird als Teil von ihm bewegt.
     */
    public void couple() {
        // Couples a new wagon next to the last wagon of the train (or the loc, if there is no wagon already)
        // Conditions:
        // - new wagon is next to the last wagon
        // - new wagon is on a side of the last wagon, where not other wagon or loc is already, i.e. it's not already part of the train
        // - track below new wagon is connected to the track of the last wagon
        // Be aware of:
        // - on switches there might be multiple wagons fulfilling these conditions
        // - if something like orientation is implemented, couple the wagon on the available side of the wagon (also solves switch problem)
                
        RailVehicle couplingVehicle = this.hasVehicles() ? getLastVehicle() : getLoc();
        
        List<RailVehicle> nearbyVehicles = couplingVehicle.getNearbyRailVehicles();
        for(RailVehicle candidate : nearbyVehicles) {
            if(!this.vehicles.contains(candidate) && !this.getLoc().equals(candidate)) {
                if(candidate.getTrackBelow() != null) {
                    if(couplingVehicle.getTrackBelow().isConnectedWith(candidate.getTrackBelow())) {
                        this.addVehicle(candidate);
                        candidate.onCouple();
                        break;
                    }
                }
            }
        }
    }
    
    /**
     * Kehrt das Ankuppeln um, indem das letzte RailVehicle (außer der führenden Locomotive) vom Train abgetrennt
     * wird. Anschließend ist es nicht mehr Teil des Trains und wird von diesem nicht mehr bewegt. Falls kein
     * RailVehicle mehr Teil des Trains ist, ist der Aufruf dieser Methode ohne Folgen.
     */
    public void decouple() {
        if(this.hasVehicles()) {
            this.getVehicle(this.getNumberOfVehicles() - 1).onDecouple();
            this.removeVehicle(this.getNumberOfVehicles() - 1);
        }
    }
    
    /**
     * Bewegt den Train mit all seinen Komponenten über das Schienennetz aus Tracks. Bestimmend sind der Gear und
     * damit die Bewegungsrichtung und damit wiederum das <i>leading RailVehicle</i> (das entsprechende Ende des Trains).
     * In Abhängigkeit von dem Track, der sich unter dem <i>leading RailVehicle</i> befindet, wird dieses auf einen
     * hoffentlich vorhandenen, anderen Track weitergeleitet. Dieser nächste Track wird in Abhängigkeit davon ausgewählt,
     * in welche Direction (TOP, RIGHT, BOTTOM, LEFT) sich das <i>leading RailVehicle</i> bisher bewegte und in welche
     * Richtung es auf Grund dessen vom aktuellen darunter befindlichen Track geleitet wird. Eine Kurve wird diese
     * Richtung verändern, eine Weiche (Switch) in Abhängigkeit von ihrer momentanen Stellung.<br>
     * Nachdem das <i>leading RailVehicle</i> so bewegt worden ist, folgen alle anderen Teile des Trains sukzessive und
     * nehmen dabei den Platz des jeweiligen Vorgängers (in Bewegungsrichtung) ein. Ein Zerteilen oder Entgleisen des
     * Trains an einer Switch, die gestellt wird, während ein Train darauf fährt, geschieht deshalb nicht.<br>
     * Der Train bewegt sich nur, falls der nicht verunfallt ist. Er verunfallt, sobald das <i>leading RailVehicle</i>
     * keinen Track unter sich hat oder falls der zu befahrende Track von einem anderen RailVehicle blockiert ist.
     */
    public void move() {
        /* if(isCrashed()) {
            System.err.println("Train can't move, it's crashed");
            return;
        } */
        
        // Determine leading vehicle, current and next leading track and direction of next leading track
        RailVehicle leadingVehicle = this.getLeadingVehicle();
        Track currentLeadingTrack = leadingVehicle.getTrackBelow();
        Track nextLeadingTrack = currentLeadingTrack.determineNextTrack(leadingVehicle.getDirection());
        Direction nextLeadingDirection = currentLeadingTrack.getDirectionOf(nextLeadingTrack);
        
        // Crash if necessary
        if(nextLeadingTrack == null) {
            System.err.println("The train has left the track");
            onCrash();
        } else if(nextLeadingTrack.isOccupiedByRailVehicle()) {
            System.err.println("The train hit a rail vehicle");
            onCrash();
        } else {
            // Depending on gear
            if(gear == Gear.FORWARD) {
                // Only move vehicles if there are any at all
                if(this.hasVehicles()) {
                    for(int i = this.vehicles.size() - 1; i > 0; i--) {
                        this.getVehicle(i).moveTo(this.getVehicle(i-1).getX(), this.getVehicle(i-1).getY(), gear);
                    }
                    this.getFirstVehicle().moveTo(leadingVehicle.getX(), leadingVehicle.getY(), gear);
                }
            } else /* gear = Gear.BACKWARD */ {
                // If there is at least one more vehicle in the train than just the loc, move that vehicle)
                if(this.getNumberOfVehicles() > 0) {
                    this.getLoc().moveTo(this.getFirstVehicle().getX(), this.getFirstVehicle().getY(), gear);
                }
                // If there are more vehicles (except leading wagon and the loc), move them, too
                if(this.getNumberOfVehicles() > 1) {                    
                    for(int i = 0; i < this.vehicles.size() - 1; i++) {
                        this.getVehicle(i).moveTo(this.getVehicle(i+1).getX(), this.getVehicle(i+1).getY(), gear);
                    }
                }
            }
            // Move leading vehicle
            leadingVehicle.moveTo(nextLeadingTrack.getX(), nextLeadingTrack.getY(), gear);
            
            // Update leading vehicle with new direction (the one of the next leading track)
            leadingVehicle.setDirection(nextLeadingDirection);
        }
    }
    
    /**
     * Gibt an, ob der nächste zu befahrende Track (bei Fortsetzung der Fahrt mit aktuellen Einstellungen,
     * z. B. aktuellem Gear) von einem Hindernis versperrt oder gar nicht erst vorhanden ist.<br>
     * Diese Methode ist besonders hilfreich, wenn der Train ohne manuelle Eingaben, sondern automatisiert
     * bewegt werden soll. Das Ende eines Gleises ebenso wie ein RailVehicle auf den Schienen kann auf diese
     * Weise erkannt werden.
     * @return true, falls der nächste zu befahrende Track von einem Hindernis versperrt oder gar nicht
     * erst vorhanden ist; false sonst.
     */
    public boolean isObstacleAhead() {
        RailVehicle leadingVehicle = this.getLeadingVehicle();
        Track currentLeadingTrack = leadingVehicle.getTrackBelow();
        Track nextLeadingTrack = currentLeadingTrack.determineNextTrack(leadingVehicle.getDirection());
        
        return nextLeadingTrack == null ? true : nextLeadingTrack.isOccupiedByRailVehicle();
    }
    
    /**
     * Gibt an, ob ein Wagon zum Kuppeln bereitsteht, d.h. an der richtigen Position, die für ein Kuppeln
     * erforderlich ist.
     * @return true, falls ob ein Wagon zum Kuppeln bereitsteht, d.h. an der richtigen Position, die für ein Kuppeln
     * erforderlich ist; false sonst.
     */
    public boolean isWagonAvailableForCoupling() {
        RailVehicle couplingVehicle = this.hasVehicles() ? getLastVehicle() : getLoc();
        
        List<RailVehicle> nearbyVehicles = couplingVehicle.getNearbyRailVehicles();
        for(RailVehicle candidate : nearbyVehicles) {
            if(!this.vehicles.contains(candidate) && !this.getLoc().equals(candidate)) {
                if(candidate.getTrackBelow() != null) {
                    if(couplingVehicle.getTrackBelow().isConnectedWith(candidate.getTrackBelow())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
}