import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BodenDurchsichtig sorgt für die Bewegung von Boden ohne diesen dabei zu drehen
 * und ist die Mitte der Map.
 * 
 * @author (Jonas Wischeropp, Julius Schreiber) 
 * @version (5.Mai.2018)
 */
public class BodenDurchsichtig extends Actor
{
    Boden boden;
    BodenRot bodenRot;
    Flugzeug flugzeugSpieler;
    Menue menue;
    /**
     * Konstruktor der Klasse BodenDurchsichtig
     * @param b Referenz aufs den Boden (Grafik der Welt)
     * @param br Referenz auf den BodenRot (Rand der Welt)
     * @param f Referenz aufs Flugzeug
     * @param m Referenz aufs Menue
     */
    public BodenDurchsichtig(Boden b, BodenRot br, Flugzeug f, Menue m){
        boden = b;
        bodenRot = br;
        flugzeugSpieler = f;
        menue = m;
    }
    
     /**
     * Whileschleife die läuft wenn der Runn-Knopf gedrückt wurde
     * und in die alles geschrieben werden muss was ausgeführt werden soll..
     */
    public void act() 
    {
        bodenFolgt();
        if(menue.getSpielZustand().equals("Spielen")){
            bewegen(flugzeugSpieler.getGeschwindigkeit());
        }
    }  
    
    /**
     * Sorgt dafür, dass sich Boden und BodenRot in die entgegengesetze Richtung
     * von Flugzeug bewegen.
     */
    public void bewegen(int v){
        setRotation(flugzeugSpieler.getRotation()-180);
        move(v);
    }
    
    /**
     * Sorgt dafür, dass sich Boden an der gleichen Stelle,
     * wie BodenDurchsichtig befindet.
     */
    public void bodenFolgt(){
        boden.setLocation(getX(), getY());
        bodenRot.setLocation(getX(), getY());
    }
}
