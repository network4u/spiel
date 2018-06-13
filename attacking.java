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
    /**
     * Constructor for objects of class movedObject
     */
    public attacking(int nx,int ny)
    {
        super(new String[] {"FlugzeugGegner1.png"},nx,ny);
        exploded="Explosion.png";
        this.speed = 1;
        this.delay = 5000;
    }
    
    public void act(){
        super.act();
        if(getWorld().menue.getSpielZustand().equals("Spielen")){
            this.turntoplain();
            this.move(this.speed);
            java.util.List<Flugzeug> actors;
            actors= this.getObjectsInRange(20,Flugzeug.class);
            if(actors.size()==0){
                this.shoot();
            }
            if(getOneIntersectingObject(shot.class)!=null){
                this.touched();
            }
        }
    }
    
    public void touched(){
        removeTouching(shot.class);
        setImage(exploded);
        getWorld().removeObject(this);
        this.setstate("dead");
    }
    
    public void shoot(){
        getWorld().newshot(this.getPosition(),this.getRotation());
        
    }
    
    public void turntoplain(){
        int plainx = getWorld().flugzeug.getX();
        int plainy = getWorld().flugzeug.getY();
        
        this.turnTowards(plainx,plainy);
    }
}
    

