import greenfoot.*;


/**
 * Superklasse für Objekte, die sich relativ zum Boden bewegen müssen
 * @author Julius,Jonas
 */
public class MovedObject extends MyActor
{
    // instance variables - replace the example below with your own
    private int[] onMapPosition;
    private String[] imagelist;
    private int actualImage;
    private int propellerDrehgesch;

    /**
     * Konstruktor der Klasse MovedObject
     * @param nimages Ein Array mit Pfaden zu allen Bildern, zwischen denen das Objekt wechseln soll
     * @param x Der X-Wert der Position, auf der das Objekt erstellt werden soll
     * @param y Der Y-Wert der Position, auf der das Objekt erstellt werden soll
     */
    public MovedObject(String[] nimages, int x,int y)
    {
        super();
        this.imagelist = nimages;
        this.onMapPosition = new int[] {x,y};
        this.actualImage=0;
        this.propellerDrehgesch=8;
    }
    /**
     * Setzt die Position des Objektes auf die in onMapPosition vorgegebene und wechselt regelmäßig zwischen den im Konstruktor übergebenen Bildern
     */
    public void act(){
        int xpos= onMapPosition[0]+this.getWorld().bodenDurchsichtig.getX();
        int ypos= onMapPosition[1]+this.getWorld().bodenDurchsichtig.getY();
        this.setLocation(xpos,ypos);
        if (this.isState("g_Spielen")){
            this.actualImage= (this.actualImage + 1) % (this.imagelist.length * this.propellerDrehgesch);
            double nextImage= (double) this.actualImage / (double) this.propellerDrehgesch;
            setImage(this.imagelist[(int) nextImage]);
        }
    }
    
    
    /**
     * Rechnet die Position aus, die das Objekt besitzen würde, wenn es sich steps Schritte in Richtund degrees bewegen würde
     * @param steps Die Anzahl der Schritte, für die die Position erfragt wird
     * @param degrees Die Rotation, von der für die Anfrage ausgegengen wird
     * @return Die Position, die das Objekt besäße, wenn es sich steps Schritte mit der Rotation degrees bewegen würde
     */
    public int[] getNewPosition(int steps, int degrees){
        Welt world= getWorld();
        double selfrotation = Math.toRadians(degrees);
        int selfmovementy = (int) Math.round(Math.sin(selfrotation)* (double) steps);
        int selfmovementx = (int) Math.round(Math.cos(selfrotation)* (double) steps);
        int absposx = selfmovementx + this.onMapPosition[0];
        int absposy = selfmovementy + this.onMapPosition[1];
        return new int[] {absposx,absposy};
    }
    
    /**
     * Bewegt das Objekt relativ zum Boden
     * @param steps Die Anzahl der Schritte, die sich das Objekt bewegen soll
     */
    public void move(int steps){
        this.onMapPosition = this.getNewPosition(steps,this.getRotation());
        
    }
    
    /**
     * Gibt die Position relativ zum Boden zurück
     * @return Die Position relativ zum Boden
     */
    public int[] getPosition(){
        return this.onMapPosition;
    }
    
    /**
     * Setzt die Position relativ zum boden
     * @param x Der X-Wert der Position relativ zum Boden
     * @param y Der Y-Wert der Position relativ zum Boden
     */
    public void setLocation2(int x, int y){
        this.onMapPosition = new int[] {x,y};
        int absX= x+this.getWorld().bodenDurchsichtig.getX();
        int absY= y+this.getWorld().bodenDurchsichtig.getY();
        super.setLocation(absX,absY);
    }
}