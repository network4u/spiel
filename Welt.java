import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Welt sorgt für die Anfangszustände.
 * 
 * @author (Jonas Wischeropp, Julius Schreiber) 
 * @version (5.Mai.2018)
 */
public class Welt extends World
{
    Menue menue = new Menue();
    LebensAnzeige lebensAnzeige = new LebensAnzeige(menue);
    Flugzeug flugzeug = new Flugzeug(lebensAnzeige, menue);
    Propeller propeller = new Propeller(flugzeug, menue);
    Boden boden = new Boden();
    BodenRot bodenRot = new BodenRot(boden);
    BodenDurchsichtig bodenDurchsichtig = new BodenDurchsichtig(boden, bodenRot,
            flugzeug, menue);
    Schrift schrift = new Schrift(menue);
    Schrift schrift2 = new Schrift(menue);
    Boot boot;
    Geschuetz geschuetz;

    java.util.ArrayList<shot> shots;
    java.util.ArrayList<Tasten> tastenListe;
    java.util.ArrayList<Boot> bootListe;
    java.util.ArrayList<Geschuetz> geschuetzListe;

    int counter;
    int FLACKERN;
    boolean flackern;
    /**
     * Konstruktor der Klasse Welt
     */
    public Welt()
    {    
        //Generiert eine neu Welt mit den Maßen 900 x 600 und der Pixelgröße 1.
        super(900, 600, 1, false); 

        shots = new java.util.ArrayList<shot>();
        tastenListe = new java.util.ArrayList<Tasten>();
        bootListe = new java.util.ArrayList<Boot>();
        geschuetzListe = new java.util.ArrayList<Geschuetz>();

        //Legt die Riehenfolge der verschieden Ebenen fest
        //(erstes = Oben, letzes = Unten).
        setPaintOrder(Tasten.class, Schrift.class, LebensAnzeige.class, Flugzeug.class,
            Propeller.class, Menue.class, attacking.class, shot.class, Geschuetz.class,
            Boot.class, Boden.class, BodenRot.class, BodenDurchsichtig.class);

        namesMenueErstellen();

        //Ertellt am Anfang einen Boden (und somit BodenDurchsichtig)
        addObject(boden, getWidth()/2, getHeight()/2);
        addObject(bodenRot, getWidth()/2, getHeight()/2);
        addObject(bodenDurchsichtig, getWidth()/2, getHeight()/2);

        counter = 0;
        flackern = true;

        FLACKERN = 10;
    }

    /**
     * Whileschleife die läuft wenn der Runn-Knopf gedrückt wurde
     * und in die alles geschrieben werden muss was ausgeführt werden soll.
     */
    public void act() 
    {
        if(menue.getSpielZustand().equals("Spielen")
        && bootListe.size() == 0){
            winErstellen();
        }

        if(menue.getSpielZustand().equals("Verloren")){
            gameOverErstellen();
        }

        if(menue.getSpielZustand().equals("Spielen")){
            if(boden.getY() >= 1269
            || boden.getY() <= -678
            || boden.getX() >= 1884
            || boden.getX() <= -967){
                counter++;
                if(counter == FLACKERN){
                    if(flackern){
                        flackern = false;
                    }
                    else{
                        flackern = true;
                    }
                    counter = 0;
                }
                if(menue.getGrafik()){
                    if(flackern){
                        addObject(schrift, 840, 100);
                        addObject(schrift2, 670, 150);
                        menue.warnGeraeusch();
                    }
                    else{
                        removeObject(schrift);
                        removeObject(schrift2);
                    }
                }
                else{
                    addObject(schrift, 840, 100);
                    addObject(schrift2, 670, 150);
                    menue.warnGeraeusch();
                }
            }
            else{
                removeObject(schrift);
                removeObject(schrift2);
                counter = 0;
                menue.warnGeraeuschStoppen();
            }
        }  
    }

