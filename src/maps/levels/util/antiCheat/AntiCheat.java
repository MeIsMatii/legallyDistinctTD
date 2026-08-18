package maps.levels.util.antiCheat;

import core.MainClass;
import core.Player;
import maps.levels.GameMap;

import java.util.List;
import java.util.Locale;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author colin
 *
 * Simple anticheat against memory altering
 */
public class AntiCheat extends MainClass {

    // Singleton Instanz
    private static AntiCheat instance;

    // HONEYPOT
    public static int playerMoney = 1000;
    public static int playerHealth = 100;

    //Honeypot values
    private static final int INITIAL_HONEYPOT_MONEY = 1000;
    private static final int INITIAL_HONEYPOT_HEALTH = 100;

    // every 20 seconds (20.000mils)
    private static final long CHECK_INTERVAL_MS = 20000;
    private long lastCheckTime = System.currentTimeMillis();

    // Blacklist of cheating programms
    private static final List<String> BLACKLISTED_PROGRAMS = Arrays.asList(
        "cheatengine",
        "artmoney",
        "wemod",
        "cheat-engine",
        "speedhack",
        "gameguardian",
        "x64dbg",
        "x32dbg",
        "cheathappens"
    );

    // private constructor bc of singleton
    private AntiCheat() {}

    // Pass the active world into update
    public static void update(GameMap world) {
        if (instance == null) {
            instance = new AntiCheat();
        }
        instance.tick(world);
    }

    private void tick(GameMap world) {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastCheckTime >= CHECK_INTERVAL_MS) {
            try {
                checkHoneypot();
                checkRunningProcesses();
                checkValueCeiling(world);
            } catch (Exception e) {
                e.printStackTrace();
            }
            lastCheckTime = currentTime;
        }
    }

    /**
     * checks for pretty much impossible values
     */
    private void checkValueCeiling(GameMap world) {
        if (world != null) {
            Player player = world.getPlayer();
            if (player != null) {
                int coins = player.getCoins();
                int health = player.getHealth();

                if (coins > 1000 || coins < 0 || health > 100 || health < 0) {
                    System.out.println("Anti-Cheat Trigger: Invalid Coins/Health values.");
                    System.exit(1);
                }
            }
        }
    }

    /**
     * cheks for changes in honeypot values
     *
     */
    private void checkHoneypot() {
        if (playerMoney != INITIAL_HONEYPOT_MONEY || playerHealth != INITIAL_HONEYPOT_HEALTH) {
            System.out.println("Security Trigger: Stop changing values in your memory!");
            System.exit(1);
        }
    }

    /**
     * checks blacklist
     */
    private void checkRunningProcesses() {
        boolean detected = false;

        // Alle laufenden Prozesse durchgehen
        // using an Iterator bc .toList() only work on java 16 and up
        //Important Note Used google and some forums to research how this is possible(I modified or wrote all of the code I found there is no copy paste)
        Iterator<ProcessHandle> iterator = ProcessHandle.allProcesses().iterator();
        while (iterator.hasNext()) {
            ProcessHandle process = iterator.next();
            ProcessHandle.Info info = process.info();

            String cmd = info.command().orElse("").toLowerCase(Locale.ROOT);
            String cmdLine = info.commandLine().orElse("").toLowerCase(Locale.ROOT);

            // checks for blacklist
            for (String badProgram : BLACKLISTED_PROGRAMS) {
                if (cmd.contains(badProgram) || cmdLine.contains(badProgram)) {
                    detected = true;
                    break;
                }
            }

            if (detected) {
                break;
            }
        }

        if (detected) {
            System.out.println("Security Trigger: Blacklisted program detected.");
            System.exit(1);
        }
    }
}