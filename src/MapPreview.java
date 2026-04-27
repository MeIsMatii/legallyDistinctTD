import greenfoot.Greenfoot;
import greenfoot.World;

public class MapPreview extends MainClass implements Clickable{
    private boolean clicked = false;
    private int world = 0;

    public int getWorldNr() {
        return world;
    }
    public void setWorldNr(int world) {
        this.world = world;
    }
    public boolean isClicked() {
        return clicked;
    }
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
    private World loadingScreen;


    public World getLoadingScreen() {
        return loadingScreen;
    }
    public void setLoadingScreen(World loadingScreen) {
        this.loadingScreen = loadingScreen;
    }


    public MapPreview(int worldNumber, World loadingScreen){
        setWorldNr(worldNumber);
        setLoadingScreen(loadingScreen);
        if (getWorldNr()>9 || getWorldNr()<0){
            System.out.println("Too high or low World number Fix map maker");
            return; ///the error Handling of a true genius --Colin
        }
        switch (getWorldNr()){/// setzt das preview auf die Welt die es sein soll (kann im Konstruktor als Parameter angegeben werden)
            case 1:
                setImage("TempMap1Preview.png");
                break;
            case 2:
                setImage("TempMap2Preview.png");
                break;
            case 3:
                setImage("TempMap3Preview.png");
                break;
            case 4:
                setImage("TempMap4Preview.png");
                break;
            case 5:
                setImage("TempMap5Preview.png");
                break;
            case 6:
                setImage("TempMap6Preview.png");
                break;
            case 7:
                setImage("TempMap7Preview.png");
                break;
            case 8:
                setImage("TempMap8Preview.png");
                break;
            case 9:
                setImage("TempMap9Preview.png");
                break;
        }///Note for Colin: Break in Zukunft nicht mehr vergessen
        getImage().scale(120, 120);
    }

    @Override
    public void act() {
        checkClick();
    }

    @Override
    public void onClick() {
        setClicked(!isClicked());
        switch (getWorldNr()){///  erstellt die welt wenn man drauf drückt(welche kann im Konstruktor als Parameter angegeben werden)
            case 1:
                Greenfoot.setWorld(getLoadingScreen());
                Greenfoot.setWorld(new Map1());
                break;
            case 2:
                Greenfoot.setWorld(new Map2());
                break;
            case 3:
                Greenfoot.setWorld(new Map3());
                break;
            case 4:
                Greenfoot.setWorld(new Map4());
                break;
            case 5:
                Greenfoot.setWorld(new Map5());
                break;
            case 6:
                Greenfoot.setWorld(new Map6());
                break;
            case 7:
                Greenfoot.setWorld(new Map7());
                break;
            case 8:
                Greenfoot.setWorld(new Map8());
                break;
            case 9:
                Greenfoot.setWorld(new Map9());
                break;
        }

    }
}
