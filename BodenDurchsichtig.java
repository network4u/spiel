import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BodenDurchsichtig sorgt für die Bewegung von dem Spielfeld (Klasse: Boden)
 * und dem Rand des Spielfeldes (KLasse: BodenRot) ohne diese dabei zu drehen.
 * Ist dadurch immer die Mitte der Welt/ des Spielbereiches.
 * 
 * @author (Jonas Wischeropp, Julius Schreiber) 
 * @version (30.Juni.2018)
 */
public class BodenDurchsichtig extends Actor
{
    Boden boden;
    BodenRot bodenRot;
    Flugzeug flugzeugSpieler;
    Menue menue;
    
    int geschwindigkeit;
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
        geschwindigkeit = 3;
    }
    
     /**
     * Whileschleife die läuft wenn der Runn-Knopf gedrückt wurde
     * und in die alles geschrieben werden muss was ausgeführt werden soll..
     */
    public void act() 
    {
        bodenFolgt();
        if(menue.getSpielZustand().equals("Spielen")){
            bewegen(geschwindigkeit);
        }
    }  
    
    /**
     * Sorgt dafür, dass sich BodenDurchsichtig in die entgegengesetzte Richtung
     * des Flugzeug bewegt und somit es so aussieht alsob sich das Flugzeug bewegt.
     */
    public void bewegen(int v){
        setRotation(flugzeugSpieler.getRotation()-180);
        move(v);
    }
    
    /**
     * Sorgt dafür, dass sich die das Spielfeld (Klasse: Boden) und der Rand der Welt
     * (Klasse: BodenRot immer an der Position von BodenDurchsichtig befinden.
     */
    public void bodenFolgt(){
        boden.setLocation(getX(), getY());
        bodenRot.setLocation(getX(), getY());
    }
    
    /**
     * Getter der es ermöglicht die Geschwindigkeit des Flugzeugs zu erfahren. 
     */
    public int getGeschwindigkeit(){
        return geschwindigkeit;
    }
    /**
     * Setter der es ermöglicht die Geschwindigkeit des Flugzeugs zu setzen. 
     */
    public void setGeschwindigkeit(int v){
        geschwindigkeit = v;
    }
}
