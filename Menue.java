import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Menue ist die Bediehnoberfläche des Menüs.
 * 
 * @author (Jonas Wischeropp, Julius Schreiber) 
 * @version (5.Mai.2018)
 */
public class Menue extends Actor
{
    GreenfootSound propellerGeraeusch = new GreenfootSound("propellerGeraeusch.WAV");
    GreenfootSound clickGeraeusch = new GreenfootSound("Clicken.WAV");
    GreenfootSound explosionsGeraeusch = new GreenfootSound("explosionsGeraeusch.WAV");
    GreenfootSound warnGeraeusch = new GreenfootSound("Warnsignal.WAV");
    GreenfootSound schussGeraeusch = new GreenfootSound("schuss.WAV");
    GreenfootSound hintergrundmusik = new GreenfootSound("BatmanMusik.WAV");

    int breite;
    int breiteNeu;
    int hoehe;
    int hoeheNeu;
    String spielZustand;
    int skin;
    int level;
    int counter;
    int musikVolumen;
    int soundVolumen;
    boolean grafik;

    int LEVELANZAHL;
    int SKINANZAHL;

    /**
     * Konstruktor der Klasse Menue
     */
    public Menue(){
        spielZustand = "NamenWaehlen";
        skin = 1;
        level = 1;

        musikVolumen = 99;
        soundVolumen = 99;
        grafik= true;

        breite = getImage().getWidth();
        breiteNeu = (int)(getImage().getWidth()*1.1);
        hoehe = getImage().getHeight() ;
        hoeheNeu = (int)(getImage().getHeight()*1.1);

        LEVELANZAHL = 10;
        SKINANZAHL = 13;
    }

    /**
     * Whileschleife die läuft wenn der Runn-Knopf gedrückt wurde
     * und in die alles geschrieben werden muss was ausgeführt werden soll.
     */
    public void act() 
    {

    }  

    /**
     * Spielt ein Clickendes Geräusch ab.
     */
    public void clickGeraeusch(){
        clickGeraeusch.play();
    }

    /**
     * Spielt das Geräusch wenn geschossen wird.
     */
    public void schussGeraeusch(){
        schussGeraeusch.play();
    }

    /**
     * Spielt ein Geräusch einer Explosion ab.
     */
    public void explosionsGeraeusch(){
        explosionsGeraeusch.play();
    }

    /**
     * Spielt das Geräusch zum Propeller ab.
     */
    public void propellerGeraeusch(){
        propellerGeraeusch.playLoop();
    }

    /**
     * Stoppt das Geräusch zum Propeller.
     */
    public void propellerGeraeuschStoppen(){
        propellerGeraeusch.stop();
    }

    /**
     * Spielt das Geräusch zum Warnsignal ab.
     */
    public void warnGeraeusch(){
        if(spielZustand.equals("Spielen")){
            warnGeraeusch.playLoop();
        }
    }

    /**
     * Stoppt das Geräusch zum Warnsignal.
     */
    public void warnGeraeuschStoppen(){
        warnGeraeusch.stop();
    }

    /**
     * Spielt die Hintergrundmusik ab.
     */
    public void hintergrundmusik(){
        hintergrundmusik.playLoop();
    }

    /**
     * Setter um die Lautstaärke von der Hintergrundmusik zu bestimmen.
     * @param neue Lautstärke der Hintergrundmusik
     */
    public void hintergrundmusikVolumen(int v){
        hintergrundmusik.setVolume(v);
        musikVolumen = v;
    }

    /**
     * Getter um die Lautstärke von der Hintergrundmusik zu erfahren.
     */
    public int getMusikVolumen(){
        return musikVolumen;
    }

    /**
     * Setter um die Lautstärke von des Sounds (alles außer der Hintergrungmusik)
     * zu bestimmen.
     * @param v neue Lautstärke des Sounds
     */
    public void soundVolumen(int v){
        clickGeraeusch.setVolume(v);
        schussGeraeusch.setVolume(v);
        explosionsGeraeusch.setVolume(v);
        propellerGeraeusch.setVolume(v);
        warnGeraeusch.setVolume(v);
        soundVolumen = v;
    }

    /**
     * Getter um die Lautstärke vom Sound (alles außer Hintergrundmusik) zu erfahren.
     */
    public int getSoundVolumen(){
        return soundVolumen;
    }

    /**
     * Setter um den SpielZustand festzulegen.
     * @param z neuer SpielZustand
     */
    public void setSpielZustand(String z){
        spielZustand = z;
    }

    /**
     * Getter um den SpielZustand zu erfahren.
     */
    public String getSpielZustand(){
        return spielZustand;
    }

    /**
     * Setter um den Skin festzulegen.
     * @param s neuer Skin
     */
    public void setSkin(int s){
        skin = s;
    }

    /**
     * Getter um den Skin zu erfahren.
     */
    public int getSkin(){
        return skin;
    }

    /**
     * Setter um das Level festzulegen.
     * @param l neues Level
     */
    public void setLevel(int l){
        level = l;
    }

    /**
     * Getter um das Level zu erfahren.
     */
    public int getLevel(){
        return level;
    }
    
    /**
     * Getter um die maximale Levelanzahl zu erfahren.
     */
    public int getLevelanzahl(){
        return LEVELANZAHL;
    }

    /**
     * Setter der bestimmt ob sich der Propeller drehen soll (true = drehen).
     */
    public void setGrafik(boolean g){
        grafik = g;
    }

    /**
     * Getter zu erfahren ob sich der Propeller dreht.
     */
    public boolean getGrafik(){
        return grafik;
    }
}
