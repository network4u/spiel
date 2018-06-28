import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Geschuetz ist die Waffe die auf dem Boot ist und das Flugzeug versucht abzuschießen.
 * 
 * @author (Jonas Wischeropp, Julius Schreiber) 
 * @version (5.Mai.2018)
 */
public class Geschuetz extends Actor
{
    Boot boot;
    Flugzeug flugzeug;
    private int lastshot;
    /**
     * Konstruktor der Klasse Boden
     * @param b Referenz aufs Boot
     * @param f Referenz aufs Flugzeug
     */
    public Geschuetz(Boot b, Flugzeug f){
        boot = b;
        flugzeug = f;
        lastshot=1;
    }
    
     /**
     * Whileschleife die läuft wenn der Runn-Knopf gedrückt wurde
     * und in die alles geschrieben werden muss was ausgeführt werden soll..
     */
    public void act() 
    {
         folgtBoot();
         turnTowards(flugzeug.getX(), flugzeug.getY());
         shoot();
    }  
    
    
    public void shoot(){
        if(this.lastshot==0){
            Welt welt = this.getWorldOfType(Welt.class);
            int [] posList= new int[]{this.getX()-welt.bodenDurchsichtig.getX(),this.getY()-welt.bodenDurchsichtig.getY()};
            welt.newshot(posList,this.getRotation(),this);
        }
        this.lastshot = (lastshot+1)%300;
    }
    
    
    /**
     * Sorgt dafür, dass das Geschütz an der Richtigen stelle vom Boot ist.
     */
    public void folgtBoot(){
        if(boot.getRotation() == 0){
            setLocation(boot.getX() + 96, boot.getY());
        }
        if(boot.getRotation() == 90){
            setLocation(boot.getX(), boot.getY() + 96);
        }
        if(boot.getRotation() == 180){
            setLocation(boot.getX() - 96, boot.getY());
        }
        if(boot.getRotation() == 270){
            setLocation(boot.getX(), boot.getY() - 96);
        }
    }
}
