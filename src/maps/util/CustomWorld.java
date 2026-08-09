package maps.util;

import core.Player;
import entities.Hitbox;
import entities.enemy.Enemy;
import entities.projectiles.Explosion;
import entities.projectiles.Projectile;
import entities.tower.Helicopter;
import entities.tower.Tower;
import entities.tower.util.RangeDisplay;
import greenfoot.World;
import maps.levels.util.MapCoordinatesUtilGuy;
import maps.levels.util.Path;
import maps.menu.PauseMenu;
import ui.common.BackButton;
import ui.common.ImageDisplay;
import ui.common.TutorialHud;
import ui.hud.PopupScreen;
import ui.hud.QuestionPopup;
import ui.hud.Textboard;
import ui.hud.UpgradeDescriptionOverlay;
import ui.hud.buttons.*;
import ui.hud.towerSelector.TowerSelector;
import ui.hud.towerSelector.TowerSelectorSpawner;
import ui.hud.upgrades.UpgradeMenu;
import ui.hud.upgrades.UpgradePath;
import ui.settings.SettingsPopup;
import ui.settings.sound.SongButton;
import ui.settings.sound.SongDropDown;
import ui.settings.sound.VolumeSlider;
import util.Cursor;
import util.multiplayer.popups.IPMenuOverlay;

/**
 * @author mathilo<br><br>
 *
 * To set a unified world size for all worlds.
 */

public class CustomWorld extends World {
    public CustomWorld() {
        super(1920, 1080, 1);
        setupPaintOrder();
    }
    public CustomWorld(int x, int y, int cs) {
        super(x,y,cs);
    }
    /**
     * Sets the rendering paint order for all actors spawned on a GameMap,
     * based on their visual image hierarchy (foreground to background).
     *
     * Listed earlier = painted on top (front).
     * Listed later = painted underneath (back).
     *
     */
    private void setupPaintOrder() {
        setPaintOrder(
            // --- TOP LAYER: Tooltips & Dropdowns ---
            UpgradeDescriptionOverlay.class, // Text tooltip over upgrade options
            SongButton.class,                // Song dropdown items (rendered over settings)
            SongDropDown.class,// Song dropdown box


            // --- UI BUTTONS & CONTROLS (Rendered on top of Popups/Menus) ---
            ClosePopupButton.class,             // "x.png"
            RetryButton.class,
            PlayOnButton.class,
            BackButton.class,                // "BackButton.png"
            SettingsButton.class,
            MuteButton.class,
            WaveResetButton.class,
            SellButton.class,
            StartingButton.class,
            NewSaveButton.class,
            LoadSaveButton.class,
            TutorialHud.class,               // "StartingButton.PNG"
            Button.class,                    // Base class for all buttons
            VolumeSlider.class,             // Volume slider control
            DifficultySelector.class,
            DifficultySelectorPopup.class,


            // --- POPUPS & MODAL OVERLAYS (Rendered over HUD & Game) ---

            QuestionPopup.class,
            IPMenuOverlay.class,
            PopupScreen.class,               // Base class for popups
            SettingsPopup.class,             // Brown settings modal window
            PauseMenu.class,                 // Gray pause menu box (1500x700)

            // --- HUD & PANELS ---
            UpgradePath.class,               // Upgrade buttons inside upgrade menu
            UpgradeMenu.class,               // "upgradeMenu.png" (1620x216 bottom HUD panel)
            TowerSelector.class,             // Individual tower icons in sidebar
            TowerSelectorSpawner.class,      // "upgradesPrototype.png" (right sidebar panel)
            ImageDisplay.class,              // "heart.png", "Coin.png" HUD icons
            Textboard.class,
            Player.class,

            // --- GAME ENTITIES (Towers, FX, Bloons, Projectiles) ---
            Helicopter.class,                // Flying helicopter (above ground towers)
            Explosion.class,                 // "Explosion.png" visual effect
            Hitbox.class,                    // Placement / collision red/green debug outline
            Projectile.class,                // Projectiles ("rocket.png", etc.)
            Tower.class,                     // "towers/<name>/<name>_idle.png"
            RangeDisplay.class,              // Semi-transparent range circle (under tower image)
            Enemy.class,                     // Enemy bloon sprites ("arealEnemy1.png", etc.)

            // --- INVISIBLE / UTILITY / TRACK ACTORS (Bottom Layer) ---
            Cursor.class,                    // "invisible.png" (mouse follower)
            Path.class,                      // "invisible.png" (path waypoint tiles)
            MapCoordinatesUtilGuy.class      // Developer coordinate utility tool
        );
    }
}
