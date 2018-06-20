import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Lebensanzeige sorgt für eine Anzeige am oberen Bildschirmrand,
 * die die Leben des Spilers anzeigen.
 * 
 * @author (Jonas Wischeropp, Julius Schreiber) 
 * @version (5.Mai.2018)
 */
public class LebensAnzeige extends Actor
{
    int leben;
    boolean boom;
    
    Flugzeug flugzeug;
    Menue menue;
    /**
     * Konstruktor der Klasse Menue
     * @param m Referenz aufs Menue
     */
    public LebensAnzeige(Menue m){
        leben = 3;
        boom = true;

        menue = m;
    }
    
    /**
     * Whileschleife die läuft wenn der Runn-Knopf gedrückt wurde
     * und in die alles geschrieben werden muss was ausgeführt werden soll.
     */
    public void act() 
    {
        lebenAnzeigen();
        
    }
    
    /**
     * Setter um die Anzahl der Leben festzulegen.
     * @param s neue Anzahl der Leben
     */
    public void setLeben(int s){
        leben = s;
    }
    /**
     * Getter um die Anzahl der Leben zu erfahren.
     */
    public int getLeben(){
        return leben;
    }
    
    /**
     * Methode um die Anzahl der Leben anzuzeigen.
     */
    public void lebenAnzeigen(){
        setImage("LebensAnzeige" + leben + ".png");
    }
    
    /**
     * Methode um die Anzahl der Leben anzuzeigen.
     */
    public void lebenAbziehen(){
        if(leben >= 2){
            setLeben(getLeben()-1);
        }
        else{
            if(menue.getSpielZustand().equals("Spielen")){
                menue.setSpielZustand("Verloren");
            } 
        }
    }
}
