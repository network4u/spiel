import greenfoot.*;

public class movedObject extends myActor
{
    // instance variables - replace the example below with your own
    private int[] position;
    private String[] imagelist;
    private int actualImage;
    private int propellerDrehgesch;

    /**
     * Constructor for objects of class movedObject
     */
    public movedObject(String[] nimages, int nx,int ny)
    {
        super();
        this.imagelist = nimages;
        this.position = new int[] {nx,ny};
        this.actualImage=0;
        this.propellerDrehgesch=8;
    }
    
    public void act(){
        if(getWorld().menue.getSpielZustand().equals("Spielen")){
            this.setLocation(position[0],position[1]);
            this.actualImage= (this.actualImage + 1) % (this.imagelist.length * this.propellerDrehgesch);
            double nextImage= (double) this.actualImage / (double) this.propellerDrehgesch;
            setImage(this.imagelist[(int) nextImage]);
            
        }
    }
    
    public int[] getNewPosition(double steps, int degrees){
        double playerspeed=2.0;
        Welt world= getWorld();
        double antirotation = Math.toRadians(getWorld().bodenDurchsichtig.getRotation());
        double selfrotation = Math.toRadians(degrees);
        
        double worldmovementy = Math.sin(antirotation) * playerspeed;
        double worldmovementx = Math.cos(antirotation) * playerspeed;
        double selfmovementy = Math.sin(selfrotation)* steps;
        double selfmovementx = Math.cos(selfrotation)* steps;
        int absposx = (int) Math.round(selfmovementx + worldmovementx + ((double) position[0]));
        int absposy = (int) Math.round(selfmovementy + worldmovementy + ((double) position[1]));
        return new int[] {absposx,absposy};
    }
    
    public void move(int steps){
        this.position = getNewPosition((double) steps,this.getRotation());
        
    }
    
    public int[] getPosition(){
        return position;
    }
}