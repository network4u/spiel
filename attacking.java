/**
 * Write a description of class attacking here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*;
public class attacking extends movedObject
{
    // instance variables - replace the example below with your own
    private int speed;
    private int delay;
    private String exploded;
    private Flugzeug flugzeug;
    private int shotit;
    /**
     * Constructor for objects of class movedObject
     */
    public attacking(int nx,int ny)
    {
        super(new String[] {"FlugzeugGegner1.png"},nx,ny);
        exploded="Explosion.png";
        this.setstate("g_Spielen");
        this.speed = 1;
        this.delay = 5000;
        shotit=1;
    }
    
    public void act(){
        super.act();
        if(this.isstate("g_Spielen")){
            this.turntoplain();
            this.move(this.speed);
            java.util.List<Flugzeug> actors;
            actors= this.getObjectsInRange(1000,Flugzeug.class);
            if(actors.size()!=0){
                this.shoot();
            }
            java.util.List<shot> s = getIntersectingObjects(shot.class);
            this.touched(s);
            
        }
        if(getstate().startsWith("g_")){
            this.setstate("g_"+getWorld().menue.getSpielZustand());
        }
    }
    
    public void touched(java.util.List<shot> shots){
        boolean hit= false;
        for(int i=0; i<shots.size();i++){
            if(!shots.get(i).isFrom((Actor) this)){
                getWorld().removeObject((Actor)shots.get(i));
                hit = true;
            }
            if(hit){
                setImage(exploded);
                getWorld().removeObject(this);
                this.setstate("dead");
            }
        }
    }
    
    public void shoot(){
        if(this.shotit==0){
            getWorld().newshot(this.getPosition(),this.getRotation(),this);
        }else{
            this.shotit = (shotit+1)%20;
        }
    }
    
    public void turntoplain(){
        int plainx = getWorld().flugzeug.getX();
        int plainy = getWorld().flugzeug.getY();
        
        this.turnTowards(plainx,plainy);
    }
}
    

