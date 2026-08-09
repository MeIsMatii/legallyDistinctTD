package ui.hud.buttons;

import greenfoot.World;
import maps.levels.GameMap;
import ui.hud.PopupScreen;

/**
 * @author Julian
 */
public class DifficultySelectorPopup extends PopupScreen {
    private final GameMap map;

    public DifficultySelectorPopup(GameMap map) {
        this.map = map;
    }



    public void addedToWorld(World w){
        for (PopupScreen p:getWorld().getObjects(PopupScreen.class) ){
            if (p!=this){
                getWorld().removeObject(p);
            }
        }
       DifficultySelector difficultySelectorEasy= new DifficultySelector(map, GameMap.Difficulty.EASY);

       DifficultySelector difficultySelectorMedium= new DifficultySelector(map, GameMap.Difficulty.MEDIUM);

       DifficultySelector difficultySelectorHard= new DifficultySelector(map, GameMap.Difficulty.HARD);
        getWorld().addObject(difficultySelectorHard,getX()+getImage().getWidth()/3,getY());
        getWorld().addObject(difficultySelectorMedium,getX(),getY());
       getWorld().addObject(difficultySelectorEasy,getX()-getImage().getWidth()/3,getY());


    }



    public void onRemove(){
        for (PopupScreen p:getWorld().getObjects(PopupScreen.class) ){
            if (p!=this){
                getWorld().removeObject(p);
            }
        }
    }
}
