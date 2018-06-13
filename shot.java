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

    /**
     * Constructor for objects of class shot
     */
    public shot(int rotation,int[] npos)
    {
        super(new String[] {"Schuesse.png",},npos[0],npos[1]);
        this.setRotation(rotation);
        flew = 0;
        speed= 10;
        
    }
    
    public void act(){
        super.act();
        if(getWorld().menue.getSpielZustand().equals("Spielen")){
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
        }
    }

}
