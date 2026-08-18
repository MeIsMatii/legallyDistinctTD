package ui.hud.buttons;

import ui.hud.PopupScreen;
import ui.settings.SettingsPopup;

/**
 * @author Colin
 * @author Mathilo
 * @author Julian
 */

public class SettingsButton extends Button {

    public SettingsButton() {
        setImage("settingsIcon.png");
        getImage().scale(60, 60);
        owner = null;
    }

    public SettingsButton(PopupScreen owner) {
        this();
        this.owner = owner;
    }

    @Override
    public void onClick() {
        getWorld().addObject(new SettingsPopup(), getWorld().getWidth() / 2, getWorld().getHeight() / 2);
        if (owner != null) {
            owner.onRemove();
        }

    }

}
