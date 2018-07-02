import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * PowerUps sind Objekte die einen schneller, unverwundbar machen
 * oder einem ein zusätzliches Leben geben.
 * Sie verden zufällig auf der Welt erschaffen.
 * 
 * @author (Jonas Wischeropp, Julius Schreiber) 
 * @version (30.Juni.2018)
 */
public class PowerUps extends Actor
{
    BodenDurchsichtig bodenDurchsichtig;
    
    String powerUpName;
    int xPosition;
    int yPosition;
    boolean bewegen;
    int counter;
    int ABBLINKZEIT;
    /**
     * Konstruktor der Klasse powerUps
     * @param bd Referenz auf den Bodendurchsichtig
     * @param pn Name/ Funktion des Powerups
     * @param x x-Position
     * @param y y-Position

     */
    public PowerUps(BodenDurchsichtig bd, String pn, int x, int y){
        bodenDurchsichtig = bd;
        
        powerUpName = pn;
        xPosition = x;
        yPosition = y;
        setImage(pn + "Item.png");
        bewegen = true;
        counter = 0;
        ABBLINKZEIT = 500;
    }
    
    /**
     * Whileschleife die läuft wenn der Runn-Knopf gedrückt wurde
     * und in die alles geschrieben werden muss was ausgeführt werden soll..
     */
    public void act() 
    {
        if(bewegen){
            setLocation(bodenDurchsichtig.getX() + xPosition,
                bodenDurchsichtig.getY() + yPosition);
        }
        else{
            setRotation(bodenDurchsichtig.getRotation()-180);
        }
        
        if(getOneIntersectingObject(Flugzeug.class) != null){
            if(powerUpName.equals("Herz")){
                welt().lebensAnzeige.lebenHinzufuegen();
                getWorldOfType(Welt.class).menue.herzPlus();
                welt().removeObject(this);
            }
            if(powerUpName.equals("Schnelligkeit")){
                bodenDurchsichtig.setGeschwindigkeit(5);
                setLocation(welt().getWidth()/2, welt().getHeight()/2);
                setImage("Schnelligkeit.png");
                bewegen = false;
            }
            if(powerUpName.equals("Schild")){
                setLocation(welt().getWidth()/2, welt().getHeight()/2);
                setImage("Schild.png");
                bewegen = false;
            }
        }
        
        if(!bewegen){
            counter++;
            if(counter == ABBLINKZEIT){
                if(powerUpName.equals("Schnelligkeit")){
                    getWorldOfType(Welt.class).menue.schnelligkeitPlus();
                    bodenDurchsichtig.setGeschwindigkeit(3);
                }
                else{
                    getWorldOfType(Welt.class).menue.schildPlus();
                }
                welt().removeObject(this);
            }
        }
        
        if(getWorld() == null){
            return;
        }
        
        java.util.List<Shot> shots = getIntersectingObjects(Shot.class);
        //if(isTouching(Shot.class)){
        for(int i=0; i<shots.size();i++){
            if(!shots.get(i).isFrom((Actor) welt().flugzeug)){
                if(powerUpName.equals("Schild")
                && !bewegen){
                    removeTouching(Shot.class);
                }
            }
        }
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
    
    /**
     * Referenz auf die Welt
     */
    public Welt welt(){
        return getWorldOfType(Welt.class);
    }
}
