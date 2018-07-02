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
    Boot boot;
    Geschuetz geschuetz;
    PowerUps powerUps;

    java.util.ArrayList<Shot> shots;
    java.util.ArrayList<Tasten> tastenListe;
    java.util.ArrayList<Schrift> schriftListe;
    java.util.ArrayList<Boot> bootListe;
    java.util.ArrayList<attacking> attackingListe;
    java.util.ArrayList<Geschuetz> geschuetzListe;
    java.util.ArrayList<PowerUps> powerUpsListe;
    
    boolean warnung;
    /**
     * Konstruktor der Klasse Welt
     */
    public Welt()
    {    
        //Generiert eine neu Welt mit den Maßen 900 x 600 und der Pixelgröße 1.
        super(900, 600, 1, false); 

        shots = new java.util.ArrayList<Shot>();
        schriftListe = new java.util.ArrayList<Schrift>();
        tastenListe = new java.util.ArrayList<Tasten>();
        bootListe = new java.util.ArrayList<Boot>();
        attackingListe = new java.util.ArrayList<attacking>();
        geschuetzListe = new java.util.ArrayList<Geschuetz>();
        powerUpsListe = new java.util.ArrayList<PowerUps>();

        //Legt die Riehenfolge der verschieden Ebenen fest
        //(erstes = Oben, letzes = Unten).
        setPaintOrder(Tasten.class, Schrift.class, LebensAnzeige.class, Flugzeug.class,
            Propeller.class, Menue.class, attacking.class, Shot.class, PowerUps.class,
            Geschuetz.class, Boot.class, Boden.class, BodenRot.class,
            BodenDurchsichtig.class);

        namesMenueErstellen();

        //Ertellt am Anfang einen Boden (und somit BodenDurchsichtig)
        addObject(boden, getWidth()/2, getHeight()/2);
        addObject(bodenRot, getWidth()/2, getHeight()/2);
        addObject(bodenDurchsichtig, getWidth()/2, getHeight()/2);
        
        warnung = false;
    }

    /**
     * Whileschleife die läuft wenn der Runn-Knopf gedrückt wurde
     * und in die alles geschrieben werden muss was ausgeführt werden soll.
     */
    public void act() 
    {
        if(menue.getSpielZustand().equals("Spielen")
        && bootListe.size() == 0
        && attackingListe.size() == 0){
            menue.winGeraeusch();
            winErstellen();
            geschuetzListe.clear();
        }

        if(menue.getSpielZustand().equals("Verloren")){
            menue.todePlus();
            menue.verlorenPlus();
            menue.explosionsGeraeusch();
            gameOverErstellen();
        }

        if(menue.getSpielZustand().equals("Spielen")){
            if(boden.getY() >= 1269
            || boden.getY() <= -678
            || boden.getX() >= 1884
            || boden.getX() <= -967){
                menue.warnGeraeusch();
                if(!warnung){
                    schriftErstellen(840, 100, "WarnungOben");
                    schriftErstellen(670, 150, "WarnungUnten");
                    warnung = true;
                }
            } 
            else{
                    schriftLoeschen();
                    warnung = false;
                    menue.warnGeraeuschStoppen();
            }
        }
        else{
            menue.warnGeraeuschStoppen();
        }
    }

    /**
     * Erstellt alle Objekte die für die Oberfläche des Menüs wichtig sind
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
        schriftErstellen(693, 424, "Level");
        tasteErstellen(getWidth()/2, 525, "StartTaste");
        tasteErstellen(105, 50, "SettingsTaste");
        tasteErstellen(getWidth() - 105, 50, "ProfilTaste");

        menue.setSpielZustand("Menue");
    }

    /**
     * Erstellt alle Objekte die für die Oberfläche des Spiels wichtig sind
     * und setzt das Spieler Flugzeug richtig.
     */
    public void spielErstellen(){
        allesLoeschen();
        
        tasteErstellen(40, 40, "PauseTaste");
        addObject(lebensAnzeige, 800, 40);
        
        menue.setSpielZustand("Spielen");
    }
    
    /**
     * Erstellt alle Objekte die für die Oberfläche des Spiels wichtig sind
     * und setzt das Spieler Flugzeug richtig.
     */
    public void neuesSpielErstellen(){
        flugzeug.setLocation(getWidth()/2, getHeight()/2);
        flugzeug.setRotation(0);
        bodenDurchsichtig.setLocation(getWidth()/2, getHeight()/2);
        
        gegnerLoeschen();
        spielErstellen();
        
        lebensAnzeige.setLeben(3);
        levelErstellen();
        }

    /**
     * Erstellt alle Objekte die für die Oberfläche des PauseMenüs wichtig sind
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
        menue.setImage("WinMenue.png");

        tasteErstellen(590, 515, "MenueTaste");
        tasteErstellen(630, 375, "RestartTaste");
        if(menue.getLevel() != menue.getLevelanzahl()){
            tasteErstellen(670, 235, "NextTaste");
        }

        menue.setSpielZustand("Win");
    }

    /**
     * Erstellt alle Objekte die für die Oberfläche des GameOverMenüs wichtig sind
     * und löscht die PauseTaste.
     */
    public void gameOverErstellen(){
        allesLoeschen();
        addObject(menue, getWidth()/2, getHeight()/2);
        menue.setImage("GameOverMenue.png");
        tasteErstellen(590, 515, "MenueTaste");
        tasteErstellen(630, 375, "RestartTaste");

        menue.setSpielZustand("GameOver");
    }

    /**
     * Löscht alle Objekte bis auf das Spieler Flugzeug und den Boden.
     */
    public void allesLoeschen(){
        removeObject(menue);
        removeObject(lebensAnzeige);
        
        schriftLoeschen();
        
        for(int i=0; i < tastenListe.size(); i++){
            removeObject(tastenListe.get(i));
        }
        tastenListe.clear();
    }

    /**
     * Startet das nächste Level von vorne.
     */
    public void next(){
        menue.setLevel(menue.getLevel()+1);
        neuesSpielErstellen();
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
     * Löscht alle objekte der Klasse Schrift.
     */
    public void schriftLoeschen(){
        for(int i=0; i < schriftListe.size(); i++){
            removeObject(schriftListe.get(i));
        }
        schriftListe.clear();
    }
    
    /**
     * Löscht alle Gegner (Boote und Flugzeuge) und PowerUps.
     */
    public void gegnerLoeschen(){
        int geschuetzAnzahl = geschuetzListe.size();
        int bootAnzahl = bootListe.size();
        int attackingAnzahl = attackingListe.size();
        int powerUpsAnzahl = powerUpsListe.size();
        for(int i=0; i < geschuetzAnzahl; i++){
            removeObject(geschuetzListe.get(i));
        }
        geschuetzListe.clear();
        for(int i=0; i < bootAnzahl; i++){
            removeObject(bootListe.get(i));
        }
        bootListe.clear();
        for(int i=0; i < attackingAnzahl; i++){
            removeObject(attackingListe.get(i));
        }
        attackingListe.clear();
        for(int i=0; i < powerUpsAnzahl; i++){
            removeObject(powerUpsListe.get(i));
        }
        powerUpsListe.clear();
        
        bodenDurchsichtig.setGeschwindigkeit(2);
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
        schriftErstellen(731, 239, "Musik");
        schriftErstellen(731, 386, "Sound");

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
        schriftErstellen(494, 278, "Namen");

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
        
        schriftErstellen(551, 158, "Name");
        schriftErstellen(589, 247, "Deaths");
        schriftErstellen(589, 305, "Lost");
        schriftErstellen(825, 247, "Kills");
        schriftErstellen(825, 305, "Won");
        schriftErstellen(1050, 247, "K/D");
        schriftErstellen(1050, 305, "W/L");
        schriftErstellen(825, 403, "Items");
        schriftErstellen(589, 450, "Shield");
        schriftErstellen(825, 450, "Heard");
        schriftErstellen(1070, 450, "Speed");

        menue.setSpielZustand("Profil");
    }

    /**
     * Erstellt einen neuen Schuss in der Welt
     * @param x Der X-Wert der Position, in der der Schuss erstellt werden soll
     * @param y Der Y-Wert der Position, in der der Schuss erstellt werden soll
     * @param rotation Die Flugrichtung des Schusses
     * @param from Referenz auf das Objekt, das die Erstellung des Schusses in Auftrag gegeben hat
     */
    
    public void newShot(int x, int y, int rotation, Actor from){
        
        menue.schussGeraeusch();
        Shot make= new Shot(x,y,rotation,from);
        int[] position = make.getPosition();
        shots.add(make);
        addObject(make, position[0], position[1]);
    }

    /**
     * Erstellt eine Taste.
     * @param x x-Positon
     * @param y y-Positon
     * @param tastenName Funktion, die die Taste erfüllt.
     */
    public void tasteErstellen(int x, int y, String tastenName){
        Tasten tasten = new Tasten(menue, tastenName);
        tastenListe.add(tasten);
        addObject(tasten, x, y);
    }
    
    /**
     * Erstellt eine Feld um einen Text anzuzeigen.
     * @param x x-Positon
     * @param y y-Positon
     * @param schriftName Funktion, die die Schrift erfüllt.
     */
    public void schriftErstellen(int x, int y, String schriftName){
        Schrift schrift = new Schrift(menue, schriftName);
        schriftListe.add(schrift);
        addObject(schrift, x, y);
    }

    /**
     * Erstellt soviele Boote, Flugzeuge und PowerUps, wie hoch das Level ist.
     */
    public void levelErstellen(){
        for(int i = 0; i < menue.getLevel();i++){
            attacking neuer= new attacking(1,2);
            this.addObject(neuer,1,2);
            attackingListe.add(neuer);
            do{
                int ranx= Greenfoot.getRandomNumber(2810)-967;
                int rany= Greenfoot.getRandomNumber(1947)-678;
                neuer.setLocation2(ranx,rany);
            }while(neuer.getIntersectingObjects2(Actor.class).size()!=2);
            
        }
        
        for(int i = 0; i < menue.getLevel();i++){
            int ranRotation = Greenfoot.getRandomNumber(3)*90;
            Boot neuer= new Boot(1, 2, ranRotation, menue, bodenDurchsichtig);
            bootListe.add(neuer);
            this.addObject(neuer,1,2);
            int ranx;
            int rany;
            do{
                ranx= Greenfoot.getRandomNumber(2810)-967;
                rany= Greenfoot.getRandomNumber(1947)-678;
                neuer.setLocation2(ranx,rany);
            }while(neuer.getIntersectingObjects2(Actor.class).size()!=2);
            
            Geschuetz geschuetz = new Geschuetz(neuer, flugzeug);
            geschuetzListe.add(geschuetz);
            this.addObject(geschuetz, ranx, rany);
        }
        
        for(int i = 0; i < menue.getLevel();i++){
            int randomItemNumber = Greenfoot.getRandomNumber(3);
            String randomItem = "Schild";
            if(randomItemNumber == 0){
                randomItem = "Schild";
            }
            if(randomItemNumber == 1){
                randomItem = "Schnelligkeit";
            }
            if(randomItemNumber == 2){
                randomItem = "Herz";
            }
            PowerUps neuer= new PowerUps(bodenDurchsichtig, randomItem, 1,2);
            this.addObject(neuer,1,2);
            powerUpsListe.add(neuer);
            do{
                int ranx= Greenfoot.getRandomNumber(2810)-967;
                int rany= Greenfoot.getRandomNumber(1947)-678;
                neuer.setLocation2(ranx,rany);
            }while(neuer.getIntersectingObjects2(Actor.class).size()!=2);
        }
    }
}
