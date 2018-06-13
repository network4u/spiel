import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font.*;
import java.awt.Color.*;
/**
 * Schrift ermöglicht es eine belibiegen Text auf dem Bildschirm erscheinen zu lassen.
 * 
 * @author (Jonas Wischeropp, Julius Schreiber) 
 * @version (5.Mai.2018)
 */
public class Schrift extends Actor
{
    GreenfootImage bild;
    Menue menue;
    
    String name;
    String taste;
    int buchstaben;
    int counter;
    boolean strich;
    
    int BLINKEN;
    int MAXBUCHSTABEN;
    /**
     * Konstruktor der Klasse Schrift
     * @param m Referenz aufs Menue
     */
    public Schrift(Menue m){
        menue = m;
        
        textAnzeigen("|", 55);
         
        name = "";
        buchstaben = 0;
        counter = 0;
        strich = true;
        
        BLINKEN = 40;
        MAXBUCHSTABEN = 13;
    }
    
    /**
     * Whileschleife die läuft wenn der Runn-Knopf gedrückt wurde
     * und in die alles geschrieben werden muss was ausgeführt werden soll.
     */
    public void act() 
    {
        if(menue.getSpielZustand().equals("NamenWaehlen")){
            nameEingeben();
        }
        if(menue.getSpielZustand().equals("Menue")){
            levelAnzeigen(40);
        }
        if(menue.getSpielZustand().equals("Einstellungen")){
            if(getY() == 239){
                textAnzeigen(menue.getMusikVolumen() + "", 50);
            }
            if(getY() == 386){
                textAnzeigen(menue.getSoundVolumen() + "", 50);
            }
        }
        if(menue.getSpielZustand().equals("Spielen")){
            warnungAnzeigen(30);
        }
    }    
    
    /**
     * Mit dieser Methode lässt sich ein belibiger Text in einer belibiegen Größen anzeigen.
     * @param text Text der angezeigt werden soll
     * @param schriftgroesse Schriftgröße des Angezeigten Textes
     */
    public void textAnzeigen(String text, int schriftgroesse){
        bild = new GreenfootImage(600, 100);
        Font font = new Font("BoldsterBold Fett", schriftgroesse);
        bild.setColor(Color.BLACK);
        bild.clear();
        bild.setFont(font);
        bild.drawString(text, 0, 50);
        setImage(bild);
    }
    
    /**
     * Methode um das Ausgewählte Level im Menü anzuzeigen.
     * @param schriftgroesse Schriftgröße des Angezeigten Textes
     */
    public void levelAnzeigen(int schriftgroesse){
        textAnzeigen("Level " + menue.getLevel(), schriftgroesse);
    }
    
    /**
     * Mit dieser Methode lässt sich ein belibiger Text in einer belibiegen Größen anzeigen.
     * @param schriftgroesse Schriftgröße des Angezeigten Textes
     */
    public void warnungAnzeigen(int schriftgroesse){
        bild = new GreenfootImage(900, 100);
        Font font = new Font("BoldsterBold Fett", schriftgroesse);
        bild.setColor(Color.RED);
        bild.clear();
        bild.setFont(font);
        if(getY() == 100){
            bild.drawString("WARNING!!!", 0, 50);
        }
        else{
            bild.drawString("DO NOT ENTER THE RED ZONE!!!", 0, 50);
        }
        setImage(bild);
    }
    
    /**
     * Mit dieser Methode lässt sich ein Text (in dem Fall der Name des Spielers) eingeben
     * und anzeigen.
     */
    public void nameEingeben(){
        taste = Greenfoot.getKey();
        if(taste != null){
            if(taste.equals("enter")
            || taste.equals("up")
            || taste.equals("down")
            || taste.equals("right")
            || taste.equals("left")
            || taste.equals("tab")
            || taste.equals("shift")
            || taste.equals("control")
            || taste.equals("escape")){
                    taste = "";
            }
            if(taste.equals("backspace")){
                if (name.length() > 0) {
                    name = name.substring(0, name.length() - 1);
                    buchstaben = buchstaben - 1;
                }
                taste = "";
            }
            if(buchstaben <= MAXBUCHSTABEN){
                if(taste.equals("space")){
                    taste = " ";
                }
                if(!taste.equals("")){
                    buchstaben++;
                    name = name + taste;
                }
            }
        }
        counter++;
        if(counter == BLINKEN){
            if(strich){
                strich = false;
            }
            else{
                strich = true;
            }
            counter = 0;
        }
        if(strich){
            textAnzeigen(name, 55);
        }
        else{
            textAnzeigen(name + "|", 55);
        }
    }
}