    /**
     * Ertellt alle Objekte die für die Oberfläche des Menüs wichtig sind
     * und setzt das Spieler Flugzeug richtig.
     */
    public void menueErstellen(){
        allesLoeschen();
        flugzeug.setLocation(getWidth()/2, 232);
        flugzeug.setRotation(0);

        bodenDurchsichtig.setLocation(getWidth()/2, getHeight()/2);

        addObject(menue, getWidth()/2, getHeight()/2);

        menue.setImage("Menue.png");

        tasteErstellen(140, 232, "SkinLinksTaste");
        tasteErstellen(getWidth()-140, 232, "SkinRechtsTaste");
        tasteErstellen(140, 412, "LevelLinksTaste");
        tasteErstellen(getWidth()-140, 412, "LevelRechtsTaste");
        addObject(schrift, 693, 424);
        tasteErstellen(getWidth()/2, 525, "StartTaste");
        tasteErstellen(105, 50, "SettingsTaste");
        tasteErstellen(getWidth() - 105, 50, "ProfilTaste");

        menue.setSpielZustand("Menue");
    }

    /**
     * Ertellt alle Objekte die für die Oberfläche des Spiels wichtig sind
     * und setzt das Spieler Flugzeug richtig.
     */
    public void spielErstellen(){
        allesLoeschen();
        flugzeug.setLocation(getWidth()/2, getHeight()/2);
        lebensAnzeige.setLeben(3);

        tasteErstellen(40, 40, "PauseTaste");
        addObject(lebensAnzeige, 800, 40);

        levelErstellen();
        addObject(new attacking(getWidth()/4, getHeight()/4), getWidth()/4, getHeight()/4);

        menue.setSpielZustand("Spielen");
    }

    /**
     * Ertellt alle Objekte die für die Oberfläche des PauseMenüs wichtig sind
     * und löscht die PauseTaste.
     */
    public void pauseErstellen(){
        allesLoeschen();
        addObject(menue, getWidth()/2, getHeight()/2);
        menue.setImage("PauseMenue.png");

        tasteErstellen(590, 515, "MenueTaste");
        tasteErstellen(630, 375, "RestartTaste");
        tasteErstellen(670, 235, "ResumeTaste");

        menue.setSpielZustand("Pause");
    }

    /**
     * Ertellt alle Objekte die für die Oberfläche des WinMenüs wichtig sind
     * und löscht die PauseTaste.
     */
    public void winErstellen(){
        allesLoeschen();
        addObject(menue, getWidth()/2, getHeight()/2);
        menue.setImage("PauseMenue.png");

        tasteErstellen(590, 515, "MenueTaste");
        tasteErstellen(630, 375, "RestartTaste");
        if(menue.getLevel() != menue.getLevelanzahl()){
            tasteErstellen(670, 235, "NextTaste");
        }

        menue.setSpielZustand("Win");
    }

    /**
     * Ertellt alle Objekte die für die Oberfläche des WinMenüs wichtig sind
     * und löscht die PauseTaste.
     */
    public void gameOverErstellen(){
        allesLoeschen();
        addObject(menue, getWidth()/2, getHeight()/2);
        menue.setImage("PauseMenue.png");
        tasteErstellen(590, 515, "MenueTaste");
        tasteErstellen(630, 375, "RestartTaste");

        menue.setSpielZustand("GameOver");
    }

    /**
     * Löscht alle Objekte bis auf das Spieler Flugzeug und den Boden.
     */
    public void allesLoeschen(){
        removeObject(menue);
        removeObject(schrift);
        removeObject(schrift2);
        removeObject(lebensAnzeige);

        for(int i=0; i < tastenListe.size(); i++){
            removeObject(tastenListe.get(i));
        }
        tastenListe.clear();
    }

    /**
     * Startet das Level von vorne.
     */
    public void restart(){
        menueErstellen();
        spielErstellen();
    }

    /**
     * Startet das nächste Level von vorne.
     */
    public void next(){
        menueErstellen();
        menue.setLevel(menue.getLevel()+1);
        spielErstellen();
    }

