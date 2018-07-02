import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Propeller sorgt für die Animation des Propellers des Flugzeuges (Klasse: Flugzeug).
 * 
 * @author (Jonas Wischeropp, Julius Schreiber) 
 * @version (30.Juni.2018)
 */
public class Propeller extends Actor
{
    int counter;
    
    int DREHGESCHWINDIGKEIT;
    
    Flugzeug flugzeug;
    Menue menue;
    /**
     * Konstruktor der Klasse Propeller
     * @param f Referenz aufs Flugzeug
     * @param m Referenz aufs Menue
     */
    public Propeller(Flugzeug f, Menue m){
        flugzeug = f;
        menue = m;
        
        counter = 0;
        
        DREHGESCHWINDIGKEIT = 4;
    }
    
    /**
     * Whileschleife die läuft wenn der Runn-Knopf gedrückt wurde
     * und in die alles geschrieben werden muss was ausgeführt werden soll..
     */
    public void act() 
    {
        if(menue.getSpielZustand().equals("Spielen")
        || menue.getSpielZustand().equals("Menue")){
            if(menue.getGrafik()){
                propellerDrehen();
            }
            else{
                setImage("Propeller1.png");
            }
        }
        propellerFolgt();
    }  
    
    /**
     * Sorgt dafür, dass sich Propeller an der gleichen Stelle,
     * wie das Flugzeug befindet und die selbe ausrichtung hat.
     */
    public void propellerFolgt(){
        setLocation(flugzeug.getX(), flugzeug.getY());
        setRotation(flugzeug.getRotation());
    }
    
    /**
     * Sorgt durch die Abfolge von Bildern für die Drehung des Propellers.
     */
    public void propellerDrehen(){
        counter++;
        if(counter == DREHGESCHWINDIGKEIT){
            setImage("Propeller2.png");
        }
        if(counter == DREHGESCHWINDIGKEIT*2){
            setImage("Propeller3.png");
        }
        if(counter == DREHGESCHWINDIGKEIT*3){
            setImage("Propeller4.png");
        }
        if(counter == DREHGESCHWINDIGKEIT*4){
            setImage("Propeller5.png");
        }
        if(counter == DREHGESCHWINDIGKEIT*5){
            setImage("Propeller4.png");
        }
        if(counter == DREHGESCHWINDIGKEIT*6){
            setImage("Propeller3.png");
        }
        if(counter == DREHGESCHWINDIGKEIT*7){
            setImage("Propeller2.png");
        }
        if(counter == DREHGESCHWINDIGKEIT*8){
            setImage("Propeller1.png");
            counter = 0;
        }
    }
}
