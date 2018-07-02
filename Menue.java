import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Menue ist der Hintergrund für alle Menüs (Einstellung, Hauptmenü, Pause, GameOverBildschirm,...).
 * Außerdem ist Menue die Klasse in der Sämtliche Informationen,
 * wie das ausgewählte Level, die Lautstärke, der Spielzustand, die Erfolge (Informationen, die im Profil angezeigt werden),...
 * gespeichert werden.
 * Des Weiteren ist es die Klasse die für alle Sounds verantwortlich ist.
 * 
 * @author (Jonas Wischeropp, Julius Schreiber) 
 * @version (30.Juni.2018)
 */
public class Menue extends Actor
{
    GreenfootSound propellerGeraeusch = new GreenfootSound("propellerGeraeusch.WAV");
    GreenfootSound clickGeraeusch = new GreenfootSound("Clicken.WAV");
    GreenfootSound explosionsGeraeusch = new GreenfootSound("explosionsGeraeusch.WAV");
    GreenfootSound warnGeraeusch = new GreenfootSound("Warnsignal.WAV");
    GreenfootSound schussGeraeusch = new GreenfootSound("schuss.WAV");
    GreenfootSound hintergrundmusik = new GreenfootSound("BatmanMusik.WAV");
    GreenfootSound winGeraeusch = new GreenfootSound("WinSound.WAV");
    
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
    int tode;
    int abschuesse;
    int verloren;
    int gewonnen;
    int heard;
    int shield;
    int speed;
    String name;

    int LEVELANZAHL;
    int SKINANZAHL;

    /**
     * Konstruktor der Klasse Menue
     */
    public Menue(){
        spielZustand = "NamenWaehlen";
        
        skin = 1;
        level = 1;
        
        tode = 0;
        abschuesse = 0;
        verloren = 0;
        gewonnen = 0;
        heard = 0;
        shield = 0;
        speed = 0;

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
     * Spielt ein Geräusch ab, dass kommt wenn man Gewinnt.
     */
    public void winGeraeusch(){
        winGeraeusch.play();
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
     * Stoppt das die Hintergrundmusik.
     */
    public void hintergrundmusikStoppen(){
        hintergrundmusik.stop();
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
        winGeraeusch.setVolume(v);
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
     * Getter um zu erfahren ob sich der Propeller dreht.
     */
    public boolean getGrafik(){
        return grafik;
    }
    
    /**
     * Fügt einen Tod zu dem Counter der die tode zählt hinzu.
     */
    public void todePlus(){
        tode++;
    }
    /**
     * Getter um die Anzahl der Tode zu erfahren.
     */
    public int getTode(){
        return tode;
    }
    
    /**
     * Fügt einen Abschüss zu dem Counter der die Abschusse zählt hinzu.
     */
    public void abschuessePlus(){
        abschuesse++;
    }
    /**
     * Getter um die Anzahl der Abschüsse zu erfahren.
     */
    public int getAbschuesse(){
        return abschuesse;
    }
    
    /**
     * Fügt eine verlore Runde zu dem Counter der die tode zählt hinzu.
     */
    public void verlorenPlus(){
        verloren++;
    }
    /**
     * Getter um die Anzahl der verloren Runden zu erfahren.
     */
    public int getVerloren(){
        return verloren;
    }
    
    /**
     * Fügt einen Abschuss zu dem Counter der die gewonnen Runden zählt hinzu.
     */
    public void gewonnenPlus(){
        gewonnen++;
    }
    /**
     * Getter um die Anzahl der gewonnen Runden zu erfahren.
     */
    public int getGewonnen(){
        return gewonnen;
    }
    
    
    /**
     * Fügt einen Herz zu dem Counter der die eingesammelten Herzen zählt hinzu.
     */
    public void herzPlus(){
        heard++;
    }
    /**
     * Getter um die Anzahl der eingesammelten Herzen zu erfahren.
     */
    public int getHerz(){
        return heard;
    }
    
    /**
     * Fügt einen Schilder zu dem Counter der die eingesammelten Schilder zählt hinzu.
     */
    public void schildPlus(){
        shield++;
    }
    /**
     * Getter um die Anzahl der eingesammelten Schilder zu erfahren.
     */
    public int getSchild(){
        return shield;
    }
    
    /**
     * Fügt einen Schnelligkeit zu dem Counter der die eingesammelten Schnelligkeiten zählt hinzu.
     */
    public void schnelligkeitPlus(){
        speed++;
    }
    /**
     * Getter um die Anzahl der eingesammelten Schilder zu erfahren.
     */
    public int getSchnelligkeit(){
        return speed;
    }

    /**
     * Setter der den Name festlegt.
     */
    public void setName(String n){
        name = n;
    }
    /**
     * Getter um den Namen zu erfahren.
     */
    public String getName(){
        return name;
    }
}
