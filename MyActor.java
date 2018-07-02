import greenfoot.*;
/**
 * Superklasse, die nützliche Funktionen wie eine Statusverwaltung oder einen Befehl zum Aufrufen der Welt des gebrauchten Typs bereithält
 * 
 * @author Julius,Jonas
 */
public class MyActor extends Actor
{
    private String state;
    /**
     * Konstruktor der Klasse MyActor
     */
    public MyActor()
    {
        super();
        this.state="play";
    }
    

    /**
     * Setzt den Status des Objektes auf den übergebenen Wert
     * @patam newstate Der Status, der gesetzt werden soll
     */
    public void setState(String newstate){
        this.state=newstate;
    }
    
    /**
     * @return Der Aktuelle Status
     */
    public String getState(){
        return this.state;
    }
    
    /**
     * Überprüft eine Übereinstimmung des übergebenen Wertes mit dem Status
     * @param isit Der Wert, mit dem der Status verglichen werden soll
     * @return true bei Übereinstimmung, sonst false
     */
    public boolean isState(String isit){
        if(this.state.equals(isit)){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Überschreibt Actor.getWorld, weil meist die Welt der Klass Welt benötigt wird, nicht World
     * @return Die Welt, in der sich das Objekt befindet
     */
    public Welt getWorld(){
        return getWorldOfType(Welt.class);
    }
    

    /**
     * Wird benötigt um bei der Levelgenerierung Überschneidungen mit anderen Objekten zu vermeiden
     * @return Eine Liste der Objekte, mit der sich das Objekt überschneidet
     */
    public java.util.List <Actor> getIntersectingObjects2(java.lang.Class<Actor> cls){
        return super.getIntersectingObjects(cls);
    }
}
