import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font.*;
import java.awt.Color.*;
/**
 * Schrift ermöglicht es einen beliebigen Text auf dem Bildschirm erscheinen zu lassen.
 * Definiert außerdem die Funktion/ den Text der angezeigt werden soll.
 * 
 * @author (Jonas Wischeropp, Julius Schreiber) 
 * @version (30.Juni.2018)
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
    String schriftName;
    
    int BLINKEN;
    int MAXBUCHSTABEN;
    /**
     * Konstruktor der Klasse Schrift
     * @param m Referenz aufs Menue
     * @param s Funktion/Name des Textfeldes
     */
    public Schrift(Menue m, String s){
        menue = m;
        schriftName = s;
        
        textAnzeigen("I", 55);
         
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
            menue.setName(name);
        }
        if(menue.getSpielZustand().equals("Menue")){
            levelAnzeigen(40);
        }
        if(menue.getSpielZustand().equals("Einstellungen")){
            if(schriftName.equals("Musik")){
                textAnzeigen(menue.getMusikVolumen() + "", 50);
            }
            if(schriftName.equals("Sound")){
                textAnzeigen(menue.getSoundVolumen() + "", 50);
            }
        }
        if(menue.getSpielZustand().equals("Spielen")){
            warnungAnzeigen(30);
        }
        if(menue.getSpielZustand().equals("Profil")){
            if(schriftName.equals("Name")){
                textAnzeigen(menue.getName(), 40);
            }
            if(schriftName.equals("Deaths")){
                textAnzeigen("" + menue.getTode(), 40);
            }
            if(schriftName.equals("Kills")){
                textAnzeigen("" + menue.getAbschuesse(), 40);
            }
            if(schriftName.equals("Lost")){
                textAnzeigen("" + menue.getVerloren(), 40);
            }
            if(schriftName.equals("Won")){
                textAnzeigen("" + menue.getGewonnen(), 40);
            }
            if(schriftName.equals("K/D")){
                if(menue.getTode() != 0){
                    textAnzeigen("" + (double)(menue.getAbschuesse())/(double)(menue.getTode()), 40);
                }
                else{
                    if(menue.getAbschuesse() != 0){
                        textAnzeigen("" + menue.getAbschuesse() + ".0", 40);
                    }
                    else{
                        textAnzeigen("0.0", 40);
                    }
                }
            }
            if(schriftName.equals("W/L")){
                if(menue.getVerloren() != 0){
                    textAnzeigen("" + (double)(menue.getGewonnen())/(double)(menue.getVerloren()), 40);
                }
                else{
                    if(menue.getGewonnen() != 0){
                        textAnzeigen("" + menue.getGewonnen() + ".0", 40);
                    }
                    else{
                        textAnzeigen("0.0", 40);
                    }
                }
            }
            if(schriftName.equals("Shield")){
                textAnzeigen("" + menue.getSchild(), 40);
            }
            if(schriftName.equals("Speed")){
                textAnzeigen("" + menue.getSchnelligkeit(), 40);
            }
            if(schriftName.equals("Heard")){
                textAnzeigen("" + menue.getHerz(), 40);
            }
            if(schriftName.equals("Items")){
                textAnzeigen("" + (menue.getHerz() + menue.getSchnelligkeit() + menue.getSchild()), 40);
            }
        }    
    }
    
    /**
     * Mit dieser Methode lässt sich ein belibiger Text in einer belibiegen Größen anzeigen.
     * @param text Text der angezeigt werden soll
     * @param schriftgroesse Schriftgröße des Angezeigten Textes
     */
    public void textAnzeigen(String text, int schriftgroesse){
        bild = new GreenfootImage(600, 100);
        Font font = new Font("BolsterBold", schriftgroesse);
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
     * Methode um die Warnug auf dem Bildschirm anzuzeigen, die der Spieler erhät,
     * wenn er sich zunah am Spielfeldrand befindet.
     * @param schriftgroesse Schriftgröße des Angezeigten Textes
     */
    public void warnungAnzeigen(int schriftgroesse){
        bild = new GreenfootImage(900, 100);
        Font font = new Font("BolsterBold", schriftgroesse);
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
     * Diese Methode ist für das Eingabefeld am Anfang des Spiels verantwortlich, das
     * ermöglicht einen Spielernamen zu wählen/ einzugeben und diesen mit Curser anzeigt.
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
            textAnzeigen(name + "I", 55);
        }
    }
    
    /**
     * Getter um den Namen zu erfahren.
     */
    public String getName(){
        return name;
    }
}
