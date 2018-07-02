import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Boot ist ein Gegner der abgeschossen werden muss und versucht den Spieler abzuschießen.
 * Boote werden zufällig auf der Welt erschaffen.
 * 
 * @author (Jonas Wischeropp, Julius Schreiber) 
 * @version (30.Juni.2018)
 */
public class Boot extends Actor
{
    int xPosition;
    int yPosition;
    int rotation;
    boolean lebend;
    int counter ;
    int EXPLOSIONSLAENGE;
    
    Menue menue;
    BodenDurchsichtig bodenDurchsichtig;
    /**
     * Konstruktor der Klasse Boot
     * @param x x-Position
     * @param y y-Position
     * @param r Ausrichtung/ Rotation des Bootes (möglich: 0, 90, 180, 270)
     * @param m Referenz aufs Menue
     * @param bd Referenz auf den BodenDurchsichtig
     */
    public Boot(int x, int y, int r, Menue m, BodenDurchsichtig bd){
        xPosition = x;
        yPosition = y;
        setRotation(r);
        lebend = true;
        counter = 0;
        
        EXPLOSIONSLAENGE = 30;
        
        menue = m;
        bodenDurchsichtig = bd;
    }
    
    /**
     * Whileschleife die läuft wenn der Runn-Knopf gedrückt wurde
     * und in die alles geschrieben werden muss was ausgeführt werden soll..
     */
    public void act() 
    {
        super.setLocation(bodenDurchsichtig.getX() + xPosition,
        bodenDurchsichtig.getY() + yPosition);
        
        if(getOneIntersectingObject(Flugzeug.class)!= null
        && Greenfoot.isKeyDown("s")){
            setImage("Explosion.png");
            menue.explosionsGeraeusch();
            welt().bootListe.remove(this);

            removeTouching(Geschuetz.class);
            lebend = false;
        }
        
        if(!lebend){
            counter++;
            if(counter == EXPLOSIONSLAENGE){
                menue.abschuessePlus();
                getWorld().removeObject(this);
            }
        }
    }   
    
    /**
     * Eine Referenz auf die Welt.
     */
    public Welt welt(){
        return getWorldOfType(Welt.class);
    }
    
    /**
     * Überschreibt die Position des Bootes in der Bootklasse und setzt sie in der ActorKlasse
     * @ param x neue X-Position
     * @ param y neue Y-Position
     */
    public void setLocation2(int x, int y){
        this.setLocation(x,y);
        this.xPosition=x;
        this.yPosition=y;
    }
    
    public java.util.List <Actor> getIntersectingObjects2(java.lang.Class<Actor> cls){
        return super.getIntersectingObjects(cls);
    }
}
