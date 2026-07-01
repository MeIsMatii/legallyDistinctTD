package ui.hud;

import greenfoot.Color;
import greenfoot.Font;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;
import util.Clickable;

/**
 *
 * HOW TO USE:
 *   1. Create an instance:  IPMenuOverlay overlay = new IPMenuOverlay();
 *   2. Add it to the world: world.addObject(overlay, 960, 540);
 *   3. After the user presses Connect (or Enter), the overlay removes itself
 *      and stores the typed IP in ipAddress.
 *   4. Read the result:     String ip = overlay.getIP();
 *      (call this from the parent actor after overlay.getWorld() == null)
 */
public class IPMenuOverlay extends PopupScreen implements Clickable {

    private static final int W = 500;   // panel width
    private static final int H = 640;   // panel height

    // Keypad area: 3 columns × 4 rows
    private static final int KEY_W       = 110;  // width of each key
    private static final int KEY_H       = 72;   // height of each key
    private static final int KEY_LEFT    = 30;   // x of column 0
    private static final int KEY_COL_GAP = 155;  // x distance between column starts (30, 185, 340)
    private static final int KEY_TOP     = 165;  // y of row 0
    private static final int KEY_ROW_GAP = 88;   // y distance between row starts

    // Connect button area
    private static final int CONNECT_X = 30;
    private static final int CONNECT_Y = H - 80;
    private static final int CONNECT_W = W - 60;
    private static final int CONNECT_H = 50;

    // Close "X" button area (top-right of panel)
    private static final int CLOSE_X = W - 42;
    private static final int CLOSE_Y = 8;
    private static final int CLOSE_SIZE = 30;

    // The keypad layout: row 3 col 2 is the backspace key
    private static final String[][] KEYS = {
        {"1", "2", "3"},
        {"4", "5", "6"},
        {"7", "8", "9"},
        {".", "0", "<-"}
    };

    // ── State ──────────────────────────────────────────────────────────────
    private String ipAddress = "";      // what the user has typed so far
    private boolean connected = false;  // true if user pressed Connect/Enter

    // ── Constructor ────────────────────────────────────────────────────────
    public IPMenuOverlay() {
        redraw();
    }

    // ── Act ───────────────────────────────────────────────────────────────
    @Override
    public void act() {
        handleKeyboard();
        checkClick();          // from Clickable — calls onClick() if this actor was clicked
    }

    // ── Clickable: called when the actor itself is clicked ─────────────────
    @Override
    public void onClick() {
        MouseInfo mi = Greenfoot.getMouseInfo();
        if (mi == null) return;

        // Convert world coordinates to local panel coordinates.
        // cellSize is 1, so world pixels = screen pixels.
        // getX()/getY() is the CENTER of the actor.
        int localX = mi.getX() - (getX() - W / 2);
        int localY = mi.getY() - (getY() - H / 2);

        // Close button
        if (localX >= CLOSE_X && localX <= CLOSE_X + CLOSE_SIZE && localY >= CLOSE_Y && localY <= CLOSE_Y + CLOSE_SIZE) {
            onRemove();
            return;
        }

        // Connect button
        if (localX >= CONNECT_X && localX <= CONNECT_X + CONNECT_W
            && localY >= CONNECT_Y && localY <= CONNECT_Y + CONNECT_H) {
            onConnect();
            return;
        }

        // Key buttons
        for (int row = 0; row < KEYS.length; row++) {
            for (int col = 0; col < KEYS[row].length; col++) {
                int kx = KEY_LEFT + col * KEY_COL_GAP;
                int ky = KEY_TOP  + row * KEY_ROW_GAP;
                if (localX >= kx && localX <= kx + KEY_W && localY >= ky && localY <= ky + KEY_H) {
                    String label = KEYS[row][col];
                    if (label.equals("<-")) {
                        typeBackspace();
                    } else {
                        typeChar(label.charAt(0));
                    }
                    return;
                }
            }
        }
    }

    // ── Keyboard input ─────────────────────────────────────────────────────
    private void handleKeyboard() {
        String key = Greenfoot.getKey();
        if (key == null) return;

        switch (key) {
            case "escape":
                onRemove();
                break;
            case "enter":
                onConnect();
                break;
            case "backspace":
                typeBackspace();
                break;
            default:
                if (key.length() == 1 && "0123456789.".indexOf(key.charAt(0)) >= 0) {
                    typeChar(key.charAt(0));
                }
                break;
        }
    }

