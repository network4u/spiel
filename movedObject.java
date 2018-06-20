import greenfoot.*;

public class movedObject extends myActor
{
    // instance variables - replace the example below with your own
    private int[] position;
    private String[] imagelist;

    /**
     * Constructor for objects of class movedObject
     */
    public movedObject(String[] nimages, int nx,int ny)
    {
        super();
        this.imagelist = nimages;
        this.position = new int[] {nx,ny};
    }
    
    public void act(){
        if(getWorld().menue.getSpielZustand().equals("Spielen")){
            this.setLocation(position[0],position[1]);
            for(int i=0; i < this.imagelist.length; i++){
                setImage(this.imagelist[i]);
            }
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
        /**
        System.out.println("-----------");
        System.out.println(position[0]);
        System.out.println(position[1]);
        */
    }
    
    public int[] getPosition(){
        return position;
    }
}