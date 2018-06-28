import greenfoot.*;

/**
 * Write a description of class shot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shot extends movedObject
{
    // instance variables - replace the example below with your own
    private int flew;
    private int speed;
    private Actor attacker;

    /**
     * Konstruktor der Klasse shot
     * @param x Der X-Wert, auf dem sich der Schuss anfangs relativ zum Boden befinden soll
     * @param y Der Y-Wert, auf dem sich der Schuss anfangs relativ zum Boden befinden soll
     * @param rotation Die Richtung, in die sich der Schuss bewegen soll
     * @param from Referenz auf das Flugzeug, welches den Schuss abgegeben hat
     */
    public Shot(int x, int y, int rotation, Actor from)
    {
        super(new String[] {"Explosion.png",},x,y);
        this.setRotation(rotation);
        this.setstate("setup");
        flew = 0;
        speed= 10;
        this.attacker= from;
        
    }
    
    public void act(){
        super.act();
        if(this.isstate("g_Spielen")){
            try{
                this.move(speed);
            }catch(java.lang.NullPointerException error){
                this.setstate("dead");
            }
            flew += speed;
            if(flew > 500){
                this.setstate("dead");
                getWorld().removeObject(this);
            }
        }else if(this.isstate("setup")){
            this.move(100);
            this.setstate("g_Spielen");
        }
        if(getstate().startsWith("g_")){
            this.setstate("g_"+getWorld().menue.getSpielZustand());
        }
    }
    
    /**
     * Überprüft ob die Übergebene Referenz mit dem Erzeuger des Schusses übereinstimmt
     * @param ques Referenz auf das Objekt, für das eine Herkunftsübereinstimmung überprüft werden soll
     * @return true, wenn ques mit dem Erzeuger des Schusses übereinstimmt, sonst false
     */
    public boolean isFrom(Actor ques){
        return this.attacker == ques;
    }
}
