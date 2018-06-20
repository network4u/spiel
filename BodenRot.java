import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BodenRot wird von BodenDurchsichtig gesteuert und ist nur
 * die "Garfik" für den Rand der Welt.
 * 
 * @author (Jonas Wischeropp, Julius Schreiber) 
 * @version (5.Mai.2018)
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
