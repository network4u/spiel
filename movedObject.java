import greenfoot.*;

public class movedObject extends myActor
{
    // instance variables - replace the example below with your own
    private int[] onMapPosition;
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
        this.onMapPosition = new int[] {nx,ny};
        this.actualImage=0;
        this.propellerDrehgesch=8;
    }
    
    public void act(){
        int xpos= onMapPosition[0]+this.getWorld().bodenDurchsichtig.getX();
        int ypos= onMapPosition[1]+this.getWorld().bodenDurchsichtig.getY();
        this.setLocation(xpos,ypos);
        if (this.isstate("g_Spielen")){
            this.actualImage= (this.actualImage + 1) % (this.imagelist.length * this.propellerDrehgesch);
            double nextImage= (double) this.actualImage / (double) this.propellerDrehgesch;
            setImage(this.imagelist[(int) nextImage]);
        }
    }
    
    public int[] getNewPosition(int steps, int degrees){
        Welt world= getWorld();
        double selfrotation = Math.toRadians(degrees);
        int selfmovementy = (int) Math.round(Math.sin(selfrotation)* (double) steps);
        int selfmovementx = (int) Math.round(Math.cos(selfrotation)* (double) steps);
        int absposx = selfmovementx + this.onMapPosition[0];
        int absposy = selfmovementy + this.onMapPosition[1];
        return new int[] {absposx,absposy};
    }
    
    public void move(int steps){
        this.onMapPosition = this.getNewPosition(steps,this.getRotation());
        
    }
    
    public int[] getPosition(){
        return this.onMapPosition;
    }
    
    public void setLocation2(int x, int y){
        this.onMapPosition = new int[] {x,y};
        int absX= x+this.getWorld().bodenDurchsichtig.getX();
        int absY= y+this.getWorld().bodenDurchsichtig.getY();
        super.setLocation(absX,absY);
    }
}