package ui.settings;

import core.Clickable;
import core.MainClass;

import javax.crypto.Mac;
import java.util.List;

public class CloseButton extends MainClass implements Clickable {


    public CloseButton() {
        setImage("x.png");
        getImage().scale(80, 80);
    }


    public void onClick(){
        List<SettingsPopup> settingsPopups = getWorld().getObjects(SettingsPopup.class);
        if (!settingsPopups.isEmpty()){
            for (SettingsPopup settingsPopup:settingsPopups){
                settingsPopup.onRemove();
            }
        }
    }
    public void act(){
        checkClick();
    }
}