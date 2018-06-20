import greenfoot.*;

/**
 * Write a description of class shot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class shot extends movedObject
{
    // instance variables - replace the example below with your own
    private int flew;
    private int speed;
    private Actor attacker;

    /**
     * Constructor for objects of class shot
     */
    public shot(int[] npos, int rotation, Actor from)
    {
        super(new String[] {"Schuesse.png",},npos[0],npos[1]);
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
            this.setstate(getWorld().menue.getSpielZustand());
        }
    }
    
    public boolean isFrom(Actor ques){
        return this.attacker == ques;
    }
}