    /**
     * Erstellt das Menü mit dem Flugzeug.
     */
    public void menueUndFlugzeug(){
        addObject(flugzeug, getWidth()/2, 232);
        addObject(propeller, getWidth()/2, 232);
        menueErstellen();
    }

    /**
     * Erstellt ein Boot mit einem Geschütz
     * (kann immer im rechten Wnkel ausgerichtet werden).
     * @param x x-Positon
     * @param y y-Positon
     * @param r Ausrichtung des Bootes (möglich: 0, 90, 180, 270)
     */
    public void bootErstellen(int x, int y, int r){
        boot = new Boot(x, y, r, menue, bodenDurchsichtig);
        geschuetz = new Geschuetz(boot, flugzeug);
        bootListe.add(boot);
        geschuetzListe.add(geschuetz);
        addObject(boot, x, y);
        addObject(geschuetz, x, y);
    }

    /**
     * Löscht alle Gegner (Boote und Flugzeuge).
     */
    public void gegnerLoeschen(){
        for(int i=0; i < bootListe.size(); i++){
            removeObject(geschuetzListe.get(i));
            removeObject(bootListe.get(i));
        }
        bootListe.clear();
        geschuetzListe.clear();
    }

    /**
     * Erstellt die Oberfläche für die Einstellungen.
     */
    public void settingsErstellen(){
        allesLoeschen();
        removeObject(flugzeug);
        removeObject(propeller);
        addObject(menue, getWidth()/2, getHeight()/2);
        menue.setImage("Einstellungen.png");
        tasteErstellen(138, 50, "BackTaste");
        tasteErstellen(menue.getMusikVolumen()*3+508, 227, "Regler");
        tasteErstellen(menue.getSoundVolumen()*3+508, 374, "Regler");
        String kastenName;
        if(menue.getGrafik()){
            kastenName = "KastenHaken";
        }
        else{
            kastenName = "Kasten";
        }
        tasteErstellen(117, 272, kastenName);
        addObject(schrift, 731, 239);
        addObject(schrift2, 731, 386);

        menue.setSpielZustand("Einstellungen");
    }

    /**
     * Erstellt die Oberfläche für die Namenswahl.
     */
    public void namesMenueErstellen(){
        allesLoeschen();
        addObject(menue, getWidth()/2, getHeight()/2);
        menue.setImage("Name.png");
        tasteErstellen(getWidth()/2, 371, "AcceptTaste");
        addObject(schrift, 494, 278);

        menue.setSpielZustand("NamenWaehlen");
    }

    /**
     * Erstellt die Oberfläche für das Profil.
     */
    public void profilErstellen(){
        allesLoeschen();
        removeObject(flugzeug);
        removeObject(propeller);
        addObject(menue, getWidth()/2, getHeight()/2);
        menue.setImage("Profil.png");
        tasteErstellen(138, 50, "BackTaste");

        menue.setSpielZustand("Profil");
    }

    public void newshot(int[] apos, int rotation){
        shot make= new shot(apos,rotation);
        int[] position = make.getPosition();
        shots.add(make);
        addObject(make, position[0], position[1]);
    }

    /**
     * Erstellt eine Taste.
     * @param x x-Positon
     * @param y y-Positon
     * @param tastenName Funktion, die die Taste erfüllt
     */
    public void tasteErstellen(int x, int y, String tastenName){
        Tasten tasten = new Tasten(menue, tastenName);
        tastenListe.add(tasten);
        addObject(tasten, x, y);
    }

    public void levelErstellen(){
        int randomRotation = 0;
        int randomNumber = Greenfoot.getRandomNumber(3);
        if(randomNumber == 0){
            randomRotation = 0;
        }
        if(randomNumber == 1){
            randomRotation = 90;
        }
        if(randomNumber == 2){
            randomRotation = 180;
        }
        if(randomNumber == 3){
            randomRotation = 270;
        }
        for(int i=0; i < menue.getLevel(); i++){
            bootErstellen(Greenfoot.getRandomNumber(2650)-867,
                Greenfoot.getRandomNumber(1746)-578, randomRotation);
        }
    }
}
