/**
 * Ein feindliches Flugzeug, das dem Spielerflugzeug flolgt und in vorgegebenen Intervallen Schüsse auslöst, wenn sich das Spielerflugzeug in der Nähe befindet
 * 
 * @author Julius,Jonas
 */
import greenfoot.*;
public class attacking extends MovedObject
{
    // instance variables - replace the example below with your own
    private int speed;
    private int delay;
    private String exploded;
    private Flugzeug flugzeug;
    private int lastShot;
    private java.util.ArrayList<int[]> turnto;
    /**
     * Konstruktor der Klasse Attacking
     * @param x Der X-Wert, auf dem sich das Objekt anfangs relativ zum Boden befinden soll
     * @param y Der Y-Wert, auf dem sich das Objekt anfangs relativ zum Boden befinden soll
     */
    public attacking(int x,int y)
    {
        super(new String[] {"FlugzeugGegner1.png","FlugzeugGegner2.png","FlugzeugGegner3.png","FlugzeugGegner4.png","FlugzeugGegner5.png"},x,y);
        this.exploded="Explosion.png";
        this.setState("g_Spielen");
        this.speed = 1;
        this.delay = 5000;
        this.lastShot=1;
        this.turnto= new java.util.ArrayList<int []>();
        for( int i=0; i<50;i++){
            turnto.add(new int[] {0,0});
        }
    }
    
    public void act(){
        super.act();
        if(this.isState("g_Spielen")){
            this.turntoplain();
            this.move(this.speed);
            java.util.List<Flugzeug> actors;
            actors= this.getObjectsInRange(1000,Flugzeug.class);
            if(actors.size()!=0){
                this.shoot();
            }
            
            this.touched();
            
        }
        if(getState().startsWith("g_")){
            this.setState("g_"+getWorld().menue.getSpielZustand());
        }
    }
    
    /**
     * Überprüft, ob das Objekt von einem fremden Schuss berührt wird. Falls dies der Fall ist, wird das Objekt gelöscht und alle feindlichen, das Objekt berührnenden Schüsse gelöscht
     */
    public void touched(){
        java.util.List<Shot> shots = getIntersectingObjects(Shot.class);
        boolean hit= false;
        for(int i=0; i<shots.size();i++){
            if(!shots.get(i).isFrom((Actor) this)){
                getWorld().removeObject((Actor)shots.get(i));
                hit = true;
            }
            if(hit){
                this.setImage(exploded);
                if(getWorld() == null){
                    return;
                }
                this.getWorld().removeObject(this);
                this.setState("dead");
            }
        }
    }
    
    /**
     * Löst einen Schuss mit Position und Bewegungsrichtung des Objektes aus
     */
    public void shoot(){
        if(this.lastShot==0){
            int[] position= this.getPosition();
            getWorld().newShot(position[0], position[1],this.getRotation(),this);
        }
        this.lastShot = (lastShot+1)%100;
    }
    
    /**
     * Speichert die Position relativ zum Spielerflugzeug in einer ArrayList und verwendet eine Vergangene Position relativ zum Flugzeug um sich in diese Richtung zu drehen
     */
    public void turntoplain(){
        int plainx = getWorld().flugzeug.getX(); // stays always the same
        int plainy = getWorld().flugzeug.getY(); // stays always the same
        int myx = this.getX();
        int myy = this.getY();
        int relativx = plainx-myx;
        int relativy = plainy-myy;
        this.turnto.add( new int[] {relativx,relativy});
        int [] oldPosition = this.turnto.remove(0);
        int newTurnToX = oldPosition[0]+ myx;
        int newTurnToY = oldPosition[1]+ myy;
        this.turnTowards(newTurnToX,newTurnToY);
    }
}
    