    // ── Typing helpers ─────────────────────────────────────────────────────
    private void typeChar(char c) {
        ipAddress = ipAddress + c;
        redraw();
    }

    private void typeBackspace() {
        if (ipAddress.length() > 0) {
            ipAddress = ipAddress.substring(0, ipAddress.length() - 1);
        }
        redraw();
    }

    // ── Connect ────────────────────────────────────────────────────────────
    private void onConnect() {
        connected = true;   // flag that the user confirmed (vs just closing)
        onRemove();
    }

    /**
     * Returns the IP the user typed and confirmed with Connect/Enter.
     * Returns an empty string if the popup was closed with Escape / close button,
     * or if the user has not pressed Connect yet.
     */
    public String getIP() {
        return connected ? ipAddress : "";
    }

    /** True if the user pressed Connect or Enter (false if closed without pressing connect). */
    public boolean isConnected() {
        return connected;
    }

    // ── PopupScreen ────────────────────────────────────────────────────────
    @Override
    public void onRemove() {
        if (getWorld() != null) {
            getWorld().removeObject(this);
        }
    }

    // ── Drawing ────────────────────────────────────────────────────────────
    private void redraw() {
        GreenfootImage img = new GreenfootImage(W, H);

        // Panel background
        img.setColor(new Color(35, 30, 25, 240));
        img.fillRect(0, 0, W, H);

        // Brown border (two lines for thickness)
        img.setColor(new Color(139, 69, 19));
        img.drawRect(0, 0, W - 1, H - 1);
        img.drawRect(2, 2, W - 5, H - 5);

        // ── Title ──────────────────────────────────────────────────────────
        img.setColor(Color.WHITE);
        img.setFont(new Font("Arial", true, false, 22));
        img.drawString("Enter Server IP", 150, 45);

        // ── Text field ─────────────────────────────────────────────────────
        img.setColor(new Color(15, 15, 15));
        img.fillRect(30, 60, W - 60, 60);
        img.setColor(new Color(200, 200, 200));
        img.drawRect(30, 60, W - 61, 59);

        img.setFont(new Font("Monospaced", false, false, 26));
        if (ipAddress.isEmpty()) {
            img.setColor(new Color(110, 110, 110));
            img.drawString("0.0.0.0", 45, 100);
        } else {
            img.setColor(Color.WHITE);
            img.drawString(ipAddress, 45, 100);
        }

        // ── Key buttons ────────────────────────────────────────────────────
        for (int row = 0; row < KEYS.length; row++) {
            for (int col = 0; col < KEYS[row].length; col++) {
                String label = KEYS[row][col];
                int kx = KEY_LEFT + col * KEY_COL_GAP;
                int ky = KEY_TOP  + row * KEY_ROW_GAP;

                // Key background
                Color bg = label.equals("<-") ? new Color(140, 60, 60) : new Color(55, 55, 60);
                img.setColor(bg);
                img.fillRect(kx, ky, KEY_W, KEY_H);
                img.setColor(new Color(90, 90, 95));
                img.drawRect(kx, ky, KEY_W - 1, KEY_H - 1);

                // Key label
                img.setColor(Color.WHITE);
                img.setFont(new Font("Arial", true, false, 28));
                // Center the text inside the key
                int textX = kx + KEY_W / 2 - (label.length() > 1 ? 20 : 8);
                int textY = ky + KEY_H / 2 + 12;
                img.drawString(label, textX, textY);
            }
        }

        // ── Connect button ─────────────────────────────────────────────────
        img.setColor(new Color(40, 130, 50));
        img.fillRect(CONNECT_X, CONNECT_Y, CONNECT_W, CONNECT_H);
        img.setColor(new Color(70, 170, 80));
        img.drawRect(CONNECT_X, CONNECT_Y, CONNECT_W - 1, CONNECT_H - 1);
        img.setColor(Color.WHITE);
        img.setFont(new Font("Arial", true, false, 24));
        img.drawString("Connect", W / 2 - 42, CONNECT_Y + 33);

        // ── Close "X" button ───────────────────────────────────────────────
        img.setColor(new Color(150, 30, 30));
        img.fillRect(CLOSE_X, CLOSE_Y, CLOSE_SIZE, CLOSE_SIZE);
        img.setColor(Color.WHITE);
        img.setFont(new Font("Arial", true, false, 20));
        img.drawString("X", CLOSE_X + 8, CLOSE_Y + 22);

        setImage(img);
    }
}