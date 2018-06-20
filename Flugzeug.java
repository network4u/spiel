import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Flugzeug legt die Steureung der Flugzeuge im Spiel fest (Bots und Spieler).
 * 
 * @author (Jonas Wischeropp, Julius Schreiber) 
 * @version (5.Mai.2018)
 */
public class Flugzeug extends Actor
{
    int GESCHWINDIGKEIT;
    
    Menue menue;
    LebensAnzeige lebensAnzeige;
    /**
     * Konstruktor der Klasse Flugzeug
     * @param l Referenz auf die Lebensanzeige
     * @param m Referenz aufs Menue
     */
    public Flugzeug(LebensAnzeige l, Menue m){
        menue = m;
        lebensAnzeige = l;
        
        GESCHWINDIGKEIT = 2;
    }
    
     /**
     * Whileschleife die läuft wenn der Runn-Knopf gedrückt wurde
     * und in die alles geschrieben werden muss was ausgeführt werden soll.
     */
    public void act() 
    {
        if(menue.getSpielZustand().equals("Spielen")){
            steuerung();
        }
        skinWaehlen(menue.getSkin());
        if(getOneIntersectingObject(Boden.class) == null){
            if(menue.getSpielZustand().equals("Spielen")){
                menue.setSpielZustand("Verloren");
            }
        }
        
        if(menue.getSpielZustand().equals("Spielen")){
            menue.propellerGeraeusch();
        }
        else{
            menue.propellerGeraeuschStoppen();
        }

        menue.hintergrundmusik();
        
        if(getOneIntersectingObject(shot.class)!=null){
            removeTouching(shot.class);
            lebensAnzeige.lebenAbziehen();
        }
        
        if(menue.getSpielZustand().equals("Verloren")){
            menue.explosionsGeraeusch();
        }
    }    
    
    /**
     * Legt die steuerung von Flugzeug fest um es zu bewegen.
     */
    public void steuerung(){
        //linkskurve
        if(Greenfoot.isKeyDown("a")){
            setRotation(getRotation()-1);
        }
        //rechtskurve
        if(Greenfoot.isKeyDown("d")){
            setRotation(getRotation()+1);
        }
         //schießen
        if(Greenfoot.isKeyDown("w")){
            menue.schussGeraeusch();
            //Objekt Schuss Spawen
        }
        //Bombe abwerfen
        if(Greenfoot.isKeyDown("s")){
            //Objekt Bombe Spawnen
        }
    }
    
    /**
     * Getter der es ermöglicht die Geschwindigkeit des Flugzeugs zu erfahren. 
     */
    public int getGeschwindigkeit(){
        return GESCHWINDIGKEIT;
    }
    
    /**
     * Legt den Skin (das Aussehen) des Flugzeugs fest.
     * @param skin Nummer des kins/ Aussehens
     */
    public void skinWaehlen(int skin){
        if(menue.getSpielZustand().equals("Verloren")
        || menue.getSpielZustand().equals("GameOver")){
            setImage("Explosion.png");
        }
        else{
            setImage("FlugzeugD" + skin + ".png");
        }
    }
}