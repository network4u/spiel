import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Boden wird von BodenDurchsichtig gesteuert und ist nur die "Garfik".
 * 
 * @author (Jonas Wischeropp, Julius Schreiber) 
 * @version (5.Mai.2018)
 */
public class Boden extends Actor
{
    int mapFaktor;
    /**
     * Konstruktor der Klasse Boden
     */
    public Boden(){
        mapFaktor = 4;
        getImage().scale(900* mapFaktor, 600* mapFaktor);
    }
    
     /**
     * Whileschleife die läuft wenn der Runn-Knopf gedrückt wurde
     * und in die alles geschrieben werden muss was ausgeführt werden soll..
     */
    public void act() 
    {
       
    } 
    
    /**
     * Getter um den den Faktor zu erhalten mit dem die Bildgröße multiplizeirt wurde.
     */
    public int getMapFaktor(){
        return mapFaktor;
    }
}
