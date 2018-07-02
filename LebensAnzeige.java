import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Lebensanzeige sorgt für eine Anzeige am oberen Bildschirmrand,
 * die die Leben des Spielers anzeigen.
 * Maximal besitzt der Spieler 3 leben und wenn die Leben auf 0 fallen, verliert er.
 * 
 * @author (Jonas Wischeropp, Julius Schreiber) 
 * @version (30.Juni.2018)
 */
public class LebensAnzeige extends Actor
{
    int leben;
    
    Flugzeug flugzeug;
    Menue menue;
    /**
     * Konstruktor der Klasse Menue
     * @param m Referenz aufs Menue
     */
    public LebensAnzeige(Menue m){
        leben = 3;

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
     * Methode um ein Leben abzuziehen.
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
    
    /**
     * Methode um ein Leben hinzuzufügen.
     */
    public void lebenHinzufuegen(){
        if(leben != 3){
            setLeben(getLeben()+1);
        }
    }
}
