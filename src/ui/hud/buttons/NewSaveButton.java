package ui.hud.buttons;

import greenfoot.GreenfootImage;
import maps.levels.*;
import util.saves.SaveManager;

/**
 * @author Colin
 * @author Mathilo
 * @author Julian
 */
public class NewSaveButton extends Button {
    private int worldNr;

    private boolean isMultiplayer = false;


    public NewSaveButton(int worldNr) {
        GreenfootImage img = new GreenfootImage("buttons/NoButton.png");
        img.scale(100, 50);
        setImage(img);
        setWorldNr(worldNr);
    }

    public NewSaveButton(int worldNr, boolean isMultiplayer) {
        this(worldNr);
        this.isMultiplayer = isMultiplayer; //isHost doesnt need to be said, bc clients dont select maps --Mathilo
    }


    public int getWorldNr() {
        return worldNr;
    }

    public void setWorldNr(int worldNr) {
        this.worldNr = worldNr;
    }

    public void onClick() {
        GameMap nextWorld = null;
        switch (getWorldNr()) {///  erstellt die welt wenn man drauf drückt(welche kann im Konstruktor als Parameter angegeben werden)
            case 1: {
                nextWorld = new GameMap1(isMultiplayer, isMultiplayer); //isMultiplayer for both bc its only true when also host
                break;
            }
            case 2: {
                nextWorld = new GameMap2(isMultiplayer, isMultiplayer);
                break;
            }
            case 3: {
                nextWorld = new GameMap3(isMultiplayer, isMultiplayer);
                break;
            }
            case 4: {
                nextWorld = new GameMap4(isMultiplayer, isMultiplayer);
                break;
            }
            case 5: {
                nextWorld = new GameMap5(isMultiplayer, isMultiplayer);
                break;
            }
            case 6: {
                nextWorld = new GameMap6(isMultiplayer, isMultiplayer);
                break;
            }
            case 7: {
                nextWorld = new GameMap7(isMultiplayer, isMultiplayer);
                break;
            }
            case 8: {
                nextWorld = new GameMap8(isMultiplayer, isMultiplayer);
                break;
            }
        }

        SaveManager.getInstance().setLastMap(getWorldNr());
        assert nextWorld != null : "invalid map number";//idea suggested this --Mathilo

        DifficultySelectorPopup difficultySelectorPopup = new DifficultySelectorPopup(nextWorld);
        getWorld().addObject(difficultySelectorPopup, getWorld().getWidth() / 2, getWorld().getHeight() / 2);


        if (owner != null){
            owner.onRemove();
        }

    }
}
