package map.menu;

import core.MainClass;
import greenfoot.GreenfootImage;
import ui.hud.IPMenuOverlay;
import util.Clickable;

public class MultiplayerPreview extends MainClass implements Clickable {

    /** The IP address the user last confirmed. Empty string if none yet. */
    private String lastIP = "";

    /** Reference to the currently open popup, null when not open. */
    private IPMenuOverlay overlay = null;

    public MultiplayerPreview() {
        GreenfootImage im = new GreenfootImage("Multiplayer.png");
        im.scale(500, 300);
        setImage(im);
    }

    @Override
    public void act() {
        checkClick();

        // Check whether the overlay was closed because the user pressed Connect
        // overlay.getWorld() == null means it has been removed from the world
        if (overlay != null && overlay.getWorld() == null) {
            if (overlay.isConnected()) {
                lastIP = overlay.getIP();
                System.out.println("Connecting to: " + lastIP);
                // TODO: put code for multiplayer here @Colin @Mathilo
            }
            overlay = null;
        }
    }

    @Override
    public void onClick() {
        if (overlay == null) {
            // No popup open yet — open one
            if (getWorld() != null) {
                overlay = new IPMenuOverlay();
                getWorld().addObject(overlay, 960, 540);
            }
        } else {
            // Popup is already open — close it
            overlay.onRemove();
            overlay = null;
        }
    }

    /**
     * Returns the last IP address the user typed and confirmed.
     * Empty string if the user has never pressed Connect.
     */
    public String getLastIP() {
        return lastIP;
    }
}