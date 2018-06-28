import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Flugzeug legt die Steureung der Flugzeuge im Spiel fest (Bots und Spieler).
 * 
 * @author (Jonas Wischeropp, Julius Schreiber) 
 * @version (5.Mai.2018)
 */
public class Flugzeug extends Actor
{
    
    Menue menue;
    LebensAnzeige lebensAnzeige;
    int lastshot;
    /**
     * Konstruktor der Klasse Flugzeug
     * @param l Referenz auf die Lebensanzeige
     * @param m Referenz aufs Menue
     */
    public Flugzeug(LebensAnzeige l, Menue m){
        menue = m;
        lebensAnzeige = l;
        lastshot=1;
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
        
        touched();
        
        if(menue.getSpielZustand().equals("Verloren")){
            menue.explosionsGeraeusch();
        }
    }
    

    public void touched(){
        java.util.List<shot> shots = getIntersectingObjects(shot.class);
        boolean hit= false;
        for(int i=0; i<shots.size();i++){
            if(!shots.get(i).isFrom((Actor) this)){
                getWorld().removeObject((Actor)shots.get(i));
                hit = true;
            }
            if(hit){
                lebensAnzeige.lebenAbziehen();
            }
        }
    }
    
    
    public void shoot(){
        if(this.lastshot==0){
            int relX= this.getX() - getWorldOfType(Welt.class).bodenDurchsichtig.getX();
            int relY= this.getY() - getWorldOfType(Welt.class).bodenDurchsichtig.getY();
            int[] posInt= new int[] {relX,relY};
            getWorldOfType(Welt.class).newshot(posInt,this.getRotation(),this);
        }
        this.lastshot = (lastshot+1)%13;
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
            shoot();
        }
        //Bombe abwerfen
        if(Greenfoot.isKeyDown("s")){
            //Objekt Bombe Spawnen
        }
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
