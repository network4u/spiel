import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BodenRot ist der Rand der Welt/ die Begrenzung des Spielfeldes.
 * Wenn sich der Spieler in diesem befindet verliert er.
 * 
 * @author (Jonas Wischeropp, Julius Schreiber) 
 * @version (30.Juni.2018.2018)
 */
public class BodenRot extends Actor
{
    Boden boden;
    /**
     * Konstruktor der Klasse Boden
     * @param b Referenz auf den Boden
     */
    public BodenRot(Boden b){
        boden = b;
        getImage().scale(1206* boden.getMapFaktor(), 792* boden.getMapFaktor());
    }
    
     /**
     * Whileschleife die läuft wenn der Runn-Knopf gedrückt wurde
     * und in die alles geschrieben werden muss was ausgeführt werden soll..
     */
    public void act() 
    {
        
    }   
}
