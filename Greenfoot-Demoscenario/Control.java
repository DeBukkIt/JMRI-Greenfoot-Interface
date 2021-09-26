import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse Control.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public abstract class Control extends Actor
{
    
    protected Thread controlThread = null;
    
    protected void doAsync(Runnable runnable) {
        controlThread = new Thread(runnable);
        controlThread.start();
    }
    
    public void act() 
    {
        // controls do not act
    }
    
    @Override
    public void move(int distance) {
        // controls do not move
    }
    
}
