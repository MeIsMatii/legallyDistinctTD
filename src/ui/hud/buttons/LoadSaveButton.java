package ui.hud.buttons;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;
import maps.levels.*;
/**
 * @author Colin
 * @author Mathilo
 */
public class LoadSaveButton extends Button{
    int worldNr;




    public int getWorldNr() {
        return worldNr;
    }

    public void setWorldNr(int worldNr) {
        this.worldNr = worldNr;
    }


    public LoadSaveButton(int Worldnr) {
        GreenfootImage img = new GreenfootImage("buttons/YesButton.png");
        img.scale(100, 50);
        setImage(img);
        setWorldNr(Worldnr);
    }


    public void onClick() {
        GameMap nextWorld = null;
        switch (getWorldNr()) { /// erstellt die welt wenn man drauf drückt(welche kann im Konstruktor als Parameter angegeben werden) --Colin
            case 1: {
                nextWorld = new GameMap1();
                break;
            }
            case 2: {
                nextWorld = new GameMap2();
                break;
            }
            case 3: {
                nextWorld = new GameMap3();
                break;
            }
            case 4: {
                nextWorld = new GameMap4();
                break;
            }
            case 5: {
                nextWorld = new GameMap5();
                break;
            }
            case 6: {
                nextWorld = new GameMap6();
                break;
            }
            case 7: {
                nextWorld = new GameMap7();
                break;
            }
            case 8: {
                nextWorld = new GameMap8();
                break;
            }
        }
        nextWorld.getGameSaveManager().reload();

        nextWorld.getGameSaveManager().loadGame(nextWorld);


        //todo Make it load game saves and not just enw worlds @Mathilo @Colin
    }
}
