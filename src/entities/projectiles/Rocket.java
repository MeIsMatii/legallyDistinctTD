package entities.projectiles;

import entities.Entity;
import entities.enemy.Enemy;
import entities.tower.Tower;
import ui.common.ImageDisplay;
import util.HasSound;
import util.multiplayer.NetworkManager;

import java.util.List;
/**
 * @author Mathilo
 * @author colin
 * @author Elias
 */
public class Rocket extends Projectile implements HasSound {
    private final long destroyAfter = System.currentTimeMillis() + 150;

    public Rocket(Tower owner) {
        super(owner);
        setImage("rocket.png");
    }


    public void onHit(Entity hitter) {
        if (!(hitter instanceof Enemy) || getWorld() == null) return;
        ImageDisplay explosion = new ImageDisplay("Explosion.png");
        getWorld().addObject(explosion, getX(), getY());

        if (NetworkManager.getInstance().isHost()) {
            List<Enemy> enemies = getObjectsInRange(200, Enemy.class);
            if (!enemies.isEmpty()) {
                for (Enemy enemy : enemies) {
                    enemy.damage(getDamage());
                    if (getWorldOfType(maps.levels.GameMap.class) != null && getWorldOfType(maps.levels.GameMap.class).isMultiplayer()) {
                        String msg = "DAMAGE_ENEMY" + "," + enemy.getUniqueId() + "," + getDamage();
                        NetworkManager.getInstance().sendData(msg);
                    }
                }
            }
        }
        playSound("Explosion.mp3");

        getWorld().removeObject(explosion);
        getWorld().removeObject(this);
    }


    private void handleVisual() {
        playSound("Explosion.mp3");
        ImageDisplay explosion = new ImageDisplay("Explosion.png");
        getWorld().addObject(explosion, getX(), getY());
        if (destroyAfter < System.currentTimeMillis()) {
            getWorld().removeObject(explosion);
        }
    }
}