import greenfoot.*;
/**
 * Write a description of class myActor here.
 * 
 * @author Julius
 */
public class myActor extends Actor
{
    private String state;
    /**
     * Constructor for objects of class myActor
     */
    public myActor()
    {
        super();
        this.state="play";
    }
    
    public void setstate(String newstate){
        this.state=newstate;
    }
    
    public String getstate(){
        return this.state;
    }
    
    public boolean isstate(String isit){
        if(this.state.equals(isit)){
            return true;
        }
        else{
            return false;
        }
    }
    
    public Welt getWorld(){
        return getWorldOfType(Welt.class);
    }
    
    public java.util.List <Actor> getIntersectingObjects2(java.lang.Class<Actor> cls){
        return super.getIntersectingObjects(cls);
    }
}
