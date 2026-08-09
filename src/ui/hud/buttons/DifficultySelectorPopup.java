package ui.hud.buttons;

import maps.levels.GameMap;
import ui.hud.PopupScreen;

public class DifficultySelectorPopup extends PopupScreen {


    public DifficultySelectorPopup(GameMap map) {
    }



    public void addedToWorld(GameMap map){
       DifficultySelector difficultySelectorEasy= new DifficultySelector(map, GameMap.Difficulty.EASY);
       getWorld().addObject(difficultySelectorEasy,getX()+getImage().getWidth()/3,getY());

       DifficultySelector difficultySelectorMedium= new DifficultySelector(map, GameMap.Difficulty.MEDIUM);
       getWorld().addObject(difficultySelectorMedium,getX(),getY());

       DifficultySelector difficultySelectorHard= new DifficultySelector(map, GameMap.Difficulty.HARD);
       getWorld().addObject(difficultySelectorHard,getX()-getImage().getWidth()/3,getY());
    }



    public void onRemove(){
        for (PopupScreen p:getWorld().getObjects(PopupScreen.class) ){
            if (p!=this){
                getWorld().removeObject(p);
            }
        }
    }
}
