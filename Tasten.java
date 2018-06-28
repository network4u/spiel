import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Tatsen ist die Klasse die für alle Knöpfe im Spiel verantwortlich ist.
 * 
 * @author (Jonas Wischeropp, Julius Schreiber) 
 * @version (5.Mai.2018)
 */
class Tasten extends Menue
{
    Menue menue;
    String tastenName;
    /**
     * Konstruktor der Klasse Tasten
     * @param m Referenz aufs Menue
     * @param tn Funktion/ Name der Taste
     */
    public Tasten(Menue m, String tn){
        menue = m;

        tastenName = tn;
        setImage(tn + ".png");
        
        mausNeuScale();
    }

    /**
     * Whileschleife die läuft wenn der Runn-Knopf gedrückt wurde
     * und in die alles geschrieben werden muss was ausgeführt werden soll.
     */
    public void act() 
    { 
        mausScale();

        if(Greenfoot.mouseClicked(this)){
            clickGeraeusch();
            
            if(tastenName.equals("AcceptTaste")){
                if(!menue.getName().equals("")){
                    welt().menueUndFlugzeug();
                }
            }

            if(tastenName.equals("BackTaste")){
                welt().menueUndFlugzeug();
            }
            
            if(tastenName.equals("KastenHaken")
            || tastenName.equals("Kasten")){
                if(menue.getGrafik()){
                    menue.setGrafik(false);
                    setImage("Kasten.png");
                    mausNeuScale();
                }
                else{
                    menue.setGrafik(true);
                    setImage("KastenHaken.png");
                    mausNeuScale();
                }
            }
            
            if(tastenName.equals("SettingsTaste")){
                welt().settingsErstellen();
            }
            
            if(tastenName.equals("ProfilTaste")){
               welt().profilErstellen();
            }
            
            if(tastenName.equals("StartTaste")
            || tastenName.equals("RestartTaste")){
                welt().neuesSpielErstellen();
            }
            
            if(tastenName.equals("SkinLinksTaste")){
                if(menue.getSkin() - 1 >= 1){
                    menue.setSkin(menue.getSkin() - 1);
                }
                else{
                    menue.setSkin(SKINANZAHL);
                }
            }
            
            if(tastenName.equals("SkinRechtsTaste")){
                if(menue.getSkin() + 1 <= SKINANZAHL){
                    menue.setSkin(menue.getSkin() + 1);
                }
                else{
                    menue.setSkin(1);
                }
            }
            
            if(tastenName.equals("LevelLinksTaste")){
                if(menue.getLevel() - 1 >= 1){
                    menue.setLevel(menue.getLevel() - 1);
                }
                else{
                    menue.setLevel(LEVELANZAHL);
                }
            }
            
            if(tastenName.equals("LevelRechtsTaste")){
                if(menue.getLevel() + 1 <= LEVELANZAHL){
                    menue.setLevel(menue.getLevel() + 1);
                }
                else{
                    menue.setLevel(1);
                }
            }

            if(tastenName.equals("PauseTaste")){
                welt().pauseErstellen();
            }

            if(tastenName.equals("MenueTaste")){
                welt().gegnerLoeschen();
                welt().menueErstellen();
            }
    
            if(tastenName.equals("NextTaste")){
                welt().gegnerLoeschen();
                welt().next();
            }
            
            if(tastenName.equals("ResumeTaste")){
                welt().neuesSpielErstellen();
            }
        }

        if(tastenName.equals("Regler")){
            reglerBewegen();
            if(getY() == 227){
                menue.hintergrundmusikVolumen((int)((getX()-508)/3));
            }
            if(getY() == 374){
                menue.soundVolumen((int)((getX()-508)/3));
            }
        }

        // Sorgt dafür, dass manche aktionen auch mit der Tastatur ausgefürt
        // werden können.
        if(Greenfoot.isKeyDown("enter")){
            if(menue.getSpielZustand().equals("Menue")){
                welt().neuesSpielErstellen();
            }
            if(menue.getSpielZustand().equals("NamenWaehlen")){
                welt().menueUndFlugzeug();
            }
        }
        if(Greenfoot.isKeyDown("escape")){
            if(menue.getSpielZustand().equals("Spielen")){
                welt().pauseErstellen();
            }
        }
    }  
    
    /**
     * Vergrößert ein Object wenn sich der Mauszeiger auf diesen befindet.
     */
    public void mausScale(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null){
            if(mouse.getX() <= getX() + breite/2
            &&
            mouse.getY() >= getY() - hoehe/2
            &&
            mouse.getX() >= getX() - breite/2
            &&
            mouse.getY() <= getY() + hoehe/2){
                getImage().scale(breiteNeu, hoeheNeu);
            }
            else{
                getImage().scale(breite, hoehe);
            }
        }
    }
    
    /**
     * Legt die vergrößerung für die Methode mausScale() fest.
     */
    public void mausNeuScale(){
        breite = getImage().getWidth();
        breiteNeu = (int)(getImage().getWidth()*1.1);
        hoehe = getImage().getHeight() ;
        hoeheNeu = (int)(getImage().getHeight()*1.1);
    }

    /**
     * Sorgt dafür, dass sich der Regler, der für die ALutstärke zuständig ist,
     * bewegen lässt.
     */
    public void reglerBewegen(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null){
            if(mouse.getClickCount() == 1){
                if(mouse.getX() <= 807
                &&
                mouse.getY() >= getY() - hoehe/2
                &&
                mouse.getX() >= 508
                &&
                mouse.getY() <= getY() + hoehe/2){
                    setLocation(mouse.getX(), getY());
                }
            }
        } 
    }
    
    /**
     * Eine Referenz auf die Welt.
     */
    public Welt welt(){
        return getWorldOfType(Welt.class);
    }
}
