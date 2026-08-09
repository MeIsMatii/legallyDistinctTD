package maps.menu;

import greenfoot.World;
import ui.common.BackButton;
import ui.hud.PopupScreen;
import ui.hud.buttons.PlayOnButton;
import ui.hud.buttons.RetryButton;
import ui.hud.buttons.SettingsButton;
import ui.hud.buttons.WaveResetButton;

/**
 * @author Julian
 */

public class PauseMenu extends PopupScreen {

    private BackButton backButton;
    private SettingsButton settingsButton;
    private PlayOnButton playOnButton;
    private RetryButton retryButton;
    private WaveResetButton waveResetButton;

    public PauseMenu() {
    }


    public void addedToWorld(World w) {
        backButton = new BackButton();
        settingsButton = new SettingsButton();
        playOnButton = new PlayOnButton();
        retryButton = new RetryButton();
        waveResetButton = new WaveResetButton();

        w.addObject(backButton, getX() - getImage().getWidth() / 3, getY());
        w.addObject(settingsButton, getX(), getY());
        w.addObject(playOnButton, getX() + getImage().getWidth() / 3, getY());
        w.addObject(retryButton, getX(), getY() - getImage().getHeight() / 3);
        w.addObject(waveResetButton, getX(), getY() + 200);

    }

    public void onRemove() {
        World w = getWorld();
        w.removeObject(settingsButton);
        w.removeObject(backButton);
        w.removeObject(playOnButton);
        w.removeObject(retryButton);
        w.removeObject(waveResetButton);
        w.removeObject(this);
    }

}

